package common.constant.constparameter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.w3c.dom.NodeList;

import common.constant.system.SystemConstant;
import common.util.XmlParser;
import common.util.ZIP;

public class ConstUrl {
	 // 获取工程根路径
	 public static String getProjectHomePath(){
			String path = ZIP.class.getClassLoader().getResource(".").getFile();
	        File file = new File(path);
	        String project_Home = file.getParentFile().getAbsolutePath();
			return project_Home;	
   }
	 // 获取工程根路径
	 public static String getRootPath(){
			String path = ZIP.class.getClassLoader().getResource(".").getFile();
	        File file = new File(path);
	        String getRootPath = file.getParentFile().getParentFile().getAbsolutePath();
			return getRootPath;	
   }
	public static String PROJECTURL = "file:///" + SystemConstant.getProjectHomePath().replace("\\", "/");

	public static String xmlPath_testobject = SystemConstant.SystemRoot + "/setting/testobject/ConstParameter.xml";
	public static Properties testObjectProperties = getConfigProperty(xmlPath_testobject);

	public static String xmlPath_dataParam = SystemConstant.SystemRoot + "/setting/dataParam/dataParam.xml";
	public static String xmlPath_resultParam = SystemConstant.SystemRoot + "/setting/result/resultParam.xml";
	
	public static Properties dataParamProperties = getConfigProperty(xmlPath_dataParam);
	public static Properties resultParamProperties = getConfigProperty(xmlPath_resultParam);
	
	public static String IEDRIVER = SystemConstant.SystemRoot + "/tools/driver/IEDriverServer.exe";
	public static String CHROMEDRIVER = SystemConstant.SystemRoot + "/tools/driver/chromedriver.exe";

	public static String Proxy = ConstUrl.getAddress();
	public static String VENDOR = ConstUrl.getVendor();
	public static String RAT = "UMTS/LTE FDD/LTE TDD/GSM";
	public static String granularity = "Day/Hour";
	public static String LOGINURL = ConstUrl.getBSWEBLoginIP();
	public static String LOGINUSER = ConstUrl.getBSWEBLoginUser();
	public static String LOGINSECUSER = ConstUrl.getBSWEBLoginSecUser();
	public static String LOGINPWD = ConstUrl.getBSWEBLoginPwd();
	public static String LOGINVerifyCode = ConstUrl.getBSWEBLoginVerifyCode();
	public static String language = ConstUrl.getLanguage();
	public static boolean PerfFlag = ConstUrl.getPerfFlag();
	public static String ExportTimeout = ConstUrl.getExportTimeout();
	public static String TestCaseTimeout = ConstUrl.getTestCaseTimeout();
	public static String DownLoadPath = ConstUrl.getDownLoadPath();

	public static String LOGINSysUSER = ConstUrl.getSysLoginUser();
	public static String LOGINSysPWD = ConstUrl.getSysLoginPwd();
	
	public static String LOGINProUSER = ConstUrl.getProLoginUser();
	public static String LOGINProPWD = ConstUrl.getProLoginPwd();
	
	// load time
	public static int MAPDISPLAYTime = 3000;

	public static int MAPDISPLAYTimeOfVipAna = 3000;

	public static boolean exitAfterRun() {
		return "y".equalsIgnoreCase(testObjectProperties.getProperty("exitAfterRun"));
	}

	public static boolean failOnNoData() {
		return "y".equalsIgnoreCase(testObjectProperties.getProperty("failOnNoData"));
	}

	public static String getBrowserType() {
		return testObjectProperties.getProperty("BrowserType");

	}

	public static String getBSWEBLoginIP() {
		return testObjectProperties.getProperty("IP.BSWEBLogin.URL");

	}

	private static String getBSWEBLoginPwd() {
		return testObjectProperties.getProperty("IP.BSWEBLogin.Pwd");
	}

	private static String getBSWEBLoginSecUser() {
		return testObjectProperties.getProperty("IP.BSWEBLogin.SecUser");
	}

	private static String getBSWEBLoginUser() {
		return testObjectProperties.getProperty("IP.BSWEBLogin.User");
	}
	
	private static String getSysLoginUser() {
		return testObjectProperties.getProperty("IP.SysLogin.User");
	}
	private static String getSysLoginPwd() {
		return testObjectProperties.getProperty("IP.SysLogin.Pwd");
	}
	
	private static String getProLoginUser() {
		return testObjectProperties.getProperty("IP.ProLogin.User");
	}
	private static String getProLoginPwd() {
		return testObjectProperties.getProperty("IP.ProLogin.Pwd");
	}
	
	private static String getBSWEBLoginVerifyCode() {
		return testObjectProperties.getProperty("IP.BSWEBLogin.VerifyCode");
	}

	public static String getConfigFileName(String filepath) {
		XmlParser parser = new XmlParser(filepath);
		NodeList fileNameList = parser.getNodeListByName("filename");
		String fileName = null;
		if (fileNameList.getLength() == 1) {
			fileName = fileNameList.item(0).getTextContent();
		}
		return fileName;
	}

	public static Properties getConfigProperty(String xmlPath) {
		String fileName = getConfigFileName(xmlPath);
		String configURL = new File(xmlPath).getParentFile().getAbsolutePath() + "/" + fileName;
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(configURL);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	private static String getLanguage() {
		return testObjectProperties.getProperty("Language");
	}

	private static boolean getPerfFlag() {
		return StringUtils.equalsIgnoreCase(testObjectProperties.getProperty("PerfFlag"), "ON");
	}

	private static String getExportTimeout() {
		return testObjectProperties.getProperty("ExportTimeout");
	}

	private static String getTestCaseTimeout() {
		return testObjectProperties.getProperty("TestCaseTimeout");
	}

	public static boolean killWebAfterRun() {
		return "y".equalsIgnoreCase(testObjectProperties.getProperty("killWebAfterRun"));
	}

	private static String getVendor() {
		return dataParamProperties.getProperty("CURRENTVERSION");
	}

	private static String getAddress() {
		return dataParamProperties.getProperty("CURRENTPROXY", "proxycn2.huawei.com");
	}

	public static String getTargetValue(String target) {
		String result = dataParamProperties.getProperty(target);
		if (result == null) {
//			Assert.fail("Data not find ,target:"+target);
			return target;
		}else{
			return result;
		}
	}
	
	public static String getResultValue(String target) {
		return resultParamProperties.getProperty(target);
	}

	private static String getDownLoadPath() {
		String path = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";
		if (!"".equals(testObjectProperties.getProperty("DownLoadPath"))
				&& null != testObjectProperties.getProperty("DownLoadPath")) {
			path = ConstUrl.getProjectHomePath()+testObjectProperties.getProperty("DownLoadPath");
		}
		return path;
	}

}