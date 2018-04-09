package cloud_platform.testcase.upload_data.concurrentupload;

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

import common.constant.constparameter.ConstUrl;
import common.constant.system.SystemConstant;
import common.util.ReadNotes;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Concurrent_InitialParamTable {

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\全量环境";
	private static String ParaFile = SystemConstant.ConcurrentPath + "ConcurrentUpLoadParamTable.xlsx";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {

	}

	@Test(GT3Kid = "")
	public void CreatPrj_InitialParam() {
		ReadNotes.setAllProjectState(ParaFile, "ProjectInformation");
	}
}
