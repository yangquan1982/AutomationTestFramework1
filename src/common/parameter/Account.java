package common.parameter;

import common.util.PlatfromParamUtil;

public class Account {
	private static String userInforpath = "D:\\GENEXCloud_V3_P1\\Data\\baseline\\00_Platform\\UserInfor.xlsx";
	private static String userInforSheetName = "accountInfor";
	
	private String account;
	private String pwd;
	
	
	public Account(String path,String sheetName,String accountype) {
		String user = PlatfromParamUtil.getAccount(path, sheetName, accountype);
		this.account = user;
		this.pwd = PlatfromParamUtil.getAccountPwd(userInforpath, userInforSheetName, user);
	}
	public Account(String userId, String pwd) {
		super();
		this.account = userId;
		this.pwd = pwd;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
		
}
