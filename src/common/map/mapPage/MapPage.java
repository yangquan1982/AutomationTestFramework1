package common.map.mapPage;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;



public class MapPage {
	public static final String Largen="button.ol-zoom-out";
	public static final String Micrify = "button.ol-zoom-in";
	private static final String Rank= ".zoom-board.ol-unselectable.ol-control";
	private static final String scale=".ol-scale-line-inner";
	private static final String MousePosition=".ol-mouse-position";
	public static final String Drowpolygon = ".fa.fa-Drowpolygon";
	
	public static String getCursorLocation(WebDriver driver) {
//		final String script = "return $(\"" +MousePosition + "\").text()";
//		final String data = (String) ((JavascriptExecutor) driver).executeScript(script);
//		return data;
		return CommonJQ.text(driver, MousePosition, null);
	}
	
	public static String getScale(WebDriver driver) {
//		final String script = "return $(\"" +scale + "\").text()";
//		final String data = (String) ((JavascriptExecutor) driver).executeScript(script);
//		return data;
		return CommonJQ.text(driver, scale, null);
	}
	
	public static String getRank(WebDriver driver) {
//		final String script = "return $(\""+Rank + "\").text()";
//		final String data = (String) ((JavascriptExecutor) driver).executeScript(script);
//		return data;
		return CommonJQ.text(driver, Rank, null);
	}
}
