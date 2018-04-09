package cloud_plugin.page.network_performance_analysis_center.network_planning.hotspot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class LteHotSpotPage {

	public static final String TaskName_ID = "#taskName";
	public static final String NextStep_ID = "#next_Tasknew";// 下一步按钮

	public static final String CfgBtn = "select_confFilePath";
	public static final String EparaBtn = "select_parasFilePath";
	public static final String MrBtn = "select_mrFilePath";
	public static final String PfmBtn = "select_perforFilePath";
	public static final String FeatureBtn = "select_propertyFilePath";
	public static final String NeGroupDataBtn = "input[id=\"neGroupData\"]";// 选择本地网元组数据
	public static final String ChooseNeGroupBtnOk = "span[class=\"l-btn-text\"]";
	// 参数设置
	// 基础设置
	public static final String TddSingleChooseBtn = "label[class=\"taskAddDailyForecastRadioOff\"]";// 制式TDD
	public static final String PosiObjLi_PATH = "#positionObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";// 定位方式
	public static final String FeaturePosit = "ul[id=\"mshowltepositioning\"] li[title=\"特征库定位\"]";
	public static final String FastPosit = "ul[id=\"mshowltepositioning\"] li[title=\"快速定位\"]";
	public static final String ComplexPosit = "ul[id=\"mshowltepositioning\"] li[title=\"综合定位\"]";
	public static final String ShanGe_PATH = "#raster_ObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";// 栅格精度
	public static final String ShanGe50 = "ul[id=\"mshowlterasterAccuracy\"] li[title=\"50\"]";
	public static final String ShanGe20 = "ul[id=\"mshowlterasterAccuracy\"] li[title=\"20\"]";
	// 栅格速率
	public static final String QufenOutIn = "input[name=\"task.whetherDistrict\"]";// 是否区分室内外
	public static final String IsOutDoor = "ul[id=\"mshowwhetherDistrict\"] li[title=\"是\"]";
	public static final String ShangeEvaluation = "input[title=\"全时段栅格最小速率\"]";// 栅格速率评估方式
	public static final String DxsmbsvEvaluation = "ul[id=\"mshowshangeEvaluation\"] li[title=\"单小时目标速率达标率\"]";
	public static final String RsrpDoor = "#rsrpDoor";// RSRP弱覆盖门限(dBm)：
	public static final String UserAddyinzi = "#userAddyinzi";// 用户增长因子：
	public static final String VillageFile = "#villageFile";// 用户增长因子文件：
	// 根因分析
	public static final String ShibieStye = "input[title=\"栅格电平\"]";// 弱覆盖识别方式
	public static final String ShibieStyeRfg = "ul[id=\"mshowweakCoverageIdentify\"] li[title=\"栅格弱覆盖比例\"]";
	public static final String RfgDaBiaoDoor = "#ruofugaishangedabiaomenxian";
	public static final String DianPingDoor = "#dianpingdabiaomenxian";
	public static final String CQIDoor = "#CQImenxian";
	public static final String Dyhbz = "#danyonghubaozhangsulv";
	public static final String SingleUserFile = "#singleUserFile";
	// 其它设置
	public static final String MrClear = "input[name=\"task.whetherMrClean\"]";// 是否进行MR清洗
																				// eq(5)
	public static final String NoMrClear = "ul[id=\"mshowwhetherMrClean\"] li[title=\"否\"]";
	// 高级设置
	public static final String[] GaoJiCanShu = { "#ganraoyinzi",
			"#gaosuyewuyonghubili", "#PRBliyonglvguaidian",
			"#chuanshuyouxiaoyinzi" };

	public static final String CommitBtn = "#commit_Tasknew";
	public static final String CancelBtn = "#cancel_Task";

	public static String setTaskName(WebDriver driver, String taskName) {
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TaskName_ID);
		driver.findElement(By.id("taskName")).sendKeys(taskName);
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		CommonJQ.click(driver, LteHotSpotPage.NextStep_ID, true);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CommitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

}
