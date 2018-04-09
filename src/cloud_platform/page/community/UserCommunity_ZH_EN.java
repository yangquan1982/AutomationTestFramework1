package cloud_platform.page.community;

import common.constant.constparameter.ConstUrl;

public class UserCommunity_ZH_EN {
	
	//
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
	//
	public static String Ask_for_Help_or_Discuss = get_Ask_for_Help_or_Discuss() ;
	
	public static String get_Ask_for_Help_or_Discuss(){
		if(ConstUrl.language.contains("CN"))
		{
			return "发起求助或讨论" ;
		}else
		{
			return "Ask for Help or Discuss" ;
		}	
	}
	//
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

	
}
