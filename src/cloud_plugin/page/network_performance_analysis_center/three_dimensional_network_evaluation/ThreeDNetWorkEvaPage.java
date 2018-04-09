package cloud_plugin.page.network_performance_analysis_center.three_dimensional_network_evaluation;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class ThreeDNetWorkEvaPage {

	private static final String AppName = "li[id=\"genexspace.plugin.threedimensionalnetworkevaluationPlugStr\"]";

	private static final String PoorQualityThresholdIdentification_AppName = "li[id=\"genexspace.plugin.videoOptQualityThresholdPlugStr\"]";
	public static void ThreeDNetWorkEvaClick(WebDriver driver) {
		CommonJQ.click(driver, AppName, true, "插件未找到，插件名：3D网络评估");
	}
	public static void PoorQualityThresholdIdentificationClick(WebDriver driver) {
		CommonJQ.click(driver, PoorQualityThresholdIdentification_AppName, true, "插件未找到，插件名：视频优化质差门限识别");
	}
	private static final String TextTaskName = "#taskName";
	
	private static final String RBoxTDD = "input[value=\"TDD\"]";
	private static final String RBoxFDD = "input[value=\"FDD\"]";
	private static final String RBoxUMTS = "input[value=\"UMTS\"]";
	
	private static final String CBoxOnlineMap = "input[name=\"task.idartTask.selectOrOnline\"]";
	private static final String TextMap = "#ottemapNum";
	private static final String TextTeZhengKu = "#tezhengkuNum";
	private static final String TextDT = "#ottDtFileDiv";
	
	private static final String BtnAntenna = "#ottAntennasFile";
	private static final String BtnDT = "#ottDtFile";
	
	private static final String BtnCheckData = "#compare_Task";
	private static final String BtnCommit = "#commit_Tasknew";
	private static final String BtnCancel = "#cancel_Task";
	
	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";


	/**
	 * 提示框处理-IE10
	 * @param driver
	 */
	public static void TipIfoIE10OK(WebDriver driver) {
		String IE10Message = LanguageUtil.translate("3D网络评估", "Please use the above version of IE10 to open");
		boolean Flage = CommonJQ.isExisted_ByText(driver, "div", IE10Message, true);
		if(Flage){
			CommonJQ.click(driver, BtnMessageOK, true, "提示框"+IE10Message);	
		}
		CommonJQ.click(driver, CBoxOnlineMap, true,"复选按钮：在线地图");
	}
	/**
	 * 提示框处理-OTT
	 * @param driver
	 */
	public static void TipIfoOTTOK(WebDriver driver) {
		String Message = LanguageUtil.translate("3D网络评估", "OTT data is not selected , it will affect the accuracy of the assessment , to determine whether？");
		boolean Flage = CommonJQ.isExisted_ByText(driver, "div", Message, true);
		if(Flage){
			CommonJQ.click(driver, BtnMessageOK, true, "提示框"+Message);	
		}
	}
	/**
	 * 任务名称设置
	 * @param driver
	 * @param taskName
	 * @return
	 */
	public static String setTaskName(WebDriver driver, String taskName) {
		taskName = taskName + "_" + ZIP.hhmmss();		
		CommonJQ.value(driver, TextTaskName, taskName, "任务名称");
		return taskName;
	}
	/**
	 * 制式设置
	 * @param driver
	 * @param RAT
	 */
	public static void CheckRAT(WebDriver driver, String RAT) {
		if("LTE-FDD".equalsIgnoreCase(RAT)){
			CommonJQ.click(driver, RBoxFDD, true,"单选按钮：LTE-FDD");
		}else if("UMTS".equalsIgnoreCase(RAT)){
			CommonJQ.click(driver, RBoxUMTS, true,"单选按钮：UMTS");
		}else{
			CommonJQ.click(driver, RBoxTDD, true,"单选按钮：LTE-TDD");
		}
	}
	/**
	 * MR数据选择
	 * @param driver
	 * @param defaultWindowID
	 * @param ChooseFile
	 */
	public static void MRChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#selectmass_exteriorlog", "MR数据->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"selectmass_exteriorlog", defaultWindowID);
		}
	}
	/**
	 * PCHR数据选择
	 * @param driver
	 * @param defaultWindowID
	 * @param ChooseFile
	 */
	public static void PCHRChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#selectpchr", "PCHR数据->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"selectpchr", defaultWindowID);
		}
	}
	/**
	 * 工参数据选择
	 * @param driver
	 * @param defaultWindowID
	 * @param paraFile
	 */
	public static void ParaChooseFile(WebDriver driver, String defaultWindowID,
			String paraFile) {

		if (paraFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#selectparas", "工参数据->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			String[] filePath ={paraFile};
			GetDataByTypeTask.chooseFolder(driver, filePath,
					"selectparas", defaultWindowID);
		}
	}
	/**
	 * Polygon文件选择
	 * @param driver
	 * @param defaultWindowID
	 * @param ChooseFile
	 */
	public static void PolygonChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#selectottpology", "Polygon文件->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"selectottpology", defaultWindowID);
		}
	}
	/**
	 * 电子地图选择
	 * @param driver
	 * @param defaultWindowID
	 * @param ChooseFile
	 */
	public static void MapChooseFile(WebDriver driver, String defaultWindowID,boolean OnlineFlage,
			String[] ChooseFile) {
		
		if(OnlineFlage){
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, CBoxOnlineMap, true,"复选按钮：在线地图");
			String MapVal = CommonJQ.getValue(driver, TextMap, true, "电子地图呈现输入框");
			for(int i =0;i<10&&"".equalsIgnoreCase(MapVal);i++){
				Pause.pause(1000);
				MapVal = CommonJQ.getValue(driver, TextMap, true, "电子地图呈现输入框");
			}
			if("".equalsIgnoreCase(MapVal)){
				Assert.fail("在线地图加载失败");
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}else{
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#selectottemap", "电子地图->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			if (ChooseFile != null) {
				GetDataByTypeTask.chooseFolder(driver, ChooseFile,
						"selectottemap", defaultWindowID);
			}	
		}
	}
	/**
	 * 特征库选择
	 * @param driver
	 * @param defaultWindowID
	 * @param ChooseFile
	 */
	public static void FeatureChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			if(ChooseFile[0].equalsIgnoreCase("MAX")){
				String[] MaxFile = {};
				GetDataByTypeTask.chooseTimeFolder(driver, MaxFile,
						"#selecttezhengkuDialog1 span#selecttezhengku", defaultWindowID);
			}else{
				GetDataByTypeTask.chooseTimeFolder(driver, ChooseFile,
						"#selecttezhengkuDialog1 span#selecttezhengku", defaultWindowID);	
			}

		}
	}
	/**
	 * 天线文件选择
	 * @param driver
	 * @param filePath
	 */
	public static void AntennaChooseFile(WebDriver driver,String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, "span#selecttezhengku", true, "天线文件选择文件");
			CommonJQ.click(driver, BtnAntenna, true, "天线文件->选择文件");
			CommonFile.ChooseOneFile(filePath);
