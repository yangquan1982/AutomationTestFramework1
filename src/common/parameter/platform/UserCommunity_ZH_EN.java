package common.parameter.platform;

import common.constant.constparameter.ConstUrl;


public class UserCommunity_ZH_EN {

	public static String Problem_Feedback = get_Problem_Feedback() ;
	public static String get_Problem_Feedback(){
		if(ConstUrl.language.contains("CN"))
		{
			return "问题反馈" ;
		}else
		{
			return "Problem Feedback" ;
		}	
	}
	
	public static String Ask_for_Help_or_Discuss = get_Ask_for_Help_or_Discuss() ;
	
	public static String get_Ask_for_Help_or_Discuss(){
		System.out.print(ConstUrl.language+"==============");
		if(ConstUrl.language.contains("CN"))
		{
			return "发起求助或讨论" ;
		}else
		{
			return "Ask for Help or Discuss" ;
		}	
	}
	
	public static String Submit = get_Submit() ;
	
	public static String get_Submit(){
		if(ConstUrl.language.contains("CN"))
		{
			return "提交回复" ;
		}else
		{
			return "Submit" ;
		}	
	}  
	
	public static String Share = get_Share() ;
	
	public static String get_Share(){
		if(ConstUrl.language.contains("CN"))
		{
			return "分享(0)" ;
		}else
		{
			return "Share(0)" ;
		}	
	} 
	
	
	public static String Previous = get_Previous() ;
	
	public static String get_Previous(){
		if(ConstUrl.language.contains("CN"))
		{
			return "上一页" ;
		}else
		{
			return "Previous" ;
		}	
	} 
	
	public static String Next = get_Next() ;
	
	public static String get_Next(){
		if(ConstUrl.language.contains("CN"))
		{
			return "下一页" ;
		}else
		{
			return "Next" ;
		}	
	}
}
