package common.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.util.LOG;

/**
 * <b>Description:</b> Common APIs Of jQuery.
 * 
 * @author s00330949
 * @since 2016-6-6
 */
public class CommonJQ {

	private static final int TIMES = 5;

	/**
	 * <b>Description:</b> Check the current matched set of elements against a
	 * selector, element, or jQuery object and return true if at least one of
	 * these elements is existed.
	 * 
	 * @param driver
	 * @param selector
	 * @return true:Existed; false:not Existed.
	 */
	public static boolean isExisted(WebDriver driver, String selector) {
		try {
			driver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.MILLISECONDS);
			return CommonJQ.length(driver, selector) > 0;
		} catch (Exception e) {
			LOG.info_aw("Can't find elements:"+selector);
//			System.out.println(ZIP.NowTime() + "Can't find elements:"
//					+ selector);
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	}

	/**
	 * 
	 * @param driver
	 * @param selector
	 * @param isVisible
	 * @return
	 */
	public static boolean isExisted(WebDriver driver, String selector,
			boolean isVisible) {
		try {
			driver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.MILLISECONDS);
			return CommonJQ.length(driver, selector, isVisible) > 0;
		} catch (Exception e) {
			 LOG.info_aw("Can't find elements:"+selector);
//			System.out.println(ZIP.NowTime() + "Can't find elements:"
//					+ selector);
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 * @return
	 * @return boolean
	 */
	public static boolean isExisted(WebDriver driver, String selector,
			String descendantSelector) {
		try {
			driver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.MILLISECONDS);
			return CommonJQ.length(driver, selector, descendantSelector) > 0;
		} catch (Exception e) {
			LOG.info_aw("Can't find elements:"+selector+ ",descendantSelector:" + descendantSelector);
//			System.out.println(ZIP.NowTime() + "Can't find elements:"
//					+ selector + ",descendantSelector:" + descendantSelector);
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 * @return
	 * @return boolean
	 */
	public static boolean isExisted(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible) {
		try {
			driver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.MILLISECONDS);
			return CommonJQ.length(driver, selector, descendantSelector,
					isVisible) > 0;
		} catch (Exception e) {
			 LOG.info_aw("Can't find elements:"+selector+ ",descendantSelector:" + descendantSelector);
//			System.out.println(ZIP.NowTime() + "Can't find elements:"
//					+ selector + ",descendantSelector:" + descendantSelector);
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public static boolean isExisted_siblings(WebDriver driver, String selector,
			String siblingsSelector) {
		String script = "return $('" + StringUtils.replace(selector, "'", "\"")
				+ "')";
		if (StringUtils.isNotBlank(siblingsSelector)) {
			script += ".siblings('"
					+ StringUtils.replace(siblingsSelector, "'", "\"") + "')";
		}
		script += ".filter(':visible').length;";

		String length = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
		int b = Integer.parseInt(length);
		try {
			driver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.MILLISECONDS);
			if (b > 0) {
				 LOG.info_aw("Can find elements:"+script+ "|length:" + b);
//				System.out.println(ZIP.NowTime() + "Can find elements:"
//						+ script + "|length:" + b);
			}
			return b > 0;

		} catch (Exception e) {
			LOG.info_aw("Can't find elements:" + script);
//			System.out.println(ZIP.NowTime() + "Can't find elements:" + script);
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 * @param index
	 * @return
	 * @return boolean
	 */
	public static boolean isExisted(WebDriver driver, String selector,
			String descendantSelector, int index) {
		String script = "return $('" + StringUtils.replace(selector, "'", "\"")
				+ "')";
		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find('"
					+ StringUtils.replace(descendantSelector, "'", "\"") + "')";
		}
		if (index >= 0) {
			script += ".eq(" + String.valueOf(index) + ")";
		}
		script += ".length;";
		final long b = (long) ((JavascriptExecutor) driver)
				.executeScript(script);
		try {
			driver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.MILLISECONDS);
			return b > 0;

		} catch (Exception e) {
			 LOG.info_aw("Can't find elements:"+script);
//			System.out.println(ZIP.NowTime() + "Can't find elements:" + script);
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * <b>Description:</b> The number of elements in the jQuery object.
	 * 
	 * @param driver
	 * @param selector
	 * @return The number of elements in the jQuery object.The .size() method is
	 *         functionally equivalent to the .length property; however, the
	 *         .length property is preferred because it does not have the
	 *         overhead of a function call.
	 */
	public static int length(WebDriver driver, String selector) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').length;";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		String length = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
		LOG.info_aw("script:" + script + "|length:"+ length);
//		System.out.println(ZIP.NowTime() + "script:" + script + "|length:"
//				+ length);
		return Integer.parseInt(length);
	}

	/**
	 * 
	 * @param driver
	 * @param selector
	 * @param isVisible
	 * @return
	 */
	public static int length(WebDriver driver, String selector,
			boolean isVisible) {
		String script = "return $('" + StringUtils.replace(selector, "'", "\"")
				+ "').length;";
		if (isVisible) {
			script = "return $('" + StringUtils.replace(selector, "'", "\"")
					+ "').filter(':visible').length;";
		}
		 LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		String length = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
//		System.out.println(ZIP.NowTime() + "script:" + script + "|length:"
//				+ length);
		 LOG.info_aw("script:" + script + "|length:"+ length);
		return Integer.parseInt(length);
	}

	/**
	 * <b>Description:</b> The number of elements in the jQuery object.
	 * 
	 * @param driver
	 * @param selector
	 * @return The number of elements in the jQuery object.The .size() method is
	 *         functionally equivalent to the .length property; however, the
	 *         .length property is preferred because it does not have the
	 *         overhead of a function call.
	 */
	public static int length(WebDriver driver, String selector,
			String descendantSelector) {
		String script = "return $('" + selector + "')";

		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find('"
					+ StringUtils.replace(descendantSelector, "'", "\"") + "')";
		}

		script += ".length;";
		LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		String length = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
		LOG.info_aw("script:" + script + "|length:"
				+ length);
//		System.out.println(ZIP.NowTime() + "script:" + script + "|length:"
//				+ length);
		return Integer.parseInt(length);
	}

	public static int length(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible) {
		String script = "return $('" + selector + "')";

		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find('"
					+ StringUtils.replace(descendantSelector, "'", "\"") + "')";
		}

		if (isVisible) {
			script += ".filter(':visible').length;";
		} else {
			script += ".length;";
		}

		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		String length = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
//		System.out.println(ZIP.NowTime() + "script:" + script + "|length:"
//				+ length);
		LOG.info_aw("script:" + script + "|length:"
				+ length);
		return Integer.parseInt(length);
	}

	/**
	 * <b>Description:</b> Check the current matched set of elements against a
	 * selector, element, or jQuery object and return true if at least one of
	 * these elements is visible.
	 * 
	 * @param driver
	 * @param selector
	 * @return true:visible; false:invisible.
	 */
	public static boolean isVisible(WebDriver driver, String selector) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"")
				+ "').is(':visible');";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(script);
		return b.booleanValue();
	}

	/**
	 * <b>@Description:</b>Check the current matched set of elements against a
	 * selector, element, or jQuery object and return true if at least one of
	 * these elements is visible.
	 * 
	 * @author kwx363801
	 * @since 2016-7-18
	 * @param driver
	 * @param selector
	 * @param index
	 * @return
	 * @return boolean
	 */
	public static boolean isVisible(WebDriver driver, String selector, int index) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').eq(" + index
				+ ").is(':visible');";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(script);
		return b.booleanValue();
	}

	/**
	 * <b>Description:</b> Check the current matched set of elements against a
	 * selector, element, or jQuery object and return true if at least one of
	 * these elements is enabled.
	 * 
	 * @param driver
	 * @param selector
	 * @return true:enabled; false:disable.
	 */
	public static boolean isEnabled(WebDriver driver, String selector) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"")
				+ "').is(':enabled');";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(script);
		return b.booleanValue();
	}

	/**
	 * <b>Description:</b> Check the current matched set of elements against a
	 * selector, element, or jQuery object and return true if at least one of
	 * these elements is checked or selected.
	 * 
	 * @param driver
	 * @param selector
	 * @return true:is checked or selected ;false: not.
	 */
	public static boolean isChecked(WebDriver driver, String selector) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"")
				+ "').is(':checked');";
		LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(script);
		return b.booleanValue();
	}

	/**
	 * <b>Description:</b> trigger "click" event on an element.
	 * 
	 * @param driver
	 * @param selector
	 * @param isVisible
	 *            true: click visible elements only; false:click all elements
	 */
	public static void click(WebDriver driver, String selector,
			boolean isVisible) {
		click(driver, selector, isVisible, "");
	}

	public static void click(WebDriver driver, String selector,
			boolean isVisible, String Message) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		if (isVisible) {
			script += ".filter(':visible')";
		}
		boolean flage = CommonJQ.isExisted(driver, selector, isVisible);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, isVisible);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+selector);