//			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");
			String MapVal = CommonJQ.getValue(driver, TextTeZhengKu, true, "天线文件呈现输入框");
			for(int i =0;i<10&&"".equalsIgnoreCase(MapVal);i++){
				Pause.pause(1000);
				MapVal = CommonJQ.getValue(driver, TextTeZhengKu, true, "天线文件呈现输入框");
			}
			if("".equalsIgnoreCase(MapVal)){
				Assert.fail("天线文件上传失败");
			}
		}
	}

	/**
	 * OTT参数选择
	 * @param driver
	 * @param defaultWindowID
	 * @param ChooseFile
	 */
	public static void OTTChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#selectottdata", "OTT参数->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"selectottdata", defaultWindowID);
		}
	}
	/**
	 * DT文件选择
	 * @param driver
	 * @param filePath
	 */
	public static void DTChooseFile(WebDriver driver,String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, BtnDT, true, "DT文件->选择文件");
			CommonFile.ChooseOneFile(filePath);
//			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");	
			String MapVal = CommonJQ.getValue(driver, TextDT, true, "DT文件呈现输入框");
			for(int i =0;i<10&&"".equalsIgnoreCase(MapVal);i++){
				Pause.pause(1000);
				MapVal = CommonJQ.getValue(driver, TextDT, true, "DT文件呈现输入框");
			}
			if("".equalsIgnoreCase(MapVal)){
				Assert.fail("DT文件上传失败");
			}
		}
	}
	
	/**
	 * 数据完整性校验-单击
	 * @param driver
	 */
	public static void BtnCheckDataClick(WebDriver driver) {
		CommonJQ.click(driver, BtnCheckData, true, "数据完整性校验");
	}
	/**
	 * 下一步-单击
	 * @param driver
	 */
	public static void BtnCommitClick(WebDriver driver) {
		CommonJQ.click(driver, BtnCommit, true, "下一步");
	}
	/**
	 * 取消并返回-单击
	 * @param driver
	 */
	public static void BtnCancelClick(WebDriver driver) {
		CommonJQ.click(driver, BtnCancel, true, "取消并返回");
	}
	/**
	 * 3D地图是否呈现
	 * @param driver
	 */
	public static boolean earthFlage(WebDriver driver) {
		System.out.println(CommonJQ.isExisted(driver, "#earthImg", true));//img#earthImg
		return CommonJQ.isExisted(driver, "#earthImg", true);
	}
	/**
	 * 天线文件->模板下载
	 * @param driver
	 * @param ResultHome
	 */
	public static void BtnTempAntennasClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, "#ottAntennasFiletemp", true, "天线文件->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "antennas");
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome);
	}
	/**
	 * Dt文件->模板下载
	 * @param driver
	 * @param ResultHome
	 */
	public static void BtnTempDtClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, "#ottDtFiletemp", true, "Dt文件->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "DTParameter");
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome);
	}
}
