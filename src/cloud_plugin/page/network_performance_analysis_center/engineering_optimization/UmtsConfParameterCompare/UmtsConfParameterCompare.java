package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.UmtsConfParameterCompare;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class UmtsConfParameterCompare {

	
	public static final String TextTaskName="#taskName";//任务名称
	public static final String BtnConfSelectBefore="#conid5";//劈裂前配置文件
	public static final String BtnConfSelectAfter="#conid6";//劈裂后配置文件
	public static final String BtnsplitMappingFile="#splitMappingFile";//劈裂前后小区关系映射表
	public static final String BtnCancelDateChoose="span[class=\"ng-binding\"]"; // 取消1
	public static final String BtntemplateDownload="#templateDownload"; //模板下载按钮
	public static final String TextwireSearch="#wireSearch";//搜索框
	public static final String MoPara="span:contains(RsvSwitch10)";//需要封装到方法里
	
	public static final String BtnwireLessToRight="#wireLessToRight"; //到右
	public static final String BtnwireLessAllToRight="#wireLessAllToRight";//全到右
	public static final String BtnwireLessToLeft="#wireLessToLeft";//到左
	public static final String BtnwireLessAllToLeft="#wireLessAllToLeft"; //全到左
	
	public static final String BtncommitTasknew="#commit_Tasknew";//提交按钮
	public static final String BtncancelTask="#cancel_Task";//取消按钮
	
	public static final String BtnexportWireLess="#exportWireLess";//导出全参数模板	
	public static final String BtnwireImport="#wireImport";//导入自定义模板
	public static final String BtnimportFile="#importFile";//浏览按钮
	public static final String BtncommitTasknew1="span[id=\"commit_Tasknew_1\"]";//导入按钮

	public static final String RadiocellNameWire="#cellNameWire";//无线参数
	public static final String RadiocellNameSelf="#cellNameSelf";//自定义参数
	public static final String wrapbuttonleftPanel="#wrap_button_leftPanel";//空白
	
	public static final String nnn="span:contains(\"界面选择参数\")";

	
	public static String setTaskName(WebDriver driver,String taskName) {
		 taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtncommitTasknew, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtncancelTask, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
}
