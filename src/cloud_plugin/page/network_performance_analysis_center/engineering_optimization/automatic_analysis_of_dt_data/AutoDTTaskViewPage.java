package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;

public class AutoDTTaskViewPage {

	// const string.
	public static final String ReportDownLoad_ID = "a[class=\"icon_down ml10\"]";
	
	public static void reportDownLoadClick( WebDriver driver){
//		driver.findElement(By.id("reportDownLoad")).click();
		CommonJQ.click(driver, ReportDownLoad_ID, true);
	}
}
