package common.map;

import java.util.ArrayList;
import java.util.List;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.map.convert.MapUtil;
import common.map.exception.MapException;
import common.map.model.Corner;
import common.map.model.Location;
import common.map.model.Point;
import common.map.mouse.Mouse;

public class MapOperator {
	private static final int REPEAT_COUNT = 100;
	public static final float MIN_SCALE = 0.05f;
	public static final float MAX_SCALE = 100f;

	public static Logger Log = LoggerFactory.getLogger(MapOperator.class);

	public Map map;
	public Mouse mouse;

	public MapOperator(WebDriver webDriver, String mapId) throws MapException {
		map = new Map(webDriver, mapId);
		if (map.isInitSuccess()) {
			mouse = new Mouse(map.getTopLeftPoint(), map.getSize());
			if (mouse.isInitSuccess()) {
				mouse.moveToCenter();
			} else {
				throw new MapException("Mouse initialization falied.");
			}
		} else {
			throw new MapException("Map initialization falied.");
		}
	}

	public void click(WebDriver driver,double lon, double lat) {
		click(driver,new Location(lon, lat));
	}

	public boolean click(WebDriver driver,Location loc) {
		Point target = convert(driver,loc);
		return clickPoint(target);
	}

	public boolean clickPoint(Point point) {
		if (MapUtil.isValid(point)) {
			System.out.println("mouse: X: "+point.getX()+"   Y: "+point.getY());

			mouse.clickPoint(point.getX(), point.getY());
			return true;
		}
		return false;
	}

	public Point convert(WebDriver driver,Location location) {
		return MapUtil.toPoint(location,
				getCornerLocation(driver,Corner.TOPLEFT),
				getCornerLocation(driver,Corner.BOTTOMRIGHT), map.getTopLeftPoint(),
				map.getSize());
	}

	public Point convert(Location loc, Location topLeft, Location bottomRight) {
		return MapUtil.toPoint(loc, topLeft, bottomRight,
				map.getTopLeftPoint(), map.getSize());
	}

	public void doubleClick(WebDriver driver,double lon, double lat) {
		doubleClick( driver,new Location(lon, lat));
	}

	public void doubleClick(WebDriver driver,Location loc) {
		Point target = convert(driver,loc);
		if (MapUtil.isValid(target)) {
			mouse.doubleClickPoint(target.getX(), target.getY());
		}
	}

	private boolean doubleClickPoint(Point point) {
		if (MapUtil.isValid(point)) {
			System.out.println("double click: X:  "+point.getX()+"  Y: "+point.getY());
			mouse.doubleClickPoint(point.getX(), point.getY());
			return true;
		}
		return false;
	}
	
	private boolean dragTo(WebDriver driver,Location loc) {
		boolean result = false;
		Location center = getCenterLocation(driver,REPEAT_COUNT);
		if (null != center && center.equals(loc)) {
			return result;
		}

		dragToLongitude(driver,loc);

		dragToLatitude(driver,loc);

		Point target = convert(driver,loc);
		System.out.println(target.getX()+ "      "+target.getY());
		if (MapUtil.isValid(target)) {
			mouse.dragToCenter(target.getX(), target.getY());
			result = true;
		} else {
			result = false;
		}

		return result;
	}
	public void dragTo(WebDriver driver,double lon,double lat,float beforeScale, float afterScale){
		Location loc=new Location(lon, lat);
		dragTo(driver, loc, beforeScale, afterScale);
		
	}
	private void dragTo(WebDriver driver,Location loc, float beforeScale, float afterScale) {
		if (Double.compare(beforeScale, 0) >= 0) {
			scrollTo(driver,beforeScale);
		}

		dragTo(driver,loc);

		if (Double.compare(beforeScale, afterScale) != 0
				&& Double.compare(afterScale, 0) >= 0) {
			scrollTo(driver,afterScale);
			dragTo(driver,loc);
		}
	}

	public void dragTo(WebDriver driver,double lon,double lat, int scale) {
		Location loc=new Location(lon, lat);
		dragTo(driver, loc, scale);
	}
	