//				System.out.println(ZIP.NowTime() + "Can't find elements:"
//						+ selector);
			}
		}
		Assert.assertTrue(Message + "，找不到该界面元素:" + selector, flage);

		String get_script = script + ".get(0).click();";
		String eq_script = script + ".eq(0).click();";

		try {
			 LOG.info_aw("get_script:"+get_script);
//			System.out.println(ZIP.NowTime() + "get_script:" + get_script);
			((JavascriptExecutor) driver).executeScript(get_script);
		} catch (Exception e) {
			try {
				 LOG.info_aw("eq_script:"+eq_script);
//				System.out.println(ZIP.NowTime() + "eq_script:" + eq_script);
				((JavascriptExecutor) driver).executeScript(eq_script);
			} catch (Exception e1) {
				Assert.fail(Message + "，点击事件操作失败:" + eq_script);
			}
		}
	}
	/**
	 * 
	 * @param driver
	 * @param selector:在某个元素下查找
	 * @param text:按元素text值定位元素-精确匹配
	 * @param isVisible:元素是否可见
	 * @param Message:异常提示信息
	 */
	public static void click_ByText(WebDriver driver, String selector,String text,
			boolean isVisible, String Message) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		script = script+".filter(function(){return $(this).text().trim()=='"+ text+ "'})";
		if (isVisible) {
			script += ".filter(':visible')";
		}
		CommonJQ.click(driver, script, Message);
	}
	
	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 * @return
	 * @return boolean
	 */
	public static boolean isExisted_ByText(WebDriver driver, String selector,
			String text,
			boolean isVisible) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		script = script+".filter(function(){return $(this).text().trim()=='"+ text+ "'})";
		if (isVisible) {
			script += ".filter(':visible')";
		}
		LOG.info_aw("return " + script + ".length;");
