package cloud_platform.task.platformcommon;

import common.constant.constparameter.ConstUrl;

public class PlatfromCommon {

	
	//任务状态，分析失败
	public static String taskStatus_Failed = get_taskStatus_Failed() ;
	
	public static String get_taskStatus_Failed(){
		if(ConstUrl.language.contains("ZH"))
		{
			return "分析失败" ;
		}else
		{
			return "Failed" ;
		}	
	}	
}
