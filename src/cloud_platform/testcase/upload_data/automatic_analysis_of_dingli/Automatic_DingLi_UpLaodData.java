package cloud_platform.testcase.upload_data.automatic_analysis_of_dingli;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.upload_data.UploadDataTask;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.SeleniumUtil;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Automatic_DingLi_UpLaodData {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// 路测为混合数据
	private static String Cfghunhe = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\路测为混合数据\\conf\\配置数据0907_20347";
	// peac0830.xlsx
	private static String parahunhe = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\路测为混合数据\\paras\\paras\\20160830\\peac0830.xlsx";
	// 路测混合
	private static String DThunhe = ConstUrl.getRootPath() + "\\Data\\regression\\鼎利数据自动分析\\路测为混合数据\\路测为混合数据dt";
	// 单个文件夹多个rcu文件混合
	private static String DTOneFilehunhe = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\路测为混合数据\\64Mnuoxi1dt";
	// 不同名文件夹多个rcu文件
	private static String DTDifnameOneFilehunhe = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\路测为混合数据\\路测为混合数据dt\\64Mnuoxi";
	// 同名文件夹多个rcu文件混合
	private static String DTSamenameOneFilehunhe = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\路测为混合数据\\64Mnuoxi1dt";

	// 迭代2数据
	private static String Cfgdiedai = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\迭代2\\dingli数据\\dingli数据\\conf\\DingLi-yunfu";
	// GENEXCloud平台LTE工参-鼎利.xlsx
	private static String paradiedai = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\迭代2\\dingli数据\\dingli数据\\paras\\paras\\20160722\\GENEXCloud平台LTE工参-鼎利.xlsx";
	// 单个文件夹1个rcu文件迭代2
	private static String DTdiedai = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\迭代2\\dingli数据\\dingli数据\\dt\\dingli0923\\dt\\0715yu4444";
	// 不同名文件夹同一个rcu文件迭代2
	private static String DTdifname = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\迭代2\\dingli数据\\dingli数据\\dt\\dingli0923\\dt\\0715yu5555";
	// 同名文件夹同一个rcu文件迭代2
	private static String DTsamename = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\迭代2\\dingli数据\\dingli数据\\dt\\dingli0923\\dt\\0715yu4444";

	// 沁阳数据
	private static String Cfgqinyang = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\现网数据-沁阳\\conf\\20160901最新XML_802";
	// 单个文件夹多个rcu文件沁阳
	private static String DTqinyang = ConstUrl.getRootPath() + "\\Data\\regression\\鼎利数据自动分析\\现网数据-沁阳\\dt\\dt\\qinyang";
	// 不同名文件夹多个rcu文件沁阳
	private static String DTdifnameqinyang = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\现网数据-沁阳\\dt\\dt\\qinyang1";
	// 同名文件夹多个rcu文件沁阳
	private static String DTsamenameqinyang = ConstUrl.getRootPath()
			+ "\\Data\\regression\\鼎利数据自动分析\\现网数据-沁阳\\dt\\dt\\qinyang";
	// 配置数据
	private static String[] NetType = { "LTE", "LTE", "LTE" };
	private static String[] DataType = { "NE", "NE", "NE" };
	private static String[] SubDataType = { "CFG", "CFG", "CFG" };
	private static String[] FeatureType = { "All", "All", "All" };
	private static String[] filePath = { Cfghunhe, Cfgdiedai, Cfgqinyang };
	private static String[] UpDir = { "路测为混合数据", "迭代2数据", "沁阳数据" };
	private static int times = 2 * 60;
	// 路测数据迭代2
	private static String[] NetType1 = { "LTE", "LTE", "LTE" };
	private static String[] DataType1 = { "路测数据", "路测数据", "路测数据" };
	private static String[] SubDataType1 = { null, null, null };
	private static String[] FeatureType1 = { "All", "All", "All" };
	private static String[] filePath1 = { DTdiedai, DTdifname, DTsamename };
	private static String[] UpDir1 = { "单个文件夹1个rcu文件迭代2", "不同名文件夹同一个rcu文件迭代2", "同名文件夹同一个rcu文件迭代2" };
	// 路测数据混合
	private static String[] NetType11 = { "LTE", "LTE", "LTE", "LTE" };
	private static String[] DataType11 = { "路测数据", "路测数据", "路测数据", "路测数据" };
	private static String[] SubDataType11 = { null, null, null, null };
	private static String[] FeatureType11 = { "All", "All", "All", "All" };
	private static String[] filePath11 = { DThunhe, DTOneFilehunhe, DTDifnameOneFilehunhe, DTdiedai };
	private static String[] UpDir11 = { "路测混合", "单个文件夹多个rcu文件混合", "不同名文件夹多个rcu文件混合", "同名文件夹多个rcu文件混合" };
	// 路测数据沁阳
	private static String[] NetType22 = { "LTE", "LTE", "LTE" };
	private static String[] DataType22 = { "路测数据", "路测数据", "路测数据" };
	private static String[] SubDataType22 = { null, null, null };
	private static String[] FeatureType22 = { "All", "All", "All" };
	private static String[] filePath22 = { DTqinyang, DTdifnameqinyang, DTsamenameqinyang };
	private static String[] UpDir22 = { "单个文件夹多个rcu文件沁阳", "不同名文件夹多个rcu文件沁阳", "同名文件夹多个rcu文件沁阳" };

	// 工参数据
	private static String[] NetType2 = { "LTE", "LTE" };
	private static String[] DataType2 = { "paras", "paras" };
	private static String[] SubDataType2 = { null, null };
	private static String[] FeatureType2 = { null, null };
	private static String[] filePath2 = { parahunhe, paradiedai };
	private static String[] UpDir2 = { null, null };

	@BeforeClass
	public static void setUpBeforeClass() {

	}

	@Before
	public void setUp() {
		SeleniumUtil.killJAVA();
		if (driver != null) {
			driver.quit();
		}

		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@After
	public void tearDown() {
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(GT3Kid = "")
	public void T001_L_Conf_UploadData() {
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);
		UploadDataTask.BatchUpData(driver, defaultWindowID, "", NetType, DataType, SubDataType, FeatureType, UpDir,
				filePath, times);

	}

	@Test(GT3Kid = "")
	public void T002_L_DTdiedai2_UploadData() {
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);
		UploadDataTask.BatchUpData(driver, defaultWindowID, "", NetType1, DataType1, SubDataType1, FeatureType1, UpDir1,
				filePath1, times);

	}

	@Test(GT3Kid = "")
	public void T003_L_DThunhe_UploadData() {
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);
		UploadDataTask.BatchUpData(driver, defaultWindowID, "", NetType11, DataType11, SubDataType11, FeatureType11,
				UpDir11, filePath11, times);

	}

	@Test(GT3Kid = "")
	public void T004_L_DTqinyang_UploadData() {
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);
		UploadDataTask.BatchUpData(driver, defaultWindowID, "", NetType22, DataType22, SubDataType22, FeatureType22,
				UpDir22, filePath22, times);

	}

	@Test(GT3Kid = "")
	public void T005_L_Para_UploadData() {
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);
		UploadDataTask.BatchUpData(driver, defaultWindowID, "", NetType2, DataType2, null, null, UpDir2, filePath2,
				times);

	}

}