//		System.out.println("return " + script + ".length;");
		boolean flage = ((long) ((JavascriptExecutor) driver)
				.executeScript("return " + script + ".length;")) > 0;
		return flage;
	}
	/**
	 * 
	 * @param driver
	 * @param selector:在某个元素下查找
	 * @param text:按元素text值定位元素-模糊匹配
	 * @param isVisible:元素是否可见
	 * @param Message:异常提示信息
	 */
	public static void click_containsByText(WebDriver driver, String selector,String text,
			boolean isVisible, String Message) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		script = script+".filter(function(){return $(this).text().trim().contains(\""+text+"\")})";
		if (isVisible) {
			script += ".filter(':visible')";
		}
		CommonJQ.click(driver, script, Message);
	}
	/**
	 * <b>Description:</b> trigger "click" event on an element.
	 * 
	 * @param driver
	 * @param selector
	 * @param isVisible
	 *            true: click visible elements only; false:click all elements
	 * @param index
	 *            Zero-based index of the element to match.
	 */
	public static void click(WebDriver driver, String selector,
			boolean isVisible, int index) {
		click(driver, selector, isVisible, index, "");
	}

	public static void click(WebDriver driver, String selector,
			boolean isVisible, int index, String Message) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		String get_script = "";
		String eq_script = "";
		if (isVisible) {
			script += ".filter(':visible')";
		}
		if (index >= 0) {
			get_script = script + ".get(" + String.valueOf(index) + ")";
		}
		if (index >= 0) {
			eq_script = script + ".eq(" + String.valueOf(index) + ")";
		}
		boolean flage = CommonJQ.isExisted(driver, selector, isVisible);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, isVisible);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+selector);
//				System.out.println(ZIP.NowTime() + "Can't find elements:"
//						+ selector);
			}
		}
		Assert.assertTrue(Message + "，找不到该界面元素:" + selector, flage);
		script += ".click();";
		if (index >= 0) {
			get_script += ".click();";
		}
		if (index >= 0) {
			eq_script += ".click();";
		}

		try {
			if ("".equals(get_script)) {
				 LOG.info_aw("script:" + script);
//				System.out.println(ZIP.NowTime() + "script:" + script);
				((JavascriptExecutor) driver).executeScript(script);
			} else {
				LOG.info_aw("script:" + get_script);
//				System.out.println(ZIP.NowTime() + "script:" + get_script);
				((JavascriptExecutor) driver).executeScript(get_script);
			}

		} catch (Exception e) {
			if ("".equals(get_script)) {
				Assert.fail(Message + "，点击事件操作失败:" + script);
			} else {
				try {
					 LOG.info_aw("script:"+eq_script);
//					System.out.println(ZIP.NowTime() + "script:" + eq_script);
					((JavascriptExecutor) driver).executeScript(eq_script);
				} catch (Exception e1) {
					Assert.fail(Message + "，点击事件操作失败:" + eq_script);
				}
			}
		}
	}

	/**
	 * <b>@Description:</b>trigger "click" HTML event
	 * 
	 * @author kwx363801
	 * @since 2016-7-1
	 * @param driver
	 * @param selector
	 * @param isVisible
	 * @param index
	 * @return void
	 */
	public static void htmlClick(WebDriver driver, String selector,
			boolean isVisible, int index) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		if (isVisible) {
			script += ".filter(':visible')";
		}
		if (index >= 0) {
			script += ".get(" + String.valueOf(index) + ")";
		}
		script += ".click();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		((JavascriptExecutor) driver).executeScript(script);
	}

	/**
	 * <b>@Description:</b>选取兄弟元素并点击
	 * 
	 * @author kwx363801
	 * @since 2016-7-16
	 * @param driver
	 * @param selector
	 * @param siblingsSelector
	 * @return void
	 */
	public static void siblingsClick(WebDriver driver, String selector,
			String siblingsSelector) {
		String script = "$(\"" + StringUtils.replace(selector, "\"", "'")
				+ "\")";
		script += ".siblings(\""
				+ StringUtils.replace(siblingsSelector, "\"", "'")
				+ "\").click();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		((JavascriptExecutor) driver).executeScript(script);

	}

	/**
	 * <b>@Description:</b>trigger "click" HTML event
	 * 
	 * @author kwx363801
	 * @since 2016-7-1
	 * @param driver
	 * @param selector
	 * @param isVisible
	 * @param index
	 * @return void
	 */
	public static void htmlClick(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible, int index) {
		String script = "$('" + selector + "')";

		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find('" + descendantSelector + "')";
		}
		if (isVisible) {
			script += ".filter(':visible')";
		}
		if (index >= 0) {
			script += ".get(" + String.valueOf(index) + ")";
		}
		script += ".click();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		((JavascriptExecutor) driver).executeScript(script);

	}

	/**
	 * <b>Description:</b> trigger "click" event on an element.
	 * 
	 * @param driver
	 * @param selector
	 *            selector with descendants
	 * @param descendantSelector
	 *            descendant will be found.
	 * @param isVisible
	 *            true: click visible elements only; false:click all elements.
	 */
	public static void click(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible) {
		click(driver, selector, descendantSelector, isVisible, "");
	}

	public static void click(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible, String Message) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";

		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find('"
					+ StringUtils.replace(descendantSelector, "'", "\"") + "')";
		}
		if (isVisible) {
			script += ".filter(':visible')";
		}
		boolean flage = CommonJQ.isExisted(driver, selector,
				descendantSelector, isVisible);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, descendantSelector,
					isVisible);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+script);
//				System.out.println(ZIP.NowTime() + "Can't find elements:"
//						+ script);
			}
		}
		Assert.assertTrue(Message + "，找不到该界面元素:" + selector, flage);
		script += ".click();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		try {
			((JavascriptExecutor) driver).executeScript(script);
		} catch (Exception e) {
			Assert.fail(Message + "，点击事件操作失败:" + script);
		}
	}

	/**
	 * <b>Description:</b> trigger "click" event on an element.
	 * 
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 *            descendant will be found.
	 * @param isVisible
	 *            true: click visible elements only; false:click all elements.
	 * @param index
	 *            Zero-based index of the element to match.
	 */
	public static void click(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible, int index) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		String get_script = "";
		String eq_script = "";
		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find('"
					+ StringUtils.replace(descendantSelector, "'", "\"") + "')";
		}
		if (isVisible) {
			script += ".filter(':visible')";
		}
		if (index >= 0) {
			get_script = script + ".get(" + String.valueOf(index) + ")";
		}
		if (index >= 0) {
			eq_script = script + ".eq(" + String.valueOf(index) + ")";
		}
		boolean flage = CommonJQ.isExisted(driver, selector,
				descendantSelector, index);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, descendantSelector,
					index);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+selector);
