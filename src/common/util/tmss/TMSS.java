package common.util.tmss;

//import org.junit.internal.runners.TestClassMethodsRunner;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class TMSS {
	private static Dispatch GT3k = null;
	static Dispatch dom = null;
	static ActiveXComponent Com = null;

	/**
	 * 初始化TMSS
	 */
	public static void InitTMSS() {
		try {
			ComThread.InitSTA();
			ActiveXComponent Com = null;
			Com = new ActiveXComponent("Mea.Application");

			System.out.println("Init TMSS Success!\r\n");
			Dispatch sControl = (Dispatch) Com.getObject();
			Variant v = Dispatch.call(sControl, "GetObject", "GT3000_TRM");
			GT3k = v.toDispatch();
			// 2010.12.1 zhuxiaofeng165816 add, 实现自动登录
			TMSSInfo.initTmssConfigInfo();
			 Dispatch.call(GT3k, "Login", TMSSInfo.ServerName, TMSSInfo.ServerPort, TMSSInfo.userName,
					TMSSInfo.password);
			System.out.println("userName:" + TMSSInfo.userName);
			System.out.println("password:" + TMSSInfo.password);

			dom = Dispatch.get(GT3k, "Dom").toDispatch();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * 设置用例结果 Result字段值及意义:151-Pass,152-Fail,155-Block,154-Unavaliable,155-
	 * Investigate
	 */

	public static void SetTestCaseResult(String URI, String TestCaseID, String Result, String info) {
		URI = URI.replaceAll("，", ",");
		String[] versionUris = URI.split(",");
		try {
			if (GT3k == null) {
				System.out.println("Start to init tmss connent...\r\n");
				InitTMSS();
			}
			String resultInfo = null;
			if (Result.equalsIgnoreCase("151")) {
				resultInfo = "AutoTestResult:TestCaseID = " + TestCaseID + "; AutoTestCase = " + info
						+ "; Result = Success!";
			} else if (Result.equalsIgnoreCase("152")) {
				resultInfo = "AutoTestResult:TestCaseID = " + TestCaseID + "; AutoTestCase = " + info
						+ "; Result = Fail";
			} else {
				resultInfo = "Illegal TestCase Result!";
			}
			resultInfo = "----------------------------------------------------------------------------\r\n"
					+ resultInfo;
			System.out.println(resultInfo + "\r\n");

			// zhuxiaofeng modify! 修改为先遍历ID，再遍历各测试版本uri！提高ID优先级�?
			// 之前是先遍历各版本uri，再遍历ID�?
			TestCaseID = TestCaseID.replaceAll("，", ",");
			String[] ids = TestCaseID.split(",");
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i].trim();
				boolean gt3kState = false;
				for (int j = 0; j < versionUris.length; j++) {

					if (true == SetSingleTestCaseResult(versionUris[j], id, Result, info)) {
						// if(true == SetSingleTestCaseResult(versionUris[j],
						// id, "157", info)){
						gt3kState = true;
					}
				}
				String getIdResult = null;
				if (false == gt3kState) {
					getIdResult = "SendResult2Gt3kFailure：TestCaseID = " + id
							+ " is not Exist in any test version uri !";
				} else {
					getIdResult = "SendResult2Gt3kSuccess: TestCaseID = " + id + " !";
				}
				System.out.println(getIdResult + "\r\n");
			}
		} catch (Exception e) {
			System.out.println( e);
		}
	}

	private static boolean SetSingleTestCaseResult(String URI, String TestCaseID, String Result, String info)
			throws Exception {
		Variant VersionC = new Variant(URI);

		// 测试用例ID
		Variant TestCaseIDC = new Variant(TestCaseID);

		// 用例执行结果
		Variant ResultC = new Variant(Result);

		// 其它�?��无关的变�?
		Variant StartTime = new Variant("");
		Variant EndTime = new Variant("");
		Variant Info = new Variant(info);
		Variant Attch = new Variant("");

		// 2011.9.27 zhuxiaofeng modify 直接由TMSS接口"GetResourceUrisByID"来获�?
		String uri = getUribyId(URI, TestCaseID);
		String getIdResult = null;
		if (uri == null || uri.equalsIgnoreCase("null")) {
			getIdResult = "GetTestCaseUriFailure�?TestCaseID(" + TestCaseID + ") is not Exist in VersionUri(" + URI
					+ ") !";
			return false;
		} else {
			getIdResult = "GetTestCaseUriSuccess:  TestCaseID(" + TestCaseID + ") is Exist in VersionUri(" + URI
					+ ") !";
		}
		System.out.println(getIdResult);

		setGt3kCaseAuto(uri, TestCaseID);
		 Dispatch.callN(GT3k, "AddTestResultEx",
				new Variant[] { VersionC, TestCaseIDC, ResultC, StartTime, EndTime, Info, Attch });
		/*
		 * String gt3kResult = null; if(a.toBoolean()){ gt3kResult =
		 * "SendResult2Gt3kSuccess: TestCaseID(" + TestCaseID +
		 * "); VersionUri = " + URI + " !"; } else{ gt3kResult =
		 * "SendResult2Gt3kFailure: TestCaseID(" + TestCaseID +
		 * "); VersionUri = " + URI + " !"; }
		 */
		return true;
	}

	/**
	 * 得到当前节点的属�?
	 * 
	 * @param uri
	 * @param propName
	 * @return
	 * @since womat3.0
	 */
	static String getProperty(String uri, String propName) {
		try {
			Variant v = Dispatch.call(dom, "Attribute", uri, propName);
			String ret = v.toString();
			v.safeRelease();
			return ret;
		} catch (Exception ex) {
			// Logger.getLogger("").error(ex);
			return null;
		}
	}

	static String[] getChildrenUri(String uri) {
		String str = invokeByUri("GetResourceChildren", uri);
		String[] ret = str.trim().split(" ");
		return ret;
	}

	static String getResourceType(String uri) {
		return invokeByUri("GetResourceType", uri);
	}

	/**
	 * 对当前节点调用方�?
	 * 
	 * @param method
	 * @param uri
	 * @return
	 * @since womat3.0
	 */
	static String invokeByUri(String method, String uri) {
		try {
			Variant var = Dispatch.call(dom, method, uri);
			String ret = var.toString();
			var.safeRelease();
			return ret;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 直接根据TMSS接口GetResourceUrisByID获取uri
	 */
	static String getUribyId(String versionUri, String id) {
		try {
			Variant var = Dispatch.call(dom, "GetResourceUrisByID", versionUri, id);
			String ret = var.toString().trim();
			var.safeRelease();
			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @todo TODO
	 * @type void
	 * @param URI
	 * @param TestCaseID
	 */
	private static void setGt3kCaseAuto(String URI, String TestCaseID) {

		ActiveXComponent dic = new ActiveXComponent("Scripting.Dictionary");
		Dispatch.call(dic.getObject(), "Add", "AutoType", "1");
		// Dispatch.call(dic.getObject(), "Add", "TestType", "10");
		Dispatch.call(dic.getObject(), "Add", "WirelessCaseType", "1");
		Dispatch.call(dom, "ModifyResource", URI, dic);
		dic.safeRelease();
	}

	/**
	 * 调试使用 s
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TMSS.InitTMSS();
		// SetTestCaseResult("", "001.001", "152", "");
		// SetTestCaseResult("/dd2hklcuhf0/bk3id9cju3l/u89iieij4nq/bn3ihq1cgqf/bn3ihq3081q/",
		// "TM_001_001_0007_629", "151", "");
		SetTestCaseResult("/4j1jmvgpd2f/i05kdj0d2qn/i05kdrem147/", "MBB_KQI_Task_005", "151",
				"com.huawei.oss.autotest.nastar.testcase.MBB.TestMBBKQI_Lev1.testCreatMBBKQIAnalysisTaskForTaskNameBeSixty()");
		String uri_0 = getUribyId("/4j1jmvgpd2f/i05kdj0d2qn/i05kdrem147/", "MBB_KQI_Task_005");
		System.out.println(uri_0);

		String uri_1 = getUribyId("/4j1jmvgpd2f/i05kdj0d2qn/i05kdrem147/i05kdrem157/i05kece6ffp8/i05kece6fg8/",
				"MBB_KQI_Task_005");
		System.out.println(uri_1);
		// SetTestCaseResult("/dd2hklcuhf0/bk3id9cju3l/u89iieij4nq/bn3ihq1cgqf/bn3ihq30mpg/bn3ihq30mpg1/bn3ihq3inida/bn3ihq3inidb/",
		// "TM_001_001_0007_629", "151", "");
		// SetTestCaseResult("TM_001_001_0007", "151", "");
		// TMSS.SetTestCaseResult("", "", "", "");
	}
}
