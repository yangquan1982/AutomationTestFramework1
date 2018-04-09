package cloud_platform.task.upload_data;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.UploadDataPage;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import cloud_public.task.IndexTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.ImageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class PoorQualityThresholdIdentificationUploadTask {


	public static final int MAXTIME = 1000;// 正常数据上传最大时间：秒

	/**
	 * 数据上传跳转
	 * @param driver
	 */
	public static void JumpToUpLoad(WebDriver driver){
		
//		IndexPage.netUpdataMange(driver);//老界面
		IndexPage.netUpdataMange(driver);//新界面
	}
	/**
	 * 
	 * @param driver
	 * @param NetType:选择制式
	 * @param DataType:数据类型
	 * @param SubDataType:选择数据子类型
	 * @param FeatureType:特性选择
	 * @param dataPath:数据路径,支持多个
	 * @param folderName:文件夹
	 * @param projectName
	 */
	public static void dataSourceUpLoadUseCustomFolder(WebDriver driver,
			String NetType, String DataType, String SubDataType,
			String FeatureType, String[] dataPath, String folderName,
			String projectName) {
		//数据上传跳转
//		IndexPage.netUpdataMange(driver);//老界面
		IndexPage.netUpdataMange(driver);//新界面
		// 等待上传组件加载
		String WinID = PoorQualityThresholdIdentificationUploadTask.waitUpDataPage(driver, projectName);
		// 选择制式
		PoorQualityThresholdIdentificationUploadTask.NetType(driver, NetType);
		// 数据类型
		PoorQualityThresholdIdentificationUploadTask.DataType(driver, DataType);
		// 选择数据子类型
		if (SubDataType != null) {
			PoorQualityThresholdIdentificationUploadTask.SubDataType(driver,NetType, DataType, SubDataType);
		}

		// 特性选择
		if (FeatureType != null) {
			PoorQualityThresholdIdentificationUploadTask.FeatureType(driver, SubDataType, FeatureType);
		}

		// 上传位置
		if (folderName != null) {
			if("不需文件夹".equalsIgnoreCase(folderName.trim())){
				//不处理
			}else{
				PoorQualityThresholdIdentificationUploadTask.UpDir(driver, WinID, folderName);
			}
			
		}

		for (int i = 0; i < dataPath.length; i++) {
			if (i != 0) {
				boolean AddIconFlage = ImageUtil.clickImage("Btn_AddIcon.jpg");
				for (int j = 0; j < 10 && AddIconFlage == false; j++) {
					Pause.pause(1000);
					AddIconFlage = ImageUtil.clickImage("Btn_AddIcon.jpg");
				}
				if (AddIconFlage == false) {
					Assert.fail("超过10次,点击添加数据源图标失败");
				}
			}
			boolean OpenFileIconflage = ImageUtil
					.clickImage("Btn_OpenFileIcon.jpg");
			for (int j = 0; j < 10 && OpenFileIconflage == false; j++) {
				Pause.pause(1000);
				OpenFileIconflage = ImageUtil.clickImage("Btn_OpenFileIcon.jpg");
			}
			if (OpenFileIconflage == false) {
				Assert.fail("超过10次,点击添加数据源图标失败");
			}
			if (OpenFileIconflage) {
				CommonFile.ChooseOneFile2(dataPath[i]);
			}
			if ("工程参数".equalsIgnoreCase(DataType)) {
				boolean OKflage = ImageUtil.getImage("Btn_UploadOK.jpg");
				for (int i1 = 0; (i1 < 30 && OKflage == false); i1++) {
					if (OKflage) {
						break;
					} else {
						OKflage = ImageUtil.getImage("Btn_UploadOK.jpg");
						Pause.pause(1000);
					}
				}
				ImageUtil.clickImage("Btn_UploadOK.jpg");

			}
		}
		boolean BatchUpIconFlage = ImageUtil.clickImage("Btn_BatchUpIcon.jpg");
		for (int i = 0; i < 10 && BatchUpIconFlage == false; i++) {
			Pause.pause(1000);
			BatchUpIconFlage = ImageUtil.clickImage("Btn_BatchUpIcon.jpg");
		}
		if (BatchUpIconFlage == false) {
			Assert.fail("超过10次,点击批量上传图标失败");
		}
		Set<String> handles_1 = driver.getWindowHandles();
		System.out.println(ZIP.NowTime() + "handles_1.toArray().length:"
				+ handles_1.toArray().length);
		for (int i = 0; i < MAXTIME; i++) {
			if (handles_1.toArray().length == 1) {
				break;
			} else {
				Pause.pause(1000);
				boolean flag = ImageUtil.getImage("checkFailIcon.jpg");
				if (flag) {
					Assert.fail("上传失败，请查看截图");
				}
				if(i<3){
					ImageUtil.clickImage("Btn_BatchUpIcon.jpg");
				}
				System.out.println(ZIP.NowTime() + "waitting 1s," + i);
				handles_1 = driver.getWindowHandles();
			}

		}
		if (handles_1.toArray().length != 1) {
			Assert.fail("批量导入数据失败,超时时间:" + MAXTIME + "秒");
		}
	}

	/**
	 * 上传组件加载
	 * 
	 * @param driver
	 */
	public static String waitUpDataPage(WebDriver driver, String projectName) {
		LoadingPage.Loading(driver, "#uploadDataBtnText", "添加上传任务");
		IndexTask.changePrj(driver, projectName);
		System.out.println(ZIP.NowTime() + "添加数据上传任务");
		String WinID = UploadDataTask.waitPage_swithWinID(driver,
				UploadDataPage.BtnUploadData, false);
		boolean BatchUpIconflage = ImageUtil.getImage("Btn_BatchUpIcon.jpg");
		boolean SecurityAlarmflage = ImageUtil
				.getImage("CBox_SecurityAlarm.jpg");
		boolean UpSuccesflage = ImageUtil.getImage("Btn_UpSucces.jpg");
		for (int i = 0; i < 60; i++) {
			if (BatchUpIconflage) {
				break;
			} else {
				if (SecurityAlarmflage) {
					ImageUtil.clickImage("CBox_SecurityAlarm.jpg");
					try {
						Robot r = new Robot();
						r.keyPress(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_ENTER);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ImageUtil.clickImage("Btn_SecurityAlarm.jpg");
					ImageUtil.clickImage("clickYesOnAlwaysTrust.jpg");
				}
				if (UpSuccesflage) {
					ImageUtil.clickImage("Btn_UpSucces.jpg");
				}
				Pause.pause(1000);
				BatchUpIconflage = ImageUtil.getImage("Btn_BatchUpIcon.jpg");
				SecurityAlarmflage = ImageUtil
						.getImage("CBox_SecurityAlarm.jpg");
				UpSuccesflage = ImageUtil.getImage("Btn_UpSucces.jpg");
			}
		}
		if (!BatchUpIconflage) {
			Assert.fail("数据导入页面显示异常,请查看截图.");
		}
		return WinID;
	}

	/**
	 * 选择制式
	 * 
	 * @param driver
	 * @param NetType
	 *            :选择制式
	 */
	public static void NetType(WebDriver driver, String NetType) {

		CommonJQ.click(driver, UploadDataPage.CBoxCloseUploadWin, true,
				"上传完成之后自动关闭");
		// 选择制式{
		CommonJQ.click(driver, UploadDataPage.TextNetType, true);
		if ("UMTS".equalsIgnoreCase(NetType)) {
			CommonJQ.click(driver, UploadDataPage.ListNetType, "#UMTS", false,
					"制式:UMTS");
		} else if ("GSM".equalsIgnoreCase(NetType)) {
			CommonJQ.click(driver, UploadDataPage.ListNetType, "#GSM", false,
					"制式:GSM");
		} else if ("LTE".equalsIgnoreCase(NetType)) {
			CommonJQ.click(driver, UploadDataPage.ListNetType, "#LTE", false,
					"制式:LTE");
		} else if ("TDS".equalsIgnoreCase(NetType)) {
			CommonJQ.click(driver, UploadDataPage.ListNetType, "#TDS", false,
					"制式:TDS");
		} else if ("CDMA".equalsIgnoreCase(NetType)) {
			CommonJQ.click(driver, UploadDataPage.ListNetType, "#TDS", false,
					"制式:CDMA");
		}
	}

	/**
	 * 数据类型
	 * 
	 * @param driver
	 * @param DataType
	 *            :数据类型
	 */
	public static void DataType(WebDriver driver, String DataType) {
		// 选择数据类型
		CommonJQ.click(driver, UploadDataPage.TextDataType, true);

		if ("设备侧数据".equalsIgnoreCase(DataType)) {
			CommonJQ.click(driver, UploadDataPage.ListDataType, "#NE", false,
					"设备侧数据");
		} else if ("路测数据".equalsIgnoreCase(DataType)) {
			CommonJQ.click(driver, UploadDataPage.ListDataType, "#dt", false,
					"路测数据");
		} else if ("工程参数".equalsIgnoreCase(DataType)) {
			CommonJQ.click(driver, UploadDataPage.ListDataType, "#paras",
					false, "工程参数");
		} else if ("其它数据".equalsIgnoreCase(DataType)) {
			CommonJQ.click(driver, UploadDataPage.ListDataType, "#other",
					false, "其它数据");
		} else if ("综合数据包".equalsIgnoreCase(DataType)) {
			CommonJQ.click(driver, UploadDataPage.ListDataType, "#oneKey",
					false, "综合数据包");
		} else if ("北向数据".equalsIgnoreCase(DataType)) {
			CommonJQ.click(driver, UploadDataPage.ListDataType, "#othervendor",
					false, "北向数据");
		}
	}

	/**
	 * 数据子类型
	 * 
	 * @param driver
	 * @param DataType
	 *            :数据类型
	 * @param SubDataType
	 *            :数据子类型
	 */
	public static void SubDataType(WebDriver driver, String NetType, String DataType,
			String SubDataType) {
		// 选择数据子类型

		if ("设备侧数据".equalsIgnoreCase(DataType)) {
			if (SubDataType != null) {
				DataCfgSubType(driver, SubDataType);
			}
		} else if ("其它数据".equalsIgnoreCase(DataType)) {
			if (SubDataType != null) {
				DataOtherSubType(driver,NetType, SubDataType);
			}
		} else if ("综合数据包".equalsIgnoreCase(DataType)) {
			if (SubDataType != null) {
				DataComplexSubType(driver, SubDataType);
			}
		} else if ("北向数据".equalsIgnoreCase(DataType)) {
			if (SubDataType != null) {
				DataVendorSubType(driver, SubDataType);
			}
		}
	}

	/**
	 * 特性选择
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性选择
	 */
	public static void FeatureType(WebDriver driver, String SubDataType,
			String FeatureType) {

		if ("SIG数据".equalsIgnoreCase(SubDataType)) {
			if (FeatureType != null) {
				DataSigFeatureType(driver, FeatureType);
			}
		} else if ("工程参数".equalsIgnoreCase(SubDataType)) {
			if (FeatureType != null) {
				DataEnParaFeatureType(driver, FeatureType);
			}
		} else if ("PCI干扰矩阵".equalsIgnoreCase(SubDataType)) {
			if (FeatureType != null) {
				DataPCIFeatureType(driver, FeatureType);
			}
		} else if ("P3CDR数据".equalsIgnoreCase(SubDataType)) {
			if (FeatureType != null) {
				DataP3CDRFeatureType(driver, FeatureType);
			}
		} else {
			if (FeatureType != null) {
				DataAllFeatureType(driver, FeatureType);
			}
		}
	}

	/**
	 * 上传位置
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性选择
	 */
	public static void UpDir(WebDriver driver, String WinID, String UpDir) {

		boolean flage = CommonJQ.isExisted(driver, "#selectfolder",true);
		if(flage){
			UploadDataTask.waitPage_swithWinID(driver,
					"$('#selectfolder').filter(':visible').click()", true);
			GetDataByTypePage.searchInChoosePage(driver, UpDir);
			boolean flage2 = CommonJQ.isExisted(driver, "span[title=\"" + UpDir
					+ "\"]");
			if (flage2 == false) {
				CommonJQ.click(driver, UploadDataPage.BtnCreateFile, true);
				CommonJQ.value(driver, UploadDataPage.TextAddFile, UpDir, true);
				CommonJQ.click(driver, UploadDataPage.BtnSubmit, true);
			}
			if (CommonJQ.isExisted(driver, GetDataByTypePage.ListBox,
					GetDataByTypePage.InputVisible)) {
				GetDataByTypePage.clickRidoBtn(driver, UpDir);
			} else {
				GetDataByTypePage.clickcheckboxs(driver, UpDir);
			}
			SwitchDriver.switchToWinID(driver, WinID);	
		}
	}

	/**
	 * 设备侧数据->数据子类型
	 * 
	 * @param driver
	 * @param DataType
	 *            :数据类型
	 * @param SubDataType
	 *            :数据子类型
	 */
	public static void DataCfgSubType(WebDriver driver, String SubDataType) {
		// 选择数据子类型

		CommonJQ.click(driver, UploadDataPage.TextSubDataType, true);
		if ("配置数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#CONF",
					false, "配置数据");
		} else if ("话统数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#PFM",
					false, "话统数据");
		} else if ("PCHR数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#PCHR",
					false, "PCHR数据");
		} else if ("MR数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#MR",
					false, "MR数据");
		} else if ("告警数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#ALARM",
					false);
		} else if ("License数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#LICENSE",
					false);
		} else if ("Nodeb 配置数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#nodebconf", false);
		} else if ("Nodeb 话统数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#nodebpfm",
					false);
		} else if ("Nodeb CHR数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#nodebchr",
					false);
		} else if ("Nodeb M2000话统数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#nodebm2000pfm", false);
		} else if ("Nodeb 告警数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#nodebalarm", false);
		} else if ("Nodeb License数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#nodeblicense", false);
		} else if ("Nodeb MML数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#nodebmml",
					false);
		} else if ("操作日志".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#OPTLOG",
					false);
		} else if ("SIG数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#EXTERIORLOG", false);
		} else if ("反向频谱扫描CHR数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#CELLRICCHR", false);
		} else {
			Assert.fail("设备侧数据->数据子类型，选择数据子类型错误：" + SubDataType);
		}

	}

	/**
	 * 其它数据->数据子类型
	 * 
	 * @param driver
	 * @param DataType
	 *            :数据类型
	 * @param SubDataType
	 *            :数据子类型
	 */
	public static void DataOtherSubType(WebDriver driver,String NetType, String SubDataType) {
		// 选择数据子类型

		CommonJQ.click(driver, UploadDataPage.TextSubDataType, true);
		if ("勘测图片".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#sitesurvey", false);
		} else if ("速率截图".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#ratescreenshot", false);
		} else if ("扩展工参".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#parasext",
					false);
		} else if ("网格数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#grid",
					false);
		} else if ("KPI月报".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#netkpimonthly", false);
		} else if ("KPI周报".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#netkpi",
					false);
		} else if ("KQI月报".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#netkqimonthly", false);
		} else if ("RSSI数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#rssi",
					false, "RSSI数据");
		} else if ("话统数据(CSV格式)".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#pfmcsv",
					false, "话统数据(CSV格式)");
		} else if ("室分图片".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#indoorpic", false, "室分图片");
		} else if ("地图信息".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#mapinfo",
					false, "地图信息");
		} else if ("MML报文".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#ltemml",
					false, "MML报文");
		} else if ("POLYGON数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#polygon",
					false, "POLYGON数据");
		} else if ("电子地图".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#emap",
					false, "电子地图");
		} else if ("自定义数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#custom",
					false, "自定义数据");
		} else if ("PCI干扰矩阵".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#interferencematrix", false, "PCI干扰矩阵");
		} else if ("PRS话统".equalsIgnoreCase(SubDataType)) {
			if("LTE".equalsIgnoreCase(NetType)){
				CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#prspfm",
						false, "PRS话统");	
			}else if("UMTS".equalsIgnoreCase(NetType)){
				CommonJQ.click(driver, UploadDataPage.ListSubDataType,
						"#umtsprspfm", false, "PRS话统");
			}else{
				Assert.fail("制式选择错误，NetType："+NetType);
			}

		}  else if ("CSV报告".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#csvreport", false, "CSV报告");
		} else if ("LTE特征库".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#tdd",
					false, "LTE特征库");
		} else if ("OTT 数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#ott",
					false, "OTT 数据");
		} else if ("PA路测数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#padt",
					false, "PA路测数据");
		} else if ("P3CDR数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#p3cdr",
					false, "P3CDR数据");
		} else if ("运营商频点信息".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#operfreqinfo", false, "运营商频点信息");
		} else if ("UMTS M2000话统".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#umtsm2000pfm", false, "UMTS M2000话统");
		} else if ("UMTS特征库".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#umtstdd",
					false, "UMTS特征库");
		} else if ("SEQ 非加密视频话单".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#seqvideo",
					false, "SEQ 非加密视频话单");
		} else if ("SEQ 加密视频话单".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#seqencryptvideo",
					false, "SEQ 加密视频话单");
		} else if("PCAP 数据".equals(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#pcap",
					false, "PCAP 数据");
		} else if("L3 Message 数据".equals(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#l3message",
					false, "L3 Message 数据");
		} else if("UE Log".equals(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#uelog",
					false, "UE Log");
		} else if("Scanner 数据".equals(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#scanner",
					false, "Scanner 数据");
		}
		else {
			Assert.fail("其它数据->数据子类型，选择数据子类型错误：" + SubDataType);
		}

	}

	/**
	 * 综合数据包->数据子类型
	 * 
	 * @param driver
	 * @param DataType
	 *            :数据类型
	 * @param SubDataType
	 *            :数据子类型
	 */
	public static void DataComplexSubType(WebDriver driver, String SubDataType) {
		// 选择数据子类型

		CommonJQ.click(driver, UploadDataPage.TextSubDataType, true);
		if ("多种数据源(NIC采集)".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#NIC",
					false, "多种数据源(NIC采集)");
		} else if ("一键式日志".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#OCL",
					false, "一键式日志");
		} else if ("多种数据源手动采集".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#MANUAL",
					false, "多种数据源手动采集");
		} else if ("单验多种类型数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType, "#multiple",
					false, "单验多种类型数据");
		} else {
			Assert.fail("综合数据包->数据子类型，选择数据子类型错误：" + SubDataType);
		}
	}

	/**
	 * 北向数据->数据子类型
	 * 
	 * @param driver
	 * @param DataType
	 *            :数据类型
	 * @param SubDataType
	 *            :数据子类型
	 */
	public static void DataVendorSubType(WebDriver driver, String SubDataType) {
		// 选择数据子类型

		CommonJQ.click(driver, UploadDataPage.TextSubDataType, true);
		if ("邻区关系CSV数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#othervendorconf", false, "邻区关系CSV数据");
		} else if ("北向MR数据".equalsIgnoreCase(SubDataType)) {
			CommonJQ.click(driver, UploadDataPage.ListSubDataType,
					"#othervendormr", false, "北向MR数据");
		} else {
			Assert.fail("北向数据->数据子类型，选择数据子类型错误：" + SubDataType);
		}
	}

	/**
	 * 数据子类型:Sig数据->特性
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性
	 */
	public static void DataSigFeatureType(WebDriver driver, String FeatureType) {
		// 选择数据子类型
		CommonJQ.click(driver, UploadDataPage.TextFeatureType, true);
		if ("TopN小区处理,网络评估".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType, "#-1", true);
		} else if ("深度覆盖,随时随地xMbps".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"41,43\"]", true);
		} else if ("覆盖评估,价值区域评估".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"93,94\"]", true);
		} else if ("VoLTE覆盖评估".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"107\"]", true);
		} else if ("PCI优化".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"45\"]", true);
		} else if ("特性规划设计".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"103\"]", true);
		} else if ("高精度网络覆盖精准评估及基于覆盖的精准规划".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"54\"]", true);
		} else if ("视频评估与规划".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType,
					"li[id=\"101\"]", true);
		} else {
			Assert.fail("数据子类型:Sig数据->特性，选择特性类型错误：" + FeatureType);
		}
	}

	/**
	 * 数据子类型:工程参数->特性
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性
	 */
	public static void DataEnParaFeatureType(WebDriver driver,
			String FeatureType) {
		// 选择数据子类型

		if (StringUtils.equalsIgnoreCase(FeatureType, "全特性")) {
			return;
		}
		CommonJQ.click(driver, "#ext-gen1023", true, "工程参数特性选择下拉框");
		LoadingPage.Loading(driver, "#boundlist-1010-listEl", "工程参数特性选择下拉框");
		String[] Type = FeatureType.split(";");
		for (int i = 0; i < Type.length; i++) {
			String FeatureTypeEN = Type[i];
			if ("ATU数据自动分析".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Automatic Analysis of ATU Data";
			} else if ("单站验证".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Single-Site Verification";
			} else if ("DT数据自动分析".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Automatic Analysis of DT Data";
			} else if ("TopN小区处理".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "TopN Cell Processing";
			} else if ("规划类参数核查".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Planning-related Parameter Check";
			} else if ("射频通道检查".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "RF Channel Check";
			} else if ("邻区核查与优化".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Neighboring Cell Check and Optimization";
			} else if ("室分测试报告".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Indoor Test Report";
			} else if ("随时随地xMbps".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Every Where xMbps";
			} else if ("深度覆盖".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "In-Depth Coverage";
			} else if ("PCI优化".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "PCI Optimization";
			} else if ("单站验证（海外）".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Single-Site Verification(Overseas)";
			} else if ("簇优化分析".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Cluster optimization analysis";
			} else if ("网络评估".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "NetWork Audit";
			} else if ("3D Asp".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "3D Asp";
			} else if ("智能终端单站验证".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Smart PHU Single-Site Verification";
			} else if ("高精度网络覆盖精准评估及基于覆盖的精准规划".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "High Precision Depth Coverage";
			} else if ("智能终端簇优化".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Smart PHU Cluster Optimization";
			} else if ("精准规划".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Accurate Planning";
			} else if ("覆盖预测".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Predictions";
			} else if ("容量仿真".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Simulations";
			} else if ("ACP".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "ACP";
			} else if ("覆盖评估".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Coverage Evaluation";
			} else if ("价值区域评估".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Value Area Evaluation";
			} else if ("DT数据自动分析（海外）".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "intlcluster";
			} else if ("业务与话务预测".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Service and Traffic Forecast";
			} else if ("热点体验规划".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Valuable Area Experience Planning";
			} else if ("路测分析".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Driver Test Analyze";
			} else if ("视频评估与规划".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Video Evaluation and Planning";
			} else if ("排名提升规划".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Benchmark Planning";
			} else if ("特性规划设计".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "DL CoMP Cluster Design";
			} else if ("VoLTE覆盖评估".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "VoLTE Coverage Evaluation";
			} else if ("视频洞察".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Video Insight";
			} else if ("排名提升优化".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Benchmark Optimization";
			} else if ("端到端精准规划".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "E2E Accurate network Planning";
			} else if ("容量评估".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Capacity Evaluate";
			} else if ("价值区域评估".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "Value Area Evaluation";
			} else if ("工参核查".equalsIgnoreCase(Type[i])) {
				FeatureTypeEN = "EP Matching";
			} else {
				Assert.fail("数据子类型:工程参数->特性，选择特性类型错误：" + Type[i]);
			}
			String js1 = "$('#boundlist-1010-listEl').find('div').filter(function(){return $(this).text()=='"
					+ Type[i] + "'})";
			if ("CN".equalsIgnoreCase(ConstUrl.language)) {
				js1 = "$('#boundlist-1010-listEl').find('div').filter(function(){return $(this).text()=='"
						+ Type[i] + "'})";
			} else {
				js1 = "$('#boundlist-1010-listEl').find('div').filter(function(){return $(this).text()=='"
						+ FeatureTypeEN + "'})";
			}
			String jsclick = js1 + ".find('input')";
			CommonJQ.click(driver, jsclick, "当前环境工参数据无[" + FeatureType
					+ "]可选择!");
		}
		CommonJQ.click(driver, "#ext-gen1023", true, "工程参数特性选择下拉框");
	}
	/**
	 * 数据子类型:PCI干扰矩阵->特性
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性
	 */
	public static void DataPCIFeatureType(WebDriver driver, String FeatureType) {
		// 选择数据子类型
		CommonJQ.click(driver, "#textpci", true);
		if ("干扰矩阵csv数据".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, "#mshowpci", "#interferencematrix", true);
		} else if ("sig数据".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, "#mshowpci", "#interferencematrixsig", true);
		} else {
			Assert.fail("数据子类型:PCI干扰矩阵->特性，选择特性类型错误：" + FeatureType);
		}
	}
	/**
	 * 数据子类型:PP3CDR数据->特性
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性
	 */
	public static void DataP3CDRFeatureType(WebDriver driver, String FeatureType) {
		// 选择数据子类型
		CommonJQ.click(driver, UploadDataPage.TextFeatureType, true);
		if("全特性".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType, "#-1", true);
		} else if ("排名提升规划,排名提升优化".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType, "li[id=\"102,112\"]", true);
		} else {
			Assert.fail("数据子类型:P3CDR数据->特性，选择特性类型错误：" + FeatureType);
		}
	}
	/**
	 * 数据子类型:Sig数据->特性
	 * 
	 * @param driver
	 * @param FeatureType
	 *            :特性
	 */
	public static void DataAllFeatureType(WebDriver driver, String FeatureType) {
		// 选择数据子类型
		CommonJQ.click(driver, UploadDataPage.TextFeatureType, true);
		if ("全特性".equalsIgnoreCase(FeatureType)) {
			CommonJQ.click(driver, UploadDataPage.ListFeatureType, "#-1", true);
		} else {
			Assert.fail("数据子类型:Sig数据->特性，选择特性类型错误：" + FeatureType);
		}
	}


}