	private void dragTo(WebDriver driver,Location loc, int scale) {
//		if(isInMap(driver, loc))
//		{
			
			dragToLocation(driver,loc);
			
//		}
//		else
//		{
//			while(!isInMap(driver, loc))
//			{
//				map.scrollDown(driver);
//			}
//			dragToLocation(driver,loc);
//		}
		if (scale >= 0) {
			scrollTo(driver,scale);
		}
	}

	private boolean dragToLatitude(WebDriver driver,Location loc) {
		double longitude = loc.getLongitude();
		double latitude = loc.getLatitude();
		int cnt = 32;
		Location bottomRight = getCornerLocation(driver,Corner.BOTTOMRIGHT);
		while (Double.compare(longitude, bottomRight.getLongitude()) > 0
				&& (cnt-- > 0)) {
			mouse.dragToLeft();
			bottomRight = getCornerLocation(driver,Corner.BOTTOMRIGHT);
		}

		while (Double.compare(latitude, bottomRight.getLatitude()) < 0
				&& (cnt-- > 0)) {
			mouse.dragToTop();
			bottomRight = getCornerLocation(driver,Corner.BOTTOMRIGHT);
		}
		return cnt >= 0;
	}

	public void dragToLocation(WebDriver driver,Location location) {
		Point point = convert(driver,location);
		System.out.println(point);
		if (MapUtil.isValid(point)) {
			mouse.dragToCenter(point.getX(), point.getY());
		}
	}

	public boolean dragToLongitude(WebDriver driver,Location loc) {
		double longitude = loc.getLongitude();
		double latitude = loc.getLatitude();
		Location topLeft = getCornerLocation(driver,Corner.TOPLEFT);
		int cnt = 32;
		while (Double.compare(longitude, topLeft.getLongitude()) < 0
				&& (cnt-- > 0)) {
			mouse.dragToRight();
			topLeft = getCornerLocation(driver,Corner.TOPLEFT);
		}
		while (Double.compare(latitude, topLeft.getLatitude()) > 0
				&& (cnt-- > 0)) {
			mouse.dragToBottom();
			topLeft = getCornerLocation(driver,Corner.TOPLEFT);
		}
		return cnt >= 0;
	}

	public Location getCenterLocation(WebDriver driver,int count) {
		mouse.moveToCenter();
		Location center = map.getCursorLocation(driver);
		if (null == center && count > 0) {
			mouse.moveToBottomLeft();
			center = getCenterLocation(driver,count - 1);
		}
		return center;
	}

	private Location getCornerLocation(WebDriver driver,Corner corner) {
		Location pos = null;
		for (int i = 0; i < REPEAT_COUNT; i++) {
			switch (corner) {
			case TOPLEFT:
				mouse.moveToTopLeft();
				break;
			case TOPRIGHT:
				mouse.moveToTopRight();
				break;
			case BOTTOMRIGHT:
				mouse.moveToBottomRight();
				break;
			case BOTTOMLEFT:
				mouse.moveToBottomLeft();
				break;
			default:
				break;
			}
			pos = map.getCursorLocation(driver);
			if (null != pos) {
				break;
			}
			mouse.moveToCenter();
		}
		Log.info("Mouse Loc: " + pos);
		return pos;
	}

	public boolean isInMap(WebDriver driver,Location loc) {
		Point pos = MapUtil.toPoint(loc,
				getCornerLocation(driver,Corner.TOPLEFT),
				getCornerLocation(driver,Corner.BOTTOMRIGHT), map.getTopLeftPoint(),
				map.getSize());
		return MapUtil.isValid(pos);
	}

	public boolean isOutOfMap(WebDriver driver,List<Location> locs) {
		boolean result = false;
		Location topLeft = getCornerLocation(driver,Corner.TOPLEFT);
		Location bottomRight = getCornerLocation(driver,Corner.BOTTOMRIGHT);
		for (Location loc : locs) {
			Point pos = MapUtil.toPoint(loc, topLeft, bottomRight,
					map.getTopLeftPoint(), map.getSize());
			if (!MapUtil.isValid(pos)) {
				result = true;
				break;
			}
		}
		return result;
	}

