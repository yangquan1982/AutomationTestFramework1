package common.map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.map.mapPage.MapPage;
import common.map.model.Location;
import common.map.model.Point;
import common.map.model.Size;
import common.util.CommonJQ;

public class Map {
	private static final int DELAY = 100;

	private int horizontalMargin = 0;
	private int verticalMargin = 0;
	private WebElement map;
	private final Size size = new Size();
	private final Point topLeft = new Point();
	private boolean success = false;

	public Map (WebDriver driver,String mapId) {
		success = false;
		if (initMap(driver,mapId)) {
			initMargin(map.getSize());
			initTopLeft(map.getLocation().getX(), map.getLocation().getY());
			initSize(map.getSize());
			success = true;
		}
	}


	protected void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// Thread.sleep() failed.
		}
	}

	/**
	 * gets the geographical position of mouse cursor, and returns null for
	 * failure.
	 * 
	 * @return
	 */
	public Location getCursorLocation(WebDriver driver) {
		Location result = null;
		delay(DELAY);
		String loc = MapPage.getCursorLocation(driver);
		System.out.println("loc-----  "+loc);

		if (!isNone(loc)) {
			result = parseLocation(loc);
		}
		return result;
	}

	public static void chooseBrush(WebDriver driver) {
		CommonJQ.click(driver,MapPage.Drowpolygon, true);
	}

	private Matcher getMatcher(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str);
	}

	/**
	 * gets the scale of map, and returns negative value for failure.
	 * 
	 * @return map scale of kilometer
	 */
	public float getScale(WebDriver driver) {
		delay(DELAY);
		String scl = MapPage.getScale(driver);
		if (!isNone(scl)) {
			Matcher matcher = getMatcher("\\d+", scl);
			if (scl.contains("km") && matcher.find()) {
				return Integer.parseInt(matcher.group());
			} else if (scl.contains("m") && matcher.find()) {
				return Integer.parseInt(matcher.group()) / 1000.0f;
			}
		}
		return -1.0f;
	}

	public int getScaleLevel(WebDriver driver) {
		delay(DELAY);
		String scl = MapPage.getRank(driver);
		
		System.out.println("scl:  "+scl);



		if (!isNone(scl)) {
			Matcher matcher = getMatcher("\\d+", scl);
			if (matcher.find()) {
				return Integer.parseInt(matcher.group());
			}
		}
		return -1;
	}

	public Size getSize() {
		return size;
	}

	public Point getTopLeftPoint() {
		return topLeft;
	}


	private boolean initMap(WebDriver driver,String mapId) {
		map = driver.findElement(By.id(mapId));
		if (map == null) {
			return false;
		}
		return true;
	}

	private void initMargin(Dimension dimension) {
		horizontalMargin = dimension.getWidth() / 5;
		verticalMargin = dimension.getHeight() / 5;
	}

	private void initSize(Dimension dimension) {
		size.setWidth(dimension.getWidth() - 2 * horizontalMargin);
		size.setHeight(dimension.getHeight() - 2 * verticalMargin);
	}

	private void initTopLeft(int locX, int locY) {
		topLeft.setX(locX + horizontalMargin);
		topLeft.setY(locY + verticalMargin);
	}

	public boolean isInitSuccess() {
		return success;
	}

	private boolean isNone(String str) {
		return str == null || str.isEmpty();
	}

	private Location parseLocation(String loc) {
		Matcher matcher = getMatcher("(-?\\d+)(\\.\\d+)?", loc);
		double[] lonlat = new double[2];
		for (int i = 0; i < 2; i++) {
			if (matcher.find()) {
				lonlat[i] = Double.parseDouble(matcher.group());
			} else {
				return null;
			}
		}
		if (loc.startsWith("lat")) {
			return new Location(lonlat[1], lonlat[0]);
		}
		return new Location(lonlat[0], lonlat[1]);
	}

	public void scrollDown(WebDriver driver) {
		CommonJQ.click(driver, MapPage.Largen, true);
	}

	public void scrollUp(WebDriver driver) {
		CommonJQ.click(driver, MapPage.Micrify, true);
	}

	public void setMargin(int horizontal, int vertical) {
		success = false;
		if (horizontal > 0 && horizontal < size.getWidth()) {
			horizontalMargin = horizontal;
		}
		if (vertical > 0 && vertical < size.getHeight()) {
			verticalMargin = vertical;
		}
		if (map != null) {
			initTopLeft(map.getLocation().getX(), map.getLocation().getY());
			initSize(map.getSize());
			success = true;

		}
	}
}
