package cloud_plugin.task.network_performance_analysis_center.drop_call_analysis;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.drop_call_analysis.DropCallAnalysisPage;
import common.util.SwitchDriver;

public class DropCallAnalysisTask {

	public static void createNewTask(WebDriver driver, String taskName, String ratType, String[] configurationData,
			String[] engineeringParameter, String[] pfmData, String[] chrDate) {

		DropCallAnalysisPage.clickCreateTask(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		DropCallAnalysisPage.setTaskNameAndRatType(driver, taskName, ratType);
		SwitchDriver.switchDriverToSEQ(driver);
		DropCallAnalysisPage.setAnalysisData(driver, configurationData, engineeringParameter, pfmData, chrDate);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		DropCallAnalysisPage.clickSubmitButton(driver);
		DropCallAnalysisPage.analysisDataErrorMsg(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}

}
