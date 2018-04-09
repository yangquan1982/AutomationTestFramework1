package cloud_plugin.page.network_performance_analysis_center.capcacity_management;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;

public class CapcacityManagementPage {

	private final static String CREATETASK_ID = "#createTask";
	private final static String TASKNAME_ID = "#taskName";

	private final static String NEXTPAGE_BTN_CLASS = "span[class='btnNew greenNew ng-binding']";
	private final static String OK_BTN_CLASS = ".messager-button  .l-btn-left";

	private final static String CELL_FILTERRULE_CSS = "input[name=\"taskBean.cell_filterRule\"]";
	private final static String ENODEB_FILTERRULE_CSS = "input[name=\"taskBean.eNodeB_filterRule\"]";
	private final static String CELLDILATATIONTEXT_ID = "#cellDilatationText";
	private final static String ENODEBILATATIONTEXT_ID = "#enodebDilatationText";

	private final static String INPUT_NAME_017_CSS = "input[name=\"017\"]";
	private final static String INPUT_NAME_018_CSS = "input[name=\"018\"]";
	private final static String INPUT_NAME_01I_CSS = "input[name=\"01I\"]";
	private final static String INPUT_NAME_01J_CSS = "input[name=\"01J\"]";

	private final static String ZZ_SELECT_MSEL_SPAN = ".zz_select.msel span";

	private final static String TASKBEAN_CELL_X = "input[name=\"taskBean.cell_x\"]";
	private final static String TASKBEAN_CELL_N = "input[name=\"taskBean.cell_n\"]";
	private final static String TASKBEAN_CELL_Z = "input[name=\"taskBean.cell_z\"]";

	private final static String TASKBEAN_ENODEB_X = "input[name=\"taskBean.eNodeB_x\"]";
	private final static String TASKBEAN_ENODEB_N = "input[name=\"taskBean.eNodeB_n\"]";
	private final static String TASKBEAN_ENODEB_Z = "input[name=\"taskBean.eNodeB_z\"]";

	private final static String TASKNAME_CSS = "span[class=\"taskAddInfoSpan ng-binding\"]:contains(\"任务名称\")~";
	private final static String PARAMSETTING_CLASS = ".taskAddProgressDotDiv";

	private final static String FAILURE_ID = "#FAILURE";

	public final static String SELECT_ENODEBCONFIGFILECONF_ID = "#select_eNodeBConfigFileconf";
	public final static String SELECT_LTEPROJECTPARAMTERLTEMML_ID = "#select_LteprojectparamterLtemml";
	public final static String SELECT_LTEPROJECTPARAMTERPRSPFM_ID = "#select_Lteprojectparamterprspfm";
	public final static String TEMPLATEFILEPRS_ID = "#templateFilePRS";
	public final static String SELECT_LTEPROJECTPARAMTERU2000TR_ID = "#select_LteprojectparamterU2000Tr";
	public final static String TEMPLATEFILEU2000_ID = "#templateFileU2000";
	public final static String TEMPLATEFILEOBJECT_ID = "#templateFileObject";

	public final static String FILECHOICEERRORMSG = "span[class='fileChoiceErrorMsg ng-binding']";

	/**
	 * <b>Description:</b>点击确认按钮
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void clickOKButton(WebDriver driver) {
		CommonJQ.click(driver, OK_BTN_CLASS, true);
	}

	/**
	 * <b>Description:</b>新建任务
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static String createNewTask(WebDriver driver ,String taskName,String language) {
		CommonJQ.click(driver, CREATETASK_ID, true);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TASKNAME_ID, taskName, true);
		//选择语言
		if("en_US".equals(language)){
			CommonJQ.click(driver, "div[class=\"zz_select msel\"] span", true, 0);
			CommonJQ.click(driver, "#en_US", false);
		}
		clickNextPageBtn(driver, 0);
		return taskName;
	}

	/**
	 * <b>Description:</b>单击下一步按钮
	 *
	 * @author lwx242612
	 * @param driver
	 * @param pageNum
	 * @return void
	 */
	public static void clickNextPageBtn(WebDriver driver, int pageNum) {
		CommonJQ.click(driver, NEXTPAGE_BTN_CLASS, true, pageNum);
	}

