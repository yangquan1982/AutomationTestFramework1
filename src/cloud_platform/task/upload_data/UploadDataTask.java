package cloud_platform.task.upload_data;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.UploadDataPage;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.ImageUtil;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class UploadDataTask {

	public static void BatchUpData(WebDriver driver, String defaultWindowID,
			 String FileName, String[] NetType,
			String[] DataType, String[] SubDataType, String[] FeatureType,
			String[] UpDir, String[] filePath, int times) {

		if (CommonJQ.isExisted(driver, "#dataUpload", true)) {
			driver.findElement(By.id("dataUpload"))
					.findElement(By.tagName("a")).click();
		} else {
			if (CommonJQ.isExisted(driver, UploadDataPage.BtnUpLoad)) {
				CommonJQ.click(driver, UploadDataPage.BtnUpLoad, true);
			}
		}
		LoadingPage.Loading(driver);
		CommonJQ.click(driver, "a:contains(\"" + NetType[0] + "\")", true);
		CommonJQ.click(driver, UploadDataPage.BtnCfg, true);
		CommonJQ.click(driver, UploadDataPage.BtnCfg, true);
		LoadingPage.Loading(driver,UploadDataPage.BtnCreateFile);
	    CommonJQ.value(driver, "input[name=\"clusterName\"]", FileName);
	    CommonJQ.click(driver,"#shcBtn", false);
	    LoadingPage.Loading(driver);
		boolean flage = CommonJQ.isExisted(driver, "span[title=\"" + FileName
				+ "\"]",true);
		for(int i=0;i<3&&(flage==false);i++){
			flage = CommonJQ.isExisted(driver, "span[title=\"" + FileName
					+ "\"]",true);
			if(flage){
				break;
			}else{
				Pause.pause(1000);
			}
		}
		if (flage) {
			CommonJQ.click(driver, "span[title=\"" + FileName + "\"]", true);
		} else {
			CommonJQ.click(driver, UploadDataPage.BtnCreateFile, true);
			CommonJQ.value(driver, UploadDataPage.TextAddFile, FileName, true);
			CommonJQ.click(driver, UploadDataPage.BtnSubmit, true);
			CommonJQ.click(driver, "span[title=\"" + FileName + "\"]", true);
		}
		String WinID = UploadDataTask.waitUpDataPage(driver);
		CommonJQ.click(driver, UploadDataPage.CBoxCloseUploadWin, true);
		// 选择制式
		for (int i = 0; i < NetType.length; i++) {
			CommonJQ.click(driver, UploadDataPage.TextNetType, true);
			if ("UMTS".equalsIgnoreCase(NetType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListNetType, "#UMTS",
						false);
			} else if ("GSM".equalsIgnoreCase(NetType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListNetType, "#GSM", false);
			} else if ("LTE".equalsIgnoreCase(NetType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListNetType, "#LTE", false);
			} else if ("TDS".equalsIgnoreCase(NetType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListNetType, "#TDS", false);
			}

			// 选择数据类型
			CommonJQ.click(driver, UploadDataPage.TextDataType, true);
			if ("NE".equalsIgnoreCase(DataType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListDataType, "#NE", false);
			} else if ("路测数据".equalsIgnoreCase(DataType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListDataType, "#dt", false);
			} else if ("paras".equalsIgnoreCase(DataType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListDataType, "#paras",
						false);
			} else if ("other".equalsIgnoreCase(DataType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListDataType, "#other",
						false);
			} else if ("oneKey".equalsIgnoreCase(DataType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListDataType, "#oneKey",
						false);
			} else if ("othervendor".equalsIgnoreCase(DataType[i])) {
				CommonJQ.click(driver, UploadDataPage.ListDataType,
						"#othervendor", false);
			}

			// 选择数据子类型
			if (SubDataType != null) {
				if ("NE".equalsIgnoreCase(DataType[i])) {
					if(SubDataType[i] != null){
						CommonJQ.click(driver, UploadDataPage.TextSubDataType, true);
						if ("cfg".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#CONF", false);
						} else if ("PFM".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#PFM", false);
						} else if ("PCHR".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#PCHR", false);
						} else if ("MR".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#MR", false);
						} else if ("ALARM".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#ALARM", false);
						} else if ("LICENSE".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#LICENSE", false);
						} else if ("nodebconf".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodebconf", false);
						} else if ("nodebpfm".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodebpfm", false);
						} else if ("nodebchr".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodebchr", false);
						} else if ("nodebm2000pfm".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodebm2000pfm", false);
						} else if ("nodebalarm".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodebalarm", false);
						} else if ("nodeblicense".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodeblicense", false);
						} else if ("nodebmml".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#nodebmml", false);
						} else if ("OPTLOG".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#OPTLOG", false);
						} else if ("Sig".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#EXTERIORLOG", false);
						} else if ("CellRicCHR".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#CELLRICCHR", false);
						} else if ("NorthMR".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#NORTHMR", false);
						} else if ("MmeCHR".equalsIgnoreCase(SubDataType[i])) {
							CommonJQ.click(driver, UploadDataPage.ListSubDataType,
									"#MMECHR", false);
						} else {
							Assert.fail("选择数据子类型错误.");
						}	
					}

				} else if ("other".equalsIgnoreCase(DataType[i])) {
					CommonJQ.click(driver, UploadDataPage.TextSubDataType, true);
					if ("勘测图片".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#sitesurvey", false);
					} else if ("速率截图".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#ratescreenshot", false);
					} else if ("扩展工参".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#parasext", false);
					} else if ("网格数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#grid", false);
					} else if ("KPI月报".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#netkpimonthly", false);
					} else if ("KPI周报".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#netkpi", false);
					}else if ("KQI月报".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#netkqimonthly", false);
					} else if ("RSSI数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#rssi", false);
					} else if ("话统数据(CSV格式)".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#pfmcsv", false);
					} else if ("室分图片".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#indoorpic", false);
					} else if ("地图信息".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#mapinfo", false);
					} else if ("MML报文".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#ltemml", false);
					} else if ("POLYGON数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#polygon", false);
					} else if ("电子地图".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#emap", false);
					} else if ("自定义数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#custom", false);
					} else if ("PCI干扰矩阵".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#interferencematrix", false);
					} else if ("PRS话统".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#prspfm", false);
					} else if ("CSV报告".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#csvreport", false);
					} else if ("LTE特征库".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#tdd", false);
					} else if ("OTT 数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#ott", false);
					} else if ("PA路测数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#padt", false);
					} else if ("P3CDR数据".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#p3cdr", false);
					} else if ("运营商频点信息".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#operfreqinfo", false);
					} else if ("UMTS PRS话统".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#umtsprspfm", false);
					} else if ("UMTS M2000话统".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#umtsm2000pfm", false);
					} else if ("UMTS特征库".equalsIgnoreCase(SubDataType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListSubDataType,
								"#umtstdd", false);
					} else {
						Assert.fail("选择数据子类型错误.");
					}
				}

			}
			// 特性选择
			if (FeatureType != null) {
				CommonJQ.click(driver, UploadDataPage.TextFeatureType, true);
				if ("Sig".equalsIgnoreCase(SubDataType[i])){
					if ("网络评估".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"#-1", true);
					} else if ("TopN小区处理".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"#-1", true);
					} else if ("随时随地xMbps".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"41,43\"]", true);
					} else if ("深度覆盖".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"41,43\"]", true);
					} else if ("覆盖评估".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"93,94\"]", true);
					} else if ("价值区域评估".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"93,94\"]", true);
					}  else if ("VoLTE覆盖评估".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"107\"]", true);
					} else if ("PCI优化".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"45\"]", true);
					} else if ("特性规划设计".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"103\"]", true);
					} else if ("高精度深度覆盖".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"li[id=\"54\"]", true);
					} else{
						Assert.fail("选择特性选择错误.");
					}
				}else{
					if ("All".equalsIgnoreCase(FeatureType[i])) {
						CommonJQ.click(driver, UploadDataPage.ListFeatureType,
								"#-1", true);
					} else{
						Assert.fail("选择特性选择错误.");
					}	
				}
			}
			// UpDir
			if (UpDir != null) {
				if(UpDir[i] != null){
					UploadDataTask.waitPage_swithWinID(driver, "$('#selectfolder').filter(':visible').click()",true);
					GetDataByTypePage.searchInChoosePage(driver, UpDir[i]);
					boolean flage2 = CommonJQ.isExisted(driver, "span[title=\""
							+ UpDir[i] + "\"]");
					if (flage2 == false) {
						CommonJQ.click(driver, UploadDataPage.BtnCreateFile, true);
						CommonJQ.value(driver, UploadDataPage.TextAddFile,
								UpDir[i], true);
						CommonJQ.click(driver, UploadDataPage.BtnSubmit, true);
					}
					if (CommonJQ.isExisted(driver, GetDataByTypePage.ListBox,
							GetDataByTypePage.InputVisible)) {
						GetDataByTypePage.clickRidoBtn(driver, UpDir[i]);
					} else {
						GetDataByTypePage.clickcheckboxs(driver, UpDir[i]);
					}
					SwitchDriver.switchToWinID(driver, WinID);
				}
				

			}

			boolean OpenFileIconflage = ImageUtil
					.clickImage("Btn_OpenFileIcon.jpg");
			if (OpenFileIconflage) {
				CommonFile.ChooseOneFile2(filePath[i]);
			}
			if ("paras".equalsIgnoreCase(DataType[i])) {
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
			if (i < NetType.length - 1) {
				boolean AddIconFlage = ImageUtil.clickImage("Btn_AddIcon.jpg");
				for(int j = 0;j<10&&AddIconFlage==false;j++){
					Pause.pause(1000);
					AddIconFlage = ImageUtil.clickImage("Btn_AddIcon.jpg");
				}
				if(AddIconFlage==false){
					Assert.fail("超过10次,点击添加数据源图标失败");
				}
			}

		}
		boolean BatchUpIconFlage = ImageUtil.clickImage("Btn_BatchUpIcon.jpg");
		for(int i = 0;i<10&&BatchUpIconFlage==false;i++){
			Pause.pause(1000);
			BatchUpIconFlage = ImageUtil.clickImage("Btn_BatchUpIcon.jpg");
		}
		if(BatchUpIconFlage==false){
			Assert.fail("超过10次,点击批量上传图标失败");
		}
		Set<String> handles_1 = driver.getWindowHandles();
		System.out.println(ZIP.NowTime() + "handles_1.toArray().length:"
				+ handles_1.toArray().length);
		for (int i = 0; i < times; i++) {
			if (handles_1.toArray().length == 1) {
				break;
			} else {
				Pause.pause(1000);
				boolean flag = ImageUtil.getImage("checkFailIcon.jpg");
				if (flag) {
					Assert.fail("上传失败");
				}
				System.out.println(ZIP.NowTime() + "waitting 1s," + i);
				handles_1 = driver.getWindowHandles();
			}

		}
		if (handles_1.toArray().length != 1) {
			Assert.fail("批量导入数据失败,超时时间:"+times+"秒");
		}
		SwitchDriver.switchToWinID(driver, defaultWindowID);

	}

	/**
	 * 
	 * @param driver
	 */
	public static String waitUpDataPage(WebDriver driver) {

		System.out.println(ZIP.NowTime() +"添加数据上传任务");
		String WinID = UploadDataTask.waitPage_swithWinID(driver,
				UploadDataPage.BtnUploadData,false);
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
	 * 
	 * @param driver
	 */
	public static String waitPage_swithWinID(WebDriver driver, String selector,boolean timoutFlage) {

		System.out.println(ZIP.NowTime() +"选择上传位置 ");
		String WinID = "";
		Set<String> handles_0 = driver.getWindowHandles();
		// ff
		
		if(timoutFlage &&selector.startsWith("$")){
			CommonJQ.setTimeout(driver, selector, 1000);
		}else{
			CommonJQ.click(driver, selector, true);
		}
		
		Set<String> handles_1 = driver.getWindowHandles();

		for (int i = 0; i < 10; i++) {
			if ((handles_0.size() != handles_1.size())
					&& (handles_1.size() != 0)) {
				System.out.println(ZIP.NowTime()
						+ "open model window success,handles_1.size():"
						+ handles_1.size());
				for (String s : handles_1) {
					if (s.equals("")) {
						System.out.println(ZIP.NowTime()
								+ "open model window fail,handles_1 is null");
						Pause.pause(1000);
						handles_1 = driver.getWindowHandles();
						continue;
					}
				}
				break;
			} else {
				Pause.pause(1000);
				handles_1 = driver.getWindowHandles();
				continue;
			}
		}
		if (handles_0.size() == handles_1.size()) {
			Assert.fail("open model window fail");
		}
		for (String s : handles_1) {
			if (handles_0.contains(s)) {
				continue;
			} else if (s.equals("")) {
				continue;
			} else {
				SwitchDriver.switchToWinID(driver, s);
				for (int i = 0; i < 10; i++) {
					if (driver.getWindowHandle().equals(s)) {
						WinID = s;
						break;
					} else {
						SwitchDriver.switchToWinID(driver, s);
						Pause.pause(1000);
					}
				}
				LoadingPage.Loading(driver);
				System.out.println(ZIP.NowTime() + "switch to window[" + s
						+ "] successfully!");
				break;
			}
		}
		return WinID;
	}

	/**
	 * 获取界面文件夹的当前时间
	 * 
	 * @param driver
	 * @param uploadFolder
	 * @param loginUser
	 * @return
	 */
	public static String getMessage(WebDriver driver, String uploadFolder,
			String loginUser) {
		String timeFoder = "";
		String script = "return  $(\"#deviceList\").text()";
		String folderContent = CommonJQ.excuteJStoString(driver, script);

		String content = getStr(folderContent, uploadFolder, loginUser);
		System.out.println(uploadFolder + "====" + loginUser);
		System.out.println(folderContent + "界面获取的内容为,处理后的内容为" + content);

		if (content.contains(uploadFolder) && content.contains(loginUser)) {
			System.out.print("包含了");
			timeFoder = getTimeStartSeat(content, loginUser);
		}
		return timeFoder;
	}

	private static String getStr(String folderContent, String uploadFolder,
			String loginUser) {
		String[] contentList = folderContent.split(" ");
		for (int i = 0; i < contentList.length; i++) {
			if (contentList[i].contains(uploadFolder)
					&& contentList[i].contains(loginUser)) {
				String str = contentList[i + 1].substring(0,
						contentList[i + 1].lastIndexOf(":") + 3);
				return contentList[i] + " " + str;
			}
		}
		return null;
	}

	/**
	 * 处理字符串
	 * 
	 * @param folderContent
	 * @param loginUser
	 * @param loginUser2
	 * @return
	 */
	public static String getTimeStartSeat(String content, String loginUser) {
		int seatInt = content.lastIndexOf(loginUser);
		int userStrInt = loginUser.length();
		int c = seatInt + userStrInt;
		System.out.print("c的值为:" + c);
		return content.substring(c);
	}

	public static void assertUploadOK(String timeBeforeUpload,
			String timeAfterUpload) {
		if (!timeBeforeUpload.trim().equals(timeAfterUpload.trim())) {
			LOG.info_testcase("重复数据上传时，时间匹配正确");
			return;
		}
		Assert.fail("上传失败");
	}

}
