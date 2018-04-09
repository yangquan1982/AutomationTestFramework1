package cloud_platform.testcase.preparecondition;



import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.systemManage.SystemManageConst;
import cloud_platform.task.platform.SystemManageTask;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.platform.Operator;
import common.parameter.platform.SystemManageParameter;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T001_SystemMgmt_UserAdd {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	
	private static boolean isDelete = true;// 存在项目时是否删除项目
	private static String ParaFile = ConstUrl.getProjectHomePath()+ "\\Data\\baseline\\00_Platform\\UserInfor.xlsx";//参数化表路径
	
	private String secns;// 场景描述
	private String systemmanager;// 场景描述
	private String projectmanager;//工程业务领域
	private String maintainer ;// 维护员
	private String statistician ;// 统计员
	private String expert;// 昆明移动
	private String[] operatorInfor;// LTE
	
	

	public T001_SystemMgmt_UserAdd(String secns, String systemmanager,
			String projectmanager, String maintainer, String statistician,
			String expert, String[] operatorInfor) {
		this.secns = secns;
		this.systemmanager = systemmanager;
		this.projectmanager = projectmanager;
		this.maintainer = maintainer;
		this.statistician = statistician;
		this.expert = expert;
		this.operatorInfor = operatorInfor;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(
				ParaFile, "系统前置人员"));
		return coll;

	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.loginSys(driver);
	}

	@Before
	public void setUp() {
		SwitchDriver.switchDriverToSEQ(driver);
		HomeTask.JumpSystemManage(driver, true);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void CreatPrj_NotDel() {
		LOG.info_testcase("场景描述:" + secns);
		SystemManageParameter parameter = new SystemManageParameter(SystemManageConst.Set_SystemManger,systemmanager);
		if(systemmanager!=null){
			SystemManageTask.deleteAddMange(driver, parameter,false);
		}		
		if(projectmanager!=null){
			parameter.setSetitemname(SystemManageConst.Set_ProjectManger);
			parameter.setUserid(projectmanager);
			SystemManageTask.deleteAddMange(driver, parameter,false);
		}
		if(maintainer!=null){
			parameter.setSetitemname(SystemManageConst.Set_Maintain);
			parameter.setUserid(maintainer);
			SystemManageTask.deleteAddMange(driver, parameter,false);
		}
		if(statistician!=null){
			parameter.setSetitemname(SystemManageConst.Set_Statistician);			
			parameter.setManageoperator(new String[]{"中国联通","中国移动","中国电信"});
			parameter.getManagearea().setSelectall(true);//区域全选参数
			parameter.setUserid(statistician);
			SystemManageTask.deleteAddMange(driver, parameter,false);
		}
		if(expert!=null){
			parameter.setSetitemname(SystemManageConst.Set_Expert);
			parameter.setRats(new String[]{"LTE","UMTS","GSM"});
			parameter.setUserid(expert);
			parameter.setOperator(new Operator(operatorInfor[0],operatorInfor[1],operatorInfor[2].split(";")));
			SystemManageTask.deleteAddMange(driver, parameter,false);
		}
		
	}
}