//				System.out.println(ZIP.NowTime() + "Can't find elements:"
//						+ selector);
			}
		}

		Assert.assertTrue("Can't find elements:" + selector, flage);
		script += ".click();";
		if (index >= 0) {
			get_script += ".click();";
		}
		if (index >= 0) {
			eq_script += ".click();";
		}

		try {
			if ("".equals(get_script)) {
				 LOG.info_aw("script:"+script);
//				System.out.println(ZIP.NowTime() + "script:" + script);
				((JavascriptExecutor) driver).executeScript(script);
			} else {
				 LOG.info_aw("script:"+get_script);
//				System.out.println(ZIP.NowTime() + "script:" + get_script);
				((JavascriptExecutor) driver).executeScript(get_script);
			}

		} catch (Exception e) {
			if ("".equals(get_script)) {
				Assert.fail("operation failed,script:" + script);
			} else {
				try {
					 LOG.info_aw("script:"+eq_script);
//					System.out.println(ZIP.NowTime() + "script:" + eq_script);
					((JavascriptExecutor) driver).executeScript(eq_script);
				} catch (Exception e1) {
					Assert.fail("operation failed,script:" + eq_script);
				}
			}
		}

	}

	/**
	 * <b>Description:</b> check is empty or not of the combined text contents
	 * of each element in the set of matched elements, including their
	 * descendants.
	 * 
	 * @param driver
	 * @param selector
	 * @return true:is empty.false:not empty.
	 */
	public static boolean isEmpty(WebDriver driver, String selector) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').text()";
		final String text = (String) ((JavascriptExecutor) driver)
				.executeScript(script);
		return (StringUtils.isBlank(text) || StringUtils.equalsIgnoreCase(text,
				LanguageUtil.translate("No data")));
	}

	/**
	 * <b>Description:</b> Set the value of each element in the set of matched
	 * elements.The .text() method cannot be used on input elements. For input
	 * field text, use the .val() method.
	 * 
	 * @param driver
	 * @param selector
	 * @param text
	 */
	public static void value(WebDriver driver, String selector, String text) {
		value(driver, selector, text,false, "");
	}
	public static void value(WebDriver driver, String selector,
			boolean isVisible, int index, String text, String Message) {
		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		String get_script = "";
		String eq_script = "";
		String trigger = "";
		String eq_trigger = "";
		String get_trigger = "";
		if (isVisible) {
			script += ".filter(':visible')";

		}
		if (index >= 0) {
			get_script = script + ".get(" + String.valueOf(index) + ")";
			get_script = get_script + ".trigger('change')";
		}
		if (index >= 0) {
			eq_script = script + ".eq(" + String.valueOf(index) + ")";
			eq_trigger = eq_script + ".trigger('change')";
		}
		trigger = script + ".trigger('change')";

		boolean flage = CommonJQ.isExisted(driver, selector, isVisible);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, isVisible);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+selector);
//				System.out.println(ZIP.NowTime() + "Can't find elements:"
//						+ selector);
			}
		}
		Assert.assertTrue(Message + "，找不到该界面元素:" + selector, flage);
		script += ".val('" + text + "');";
		if (index >= 0) {
			get_script += ".val('" + text + "');";
		}
		if (index >= 0) {
			eq_script += ".val('" + text + "');";
		}

		try {
			if ("".equals(get_script)) {
				System.out.println(ZIP.NowTime() + "script:" + script);
				((JavascriptExecutor) driver).executeScript(script);
				((JavascriptExecutor) driver).executeScript(trigger);
			} else {
				System.out.println(ZIP.NowTime() + "script:" + get_script);
				((JavascriptExecutor) driver).executeScript(get_script);
				((JavascriptExecutor) driver).executeScript(get_trigger);
			}

		} catch (Exception e) {
			if ("".equals(get_script)) {
				Assert.fail(Message + "，赋值事件操作失败:" + script);
			} else {
				try {
					 LOG.info_aw("script:"+eq_script);
//					System.out.println(ZIP.NowTime() + "script:" + eq_script);
					((JavascriptExecutor) driver).executeScript(eq_script);
				} catch (Exception e1) {
					Assert.fail(Message + "，赋值事件操作失败:" + eq_script);
				}
				try {
					((JavascriptExecutor) driver).executeScript(eq_trigger);
				} catch (Exception e1) {
					Assert.fail(Message + "，更改value属性事件操作失败:" + eq_trigger);
				}
			}
		}
	}

	public static String getValue(WebDriver driver, String selector) {
		return getValue( driver,  selector,"");
	}
	public static String getValue(WebDriver driver, String selector,String Message) {
		boolean flage = CommonJQ.isExisted(driver, selector);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector);
			if (flage) {
				break;
			}
		}
		Assert.assertTrue(Message+"Can't find elements:" + selector, flage);
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').val();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();
	}
	public static String getValue(WebDriver driver, String selector,boolean isVisible,String Message) {
		boolean flage = CommonJQ.isExisted(driver, selector,isVisible);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector,isVisible);
			if (flage) {
				break;
			}
		}
		Assert.assertTrue(Message+"Can't find elements:" + selector, flage);
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').val();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();
	}
	public static String getValue(WebDriver driver, String selector, int index) {
		return getValue( driver,  selector,  index,"");
	}
	public static String getValue(WebDriver driver, String selector, int index,String Message) {
		boolean flage = CommonJQ.isExisted(driver, selector);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector);
			if (flage) {
				break;
			}
		}
		Assert.assertTrue(Message+"Can't find elements:" + selector, flage);
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').eq(" + index
				+ ").val();";
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();
	}
	/**
	 * 
	 * @param driver
	 * @param selector
	 * @param text
	 */
	public static void value(WebDriver driver, String selector, String text,
			boolean isVisible) {
		value(driver, selector, text, isVisible, "");
	}
	public static void value(WebDriver driver, String selector, String text, String Message) {
		value(driver, selector, text, false,  Message);
	}

	public static void value(WebDriver driver, String selector, String text,
			boolean isVisible, String Message) {
		boolean flage = CommonJQ.isExisted(driver, selector, isVisible);

		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, isVisible);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+selector);
