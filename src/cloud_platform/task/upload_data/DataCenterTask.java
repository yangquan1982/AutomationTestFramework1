package cloud_platform.task.upload_data;

import java.awt.event.KeyEvent;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cloud_platform.page.DataCenterConst;
import cloud_platform.page.UpDataPage;
import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import cloud_public.task.IndexTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.ImageUtil;
import common.util.LOG;
import common.util.ReadNotes;
import common.util.ZIP;

public class DataCenterTask {

	public static final int MAXTIME = 2000;// 正常数据上传最大时间：秒
	public static final int CONCURRENTMAXTIME = 6;// 并发 大数据上传最大时间：小时
	public static final int ErrorMAXTIME = 600;//异常数据上传最大时间 ：秒
	public static final int ErrorChecktime = 40;//异常数据上传 验证上传成功间隔时间 ：秒
	public static final int ErrorDetailChecktime = 15;//异常数据上传 验证异常提示信息时间 ： 秒
	public static final int ParamterCheckTime = 125;// 工参核查页面加载时间： 秒
	public static final int ParamterCheckGapTime = 5;// 工参核查页面加载扫描间隔时间： 秒
	public static final int AddUpLoadTaskGaptime = 5;// 数据上传页面加载扫描时间间隔： 秒

	/**
	 * 参数化上传单个类型数据
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 */
	public static void dataSourceUpLoad(WebDriver driver, String rat,
			String DataType, String subDataType, String feature,
			String[] dataPath) {
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);
		DataCenterTask.checkParam(rat + "_" + DataType + "_" + subDataType,
				dataPath);
		//DataCenterTask.selectUpLoadDataType(driver, rat, DataType, subDataType);
		String folderName = DataCenterTask.getFolderName(subDataType,
				dataPath.length);
		if (IsIntgratedPacketUpLoad(DataType)
				|| StringUtils.equalsIgnoreCase(rat, "GSM")) {
			UpDataPage.selectRat(driver, rat);
			UpDataPage.selectDataType(driver, DataCenterConst.Equipment_Data);
			
			DataCenterTask.addUploadTask(driver);
			UpDataPage.selectDateType(driver, DataType);
			UpDataPage.selectSubDateType(driver, subDataType);
			DataCenterTask.selectFolder2(driver, DataType, folderName);
		} else {
			DataCenterTask.selectUpLoadDataType(driver, rat, DataType,
					subDataType);
			Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
			DataCenterTask.selectFolder(driver, DataType, subDataType,
					folderName);
			DataCenterTask.addUploadTask(driver);
			DataCenterTask.selectFeature(driver, subDataType, feature);
		}
		DataCenterTask.selectCloseWindow(driver);
		DataCenterTask.selectUpDataSource(driver, subDataType, dataPath);
		DataCenterTask.clickBatchUpdataBTN(driver);
		DataCenterTask.assertUploadDataOk(driver, rat + "_" + DataType + "_"
				+ subDataType, MAXTIME);
	}

	/**
	 * 自定义文件夹名称、项目上传数据
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 * @param folderName
	 * @param projectName
	 */
	public static void dataSourceUpLoadUseCustomFolder(WebDriver driver,
			String rat, String DataType, String subDataType, String feature,
			String[] dataPath, String folderName, String projectName) {
		IndexTask.changePrj(driver, projectName);
		DataCenterTask.checkParam(rat + "_" + DataType + "_" + subDataType,
				dataPath);
		if (folderName == null || StringUtils.isBlank(folderName)) {
			folderName = DataCenterTask.getFolderName(subDataType,
					dataPath.length);
		}
		if (IsIntgratedPacketUpLoad(DataType)
				|| StringUtils.equalsIgnoreCase(rat, "GSM")) {
			UpDataPage.selectRat(driver, rat);
			UpDataPage.selectDataType(driver, DataCenterConst.Equipment_Data);
			DataCenterTask.addUploadTask(driver);
			UpDataPage.selectDateType(driver, DataType);
			UpDataPage.selectSubDateType(driver, subDataType);
			DataCenterTask.selectFolder2(driver, DataType, folderName);
		} else {
			DataCenterTask.selectUpLoadDataType(driver, rat, DataType,
					subDataType);
			Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
			DataCenterTask.selectFolder(driver, DataType, subDataType,
					folderName);
			DataCenterTask.addUploadTask(driver);
			DataCenterTask.selectFeature(driver, subDataType, feature);
		}
		DataCenterTask.selectCloseWindow(driver);
		DataCenterTask.selectUpDataSource(driver, subDataType, dataPath);
		DataCenterTask.clickBatchUpdataBTN(driver);
		DataCenterTask.assertUploadDataOk(driver, rat + "_" + DataType + "_"
				+ subDataType, MAXTIME);
	}

	/**
	 * 并发测试自定义项目上传数据
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 * @param folderName
	 */
	public static void dataSourceUpLoadConCurrentTest(WebDriver driver,
			String rat, String DataType, String subDataType, String feature,
			String[] dataPath, String projectName) {
		IndexTask.changePrj(driver, projectName);
		DataCenterTask.checkParam(rat + "_" + DataType + "_" + subDataType,
				dataPath);
		DataCenterTask.selectUpLoadDataType(driver, rat, DataType, subDataType);
		Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
		String folderName = DataCenterTask.getFolderName(subDataType,
				dataPath.length);
		DataCenterTask.selectFolder(driver, DataType, subDataType, folderName);
		DataCenterTask.addUploadTask(driver);
		DataCenterTask.selectFeature(driver, subDataType, feature);
		DataCenterTask.selectCloseWindow(driver);
		DataCenterTask.selectUpDataSource(driver, subDataType, dataPath);
		DataCenterTask.clickBatchUpdataBTN(driver);
		DataCenterTask.assertUploadDataOkforConcurrentTest(driver, rat + "_"
				+ DataType + "_" + subDataType, CONCURRENTMAXTIME);
	}

	/**
	 * 按照数据类型上传数据，支持所有数据类型的上传
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 * @param checkInfor
	 */
	public static void dataSourceUpLoad(WebDriver driver, String rat,
			String DataType, String subDataType, String feature,
			String[] dataPath, String[] checkInfor) {
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);
		DataCenterTask.checkParam(rat + "_" + DataType + "_" + subDataType,
				dataPath);
		String folderName = DataCenterTask.getFolderName(subDataType,
				dataPath.length);
		if (IsIntgratedPacketUpLoad(DataType)
				|| (StringUtils.equalsIgnoreCase(rat, "GSM")&&StringUtils.equalsIgnoreCase(DataType,DataCenterConst.Equipment_Data))) {
			UpDataPage.selectRat(driver, rat);
			UpDataPage.selectDataType(driver, DataCenterConst.Equipment_Data);
			DataCenterTask.addUploadTask(driver);
			UpDataPage.selectDateType(driver, DataType);
			UpDataPage.selectSubDateType(driver, subDataType);
			DataCenterTask.selectFolder2(driver, DataType, folderName);
		} else {
			DataCenterTask.selectUpLoadDataType(driver, rat, DataType,
					subDataType);
			Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
			DataCenterTask.selectFolder(driver, DataType, subDataType,
					folderName);
			DataCenterTask.addUploadTask(driver);
			DataCenterTask.selectFeature(driver, subDataType, feature);
		}
		DataCenterTask.selectCloseWindow(driver);
		DataCenterTask.selectUpDataSource(driver, subDataType, dataPath);
		DataCenterTask.clickBatchUpdataBTN(driver);
		DataCenterTask.assertUploadDataOk(driver, rat + "_" + DataType + "_"
				+ subDataType, MAXTIME);
		DataCenterTask.checkUpLoadResult(driver, rat, DataType, subDataType,
				folderName, checkInfor);
	}

	/**
	 * 上传指南验证
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 */
	public static void uploadGuide(WebDriver driver, String rat,
			String DataType, String subDataType) {
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);

		DataCenterTask.selectUpLoadDataType(driver, rat, DataType, subDataType);
		Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
		String[] subDataTypes = subDataType.split(";");
		if (subDataTypes.length == 2) {
			if (!UpDataPage.isExsistFolder(driver, subDataTypes[1])) {
				Assert.fail("不存在簇文件夹：" + subDataTypes[1]);
			}
			UpDataPage.clickFolderName(driver, subDataTypes[1]);
			UpDataPage.waitForMask(driver);
		}
		String folderName = subDataType + "_" + ZIP.GetTime("MMdd");
		if (StringUtils.equalsIgnoreCase(DataType, DataCenterConst.DT_Data)) {
			DataCenterTask.selectFolder(driver, DataType, subDataType,
					folderName);
		}

		DataCenterTask.addUploadTask(driver);
		if (subDataTypes.length == 2) {
			DataCenterTask.checkDefaultParam(driver, DataType, subDataTypes[1]);
			UpDataPage.clickUploadGuidBTN(driver);
			if (StringUtils.containsIgnoreCase(subDataTypes[0], DataCenterConst.KQI_NetworkData)) {
				DataCenterTask.checkUploadGuideDisplay(driver, DataType,
						subDataTypes[0]);
			}else{
				DataCenterTask.checkUploadGuideDisplay(driver, DataType,
						subDataTypes[1]);
			}
			
		} else {
			DataCenterTask.checkDefaultParam(driver, DataType, subDataType);
			UpDataPage.clickUploadGuidBTN(driver);
			DataCenterTask.checkUploadGuideDisplay(driver, DataType,
					subDataType);
		}

	}

	/**
	 * 综合数据包上传指南验证
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 */
	public static void IntegratedPacketUploadGuide(WebDriver driver,
			String rat, String DataType, String subDataType) {
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);
		UpDataPage.selectRat(driver, rat);
		DataCenterTask.addUploadTask(driver);
		UpDataPage.selectDateType(driver, DataType);
		UpDataPage.selectSubDateType(driver, subDataType);
		UpDataPage.clickUploadGuidBTN(driver);
		DataCenterTask.checkUploadGuideDisplay(driver, DataType, subDataType);

	}

	/**
	 * 异常数据上传提示信息验证
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 * @param errorInfor
	 */
	public static void abnormalDataSourceUpLoad(WebDriver driver, String rat,
			String DataType, String subDataType, String feature,
			String[] dataPath, String errorInfor) {
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);
		DataCenterTask.checkParam(rat + "_" + DataType + "_" + subDataType,
				dataPath);
		DataCenterTask.selectUpLoadDataType(driver, rat, DataType, subDataType);
		String folderName = DataCenterTask.getFolderName(subDataType,
				dataPath.length);
		DataCenterTask.selectFolder(driver, DataType, subDataType, folderName);
		DataCenterTask.addUploadTask(driver);
		DataCenterTask.selectFeature(driver, subDataType, feature);
		// DataCenterTask.selectCloseWindow(driver);
		DataCenterTask.selectUpDataSource(driver, subDataType, dataPath);
		DataCenterTask.clickStartUpdataBTN(driver);
		String upOKInfor = "数据类型_" + DataType + "_" + subDataType;
		DataCenterTask.checkErrorInfor(driver, errorInfor, upOKInfor);

	}

	/**
	 * 综合数据包异常数据提示验证
	 * 
	 * @param driver
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 * @param errorInfor
	 */
	public static void abNormalIntegratedPacketSourceUpLoad(WebDriver driver,
			String rat, String DataType, String subDataType, String feature,
			String[] dataPath, String errorInfor) {
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);
		DataCenterTask.checkParam(rat + "_" + DataType + "_" + subDataType,
				dataPath);
		UpDataPage.selectRat(driver, rat);
		// DataCenterTask.selectFolder(driver,DataType,subDataType);
		DataCenterTask.addUploadTask(driver);
		UpDataPage.selectDateType(driver, DataType);
		UpDataPage.selectSubDateType(driver, subDataType);
		String folderName = DataCenterTask.getFolderName(subDataType,
				dataPath.length);
		DataCenterTask.selectFolder2(driver, DataType, folderName);
		DataCenterTask.selectUpDataSource(driver, subDataType, dataPath);
		DataCenterTask.clickStartUpdataBTN(driver);
		String upOKInfor = "数据类型_" + DataType + "_" + subDataType;
		DataCenterTask.checkErrorInfor(driver, errorInfor, upOKInfor);
	}

	/**
	 * 工参模板下载
	 * 
	 * @param driver
	 * @param templateName
	 * @param featureName
	 * @param TemplateFilePath
	 */
	public static void engineerParameterTemplateDown(WebDriver driver,
			String templateName, String featureName, String TemplateFilePath) {
		// 切换项目名称
		IndexTask.changePrj(driver, IndexPage.ChinaTelecomLTEBeiJing);
		// 选择工参数据
		UpDataPage.selectDataType(driver, DataCenterConst.EngParam,
				DataCenterConst.EngParam);

		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		// 找到工参模板
		DataCenterTask.selectEngParamType(driver, templateName, featureName);
		// 读取下载文件和文件模板做对比
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, ".xlsx");
		LOG.info_aw("下载的文件模板名称：" + CommonFile.getDownLoadDirectoryFileName());
		String downloadFilePath = CommonFile
				.getDownLoadDirectorygetAbsolutePath();
		String sheetName = getTemplateSheetName(templateName);
		LOG.info_aw("下载的文件是：" + downloadFilePath);
		// 下载的文件和正常模板进行对比
		String compareResult = new ReadNotes().readExcelProperty(
				TemplateFilePath, downloadFilePath, sheetName, featureName);
		System.out.print(compareResult);
		Assert.assertTrue(templateName + "-特性-" + featureName + ",模板文件对比失败！\n\r"+compareResult,
				compareResult==null);

	}

	/**
	 * 判断是否为综合数据包上传类型
	 * 
	 * @param dataType
	 * @return
	 */
	private static boolean IsIntgratedPacketUpLoad(String dataType) {
		if (dataType != null) {
			if (StringUtils.containsIgnoreCase(dataType,
					DataCenterConst.IntegratedPacket)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 按照下载工参模板类型获取下载文件的sheet页签名称
	 * 
	 * @param templateName
	 * @return
	 */
	private static String getTemplateSheetName(String templateName) {
		String sheetname = null;
		if (templateName != null) {
			if (StringUtils.containsIgnoreCase(templateName, "LTE"))
				sheetname = "LTE";
			if (StringUtils.containsIgnoreCase(templateName, "UMTS"))
				sheetname = "UMTS";
			if (StringUtils.containsIgnoreCase(templateName, "GSM"))
				sheetname = "GSM";
			if (StringUtils.containsIgnoreCase(templateName, "TDS"))
				sheetname = "TDS";
		}
		return sheetname;
	}

	/**
	 * 选择工参模板下载类型
	 * 
	 * @param driver
	 * @param templateName
	 * @param featureName
	 */
	private static void selectEngParamType(WebDriver driver,
			String templateName, String featureName) {
		LOG.info_aw("选择" + templateName + "下的特性" + featureName);
		// 选择工参模板下载
		String script = "$('.tit').find('div').filter(function(){return $(this).text().trim()=='工参模板下载' })";

		if (!CommonJQ.excuteJStoBoolean(driver, "return " + script
				+ ".is(':visible');")) {
			Assert.fail("Web界面中工参数据源下不存在工参模板下载接口！");
		}
		script += ".click();";
		CommonJQ.excuteJS(driver, script);
		CommonJQ.waitForElementVisble(driver, ".tit>ul");
		LoadingPage.Loading(driver);

		// 选择模板
		String scriptRat = "$('.tit>ul').find('a').filter(function(){return $(this).text().trim()=='"
				+ templateName + "'})";
		if (CommonJQ.excuteJStoInt(driver, "return " + scriptRat + ".length;") == 0) {
			Assert.fail("Web界面中不存在" + templateName + "入口！");
		}
		scriptRat += ".click();";
		CommonJQ.excuteJS(driver, scriptRat);
		CommonJQ.waitForElementVisble(driver, "#featuresNameDiv");
		LoadingPage.Loading(driver);
		// 选择模板中的类型
		String scriptType = "$('#featuresNames').find('li').filter(function(){return $(this).text().trim()=='"
				+ featureName + "'})";
		if (CommonJQ.excuteJStoInt(driver, "return " + scriptType + ".length;") == 0) {
			Assert.fail("当前环境" + templateName + "中没有特性" + featureName + "入口！");
		}
		scriptType += ".find('input').click();";
		CommonJQ.excuteJS(driver, scriptType);
		Pause.pause(500);
		LoadingPage.Loading(driver);
		String OKBTN = "$('#featuresNameDiv').find('.r_pop_btn').find('.btn_green_login').find('span').click()";
		CommonJQ.excuteJS(driver, OKBTN);
		LoadingPage.Loading(driver);
		if (CommonJQ.isVisible(driver, "#featuresNameDiv")) {
			Assert.fail("当前环境" + templateName + "选择" + featureName
					+ "点击确定界面无响应！");
		}
	}

	/**
	 * 获取上传文件夹名称
	 * 
	 * @param subDataType
	 * @param length
	 * @return
	 */
	private static String getFolderName(String subDataType, int length) {
		String folderName = subDataType + "_";
		if (length > 1) {
			folderName += "批量上传_"+ZIP.GetTime("MMdd");
		}else if(StringUtils.containsIgnoreCase(subDataType, DataCenterConst.Config_Data)){
			folderName += ZIP.GetTime("MMddHHmmss");
		}else{
			folderName += ZIP.GetTime("MMdd");
		}
		
		return folderName;
	}

	/**
	 * 验证上传数据信息
	 * 
	 * @param driver
	 * @param rat
	 * @param dataType
	 * @param subDataType
	 * @param checkInfor
	 */
	private static void checkUpLoadResult(WebDriver driver, String rat,
			String DataType, String subDataType, String folderName,
			String[] checkInfor) {
		String currentTime = ZIP.GetTime("yyyyMMdd");
		if (checkInfor == null) {
			Assert.fail("数据上传正确性检查参数化表格值为空！");
		}
		//选择数据源
		if(IsIntgratedPacketUpLoad(DataType)){
			UpDataPage.selectRat(driver, rat);
			UpDataPage.selectDataType(driver, DataCenterConst.Equipment_Data,DataCenterConst.Config_Data);
		}else{
			DataCenterTask.selectUpLoadDataType(driver, rat, DataType, subDataType);
		}
		
		if (checkInfor.length == 1 && StringUtils.isBlank(checkInfor[0])) {
			Assert.fail("参数化表格验证信息为空！！");
		}
		//检查设备侧数据、DT数据信息
		if (StringUtils.equalsIgnoreCase(DataType,
				DataCenterConst.Equipment_Data)
				|| StringUtils.equalsIgnoreCase(DataType,
						DataCenterConst.DT_Data)||IsIntgratedPacketUpLoad(DataType)) {
			UpDataPage.clickFolderName(driver, folderName);
			UpDataPage.waitForMask(driver);
			String num = UpDataPage.getCurrentPageDataNum(driver);
			Assert.assertTrue("上传数据个数与预期不相符,[预期个数]" + checkInfor[0] + "[实际数量]"
					+ num, num.equalsIgnoreCase(checkInfor[0]));
			return;
		}
		String[] subDataTypes = subDataType.split(";");
		//子数据类型选择
		if (subDataTypes.length == 2) {
			UpDataPage.clickFolderName(driver, subDataTypes[1]);
			UpDataPage.waitForMask(driver);
		}
		// 检查数据名称是否存在
		for (String dataname : checkInfor) {
			String checkInformation = null;
			if (StringUtils.containsIgnoreCase(dataname, "当前日期")) {
				checkInformation = currentTime;
			} else if(StringUtils.containsIgnoreCase(dataname, "地图信息_")){
				String[] checkinformations = dataname.split("_");
				Assert.assertTrue("地图信息检查项配置信息不全！",checkinformations.length>1);
				for (int i = 1; i < checkinformations.length; i++) {
					Assert.assertFalse("地图信息检查项配置信息不全！",StringUtils.isBlank(checkinformations[i]));
					int realnum = UpDataPage.getUpLoadDataNumByName2(driver, checkinformations[i]);
					Assert.assertTrue("地图信息上传结果验证失败,不存在对应数据："+checkinformations[i],realnum>0);
				}
				return;
			}else {
				checkInformation = dataname;
			}
			int exsistNum = UpDataPage.getUpLoadDataNumByName(driver,
					checkInformation);
			if (exsistNum == 0) {
				Assert.fail("按照参数化表格对应数据不存在上传数据：" + checkInformation);
			}
			return;
		}
	}
	
	private static void checkParamPreview(WebDriver driver, String paramName){
		//搜素工参
		UpDataPage.isExsistFolder(driver, paramName);
		Assert.assertTrue("不存在工参"+paramName, UpDataPage.isExsistFolder(driver, paramName));
		String handles_0 = driver.getWindowHandle();
		UpDataPage.clickParamPreView(driver, paramName);
		String newHandle = CommonWD.getWindowHandle(driver, handles_0);
		if (newHandle == null) {
			Assert.fail("打开工参预览界面失败！");
		} else {
			driver.switchTo().window(newHandle);
			Pause.pause(1000);
		}
		LoadingPage.Loading(driver);
		Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
		LoadingPage.waitMask(driver, ".x-mask-msg.x-layer.x-mask-msg-default.x-border-box");
		UpDataPage.checkParamViewData(driver);
	}
	
	private static void checkParamPreview(WebDriver driver){
		Assert.assertTrue("当前环境无可选工参！", UpDataPage.getParamPreViewNum(driver)>0);
		String handles_0 = driver.getWindowHandle();
		UpDataPage.clickParamPreView(driver, 0);
		String newHandle = CommonWD.getWindowHandle(driver, handles_0);
		if (newHandle == null) {
			Assert.fail("打开工参预览界面失败！");
		} else {
			driver.switchTo().window(newHandle);
			Pause.pause(1000);
		}
		LoadingPage.Loading(driver);
		Assert.assertFalse("系统存在错误",UpDataPage.isErrorPage(driver));
		LoadingPage.waitMask(driver, ".x-mask-msg.x-layer.x-mask-msg-default.x-border-box");
		UpDataPage.checkParamViewData(driver);
	}

	/**
	 * 检查异常提示信息
	 * 
	 * @param driver
	 * @param errorInfor
	 * @param upOKInfor
	 */
	private static void checkErrorInfor(WebDriver driver, String errorInfor,
			String upOKInfor) {
		String realInfor = DataCenterTask.ErrorDetail(driver, upOKInfor,
				ErrorMAXTIME);
		LOG.info_aw("界面提示信息：[" + realInfor + "]");
		LOG.info_aw("预期提示信息：[" + errorInfor + "]");
		Assert.assertTrue("异常提示信息与文件中提示信息不一致\n界面提示信息：" + realInfor +"\n预期提示信息：" + errorInfor, realInfor.contains(errorInfor));

	}

	/**
	 * 检查数据上传默认设置参数
	 * 
	 * @param driver
	 * @param dataType
	 * @param subDataType
	 */
	private static void checkDefaultParam(WebDriver driver, String dataType,
			String subDataType) {

		if (!UpDataPage.checkDataTypeValueSame(driver, dataType)) {
			Assert.fail("数据上传界面数据类型默认选项不正确");
		}
		if (!UpDataPage.checkSubDataTypeValueSame(driver, subDataType)) {
			Assert.fail("数据上传界面子数据类型默认选项不正确");
		}

	}

	/**
	 * 检查上传指南默认显示和展开项信息
	 * 
	 * @param driver
	 * @param DataType
	 * @param subDataType
	 */
	private static void checkUploadGuideDisplay(WebDriver driver,
			String DataType, String subDataType) {
		LOG.info_aw("--检查上传指南的一致性");
		String expandSubDataType = null;
		String js = "return $('.current').text().trim();";
		String DataTypeDisplay = CommonJQ.excuteJStoString(driver, js);
		if (!StringUtils.equals(DataType, DataTypeDisplay)
				|| DataTypeDisplay == null) {
			Assert.fail("数据上传指南界面数据源类型显示不匹配！！[当前显示]:" + DataTypeDisplay
					+ "[预期显示]:" + DataType);
		}
		// 除工程参数外，其他检查标题
		if (StringUtils.containsIgnoreCase(DataType, DataCenterConst.EngParam) != true
				&& StringUtils.containsIgnoreCase(DataType,
						DataCenterConst.DT_Data) != true) {
			String jstitle = "return $('.tit.tit_on').text().trim();";
			expandSubDataType = CommonJQ.excuteJStoString(driver, jstitle);
			String[] subDataTypes2 = subDataType.split(";");
			String expectSubDatatype = null;
			if (subDataTypes2.length == 2) {
				expectSubDatatype = subDataTypes2[1];
			} else {
				expectSubDatatype = subDataTypes2[0];
			}
			if (!StringUtils.equals(expectSubDatatype, expandSubDataType)
					|| expandSubDataType == null) {

				Assert.fail("数据上传指南界面默认展开子数据类型不匹配！！[当前显示]:" + expandSubDataType
						+ "[预期显示]:" + expectSubDatatype);
			}
			String expandnum = "return $('.r_task_dbox_conn_pop.dis_none').filter(':visible').length;";
			int b = CommonJQ.excuteJStoInt(driver, expandnum);
			if (b != 1) {
				Assert.fail(expectSubDatatype + "：---对应项没有展开！！");
			}

		}

	}

	/**
	 * 上传界面点击选择文件夹
	 * 
	 * @param driver
	 * @param dataType
	 */
	private static void selectFolder2(WebDriver driver, String dataType,
			String folderName) {
		Set<String> handle2 = driver.getWindowHandles();
		String currentWindowID = driver.getWindowHandle();
		String script = "$('#selectfolder').click();";
		CommonJQ.setTimeout(driver, script, 2000);
		Pause.pause(2000);
		Set<String> handle3 = CommonWD.getAllHandle(driver, 3);

		for (String string : handle3) {
			if (!handle2.contains(string)) {
				driver.switchTo().window(string);
			}
		}
		UpDataPage.waitForMask(driver);
		CommonJQ.waitForElementVisble(driver, "tbody");

		if (!UpDataPage.isExsistFolder(driver, folderName)) {
			UpDataPage.createNewFolder(driver, folderName);
		}
		String selectJS = "$('span[title=\""
				+ folderName
				+ "\"]').parentsUntil('tr').filter('td').prev().find('.parasSelectRadio').click();";
		CommonJQ.excuteJS(driver, selectJS);
		UpDataPage.waitForMask(driver);
		int temp = 3;
		for (int i = 0; i < 100; i++) {
			if (temp == 3) {
				WebElement submitButton = driver.findElement(By
						.id("submitButton"));
				submitButton.click();
			} else if (temp == 2) {
				break;
			}
			Pause.pause(1000);
			temp = driver.getWindowHandles().size();
		}

		driver.switchTo().window(currentWindowID);
	}

	/**
	 * 检查参数化所需信息是否ＯＫ
	 * 
	 * @param infor
	 * @param dataPath
	 */
	private static void checkParam(String infor, String[] dataPath) {

		if (dataPath == null || StringUtils.isBlank(dataPath.toString())) {
			Assert.fail("验证" + infor + "数据上传无数据");
		}

	}

	/**
	 * 选择上传成功关闭主窗口
	 * 
	 * @param driver
	 */
	private static void selectCloseWindow(WebDriver driver) {
		LOG.info_aw("====设定上传完成后关闭上传界面====");

		if (!UpDataPage.isCloseWindowsChecked(driver)) {
			UpDataPage.clickClosePageInSuccessed(driver);
		}
		if (!UpDataPage.isCloseWindowsChecked(driver)) {
			UpDataPage.clickClosePageInSuccessed(driver);
		}

	}

	/**
	 * 选择数据上传数据类型
	 * 
	 * @param driver
	 * @param rat
	 * @param dataType
	 * @param subDataType
	 */
	private static void selectUpLoadDataType(WebDriver driver, String rat,
			String dataType, String subDataType) {
		UpDataPage.selectRat(driver, rat);
		String[] subDataTypes = subDataType.split(";");
		UpDataPage.selectDataType(driver, dataType, subDataTypes[0]);

	}

	/**
	 * 选择数据特性
	 * @param driver
	 * @param feature
	 */
	private static void selectFeature(WebDriver driver, String subDataType,
			String feature) {
		if (feature.equals("默认")) {
			return;
		}
		if (StringUtils.containsIgnoreCase(subDataType,
				DataCenterConst.PCIInterferenceMatrix)) {
			UpDataPage.selectFeatureOfPCI(driver, feature);
		} else if (StringUtils.containsIgnoreCase(subDataType,
				DataCenterConst.EngParam)) {
			UpDataPage.engineerParamFeatureSelect(driver, feature);
		} else {
			UpDataPage.selectFeature(driver, feature);
		}
	}

	/**
	 * 点击添加上传任务,处理界面异常
	 * 
	 * @param driver
	 */
	private static void addUploadTask(WebDriver driver) {
		LOG.info_aw("====点击添加上传任务，打开上传页面====");
		String handles_0 = driver.getWindowHandle();
		UpDataPage.clickAddUpLoadTaskBTN(driver);
		//CommonJQ.click(driver, UploadDataPage.BtnUploadData, true, "点击添加上传任务");
		// CommonWD.driverMax(driver);
		String newHandle = CommonWD.getWindowHandle(driver, handles_0);
//		System.out.println("newHandle:" + newHandle);
		if (newHandle == null) {
			Assert.fail("点击添加上传任务按钮打开数据上传界面失败");
		} else {
			driver.switchTo().window(newHandle);
			Pause.pause(1000);
//			LoadingPage.Loading(driver);
		}
		if (UpDataPage.isUpDataPageError(driver)) {
			Assert.fail("打开数据上传界面报错："
					+ UpDataPage.getUpLoadPageErrorInfor(driver));
		}
		boolean BatchUpIconflage = false;
		boolean BatchUpIconflage2 = false;
		for (int i = 0; i < 90; i++) {
			if (i % AddUpLoadTaskGaptime == 0) {
				BatchUpIconflage = ImageUtil
						.getImage("openFileChooseDialog.jpg");
				BatchUpIconflage2 = ImageUtil
						.getImage("openFileChooseDialog2.jpg");
				if (BatchUpIconflage || BatchUpIconflage2) {
					break;
				} else {
					boolean SecurityAlarmflage = ImageUtil
							.getImage("CBox_SecurityAlarm.jpg");
					boolean SecurityAlarmflageCN = ImageUtil
							.getImage("CBox_SecurityAlarmCN.jpg");
					boolean UpSuccesflage = ImageUtil
							.getImage("Btn_UpSucces.jpg");
					if (SecurityAlarmflage) {
						ImageUtil.clickImage("CBox_SecurityAlarm.jpg");
						Pause.pause(500);
						ImageUtil.clickImage("Btn_SecurityAlarm.jpg");
						Pause.pause(500);
						ImageUtil.clickImage("clickYesOnAlwaysTrust.jpg");
					}
					if (SecurityAlarmflageCN) {
						ImageUtil.clickImage("CBox_SecurityAlarmCN.jpg");
						Pause.pause(500);
						ImageUtil.clickImage("Btn_SecurityAlarmCN.jpg");
					}
					if (UpSuccesflage) {
						ImageUtil.clickImage("Btn_UpSucces.jpg");
						Pause.pause(500);
					}
				}
			}
			Pause.pause(1000);
		}
		if (BatchUpIconflage == false && BatchUpIconflage2 == false) {
			Assert.fail("数据上传Applet加载超时异常！！");
		}

	}

	/**
	 * 批量选择上传数据源
	 * 
	 * @param driver
	 * @param dataPath
	 */
	private static void selectUpDataSource(WebDriver driver,
			String subDataType, String[] dataPath) {
		LOG.info_aw("选择：-" + dataPath.length + "-条数据上传");
		for (int i = 0; i < dataPath.length; i++) {
			if (dataPath.length > 1 && i > 0) {
				ImageUtil.waitImage("Btn_AddIcon.jpg");
				Assert.assertTrue("点击添加按钮失败！！",
						ImageUtil.clickImage("Btn_AddIcon.jpg"));
				Pause.pause(500);
			}
			DataCenterTask.setUpDataSource(driver, subDataType, dataPath[i]);
		}

	}

	/**
	 * 选择数据源
	 * 
	 * @param driver
	 * @param path
	 */
	private static void setUpDataSource(WebDriver driver, String subDataType,
			String path) {
		LOG.info_aw("[上传数据路径]：==" + path + "==");
		ImageUtil.waitImage("openFileChooseDialog.jpg");
		Assert.assertTrue("点击选择数据源按钮失败！！",
				ImageUtil.clickImage("openFileChooseDialog.jpg", 90));
		Pause.pause(300);
		CommonFile.ChooseOneFile2(path);
		if (StringUtils.containsIgnoreCase(subDataType,
				DataCenterConst.EngParam)) {
			int i = 0;
			for (; i < ParamterCheckTime; i++) {
				if (i % ParamterCheckGapTime == 0) {
					boolean flag2 = ImageUtil.getImage("ParamterCheck.jpg");
					if (flag2) {
						ImageUtil.clickImage("ParamterCheck.jpg", 30);
						break;
					}
				}
				Pause.pause(1000);
			}
			if (i == ParamterCheckTime) {
				Assert.fail("工参核查超时！");
			}
			//验证工参校验是否有支持特性异常提示
			for (int j = 0; j < 5; j++) {
				if(ImageUtil.getImage("nosupportfeaturewarnming.png")){
					Assert.fail("上传工参选择的特性无特性支持当前匹配字段");
				}
				Pause.pause(1000);
			}
			
		}
	}

	/**
	 * 点击批量上传按钮
	 * 
	 * @param driver
	 */
	private static void clickBatchUpdataBTN(WebDriver driver) {
		ImageUtil.waitImage("Btn_BatchUpIcon.jpg");
		Assert.assertTrue("点击批量上传按钮失败！！",
				ImageUtil.clickImage("Btn_BatchUpIcon.jpg"));
		Pause.pause(2000);

	}

	/**
	 * 点击开始上传按钮
	 * 
	 * @param driver
	 */
	private static void clickStartUpdataBTN(WebDriver driver) {
		ImageUtil.waitImage("Btn_StartIcon.jpg");
		Assert.assertTrue("点击批量上传按钮失败！！",
				ImageUtil.clickImage("Btn_StartIcon.jpg"));
		Pause.pause(2000);
	}

	/**
	 * 获取异常提示信息
	 * 
	 * @param driver
	 * @return
	 */
	private static String ErrorDetail(WebDriver driver, String Information,
			int seconds) {
		LOG.info_aw("获取异常提示信息");
		String detailImageURL = "detail1.jpg";
		String uploadOKImageURL = "upload.jpg";
		String text = "";
		int i = 0;
		for (; i < seconds; i++) {
			Pause.pause(1000);
			if (i % ErrorChecktime == 0) {
				boolean flag = ImageUtil.getImage(uploadOKImageURL);
				if (flag) {
					Assert.fail("异常数据上传成功:" + Information);
				}
			}
			if (i % ErrorDetailChecktime == 0) {
				boolean flag2 = ImageUtil.getImage(detailImageURL);
				if (flag2) {
					break;
				}
			}

		}
		if (i == seconds) {
			Assert.fail("异常数据上传校验超时-耗时：" + seconds + "秒!!");
		}
		try {
			// Pause.pause(10*1000);
			Assert.assertTrue("点击详情信息失败", ImageUtil.clickImage(detailImageURL));
			Pause.pause(1000);
			ImageUtil.clickImage(detailImageURL, 0, 50);
			Pause.pause(1000);
			CommonFile.clickAcceleratorKey(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			Pause.pause(1000);
			CommonFile.clickAcceleratorKey(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
			Pause.pause(3 * 1000);
			text = CommonFile.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}


	/**
	 * 验证上传是否成功
	 * 
	 * @param driver
	 * @param Information
	 * @param seconds
	 */
	private static void assertUploadDataOk(WebDriver driver,
			String Information, int seconds) {

		int size = 2;
//		int checktime = 90;
		long starttime = System.currentTimeMillis();
		for (int i = 0; i < seconds; i++) {
			Pause.pause(1000);
			//内存溢出 解决,按秒检查
			boolean flag = ImageUtil.getImage("checkFailIcon.jpg");
			if (flag) {
				Assert.fail("上传失败:" + Information);
			}
			Set<String> handles_1 = driver.getWindowHandles();
			int size1 = handles_1.size();
			System.out.println("等待数据上传完成 …………" + i);
			System.out.println("现有窗口的个数……" + size1);
			if (size == (size1 + 1)) {
				CommonWD.switchToMainWindow(driver);
				return;
			}
		}
		long endtime = System.currentTimeMillis();
		int realtime = (int)((endtime-starttime)/1000);
		Assert.fail("上传超时,已耗时:"+String.valueOf(realtime)+"秒！" + Information);
	}

	/**
	 * 验证上传是否成功
	 * 
	 * @param driver
	 * @param Information
	 * @param seconds
	 */
	private static void assertUploadDataOkforConcurrentTest(WebDriver driver,
			String Information, int hour) {

		int size = 2;
		int times = hour * 6;
		for (int i = 0; i < times; i++) {
			Pause.pause(10 * 60 * 1000);

			Set<String> handles_1 = driver.getWindowHandles();
			int size1 = handles_1.size();
			System.out.println("等待数据上传完成 …………" + i);
			System.out.println("现有窗口的个数……" + size1);
			if (size == (size1 + 1)) {
				CommonWD.switchToMainWindow(driver);
				return;
			}
		}
		Assert.fail("上传失败:" + Information);
	}

	/**
	 * 选择上传数据文件夹
	 * 
	 * @param driver
	 * @param DataType
	 * @param subDataType
	 */
	private static void selectFolder(WebDriver driver, String DataType,
			String subDataType, String folderName) {
		String[] subDataTypes = subDataType.split(";");
		if (subDataTypes.length == 2) {
			UpDataPage.clickFolderName(driver, subDataTypes[1]);
			UpDataPage.waitForMask(driver);
			return;
		}
		if (StringUtils.equalsIgnoreCase(DataType,
				DataCenterConst.Equipment_Data)
				|| StringUtils.equalsIgnoreCase(DataType,
						DataCenterConst.DT_Data)) {

			if (!UpDataPage.isExsistFolder(driver, folderName)) {
				UpDataPage.createNewFolder(driver, folderName);
				UpDataPage.waitForMask(driver);
				UpDataPage.selectDataType(driver, DataType, subDataTypes[0]);
				UpDataPage.waitForMask(driver);
				Pause.pause(2000);
			}
			UpDataPage.clickFolderName(driver, folderName);
			UpDataPage.waitForMask(driver);
		}
		return;
	}

	//修正上传路径
	public static String[] reviseUpLoadPath(String[] dataPath) {
		String[] revisedPath = null;
		revisedPath = dataPath;
		if (revisedPath != null) {
			for (int i = 0; i < revisedPath.length; i++) {
				revisedPath[i] = revisedPath[i].replaceFirst("D:",
						ConstUrl.getRootPath() + "\\Data");
			}
		}
		return revisedPath;
	}
	
	public static void isAuthTipsDiv(WebDriver driver,boolean isAuth){
		Boolean tips = CommonJQ.isExisted(driver, "#noAuthTipsDiv",true);
		if (isAuth) {
			Assert.assertFalse("目前用户无对应项目的上传权限", tips);
		}else{
			Assert.assertTrue("目前用户有对应项目的上传权限", tips);
		}
	}
	
	

}
