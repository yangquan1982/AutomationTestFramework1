package common.parameter.platform;

import common.constant.constparameter.ConstUrl;

public class Sharing_ZH_EN {

	
public static String Basic_Optimization = get_Basic_Optimization() ;
	
	public static String get_Basic_Optimization(){
		if(ConstUrl.language.contains("CN"))
		{
			return "基础优化" ;
		}else
		{
			return "Basic Optimization" ;
		}	
	}
	//
	public static String Compare_Configuration_Parameters = get_Compare_Configuration_Parameters() ;
	
	public static String get_Compare_Configuration_Parameters(){
		if(ConstUrl.language.contains("CN"))
		{
			return "配置参数对比" ;
		}else
		{
			return "Compare Configuration Parameters" ;
		}	
	}
	//
	public static String My_Workspace = get_My_Workspace() ;
	
	public static String get_My_Workspace(){
		if(ConstUrl.language.contains("CN"))
		{
			return "个人工作空间" ;
		}else
		{
			return "My Workspace" ;
		}	
	}
	//
	public static String My_To_Do = get_My_To_Do() ;
	
	public static String get_My_To_Do(){
		if(ConstUrl.language.contains("CN"))
		{
			return "我的待办" ;
		}else
		{
			return "My To-Do" ;
		}	
	}
	//
	public static String Case_to_Be_Approved = get_Case_to_Be_Approved() ;
	
	public static String get_Case_to_Be_Approved(){
		if(ConstUrl.language.contains("CN"))
		{
			return "待审批的案例" ;
		}else
		{
			return "Case to Be Approved" ;
		}	
	}
	//
	public static String Attachments_to_Be_Approved = get_Attachments_to_Be_Approved() ;
	
	public static String get_Attachments_to_Be_Approved(){
		if(ConstUrl.language.contains("CN"))
		{
			return "待审批的附件" ;
		}else
		{
			return "Attachments to Be Approved" ;
		}	
	}
	//
	public static String Cancel_and_Back = get_Cancel_and_Back() ;
	
	public static String get_Cancel_and_Back(){
		if(ConstUrl.language.contains("CN"))
		{
			return "取消并返回" ;
		}else
		{
			return "Cancel and Back" ;
		}	
	}
	//
	public static String Logout = get_Logout() ;
	
	public static String get_Logout(){
		if(ConstUrl.language.contains("CN"))
		{
			return "注销" ;
		}else
		{
			return "Logout" ;
		}	
	}
}
