package common.constant.file;

import java.io.File;
import java.io.IOException;

import org.fest.swing.timing.Pause;
import org.junit.Assert;

import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.FileCompare;
import common.util.ZIP;

public class File_Compare {
    /******************************************************************************
	 * 功能名称 ：返回两个excel文件比较的结果---存在乱序，谨慎使用效率低
	 * 功能简述 ：比较两个除了部分sheet页不比Excel
	 * 所属类 ：     AW_ExcelCheck
	 * 作者：           shibingwu
	 * 创建日期 : 2013.11.30
	 * 修改日期：
	 *******************************************************************************/
    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
    public static boolean fileCompareRandom_ByFileType_excludePartSheet(String srcPath, String destPath ,String fileType,String excludeSheetNames) 
    {
        srcPath = FileCompare.getMaxFile_Type(srcPath, fileType);
        destPath = FileCompare.getMaxFile_Type(destPath, fileType);
        System.out.println(ZIP.NowTime()+"src file："+srcPath);
        System.out.println(ZIP.NowTime()+"dest file："+destPath);

        return fileCompareRandom_excludePartSheet_Stream( srcPath,  destPath,  excludeSheetNames) ;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 两个文件的比较，以src为标准文件，dest为目标文件。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      caoyuelong
	 * 创建日期 : 2012.10.15
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_Random_Stream(String src, String dest) {
    	
    	String FileDir = File_Compare.getDir(dest);
    	String FileName = File_Compare.getFileName(dest);
    	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
    	
        System.out.println(ZIP.NowTime()+"src file："+src);
        System.out.println(ZIP.NowTime()+"dest file："+dest);
        boolean result = false;

        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/ExcelCompare_Random.jar -mode stream -file " + "\"" + src + "\"" + " " + "\"" + dest +"\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
        	Process process = Runtime.getRuntime().exec(cmd);
            result =  File_Compare.getResultFlage(FileDir, FileName, Maxfile);
            process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println(ZIP.NowTime()+"fileCompare_Random_Stream result："+result);
        return result;
    } 

    /******************************************************************************
	 * 功能名称 ：返回两个excel文件比较的结果（不包含部分sheet页的比较）
	 * 功能简述 ：比较两个除了部分sheet页不比的文件
	 * 所属类 ：     AW_ExcelCheck
	 * 作者：           caoyuelong
	 * 创建日期 : 2012.10.19
	 * 修改日期：
	 *******************************************************************************/
    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
    public static boolean fileCompareRandom_excludePartSheet_Stream(String srcPath, String destPath, String excludeSheetNames) 
    {
    	String FileDir = File_Compare.getDir(destPath);
    	String FileName = File_Compare.getFileName(destPath);
    	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
        System.out.println(ZIP.NowTime()+"src file："+srcPath);
        System.out.println(ZIP.NowTime()+"dest file："+destPath);
        System.out.println(ZIP.NowTime()+"Maxfile file："+Maxfile);
        System.out.println(ZIP.NowTime()+"FileDir ："+FileDir);
        System.out.println(ZIP.NowTime()+"FileName ："+FileName);
        boolean result = false;
        final String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/ExcelCompare_Random.jar -mode stream -file "+ "\"" + srcPath + "\"" + " " + "\"" + destPath + "\"" + " " + "\"" + excludeSheetNames+ "\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
        	Process process = Runtime.getRuntime().exec(cmd);
            result =  File_Compare.getResultFlage(FileDir, FileName, Maxfile);
            process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(ZIP.NowTime()+"fileCompareRandom_excludePartSheet_Stream result："+result);
        return result;
    } 

	    /******************************************************************************
		 * 功能名称 ：动态获取比较结果
		 * 所属类 ：AW_ExcelCheck
		 * 作者：      shibingwu
		 * 创建日期 : 2016.5.11
		 * 修改日期：
		 *******************************************************************************/
		 public static boolean getResultFlage(String srcPath, String FileName,String Maxfile) {
			 
			 for(int i = 0;i<60;i++){
				String newMaxfile = getMaxFile_Type( srcPath,  FileName);
	 	            if (newMaxfile.compareTo(Maxfile) > 0){
	 	            	if(Maxfile.endsWith(".xlsm")){
	 	 	               if(newMaxfile.endsWith("(true).xlsm")){
	 	 	            	  return true; 
	 	 	               }else{
	 	 	            	  return false;   
	 	 	               }
	 	            	}else{
		 	 	               if(newMaxfile.endsWith("(true).xlsx")){
			 	 	            	  return true; 
			 	 	               }else{
			 	 	            	  return false;   
			 	 	               }	
	 	            	}

	 	            }
				 Pause.pause(1000);
			 }
			 return false;
		 }
	    public  static  String  getMaxFile_Type(String srcPath, String starts)
		{
	    	File result = null;

	    	
	        for (File child :new File(srcPath).listFiles()) {
	            if  (child.isDirectory()) { 
	            	continue; 
	            }else if(child.getName().startsWith("~$")){
						continue;
	            }else if(child.getName().startsWith(starts)){
	            	 if (null == result) {
		 	                result = child;
		 	             }
		 	            if (child.getName().compareTo(result.getName()) > 0){
		 	                result = child;
		 	            }
	            }else{
	            	continue; 
	            }
	        }
	        if(result == null){
	        	Assert.assertTrue("startsWith:"+starts+"not find maxfile:"+CommonFile.fileMsg(srcPath), false);
	        }
			return result.getName();
		}
	    public  static  String  getDir(String srcPath)
		{
	        //获取文件名
	         int nPath = -1;
	     	 if(-1 == srcPath.lastIndexOf("/")){
	      		 nPath = srcPath.lastIndexOf("\\");
	      	 }else{
	      		 nPath = srcPath.lastIndexOf("/");
	      	 }
	     	String Dir = srcPath.substring(0, nPath+1);
			return Dir;
		}
	    public  static  String  getFileName(String srcPath)
		{
	        //获取文件名
	         int nPath = -1;
	         int LPath = srcPath.lastIndexOf(".");
	     	 if(-1 == srcPath.lastIndexOf("/")){
	      		 nPath = srcPath.lastIndexOf("\\");
	      	 }else{
	      		 nPath = srcPath.lastIndexOf("/");
	      	 }
	      	 String reportname = srcPath.substring(nPath+1,LPath); 
			return reportname;
		}
}
