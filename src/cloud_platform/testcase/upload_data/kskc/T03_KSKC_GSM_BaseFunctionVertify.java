package cloud_platform.testcase.upload_data.kskc;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
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

import cloud_public.page.GetDataByTypePage;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import cloud_public.task.Task_KSKC;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T03_KSKC_GSM_BaseFunctionVertify {
	

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = "D:\\GENEXCloud_V3_P1\\Data\\KSKC\\参数化测试\\可视可查参数化表.xlsx";
	private static String SheetName = "GSM";
	
	private String secns;
	private String ProjectName;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String NeDate;
	private int NeNumber;
	private String PartNeName;
	private String NeName;
	private String NoDataNeName;
	private String SearchText;
	private String ImportPath;
	private String isNofindNeNum;
	private String mustfindNeNum;
	
	
	public T03_KSKC_GSM_BaseFunctionVertify(String secns,String PrjName,
			String rat, String DataType, String subDataType,String neDate,int neNumber,String partNeName,String neName,
			String noDataNeName,String searchText, String importPath, String isNofindNeNum,String mustfindNeNum) {
		this.secns = secns;
		this.ProjectName = PrjName;
		this.RAT = rat;
		this.DataType = DataType;
		this.SubDataType = subDataType;
		this.NeDate = neDate;
		this.NeNumber = neNumber;
		this.PartNeName = partNeName;
		this.NeName = neName;
		this.NoDataNeName = noDataNeName;
		this.SearchText = searchText;
		this.ImportPath = importPath;
		this.isNofindNeNum = isNofindNeNum;
		this.mustfindNeNum = mustfindNeNum;
		
	}


	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.print("This is collection");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(
				ParaFile, SheetName));
		return coll;
	}


	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomeTask.JumpToUpLoad(driver);
	}

	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_GSM_KSKCVertify() {	
		LOG.info_testcase("场景描述:"+secns);
		if(StringUtils.isBlank(PartNeName)){
			PartNeName=null;
		}
		if(StringUtils.isBlank(NeName)){
			NeName=null;
		}
		if(StringUtils.isBlank(NoDataNeName)){
			NoDataNeName=null;
		}
			
		Task_KSKC.KSKC_AllInforVertify(driver, ProjectName, RAT, DataType, SubDataType, NeDate, NeNumber,PartNeName,NeName,NoDataNeName,SearchText,ImportPath,isNofindNeNum,mustfindNeNum);
	}
	/**
	 * 可是可查用户自定义验证
	 */
	@Test(GT3Kid = "")
	public void T002_GSM_KSKCVertify() {	
		LOG.info_testcase("场景描述:"+secns);
		if(StringUtils.isBlank(PartNeName)){
			PartNeName=null;
		}
		if(StringUtils.isBlank(NeName)){
			NeName=null;
		}
		if(StringUtils.isBlank(NoDataNeName)){
			NoDataNeName=null;
		}
			
		Task_KSKC.KSKC_CustomInforVertify(driver, ProjectName, RAT, DataType, SubDataType, NeDate, NeNumber,PartNeName,NeName,NoDataNeName,SearchText,ImportPath,isNofindNeNum,mustfindNeNum);
	}

}
