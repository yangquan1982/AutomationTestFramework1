package common.constant.system;

import java.io.File;


public class SystemConstant
{

	public static final String PathSplit = "//";
	public static final String PlatForm = getProjectHomePath()+"Data\\baseline\\00_Platform\\";//平台数据路径
	public static final String UpLoadParam = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\";
	public static final String UpLoadProjectParam = UpLoadParam+"001_数据上传项目参数表.xlsx";
	public static final String UpLoadParam0KB = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\AbnormalVertify\\0KB\\";
	public static final String UpLoadParamError = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\AbnormalVertify\\errorFormat\\";
	public static final String UpLoadEngParamTable = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\EngineeringParameter\\";
	public static final String LTEEngParamTemplate = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\EngineeringParameter\\EngineeringParameterTemplate\\LTE\\";
	public static final String UMTSEngParamTemplate = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\EngineeringParameter\\EngineeringParameterTemplate\\UMTS\\";
	public static final String GSMEngParamTemplate = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\EngineeringParameter\\EngineeringParameterTemplate\\GSM\\";
	public static final String ErrorParamTemplatePath = getProjectHomePath()+"Data\\baseline\\01_UpLoadData\\ErrorEngineeringParameterTemplate\\";
	
	public static final String AlgorithmUploadParamAlgirithm = getProjectHomePath()+"Data\\baseline\\02_算法\\参数化\\视频规划与评估\\";
	public static final String AlgorithmBaseFilePath = getProjectHomePath()+"Data\\baseline\\02_算法\\基线文件\\";
	public static final String AlgorithmDownloadPath = getProjectHomePath()+"Data\\result\\算法验证";  
	
	public static final String ConcurrentPath = "D:\\";
	public static String getProjectHomePath()
	{
		String path = System.getProperty("user.dir");
		System.out.println(path);
		File file = new File(path);
		return file.getPath() + "\\";
	}


	public static String SystemRoot = getProjectHomePath();


}
