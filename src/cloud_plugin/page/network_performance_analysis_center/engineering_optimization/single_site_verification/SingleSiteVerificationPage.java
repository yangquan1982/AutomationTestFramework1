package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.single_site_verification;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class SingleSiteVerificationPage {
	public static final String TextTaskName = "#taskDesc";
	public static final String reportDetails = "#reportDetails";//报告模板
	public static final String mshowvaReportModel = "ul[id=\"mshowvaReportModel\"] li";//报告模板下拉框
	public static final String BtnSingleStateChecked = "#taskType1";//单站验证
	public static final String BtnSingleStateOptimize = "#taskType0";//单站优化
	public static final String BtnTargetCensus = "#taskType2";//指标统计
	public static final String BtnPlaneMap = "#mapType1";//平面图
	public static final String BtnSatelliteMap = "#mapType2";//卫星地图
	public static final String BtnParameterSetUp = "#dialog1Setting";
	public static final String startTime="#date1";
	public static final String endTime="#date2";
	public static final String BtnCommit_Tasknew="#commit_Tasknew";
	public static String setTaskName(WebDriver driver, String  taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver,TextTaskName);
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}
	//分析数据
	public static void analyzeData(WebDriver driver, String[] cfgData, String[] projectParame, String[] roadSurveyData, String defaultWindowID) {
		if(null!=cfgData) { //配置数据
			GetDataByTypeTask.chooseFolder(driver, cfgData, 
					"$('iframe[name=main]').contents().find('#select_conf').get(0).click()", 
					defaultWindowID);
			
		}
		if(null!=projectParame) { //工程数据
			GetDataByTypeTask.chooseFolder(driver, projectParame, 
					"$('iframe[name=main]').contents().find('#select_paras').get(0).click()",
					defaultWindowID);
		}
		if(null!=roadSurveyData) { //路测数据
			GetDataByTypeTask.chooseFolder(driver, roadSurveyData, 
					"$('iframe[name=main]').contents().find('#select_dt').get(0).click()",
					defaultWindowID);
		}
	}
	//参数设置
	public static void projectSetUp(WebDriver driver,String[] reconnaissanceMapCfg, String[] speedImg, String[] DTFileCfg, String[] RSSICfg, String[] expansionParameter,String FilePath, String defaultWindowID) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		/*if(null!=reconnaissanceMapCfg) {//勘测图片配置
			CommonJQ.click(driver, "#tit1", true);
			for(int i = 0; i < reconnaissanceMapCfg.length; i++) {
				if(!"".equals(reconnaissanceMapCfg[i])&& null!=reconnaissanceMapCfg[i]) {
					CommonJQ.click(driver, "#kanceTab > tbody span", true,i);
					String path = FilePath+reconnaissanceMapCfg[i];
					CommonFile.ChooseOneFile(path);
				}
			}
		}
		if(null!=speedImg) {//速率截图配置
			CommonJQ.click(driver, "#tit2", true);
			for(int i = 0; i < speedImg.length; i++) {
				if(!"".equals(speedImg[i])&& null!=speedImg[i]) {
					CommonJQ.click(driver, "#sulvTab > tbody span", true,0);
					String path = FilePath+speedImg[i];
					CommonFile.ChooseOneFile(path);
				}
			}
		}*/
		if(null!=DTFileCfg) {//DT文件映射
			CommonJQ.click(driver, "#tit3", true);
			for(int i = 0; i < DTFileCfg.length; i++) {
				if(!"".equals(DTFileCfg[i])&& null!=DTFileCfg[i]) {
					CommonJQ.value(driver, ".dtConfigList", true,i,DTFileCfg[i], "");
				}
			}
		}
		if(null!=RSSICfg) {//PSSI配置
			CommonJQ.click(driver, "#tit4", true);
			for(int i = 0; i < RSSICfg.length; i++) {
				System.out.println(RSSICfg[i]);
				if(!"".equals(RSSICfg[i])&& null!=RSSICfg[i]) {
					CommonJQ.click(driver, "#rssiTab > tbody span", true,i);
					String path = FilePath+RSSICfg[i];
					CommonFile.ChooseOneFile(path);
				}
			}
		}
		if(null!=expansionParameter) {//其他配置
			CommonJQ.click(driver, "#tit5", true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		if(null!=expansionParameter) {//其他配置
			/*CommonJQ.click(driver, "#select_other", true);
			String path = FilePath+expansionParameter;
			CommonFile.ChooseOneFile(path);*/
			GetDataByTypeTask.chooseFolder(driver, expansionParameter, 
					"$('iframe[name=main]').contents().find('#select_other').get(0).click()",
					defaultWindowID);
		}
		//
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, "#ok_Button", true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