//				System.out.println(ZIP.NowTime() + "Can't find elements:"
//						+ selector);
			}
		}
		Assert.assertTrue(Message + "，找不到该界面元素:" + selector, flage);
		String script = "$('" + StringUtils.replace(selector, "'", "\"")
				+ "').val('" + text + "');";
		if (isVisible) {
			script = "$('" + StringUtils.replace(selector, "'", "\"")
					+ "').filter(':visible').val('" + text + "');";
		}
		 LOG.info_aw("script:"+script);
//		System.out.println(ZIP.NowTime() + "script:" + script);
		try {
			((JavascriptExecutor) driver).executeScript(script);
		} catch (Exception e) {
			Assert.fail(Message + "，赋值事件操作失败:" + script);
		}
		final String trigger = "$('" + StringUtils.replace(selector, "'", "\"")
				+ "').trigger('change')";
		try {
			((JavascriptExecutor) driver).executeScript(trigger);
		} catch (Exception e) {
			Assert.fail(Message + "，更改value属性事件操作失败:" + trigger);
		}
	}

	/**
	 * <b>Description:</b> Remove all child nodes of the set of matched elements
	 * from the DOM.This method removes not only child (and other descendant)
	 * elements, but also any text within the set of matched elements. This is
	 * because, according to the DOM specification, any string of text within an
	 * element is considered a child node of that element.
	 * 
	 * @param driver
	 * @param selector
	 */
	public static void empty(WebDriver driver, String selector) {
		final String script = "$('" + StringUtils.replace(selector, "'", "\"")
				+ "').empty();";
		 LOG.info_aw("script:"+script);
		((JavascriptExecutor) driver).executeScript(script);
	}

	/**
	 * <b>Description:</b> set the option of select.
	 * 
	 * @param driver
	 * @param selector
	 *            selector of select.
	 * @param text
	 *            text of option.
	 */
	public static void setSelectOption(WebDriver driver, String selector,
			String text) {
		final String formattedSelector = StringUtils.replace(selector, "\"",
				"'");
		final String script = "$(\""
				+ formattedSelector
				+ "\").find('option').filter(function(){return $(this).text()=='"
				+ LanguageUtil.translate(text) + "'}).attr('selected',true);";
		final String trigger = "$(\"" + formattedSelector
				+ "\").trigger('change');";
		LOG.info_aw("script:"+script+ trigger);
		((JavascriptExecutor) driver).executeScript(script + trigger);
	}

	/**
	 * <b>Description:</b> Get the combined text contents of each element in the
	 * set of matched elements, including their descendants.
	 * 
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 *            selector of child node
	 * @return the combined text contents of each element in the set of matched
	 *         elements, including their descendants
	 */
	public static String text(WebDriver driver, String selector,
			String descendantSelector) {
		boolean flage = CommonJQ
				.isExisted(driver, selector, descendantSelector);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, descendantSelector);
			if (flage) {
				break;
			} else {
				LOG.info_aw("Can't find elements:" + selector);
			}
		}
		Assert.assertTrue("Can't find elements:" + selector, flage);
		final String formattedSelector = StringUtils.replace(selector, "\"",
				"'");
		final String formattedChildSelector = StringUtils.replace(
				descendantSelector, "\"", "'");

		final JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "return $(\"" + formattedSelector + "\")";
		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find(\"" + formattedChildSelector + "\")";
		}
		script += ".text();";
		 LOG.info_aw("script:"+script);
		return (String) js.executeScript(script);
	}

	public static String text(WebDriver driver, String selector,
			String descendantSelector, boolean isVisible) {
		boolean flage = CommonJQ
				.isExisted(driver, selector, descendantSelector);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted(driver, selector, descendantSelector);
			if (flage) {
				break;
			} else {
				 LOG.info_aw("Can't find elements:"+selector);
			}

		}
		Assert.assertTrue("Can't find elements:" + selector, flage);
		final String formattedSelector = StringUtils.replace(selector, "\"",
				"'");
		final String formattedChildSelector = StringUtils.replace(
				descendantSelector, "\"", "'");

		final JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "return $(\"" + formattedSelector + "\")";
		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find(\"" + formattedChildSelector + "\")";
		}
		if (isVisible) {
			script = script + ".filter(':visible')";
		}
		script += ".text();";
		 LOG.info_aw("script:"+script);
		return (String) js.executeScript(script);
	}

	public static String text(WebDriver driver, String selector) {
		final JavascriptExecutor js = (JavascriptExecutor) driver;
		if (selector.startsWith("$")) {
			return (String) js.executeScript(selector + ".text()");
		} else {
			boolean flage = CommonJQ.isExisted(driver, selector);
			for (int i = 0; (i < TIMES) && (flage == false); i++) {
				Pause.pause(1000);
				flage = CommonJQ.isExisted(driver, selector);
				if (flage) {
					break;
				} else {
					 LOG.info_aw("Can't find elements:"+selector);
				}

			}
			Assert.assertTrue("Can't find elements:" + selector, flage);
			final String formattedSelector = StringUtils.replace(selector,
					"\"", "'");

			String script = "return $(\"" + formattedSelector + "\")";
			script += ".filter(':visible').text();";
			 LOG.info_aw("script:"+script);
			return (String) js.executeScript(script);
		}

	}

	/**
	 * <b>Description:</b>Get the text by index
	 * 
	 * @author lwx242612
	 * @since 2016年7月5日
	 * @param driver
	 * @param selector
	 * @param descendantSelector
	 * @param index
	 * @return
	 * @return String
	 */

	public static String text(WebDriver driver, String selector,
			String descendantSelector, int index) {
		final String formattedSelector = StringUtils.replace(selector, "\"",
				"'");
		final String formattedChildSelector = StringUtils.replace(
				descendantSelector, "\"", "'");

		final JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "return $(\"" + formattedSelector + "\")";
		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find(\"" + formattedChildSelector + "\")";
		}
		if (index >= 0) {
			script += ".eq(" + String.valueOf(index) + ")";
		}
		script += ".text();";
		 LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		return (String) js.executeScript(script);
	}

	/**
	 * <b>@Description:</b>选取兄弟元素并点击
	 * 
	 * @author kwx363801
	 * @since 2016-7-16
	 * @param driver
	 * @param selector
	 * @param siblingsSelector
	 * @return void
	 */
	public static String text_siblings(WebDriver driver, String selector,
			String siblingsSelector) {
		boolean flage = CommonJQ.isExisted_siblings(driver, selector,
				siblingsSelector);
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = CommonJQ.isExisted_siblings(driver, selector,
					siblingsSelector);
			if (flage) {
				// LOG.info_aw("Can find elements:"+siblingsSelector);
				break;
			} else {
				// LOG.info_aw("Can't find elements:"+siblingsSelector);
				System.out.println(ZIP.NowTime() + "Can't find elements:"
						+ siblingsSelector);
			}

		}
		Assert.assertTrue("Can't find elements:" + siblingsSelector, flage);

		String script = "$('" + StringUtils.replace(selector, "'", "\"") + "')";
		script += ".siblings('"
				+ StringUtils.replace(siblingsSelector, "'", "\"")
				+ "').filter(':visible').text();";
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();

	}

	/**
	 * <b>Description:</b>获取指定元素属性值
	 * 
	 * @author lwx242612
	 * @since 2016年7月5日
	 * @param driver
	 * @param selector
	 * @param attr
	 * @param index
	 * @return
	 * @return String
	 */
	public static String getAttrValue(WebDriver driver, String selector,
			String attr, int index) {

		String script = "return $(\""
				+ StringUtils.replace(selector, "\"", "'") + "\")";
		if (index >= 0) {
			script += ".eq(" + String.valueOf(index) + ")";
		}
		script += ".attr(\"" + attr + "\")";
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();
	}

	/**
	 * <b>Description:</b>获取指定元素属性值
	 * 
	 * @author lwx242612
	 * @since 2016年7月5日
	 * @param driver
	 * @param selector
	 * @param attr
	 * @param index
	 * @return
	 * @return String
	 */
	public static String getAttrValue(WebDriver driver, String selector,
			String descendantSelector, String attr, int index) {

		String script = "return $(\""
				+ StringUtils.replace(selector, "\"", "'") + "\")";
		if (StringUtils.isNotBlank(descendantSelector)) {
			script += ".find(\"" + descendantSelector + "\")";
		}
		if (index >= 0) {
			script += ".eq(" + String.valueOf(index) + ")";
		}
		script += ".attr(\"" + attr + "\")";
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();
	}

	/**
	 * <b>Description:</b>等待指定页面元素加载完成
	 * 
	 * @author lwx242612
	 * @since 2016年7月5日
	 * @param driver
	 * @param selector
	 * @throws Exception
	 * @return void
	 */
	public static void waitForElement(WebDriver driver, String selector)
			throws Exception {
		final int timeout = 60 * 1000;
		final int steps = 10;
		int times = 0;
		while (!CommonJQ.isExisted(driver, selector)) {
			Thread.sleep(steps);
			times++;
			Assert.assertTrue("Timeout when waitFor " + selector
					+ " ! Timeout-Value(ms):" + String.valueOf(timeout),
					times < timeout / steps);
		}

	}

	public static void waitForElementVisble(WebDriver driver, String selector) {
		final int timeout = 60 * 1000;
		final int steps = 100;
		int times = 0;
		while (!CommonJQ.isVisible(driver, selector)) {
			Pause.pause(steps);
			times++;
			Assert.assertTrue("Timeout when waitFor " + selector
					+ " ! Timeout-Value(ms):" + String.valueOf(timeout),
					times < timeout / steps);
		}

	}

	/**
	 * <b>Description:</b>返回是否有无指定class属性
	 * 
	 * @author kwx363801
	 * @since 2016-6-28
	 * @param driver
	 * @param selector
	 * @param className
	 * @return
	 * @return boolean
	 */

	public static boolean hasClass(WebDriver driver, String selector,
			String className) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').hasClass('"
				+ className + "');";
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(script);
		return b.booleanValue();
	}

	/**
	 * <b>Description:</b>返回是否有无指定class属性
	 * 
	 * @author kwx363801
	 * @since 2016-6-28
	 * @param driver
	 * @param selector
	 * @param className
	 * @param index
	 * @return
	 * @return boolean
	 */
	public static boolean hasClass(WebDriver driver, String selector,
			String className, int index) {
		final String script = "return $('"
				+ StringUtils.replace(selector, "'", "\"") + "').eq(" + index
				+ ").hasClass('" + className + "');";
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(script);
		return b.booleanValue();
	}
	public static boolean hasClass_Custom(WebDriver driver, String script,
			String className) {
		boolean flage = ((long) ((JavascriptExecutor) driver)
				.executeScript("return " + script + ".length;")) > 0;
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = ((long) ((JavascriptExecutor) driver)
					.executeScript("return " + script + ".length;")) > 0;
			if (flage) {
				break;
			} else {
				System.out.println(ZIP.NowTime() + "Can't find elements:"
						+ script);
			}
		}
		Assert.assertTrue("Can't find elements:" + script, flage);
		
		final String HasClassScript = ("return " + script + ".hasClass('" + className + "');");
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "HasClassScript:" + HasClassScript);
		final Boolean b = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(HasClassScript);
		return b.booleanValue();
	}
	/**
	 * <b>Description:</b> trigger "click" event on an element.
	 * 
	 * @param driver
	 * @param selector
	 */
	public static void click(WebDriver driver, String script) {
		click( driver,  script,"");
	}
	public static void click(WebDriver driver, String script,String Message) {
		boolean flage = ((long) ((JavascriptExecutor) driver)
				.executeScript("return " + script + ".length;")) > 0;
		for (int i = 0; (i < TIMES) && (flage == false); i++) {
			Pause.pause(1000);
			flage = ((long) ((JavascriptExecutor) driver)
					.executeScript("return " + script + ".length;")) > 0;
			if (flage) {
				break;
			} else {
				System.out.println(ZIP.NowTime() + "Can't find elements:"
						+ script);
			}
		}
		Assert.assertTrue(Message+"Can't find elements:" + script, flage);
		String get_script = script + ".get(0).click();";
		String eq_script = script + ".eq(0).click();";
		
		try {
			LOG.info_aw("script:"+get_script);
			System.out.println(ZIP.NowTime() + "get_script:" + get_script);
			((JavascriptExecutor) driver).executeScript(get_script);
		} catch (Exception e) {
			try {
				LOG.info_aw("script:"+eq_script);
				System.out.println(ZIP.NowTime() + "eq_script:" + eq_script);
				((JavascriptExecutor) driver).executeScript(eq_script);
			} catch (Exception e1) {
				Assert.fail(Message + "，点击事件操作失败:" + eq_script);
			}
		}
	}
	/**
	 * <b>Description:</b> trigger "click" event on an element.
	 * 
	 * @param driver
	 * @param selector
	 */
	public static void setTimeout(WebDriver driver, String script, int times) {
		script = "setTimeout(function(){" + script + "}," + times + ")";
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		((JavascriptExecutor) driver).executeScript(script);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param code
	 * @return void
	 */
	public static void clickKey(int code) {
		try {
			Robot robot = new Robot();
			robot.keyPress(code);
			Pause.pause(500);
			robot.keyRelease(code);
			Pause.pause(500);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author zwx320041
	 * @param code
	 */
	public static void excuteJS(WebDriver driver, String script) {
		// LOG.info_aw("script:"+script);
		System.out.println(ZIP.NowTime() + "script:"+script);
		((JavascriptExecutor) driver).executeScript(script);
	}

	public static int excuteJStoInt(WebDriver driver, String script) {
		System.out.println(ZIP.NowTime() + "script:" + script);
		if(!script.contains("return")){
			script = "return "+script;
		}
		String result = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
		// LOG.info_aw("script:"+result);
		System.out.println(ZIP.NowTime() + "script:" + result);
		return Integer.parseInt(result);
	}

	public static long excuteJStoLong(WebDriver driver, String script) {
		if(!script.contains("return")){
			script = "return "+script;
		}
		String result = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
		// LOG.info_aw("script:"+result);
		System.out.println(ZIP.NowTime() + "script:" + result);
		return Long.parseLong(result);
	}

	public static String excuteJStoString(WebDriver driver, String script) {
		// LOG.info_aw("script:"+script);
		if(!script.contains("return")){
			script = "return "+script;
		}
		System.out.println(ZIP.NowTime() + "script:" + script);
		return ((JavascriptExecutor) driver).executeScript(script).toString();
	}

	public static boolean excuteJStoBoolean(WebDriver driver, String script) {
		if(!script.startsWith("return")){
			script = "return "+script;
		}
		String result = ((JavascriptExecutor) driver).executeScript(script)
				.toString();
		// LOG.info_aw("script:"+result);
		System.out.println(ZIP.NowTime() + "script:" + result);
		return Boolean.parseBoolean(result);

	}

	/***
	 * 断言页面控件中包含该文本信息
	 * 
	 * @param driver
	 * 
	 * @param message
	 * @param textToBeVerified
	 */
	public static void assertTextPresent(WebDriver driver,
			String textToBeVerified) {
		for(int i = 0;i<3;i++){
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
				if (driver.findElement(By.xpath("//*[contains(.,'"
						+ textToBeVerified + "')]")).isDisplayed()) {
					LOG.info("[" + textToBeVerified + "]assertTextPresent: Success");
					return;
				}
			} catch (Exception e) {
				Pause.pause(1000);
			}	
		}
		Assert.fail("[" + textToBeVerified + "] The Page don't exist \"");
	}
	
	/**
	 * 
	* @Description: 按照元素ID 导入文件
	* @param driver
	* @param selectorid
	* @param path 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void ImportFile(WebDriver driver,String selectorid ,String path){
		try {			
			WebElement element = driver.findElement(By.id(selectorid));
			element.sendKeys(path); 
		} catch (Exception e) {
			LOG.info("页面没有找到ID为"+selectorid+"的元素");
		}
		
	}
	/**
	 * 
	* @Description: 按照名称点击超链接文本
	* @param driver
	* @param clickText 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void clickLinkTextByName(WebDriver driver,String selector,String linkText)
	{
		LOG.info_aw("点击-"+linkText);
		if (existLinkText(driver, linkText)) {
			String script = "$('"+selector+"').find('a:contains("+linkText+"):visible').not(':has(a)').get(0).click();";
			CommonJQ.excuteJS(driver, script);
		}else{
			Assert.fail("界面无内容为：【"+linkText+"】的链接");
		}
		
	}
	
	public static void clickLinkTextByName(WebDriver driver,String linkText)
	{
		LOG.info_aw("点击-"+linkText);
		if (existLinkText(driver, linkText)) {
			String script = "$('a:contains("+linkText+"):visible').not(':has(a)').get(0).click();";
			CommonJQ.excuteJS(driver, script);
		}else{
			Assert.fail("界面无内容为：【"+linkText+"】的链接");
		}
		
	}
	
	public static boolean existLinkText(WebDriver driver,String selector,String linkText)
	{
		String script = "return $('"+selector+"').('a:contains("+linkText+"):visible').not(':has(a)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}
	
	public static boolean existLinkText(WebDriver driver,String linkText)
	{
		String script = "return $('a:contains("+linkText+"):visible').not(':has(a)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}
	/**
	 * 
	* @Description: 按照表格第一列名称 点击表格复选框
	* @param driver
	* @param textName 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void clickTableBoxByName(WebDriver driver,String textName){
		
		if (existTableBoxByName(driver, textName)) {
			String script = "$('a:contains("+ textName+ "):visible').not(':has(a)').parents('tr').find('.x-grid-row-checker').get(0).click();";
			CommonJQ.excuteJS(driver, script);
		}else{
			Assert.fail("选择复选框，界面数据没有有一行内容包含："+textName);
		}
	}
	
	public static boolean existTableBoxByName(WebDriver driver,String textName)
	{
		String script = "return $('a:contains("+textName+"):visible').not(':has(a)').parents('tr').find('.x-grid-row-checker').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}
	/**
	 * 
	* @Description: 按照文本内容点击 适合大多数文本按钮的点击
	* @param driver
	* @param clickText 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void clickTextByName(WebDriver driver,String clickText){
		LOG.info_aw("点击-"+clickText);
		if (existTextByName(driver, clickText)) {
			String script = "$('span:contains("+clickText+"):visible').not(':has(span)').get(0).click();";
			CommonJQ.excuteJS(driver, script);
		} else {
			Assert.fail("界面找不到内容包含："+clickText+"的可点击按钮");
		}
	}
	
	public static boolean existTextByName(WebDriver driver,String clickText){
		String script = "$('span:contains("+clickText+"):visible').not(':has(span)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;		
	}
	
	/**
	 * 
	* @Description: 导航树按照文本内容点击
	* @param driver
	* @param name 
	* @return void
	* @author zwx320041
	* @date 2017-1-11
	 */
	public static void clickNavigationByName(WebDriver driver,String name){
		LOG.info_aw("点击导航列-"+name);
		if (existNavigationByName(driver, name)) {
			String script = "$('div:contains("+name+"):visible').not(':has(div)').not(':has(a)').get(0).click();";
			CommonJQ.excuteJS(driver, script);
		} else {
			Assert.fail("界面找不到内容包含："+name+"的可点击项");
		}
		
	}
	
	public static boolean existNavigationByName(WebDriver driver,String clickText){
		String script = "return $('div:contains("+clickText+"):visible').not(':has(div)').not(':has(a)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;		
	}
	
	public static void clickNavigationByName(WebDriver driver,String selector,String name){
		LOG.info_aw("点击导航列-"+name);
		if (existNavigationByName(driver, name)) {
			String script = "$('"+selector+"').find('div:contains("+name+"):visible').not(':has(div)').not(':has(a)').get(0).click();";
			CommonJQ.excuteJS(driver, script);
		} else {
			Assert.fail("界面找不到内容包含："+name+"的可点击项");
		}
		
	}
	
	public static boolean existNavigationByName(WebDriver driver,String selector,String clickText){
		String script = "return $('"+selector+"').find('div:contains("+clickText+"):visible').not(':has(div)').not(':has(a)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;		
	}
	/**
	 * 
	* @Description: 按照下拉框中展示类容选择对应选项
	* @param driver
	* @param selector
	* @param name
	* @param message 
	* @return void
	* @author zwx320041
	* @date 2017-2-16
	 */
	public static void selectOption(WebDriver driver,String selector,String name,String message){
		String script = "$('"+selector+"').prev().find('span').get(0).click()";
		CommonJQ.excuteJS(driver, script);
		CommonJQ.waitForElementVisble(driver, selector);
		Pause.pause(300);
		String script1 = "$('"+selector+"').find('li:visible').filter(function(){return $(this).text().trim()=='"+name.trim()+"'})";
		String script2 = script1+".length>0;";
		String script3 = script1+".click()";
		System.out.println(script2);
		if (CommonJQ.excuteJStoBoolean(driver, script2)) {
			CommonJQ.excuteJS(driver, script3);
			Pause.pause(300);
		}else{
			Assert.fail(message+"-下拉框没有对应选择项"+name);
		}
	}
	
}