	private float normalizeScale(float input, float min, float max) {
		if (Float.compare(min, max) > 0) {
			return normalizeScale(input, max, min);
		}
		if (Float.compare(input, min) < 0) {
			return min;
		}
		if (Float.compare(input, max) > 0) {
			return max;
		}
		return input;
	}

	private int normalizeScale(int input, int min, int max) {
		if (min > max) {
			return normalizeScale(input, max, min);
		}
		if (input < min) {
			return min;
		}
		if (input > max) {
			return max;
		}
		return input;
	}

	private void scrollInCenter(WebDriver driver,boolean extend) {
		if (extend) {
			map.scrollUp(driver);
		} else {
			map.scrollDown(driver);
		}
	}


	private void scrollTo(WebDriver driver,float target) {
		float scl = this.normalizeScale(target, MIN_SCALE, MAX_SCALE);
		float scale = map.getScale(driver);
		if (Float.compare(scale, 0) < 0) {
			return;
		}
		double diff = scl - scale;
		int count = 24;
		while (Double.compare(Math.abs(diff), 0.3) > 0 && (count--) > 0) {
			mouse.scrollInCenter(diff < 0 ? 1 : -1);
			scale = map.getScale(driver);
			if (Float.compare(scale, 0) < 0) {
				break;
			}
			diff = scl - scale;
		}
	}

	public void scrollTo(WebDriver driver,int target) {
		int scale = this.normalizeScale(target, 0, 17);
		int scl = map.getScaleLevel(driver);
		while (scl != scale ) {
			this.scrollInCenter(driver,scl < scale);
			scl = map.getScaleLevel(driver);
		}
			
	}
	
	public void drawNetworkAnalysisFormOfLocation(WebDriver driver,double lon, double lat) {
		Location loc = new Location(lon, lat);
		dragTo(driver,loc,14);
		Map.chooseBrush(driver);
        drawOfAct(convert(driver, loc));

	}
	
	public void drawNetworkAnalysisFormOfPoint(WebDriver driver,int x, int y) {
		Point point =new Point(x,y);
		Map.chooseBrush(driver);
        drawOfAct(point);

	}
	
	private  void drawOfAct(Point point)
	{
		Pause.pause(1000);
		point.setX(point.getX()-REPEAT_COUNT);
		point.setY(point.getY()-REPEAT_COUNT);
		clickPoint(point);	
		Pause.pause(1000);
		point.setX(point.getX());
		point.setY(point.getY()+2*REPEAT_COUNT);
		clickPoint(point);	
		Pause.pause(1000);
		point.setX(point.getX()+2*REPEAT_COUNT);
		point.setY(point.getY());
		clickPoint(point);
		Pause.pause(1000);
		point.setX(point.getX());
		point.setY(point.getY()-2*REPEAT_COUNT);
		doubleClickPoint(point);
		
				
	}
	
	public void drawForm(WebDriver driver,double[]... points) {
		Map.chooseBrush(driver);
		freeDraw(driver, points);
	}
	
	private boolean freeDraw(WebDriver driver,double[]... points) {
		boolean result = false;
		if (null == points) {
			return result;
		}
		List<Location> locs = covertToLocations(points);
		clickGisPoints(driver, locs);
		return result;
		
	}
	
	private List<Location> covertToLocations(double[]... points) {
		List<Location> locs = new ArrayList<Location>(points.length);

		for (double[] point : points) {
			if (point == null || point.length != 2) {
				continue;
			}
			locs.add(new Location(point[0], point[1]));
		}
		return locs;
	}
	
	public void clickGisPoints(WebDriver driver,List<Location> locs) {
		for (int i = 0; i < locs.size(); i++) {
			map.delay(1000);
			while(!isInMap(driver, locs.get(i)))
			{
				map.scrollDown(driver);
			}
			map.delay(1000);
			if(i==locs.size()-1)
			{
				doubleClick(driver, locs.get(i));
				map.delay(1000);
			}
			else
			{
				click(driver, locs.get(i));
			}
		}
	}
	
}