	/**
	 * <b>Description:</b>设置问题识别
	 *
	 * @author lwx242612
	 * @param driver
	 * @param standardName
	 * @return void
	 */
	public static void setUpProblemRecognition(WebDriver driver, String cellOrEnodeb, String standardText, int ruleNum,
			String[] xnz, String[] timeGranularity) {
		if (standardText == null) {
			standardText = "";
		}
		// 设置扩容标准
		if (standardText != null && !"".equals(standardText)) {
			if (StringUtils.equals("cell", cellOrEnodeb)) {
				CommonJQ.click(driver, ZZ_SELECT_MSEL_SPAN, true, 0);
				CommonJQ.click(driver, "ul#mshowundefined > li:contains('中国移动扩容标准') ", true);
				CommonJQ.value(driver, CELLDILATATIONTEXT_ID, standardText);
			} else {
				CommonJQ.click(driver, ZZ_SELECT_MSEL_SPAN, true, 1);
				CommonJQ.click(driver, "ul#mshowundefined > li:contains('工具默认扩容标准') ", true);
				CommonJQ.value(driver, ENODEBILATATIONTEXT_ID, standardText);
			}
		}

		// 设置过滤规则
		if (StringUtils.equals(cellOrEnodeb, "cell")) {

			if (ruleNum == 1) {
				CommonJQ.click(driver, CELL_FILTERRULE_CSS, true, 0);
			}
			if (ruleNum == 2) {
				CommonJQ.click(driver, CELL_FILTERRULE_CSS, true, 1);
			}
		} else {

			if (ruleNum == 1) {
				CommonJQ.click(driver, ENODEB_FILTERRULE_CSS, true, 0);
			}
			if (ruleNum == 2) {
				CommonJQ.click(driver, ENODEB_FILTERRULE_CSS, true, 1);
			}
		}

		// 过滤参数设置
		if (null != xnz) {
			for (int i = 0; i < xnz.length; i++) {
				if (StringUtils.equals(cellOrEnodeb, "cell")) {
					if (i == 0) {
						CommonJQ.value(driver, TASKBEAN_CELL_X, xnz[i]);
					}
					if (i == 1) {
						CommonJQ.value(driver, TASKBEAN_CELL_N, xnz[i]);
					}
					if (i == 2) {
						CommonJQ.value(driver, TASKBEAN_CELL_Z, xnz[i]);
					}
				} else {
					if (i == 0) {
						CommonJQ.value(driver, TASKBEAN_ENODEB_X, xnz[i]);
					}
					if (i == 1) {
						CommonJQ.value(driver, TASKBEAN_ENODEB_N, xnz[i]);
					}
					if (i == 2) {
						CommonJQ.value(driver, TASKBEAN_ENODEB_Z, xnz[i]);
					}

				}
			}
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (StringUtils.equals(cellOrEnodeb, "cell")) {
			if (timeGranularity != null) {
				js.executeScript("$('.taskAddParamSetTimeGranularitySpan>span:contains(" + timeGranularity[0]
						+ ")').prev().eq(0).click()");

				if (timeGranularity.length > 1) {
					js.executeScript("$('.taskAddParamSetRadioWithSpan>span:contains(" + timeGranularity[1]
							+ ")').prev().eq(0).click()");
				}
				if (timeGranularity.length > 2) {
					
					if( timeGranularity[2].contains("指定忙时")){
						js.executeScript("$('.taskAddParamSetRadioWithSpan>span:contains(" + "指定忙时"
								+ ")').prev().eq(0).click()");
						
						String time = timeGranularity[2].split("指定忙时")[1];
						String cssStr = "select[name=\"taskBean.taskBean.cell_manuleBusyHour\"]";
						CommonJQ.setSelectOption(driver, cssStr, time);
						
					}else{
						js.executeScript("$('.taskAddParamSetRadioWithSpan>span:contains(" + timeGranularity[2]
								+ ")').prev().eq(0).click()");
					}
					
				}
			}

		} else {
			if (timeGranularity != null && timeGranularity.length>0) {
				js.executeScript("$('.taskAddParamSetTimeGranularitySpan>span:contains(" + timeGranularity[0]
						+ ")').prev().eq(1).click()");

				if (timeGranularity.length > 1) {
					js.executeScript("$('.taskAddParamSetRadioWithSpan>span:contains(" + timeGranularity[1]
							+ ")').prev().eq(1).click()");
				}
				
				if (timeGranularity.length > 2) {
					
					if( timeGranularity[2].contains("指定忙时")){
						js.executeScript("$('.taskAddParamSetRadioWithSpan>span:contains(" + "指定忙时"
								+ ")').prev().eq(1).click()");
						
						String time = timeGranularity[2].split("指定忙时")[1];
						String cssStr = "select[name=\"taskBean.eNodeB_manuleBusyHour\"]";
						CommonJQ.setSelectOption(driver, cssStr, time);
						
					}else{
						js.executeScript("$('.taskAddParamSetRadioWithSpan>span:contains(" + timeGranularity[2]
								+ ")').prev().eq(1).click()");
					}
					
				}
			}
			if (StringUtils.equals(cellOrEnodeb, "enodeb")) {
//				clickNextPageBtn(driver, 0);
				CommonJQ.click(driver, "input[class=\"taskAddParamSetCheckbox\"]" , true);
				CommonJQ.click(driver, "span[class=\"btnNew greenNew green_click ng-binding\"]" , false);
				
			}
		}
	}

	public static String getResultTaskName(WebDriver driver, int index) {
		return CommonJQ.getValue(driver, TASKNAME_CSS, index);
	}

	public static void clickResultParamSetting(WebDriver driver) {
		CommonJQ.click(driver, PARAMSETTING_CLASS, true, 2);
	}

	public static String getCellDilatationTextl(WebDriver driver) {
		return CommonJQ.getValue(driver, CELLDILATATIONTEXT_ID);
	}

	public static String getEnodebDilatationTextl(WebDriver driver) {
		return CommonJQ.getValue(driver, ENODEBILATATIONTEXT_ID);
	}

	/**
	 * <b>Description:</b> 过滤规则 中的规则
	 *
	 * @author lwx242612
	 * @param driver
	 * @param cellOrEnodeb
	 * @return
	 * @return int
	 */
	public static int getCellFourthDiv(WebDriver driver, String cellOrEnodeb) {
		if (StringUtils.equals("cell", cellOrEnodeb)) {
			String check_017 = CommonJQ.getAttrValue(driver, INPUT_NAME_017_CSS, "checked", 0);
			String check_018 = CommonJQ.getAttrValue(driver, INPUT_NAME_018_CSS, "checked", 0);
			if (StringUtils.equals("checked", check_017)) {
				return 1;
			} else if (StringUtils.equals("checked", check_018)) {
				return 2;
			} else {
				Assert.fail("小区级问题识别 过滤规则页面错误");
			}
		} else {
			String check_01I = CommonJQ.getAttrValue(driver, INPUT_NAME_01I_CSS, "checked", 0);
			String check_01J = CommonJQ.getAttrValue(driver, INPUT_NAME_01J_CSS, "checked", 0);
			if (StringUtils.equals("checked", check_01I)) {
				return 1;
			} else if (StringUtils.equals("checked", check_01J)) {
				return 2;
			} else {
				Assert.fail("基站级问题识别 过滤规则页面错误");
			}
		}
		return 3;
	}

	private static final String INPUT_X_CSS = "span:contains(\"X（单次判断周期\")~";
	private static final String INPUT_N_CSS = ".taskAddParamSetSpanWithInput span:contains(\"N\")~";
	private static final String INPUT_Z_CSS = ".taskAddParamSetSpanWithInput span:contains(\"Z（连续判断的次数）\")~";

	/**
	 * <b>Description:</b>过滤参数设置
	 *
	 * @author lwx242612
	 * @param driver
	 * @param cellOrEnodeb
	 * @return
	 * @return String[]
	 */
	public static String[] getXNZ(WebDriver driver, String cellOrEnodeb) {
		String[] xnz = new String[3];
		if (StringUtils.equals("cell", cellOrEnodeb)) {
			xnz[0] = CommonJQ.getValue(driver, INPUT_X_CSS, 0);
			xnz[1] = CommonJQ.getValue(driver, INPUT_N_CSS, 0);
			xnz[2] = CommonJQ.getValue(driver, INPUT_Z_CSS, 0);
		}
		if (StringUtils.equals("enodeb", cellOrEnodeb)) {
			xnz[0] = CommonJQ.getValue(driver, INPUT_X_CSS, 1);
			xnz[1] = CommonJQ.getValue(driver, INPUT_N_CSS, 1);
			xnz[2] = CommonJQ.getValue(driver, INPUT_Z_CSS, 1);
		}
		return xnz;
	}

	private static String TIME_INPUT_017 = "input[name=\"017\"]";
	private static String TIME_INPUT_018 = "input[name=\"018\"]";
	private static String TIME_INPUT_019 = "input[name=\"019\"]";
	private static String TIME_INPUT_01A = "input[name=\"01A\"]";
	private static String TIME_INPUT_01B = "input[name=\"01B\"]";
	private static String TIME_INPUT_01C = "input[name=\"01C\"]";
	private static String TIME_INPUT_01D = "input[name=\"01D\"]";
	private static String TIME_INPUT_01E = "input[name=\"01E\"]";
	private static String TIME_INPUT_01H = "input[name=\"01H\"]";

	private static String TIME_INPUT_01K = "input[name=\"01K\"]";
	private static String TIME_INPUT_01L = "input[name=\"01L\"]";
	private static String TIME_INPUT_01M = "input[name=\"01M\"]";
	private static String TIME_INPUT_01N = "input[name=\"01N\"]";
	private static String TIME_INPUT_01O = "input[name=\"01O\"]";
	private static String TIME_INPUT_01P = "input[name=\"01P\"]";
	private static String TIME_INPUT_01Q = "input[name=\"01Q\"]";
	private static String TIME_INPUT_01R = "input[name=\"01R\"]";
	private static String TIME_INPUT_01S = "input[name=\"01S\"]";

	public static String[] getTimeGranularity(WebDriver driver, String cellOrEnodeb) {
		String[] timeG = new String[3];
		if (StringUtils.equals("enodeb", cellOrEnodeb)) {
			String check_01K = CommonJQ.getAttrValue(driver, TIME_INPUT_01L, "checked", 0);
			String check_01L = CommonJQ.getAttrValue(driver, TIME_INPUT_01M, "checked", 0);
			if (StringUtils.equals("checked", check_01K)) {
				timeG[0] = "天";
			} else {
				timeG[0] = "小时";
			}

			String check_01M = CommonJQ.getAttrValue(driver, TIME_INPUT_01M, "checked", 0);
			String check_01N = CommonJQ.getAttrValue(driver, TIME_INPUT_01N, "checked", 0);
			if (StringUtils.equals(check_01M, "checked")) {
				timeG[1] = "网元自忙时";
			} else {
				timeG[1] = "全网忙时";
			}

			String check_01O = CommonJQ.getAttrValue(driver, TIME_INPUT_01O, "checked", 0);
			String check_01P = CommonJQ.getAttrValue(driver, TIME_INPUT_01P, "checked", 0);
			String check_01Q = CommonJQ.getAttrValue(driver, TIME_INPUT_01Q, "checked", 0);
			String check_01R = CommonJQ.getAttrValue(driver, TIME_INPUT_01R, "checked", 0);
			String check_01S = CommonJQ.getAttrValue(driver, TIME_INPUT_01S, "checked", 0);

			if (StringUtils.equals(check_01O, "checked")) {
				timeG[2] = "独立忙时";
			} else if (StringUtils.equals(check_01P, "checked")) {
				timeG[2] = "DCPRB忙时";
			} else if (StringUtils.equals(check_01Q, "checked")) {
				timeG[2] = "用户数忙时";
			} else if (StringUtils.equals(check_01R, "checked")) {
				timeG[2] = "流量忙时";
			} else if (StringUtils.equals(check_01S, "checked")) {
				timeG[2] = "指定忙时";
			}
		} else {

			String check_017 = CommonJQ.getAttrValue(driver, TIME_INPUT_017, "checked", 0);
			String check_018 = CommonJQ.getAttrValue(driver, TIME_INPUT_018, "checked", 0);
			if (StringUtils.equals("checked", check_017)) {
				timeG[0] = "天";
			} else {
				timeG[0] = "小时";
			}

			String check_019 = CommonJQ.getAttrValue(driver, TIME_INPUT_019, "checked", 0);
			String check_01A = CommonJQ.getAttrValue(driver, TIME_INPUT_01A, "checked", 0);
			if (StringUtils.equals(check_019, "checked")) {
				timeG[1] = "网元自忙时";
			} else {
				timeG[1] = "全网忙时";
			}

			String check_01B = CommonJQ.getAttrValue(driver, TIME_INPUT_01B, "checked", 0);
			String check_01C = CommonJQ.getAttrValue(driver, TIME_INPUT_01C, "checked", 0);
			String check_01D = CommonJQ.getAttrValue(driver, TIME_INPUT_01D, "checked", 0);
			String check_01E = CommonJQ.getAttrValue(driver, TIME_INPUT_01E, "checked", 0);
			String check_01H = CommonJQ.getAttrValue(driver, TIME_INPUT_01H, "checked", 0);

			if (StringUtils.equals(check_01B, "checked")) {
				timeG[2] = "独立忙时";
			} else if (StringUtils.equals(check_01C, "checked")) {
				timeG[2] = "DCPRB忙时";
			} else if (StringUtils.equals(check_01D, "checked")) {
				timeG[2] = "用户数忙时";
			} else if (StringUtils.equals(check_01E, "checked")) {
				timeG[2] = "流量忙时";
			} else if (StringUtils.equals(check_01H, "checked")) {
				timeG[2] = "指定忙时";
			}

		}
		return timeG;
	}

	public static final String STATUE_CSS = ".theme label span";
	private static final String FAILURE_CHECKBOX_CSS = "tbody>tr>td  div.x-grid-row-checker";
	private static final String TASKID_ID = "tbody>tr>td  a";
	private static final String ALL_ID = "#ALL";

	public static final String SUCCESS_ID = "#SUCCESS";

	public static void clickSuccessSelect(WebDriver driver) {
		CommonJQ.click(driver, STATUE_CSS, true, 0);
		CommonJQ.click(driver, SUCCESS_ID, false);
		if (CommonJQ.length(driver, FAILURE_CHECKBOX_CSS, true) > 0) {
			String taskId = CommonJQ.getAttrValue(driver, TASKID_ID, "", "id", 0);
			CommonJQ.click(driver, "#" + taskId, true);
		} else {
			Assert.fail("无分析成功的用例 ，不能发起任务重试");
		}
	}

	public static String clickFailureSelect(WebDriver driver) {
		CommonJQ.click(driver, STATUE_CSS, true, 0);
		CommonJQ.click(driver, FAILURE_ID, false);
		if (CommonJQ.length(driver, FAILURE_CHECKBOX_CSS, true) > 0) {
			return CommonJQ.getAttrValue(driver, TASKID_ID, "", "id", 0);
		} else {
			Assert.fail("无分析失败的用例 ，不能发起任务重试");
		}
		return "";
	}

	private static final String SEARCHID_ID = "#searchId";
	private static final String TASKRETRY_ID = "#taskRetry";

	public static void clickTaskRebuild(WebDriver driver, String id) {
		CommonJQ.click(driver, ALL_ID, false);
		CommonJQ.value(driver, SEARCHID_ID, id);
		CommonJQ.click(driver, SEARCHID_ID + "~", true);
		CommonJQ.click(driver, FAILURE_CHECKBOX_CSS, true, 0);
		CommonJQ.click(driver, TASKRETRY_ID, false);
	}

	private static final String STATUS_VALUE_CSS = "tbody > tr > td";

	public static void compartFirstStatue(WebDriver driver) {
		LoadingPage.Loading(driver);
		Pause.pause(2000);
		String value = CommonJQ.text(driver, STATUS_VALUE_CSS, "", 5);
		if (!StringUtils.equals(value, "待系统分析")) {
			Assert.fail("任务重试失败！");
		}
	}

}
