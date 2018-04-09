package cloud_plugin.page.network_performance_analysis_center.drop_call_analysis;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonJQ;

public class DropCallAnalysisPage {

	private static final String TASKNAME_ID = "#taskName";
	private static final String TDDRATTYPE_ID = "#tddRatType";
	private static final String FDDRATTYPE_ID = "#fddRatType";
	private static final String SUBMITTASK_ID = "#submitTask";
	
	private static final String SELECT_CONFIGFILEPATH_ID = "#select_configFilePath";
	private static final String SELECT_PARASFILEPATH_ID = "#select_parasFilePath";
	private static final String SELECT_PFMFILEPATH_ID = "#select_pfmFilePath";
	private static final String SELECT_CHRFILEPATH_ID = "#select_chrFilePath";
	
	private static final String FILECHOICEERRORMSG_CSS = "span[class=\"fileChoiceErrorMsg ng-binding\"]";
	
	/**
	 * <b>Description:</b>单击创建任务
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void clickCreateTask(WebDriver driver) {
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
	}

	/**
	 * <b>Description:</b>设置任务名称和制式类型
	 *
	 * @author lwx242612
	 * @param driver
	 * @param taskName
	 * @param ratType
	 * @return void
	 */
	public static void setTaskNameAndRatType(WebDriver driver, String taskName, String ratType) {
		CommonJQ.value(driver, DropCallAnalysisPage.TASKNAME_ID, taskName, true);
		if ("LTE-TDD".equalsIgnoreCase(ratType)) {
			CommonJQ.click(driver, TDDRATTYPE_ID, true);
		} else if ("LTE-FDD".equalsIgnoreCase(ratType)) {
			CommonJQ.click(driver, FDDRATTYPE_ID, true);
		} else {
			Assert.fail("Rat Type 未正确填写");
		}
	}


	/**
	 * <b>Description:</b>设置分析数据
	*
	* @author lwx242612
	* @param driver
	* @param configurationData
	* @param engineeringParameter
	* @param pfmData
	* @param chrDate
	* @return  void
	 */
	public static void setAnalysisData(WebDriver driver, String[] configurationData, String[] engineeringParameter,
			String[] pfmData, String[] chrDate) {
		String defaultWindowID = driver.getWindowHandle();
		if (configurationData != null && configurationData.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, configurationData, SELECT_CONFIGFILEPATH_ID, defaultWindowID);
		}

		if (engineeringParameter != null && engineeringParameter.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, engineeringParameter, SELECT_PARASFILEPATH_ID, defaultWindowID);
		}

		if (pfmData != null && pfmData.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, pfmData, SELECT_PFMFILEPATH_ID, defaultWindowID);
		}

		if (chrDate != null && chrDate.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, chrDate, SELECT_CHRFILEPATH_ID, defaultWindowID);
		}
	}

	/**
	 * <b>Description:</b>单击提交
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void clickSubmitButton(WebDriver driver) {
		CommonJQ.click(driver, SUBMITTASK_ID, true);
	}
	
	/**
	 * <b>Description:</b>获取分析数据提示错误信息
	*
	* @author lwx242612
	* @param driver
	* @return  void
	 */
	public static void analysisDataErrorMsg(WebDriver driver){
		String msg = CommonJQ.text(driver, FILECHOICEERRORMSG_CSS);
		if(!msg.trim().equals("")){
			Assert.fail(msg);
		}
	}

}
