package common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.fest.swing.timing.Pause;
import org.junit.Assert;

import common.constant.constparameter.ConstUrl;
import common.constant.file.CSVCmp;
import common.constant.file.File_Compare;

public class FileCompare {
    
    private static String resultStr = "";

    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 两个文件的比较，以src为标准文件，dest为目标文件。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      caoyuelong
	 * 创建日期 : 2012.10.15
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare(String src, String dest) {
    	resultStr="";
    	File srcFile =new File(src);
    	File destFile =new File(dest);
    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(src)+" srcfile is not File");
    		Assert.assertTrue(CommonFile.fileMsg(src)+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(dest)+" destfile is not File");
    		Assert.assertTrue(CommonFile.fileMsg(dest)+" is not File",false);
    	}
        System.out.println(ZIP.NowTime()+"src file："+src);
        System.out.println(ZIP.NowTime()+"dest file："+dest);
    	if(srcFile.getName().toLowerCase().endsWith(".csv") || srcFile.getName().toLowerCase().endsWith(".txt")){
    		return	FileCompare.csvFileIsEqual(src, dest);
    	}else if(srcFile.getName().toUpperCase().endsWith(".MIF")||srcFile.getName().toUpperCase().endsWith(".MID")){
    		return	FileCompare.csvFileIsEqual(src, dest);
    	}else if(srcFile.getName().toUpperCase().endsWith(".KML")){
    		return	FileCompare.csvFileIsEqual(src, dest);
    	}else if(srcFile.getName().toLowerCase().endsWith(".xml")){
    		return	FileCompare.csvFileIsEqual(src, dest);
    	}else if(srcFile.getName().toLowerCase().endsWith(".doc") || srcFile.getName().toLowerCase().endsWith(".docx")){
    		return	Word.compareWord(src, dest);
    	}else{
    	      boolean result = false;
    	        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/ExcelCompare.jar -file " +"\""+ src +"\""+ " " +"\""+ dest+"\"";
    	        System.out.println(ZIP.NowTime()+cmd);
    	        try {
    	            Process process = Runtime.getRuntime().exec(cmd);
    	            addStreamShowThread(process.getInputStream());
    	            process.waitFor();
    	            process.destroy();
    	            System.out.println(ZIP.NowTime()+"fileCompare resultStr："+resultStr);
    	            if(resultStr == null){
	                	String FileDir = File_Compare.getDir(dest);
	                	String FileName = File_Compare.getFileName(dest);
	                	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
	                	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
    	            }else if(resultStr.equals("")){
	                	String FileDir = File_Compare.getDir(dest);
	                	String FileName = File_Compare.getFileName(dest);
	                	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
	                	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
     	            }else{
        	            if (resultStr.equals("true")) {
        	                result = true;
        	            } 
    	            }
       
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    	        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
    	        resultStr=""; 
    	        if(result==false){
    	        	return File_Compare.fileCompare_Random_Stream(src,dest);
    	        }
    	        resultStr=""; 

    	        System.out.println(ZIP.NowTime()+"fileCompare result："+result);
    	        return result;	
    	}

  
    }

    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果---存在精度
	 * 功能简述 ： 两个文件的比较，以src为标准文件，dest为目标文件。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      
	 * 创建日期 : 2012.10.15
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_Deviation(String src, String dest) {
    	resultStr="";
    	File srcFile =new File(src);
    	File destFile =new File(dest);
    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(src)+" srcfile is not File");
    		Assert.assertTrue(CommonFile.fileMsg(src)+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(dest)+" destfile is not File");
    		Assert.assertTrue(CommonFile.fileMsg(dest)+" is not File",false);
    	}
        System.out.println(ZIP.NowTime()+"src file："+src);
        System.out.println(ZIP.NowTime()+"dest file："+dest);
    	if(srcFile.getName().toLowerCase().endsWith(".csv") || srcFile.getName().toLowerCase().endsWith(".txt")){
    		return	FileCompare.csvFileIsEqual(src, dest);
    	}else if(srcFile.getName().toLowerCase().endsWith(".xml")){
    		return	FileCompare.csvFileIsEqual(src, dest);
    	}else if(srcFile.getName().toLowerCase().endsWith(".doc") || srcFile.getName().toLowerCase().endsWith(".docx")){
    		return	Word.compareWord(src, dest);
    	}else{
            boolean result = false;
            String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/ExcelCompare_Deviation.jar -area true -file " + "\"" + src + "\"" + " " + "\"" + dest + "\"";
            System.out.println(ZIP.NowTime()+cmd);
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                addStreamShowThread(process.getInputStream());
                process.waitFor();
                process.destroy();
                System.out.println(ZIP.NowTime()+"fileCompare_Deviation resultStr："+resultStr);
	            if(resultStr.equals("")){
                	String FileDir = File_Compare.getDir(dest);
                	String FileName = File_Compare.getFileName(dest);
                	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
                	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
 	            }else{
    	            if (resultStr.equals("true")) {
    	                result = true;
    	            } 
	            }      
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
            resultStr=""; 
            System.out.println(ZIP.NowTime()+"fileCompare_Deviation result："+result);
            return result;
    	}

    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_Deviation(String srcPath, String destPath,String[] StarWith) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	arrStr2[i] = fileCompare_Deviation(src,dest);
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare(String srcPath, String destPath,String[] StarWith) {

    	File srcDirec =new File(srcPath);
    	if(!srcDirec.isDirectory()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(srcPath)+" srcfile is not Directory");
    		Assert.assertTrue(CommonFile.fileMsg(srcPath)+" is not Directory",false);
    	}
    	File destDirec =new File(destPath);
    	if(!destDirec.isDirectory()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(destPath)+" srcfile is not Directory");
    		Assert.assertTrue(CommonFile.fileMsg(destPath)+" is not Directory",false);
    	}
    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else{
        	arrStr2[i] = fileCompare(src,dest);
        	}
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }

    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名后缀（endWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_endWith(String srcPath, String destPath,String[] endWith) {

    	boolean[] arrStr2 = new boolean[endWith.length+1];	
        for(int i = 0;i < endWith.length;i++){
        	String srcName  = getfileName_endWith( srcPath,  endWith[i]);
        	String destName = getfileName_endWith( destPath,  endWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else{
        	arrStr2[i] = fileCompare(src,dest);
        	}
        }
        for(int i = 0;i < endWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名后缀（endWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_endWith(String srcPath, String destPath,String[] endWith, String excludeSheetNames) {

    	boolean[] arrStr2 = new boolean[endWith.length+1];	
        for(int i = 0;i < endWith.length;i++){
        	String srcName  = getfileName_endWith( srcPath,  endWith[i]);
        	String destName = getfileName_endWith( destPath,  endWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else{
        		arrStr2[i] = fileCompare_excludePartSheet(src,dest,excludeSheetNames);
        	}
        }
        for(int i = 0;i < endWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名后缀（endWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompareStream_endWith(String srcPath, String destPath,String[] endWith, String excludeSheetNames) {

    	File srcFile =new File(srcPath);
    	File destFile =new File(destPath);

    	if(!srcFile.isDirectory()){
    		System.out.println(ZIP.NowTime()+srcFile+" srcfile is not Directory");
    		Assert.assertTrue(srcFile+" is not Directory",false);
    	}
    	if(!destFile.isDirectory()){
    		System.out.println(ZIP.NowTime()+destFile+" srcfile is not Directory");
    		Assert.assertTrue(destFile+" is not Directory",false);
    	}
    	boolean[] arrStr2 = new boolean[endWith.length+1];	
        for(int i = 0;i < endWith.length;i++){
        	String srcName  = getfileName_endWith( srcPath,  endWith[i]);
        	String destName = getfileName_endWith( destPath,  endWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else if(srcName.toLowerCase().endsWith(".doc") || srcName.toLowerCase().endsWith(".docx")){
        		arrStr2[i] =  Word.compareWord(src,dest);
        	}else{
        		arrStr2[i] = fileCompareStream_excludePartSheet(src,dest,excludeSheetNames);
        	}
        }
        for(int i = 0;i < endWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_Stream(String srcPath, String destPath,String[] StarWith) {

    	File srcDirec =new File(srcPath);
    	if(!srcDirec.isDirectory()){
    		System.out.println(ZIP.NowTime()+srcPath+" srcfile is not Directory");
    		Assert.assertTrue(srcPath+" is not Directory",false);
    	}
    	File destDirec =new File(destPath);
    	if(!destDirec.isDirectory()){
    		System.out.println(ZIP.NowTime()+destPath+" srcfile is not Directory");
    		Assert.assertTrue(destPath+" is not Directory",false);
    	}
    	
    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);

        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv") || srcName.toLowerCase().endsWith(".txt")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else{
        		arrStr2[i] = fileCompare_Stream(src,dest);
        	}
        	
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_Stream(String srcPath, String destPath,String[] StarWith, String excludeSheetNames) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);

        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv") || srcName.toLowerCase().endsWith(".txt")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else{
        		arrStr2[i] = fileCompareStream_excludePartSheet(src,dest,excludeSheetNames);
        	}
        	
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/

    public static boolean fileCompare_Stream_SubString(String srcPath, String destPath,String[] StarWith) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv") || srcName.toLowerCase().endsWith(".txt")){
        		arrStr2[i] =  csvFileIsEqual_SubString(src,dest);
        	}else{
        	arrStr2[i] = fileCompare_Stream(src,dest);
        	}
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    /******************************************************************************
	 * 功能名称 ："xlsx文件" 比较 两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
	 * 功能简述 ："xlsx文件" 比较两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 创建日期 : 2014.08.16
	 * 修改日期：
	 *******************************************************************************/
    //两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
    public  static  boolean  SameNameCompareInPath(String standard, String export)
	{
    	standard = new File(standard).getAbsolutePath();
    	export = new File(export).getAbsolutePath();
		File dir = new File(standard);
		String[][] arrStr = new String[2][500];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			Assert.fail(CommonFile.fileMsg(standard)+" is not Directory");
			return false;
		}
		int filesNumber = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				File dir2 = new File(path);
				File filelist2[] = dir2.listFiles();
				for (File b : filelist2) 
				{
					String path2 = b.getAbsolutePath();
					if (path2.endsWith(".svn")) {
						continue;
					}
					if (b.isDirectory()) {
						continue;	
					}
					/* 当为文件时，递归调用该方法 */
					if (b.isFile()) {
						if(b.getName().startsWith("~$")){
							continue;
						}else if(b.getName().toLowerCase().endsWith(".xlsx") ||b.getName().toLowerCase().endsWith(".csv")){
							arrStr[0][filesNumber] = a.getName();
							arrStr[1][filesNumber] = b.getName();
							filesNumber++;
						}else if(b.getName().toLowerCase().endsWith(".txt")||b.getName().toLowerCase().endsWith(".xml")){
							arrStr[0][filesNumber] = a.getName();
							arrStr[1][filesNumber] = b.getName();
							filesNumber++;
						}else if(b.getName().toLowerCase().endsWith(".mif")||b.getName().toLowerCase().endsWith(".mid")){
							arrStr[0][filesNumber] = a.getName();
							arrStr[1][filesNumber] = b.getName();
							filesNumber++;
						}else if(b.getName().toLowerCase().endsWith(".kml")){
							arrStr[0][filesNumber] = a.getName();
							arrStr[1][filesNumber] = b.getName();
							filesNumber++;
						}else if(b.getName().toLowerCase().endsWith(".doc") || b.getName().toLowerCase().endsWith(".docx")){
							arrStr[0][filesNumber] = a.getName();
							arrStr[1][filesNumber] = b.getName();
							filesNumber++;
				    	}else{
							continue;
						}
					}
				}
	
			}
			/* 当为文件时，递归调用该方法 */
			if (a.isFile()) {
				if(a.getName().startsWith("~$")){
					continue;
				}else if(a.getName().toLowerCase().endsWith(".xlsx") ||a.getName().toLowerCase().endsWith(".csv")){
					arrStr[0][filesNumber] ="0";
					arrStr[1][filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".txt")||a.getName().toLowerCase().endsWith(".xml")){
					arrStr[0][filesNumber] ="0";
					arrStr[1][filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".mif")||a.getName().toLowerCase().endsWith(".mid")){
					arrStr[0][filesNumber] ="0";
					arrStr[1][filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".kml")){
					arrStr[0][filesNumber] ="0";
					arrStr[1][filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".doc") ||a.getName().toLowerCase().endsWith(".docx")){
					arrStr[0][filesNumber] ="0";
					arrStr[1][filesNumber] = a.getName();
					filesNumber++;	
				}else{
					continue;
				}
			}
		}
		System.out.println(ZIP.NowTime()+"filesNumber："+filesNumber);
    	if(filesNumber == 0){
    		return false;
    	}
    	boolean[] arrStr2 = new boolean[filesNumber];	
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr[0][i].equals("0")){
            	String src  = standard + "/" + arrStr[1][i];
            	String dest = export + "/" + arrStr[1][i];
            	arrStr2[i] = fileCompare(src,dest);
        	}else{
            	String src  = standard + "/"+arrStr[0][i]+ "/"+ arrStr[1][i];
            	String dest = export + "/"+arrStr[0][i]+ "/"+ arrStr[1][i];
            	arrStr2[i] = fileCompare(src,dest);
        	}

        }
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}
    /******************************************************************************
	 * 功能名称 ："xlsx文件" 比较 两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
	 * 功能简述 ："xlsx文件" 比较两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 创建日期 : 2014.08.16
	 * 修改日期：
	 *******************************************************************************/
    //两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
    public  static  boolean  SameNameCompareInPath_Stream(String standard, String export, String excludePartSheet)
	{
    	standard = new File(standard).getAbsolutePath();
    	export = new File(export).getAbsolutePath();
		File dir = new File(standard);
		String[] arrStr = new String[500];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return false;
		}
		int filesNumber = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				continue;
			}
			/* 当为文件时，递归调用该方法 */
			if (a.isFile()) {
				if(a.getName().startsWith("~$")){
					continue;
				}else if(a.getName().toLowerCase().endsWith(".xlsx") ||a.getName().toLowerCase().endsWith(".csv")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".txt")||a.getName().toLowerCase().endsWith(".xml")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".doc")||a.getName().toLowerCase().endsWith(".docx")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else{
					continue;
				}
			}
		}
		System.out.println(ZIP.NowTime()+"filesNumber："+filesNumber);
    	if(filesNumber == 0){
    		return false;
    	}
    	boolean[] arrStr2 = new boolean[filesNumber];	
        for(int i = 0;i < filesNumber;i++){
        	String src  = standard + "/" + arrStr[i];
        	String dest = export + "/" + arrStr[i];
        	arrStr2[i] = FileCompare.fileCompareStream_excludePartSheet(src,dest,excludePartSheet);       	
        }
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}
    /******************************************************************************
	 * 功能名称 ："xlsx文件" 比较 两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
	 * 功能简述 ："xlsx文件" 比较两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 创建日期 : 2014.08.16
	 * 修改日期：
	 *******************************************************************************/
    //两个文件夹底下有多个文件，比较对应名称相同的xlsx文件
    public  static  boolean  SameNameCompareInPath_Stream(String standard, String export)
	{
    	standard = new File(standard).getAbsolutePath();
    	export = new File(export).getAbsolutePath();
		File dir = new File(standard);
		String[] arrStr = new String[500];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return false;
		}
		int filesNumber = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				continue;
			}
			/* 当为文件时，递归调用该方法 */
			if (a.isFile()) {
				if(a.getName().startsWith("~$")){
					continue;
				}else if(a.getName().toLowerCase().endsWith(".xlsx") ||a.getName().toLowerCase().endsWith(".csv")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".txt")||a.getName().toLowerCase().endsWith(".xml")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".doc")||a.getName().toLowerCase().endsWith(".docx")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else{
					continue;
				}
			}
		}
		System.out.println(ZIP.NowTime()+"filesNumber："+filesNumber);
    	if(filesNumber == 0){
    		return false;
    	}
    	boolean[] arrStr2 = new boolean[filesNumber];	
        for(int i = 0;i < filesNumber;i++){
        	String src  = standard + "/" + arrStr[i];
        	String dest = export + "/" + arrStr[i];
        	arrStr2[i] = fileCompare_Stream(src,dest);
        }
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在时间戳文件夹，且该文件夹下按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompareMAX_Deviation(String srcPath, String destPath,String[] StarWith) {
    	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
        return fileCompare_Deviation( srcPath,  destPath, StarWith);
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在时间戳文件夹，且该文件夹下按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompareMAX(String srcPath, String destPath,String[] StarWith) {
    	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
        return fileCompare( srcPath,  destPath, StarWith);
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果---乱序
	 * 功能简述 ： 在srcPath、destPath存在时间戳文件夹，且该文件夹下按照文件名后缀（endWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompareMAX_endWith(String srcPath, String destPath,String[] endWith) {
   	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
   	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
     return fileCompare_endWith( srcPath,  destPath, endWith);
   }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在时间戳文件夹，且该文件夹下按照同名文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean SameNameCompareInPathMAX(String srcPath, String destPath) {
   	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
   	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
       return SameNameCompareInPath( srcPath,  destPath);
   }
    public static boolean SameNameCompareInPath2MAX(String srcPath, String destPath) {
      	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
      	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
      	 
      	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
     	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
          return SameNameCompareInPath( srcPath,  destPath);
    }
    public static boolean SameNameCompareInPath2MAX(String srcPath, String destPath,int[] SubString) {
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
     	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
     	 
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
         return SameNameCompareInPath_SubString( srcPath,  destPath,SubString);
   }
    public static boolean SameNameCompareInPath3MAX(String srcPath, String destPath) {
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
     	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
     	 
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
    	 
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
         return SameNameCompareInPath( srcPath,  destPath);
     }

    public static boolean SameNameCompareInPath2MAX_Stream(String srcPath, String destPath) {
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
     	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
     	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
         return SameNameCompareInPath_Stream( srcPath,  destPath);
     }

    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在两层时间戳文件夹，且该文件夹下按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare2MAX(String srcPath, String destPath,String[] StarWith) {
   	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
   	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
   	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
   	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
       return fileCompare( srcPath,  destPath, StarWith);
   }
    public static boolean fileCompare2MAX_Stream(String srcPath, String destPath,String[] StarWith) {
      	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
      	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
      	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
      	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
          return fileCompare_Stream( srcPath,  destPath, StarWith);
      }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在两层时间戳文件夹，且该文件夹下按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare3MAX(String srcPath, String destPath,String[] StarWith) {
   	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
   	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
	 srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
   	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
   	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
   	destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
       return fileCompare( srcPath,  destPath, StarWith);
   }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在时间戳文件夹，且该文件夹下按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompareMAX_Stream(String srcPath, String destPath,String[] StarWith) {
        if(!(new File(srcPath).isDirectory())){
        	Assert.assertTrue(srcPath+"目录不存在",false);
        }
        if(!(new File(destPath).isDirectory())){
        	Assert.assertTrue(destPath+"目录不存在",false);
        } 
    	srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
        return fileCompare_Stream( srcPath,  destPath, StarWith);
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath存在时间戳文件夹，且该文件夹下按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompareMAX_Stream(String srcPath, String destPath,String[] StarWith, String excludeSheetNames) {
        if(!(new File(srcPath).isDirectory())){
        	Assert.assertTrue(srcPath+"目录不存在",false);
        }
        if(!(new File(destPath).isDirectory())){
        	Assert.assertTrue(destPath+"目录不存在",false);
        } 
    	srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	 destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
        return fileCompare_Stream( srcPath,  destPath, StarWith,excludeSheetNames);
    }

    public static boolean fileCompareMAX_Stream_SubString(String srcPath, String destPath,String[] StarWith) {
        if(!(new File(srcPath).isDirectory())){
        	Assert.assertTrue(srcPath+"目录不存在",false);
        }
        if(!(new File(destPath).isDirectory())){
        	Assert.assertTrue(destPath+"目录不存在",false);
        }
    	srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
    	destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
        return fileCompare_Stream_SubString( srcPath,  destPath, StarWith);
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 两个文件的比较，以src为标准文件，dest为目标文件。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      caoyuelong
	 * 创建日期 : 2012.10.15
	 * 修改日期：
	 *******************************************************************************/
    public static boolean Get2fileAndfileCompare(String srcpath, String destpath,String regex,String startwith) {
        String[] StartWith ={startwith};
        return FileCompare.fileCompareMAX(srcpath, destpath,StartWith);
    }
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 两个文件的比较，以src为标准文件，dest为目标文件。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      caoyuelong
	 * 创建日期 : 2012.10.15
	 * 修改日期：
	 *******************************************************************************/
    public static boolean fileCompare_Stream(String src, String dest) {
    	resultStr="";
    	File srcFile =new File(src);
    	File destFile =new File(dest);

    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+src+" srcfile is not File");
    		Assert.assertTrue(src+"is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+dest+" destfile is not File");
    		Assert.assertTrue(dest+"is not File",false);
    	}
        boolean result = false;

        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/ExcelCompare.jar -mode stream -file " + "\"" + src + "\"" + " " + "\"" + dest +"\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            addStreamShowThread(process.getInputStream());
            process.waitFor();
            process.destroy();
            System.out.println(ZIP.NowTime()+"fileCompare_Stream resultStr："+resultStr);
            if(resultStr == null){
            	String FileDir = File_Compare.getDir(dest);
            	String FileName = File_Compare.getFileName(dest);
            	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
            	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
            }else if (resultStr.equals("")){
            	String FileDir = File_Compare.getDir(dest);
            	String FileName = File_Compare.getFileName(dest);
            	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
            	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
            }else{
	            if (resultStr.equals("true")) {
	                result = true;
	            } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
        resultStr=""; 
        if(result==false){
        	return File_Compare.fileCompare_Random_Stream(src,dest);
        }
        resultStr=""; 
        System.out.println(ZIP.NowTime()+"fileCompare_Stream result："+result);
        return result;
    }
 
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
	 *******************************************************************************/
    public static boolean fileCompareStreamMAX_excludePartSheet(String srcPath, String destPath ,String[] StarWith,String excludeSheetNames) 
    {
   	    srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
	    destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
    	return fileCompare_excludePartSheet_Stream( srcPath,  destPath , StarWith, excludeSheetNames);
    }
    public static boolean fileCompare_excludePartSheet_Stream(String srcPath, String destPath,String[] StarWith, String excludeSheetNames) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);

        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv") || srcName.toLowerCase().endsWith(".txt")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	} else if(srcName.toLowerCase().endsWith(".doc") || srcName.toLowerCase().endsWith(".docx")){
        		arrStr2[i] =  Word.compareWord(src,dest);
        	}else{
        		arrStr2[i] = fileCompare_excludePartSheet_Stream(src,dest,excludeSheetNames);
        	}
        	
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }
    
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
	 *******************************************************************************/
    public static boolean fileCompare_excludePartSheet(String srcPath, String destPath,String[] StarWith, String excludeSheetNames) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);

        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	if(srcName.toLowerCase().endsWith(".csv") || srcName.toLowerCase().endsWith(".txt")){
        		arrStr2[i] =  csvFileIsEqual(src,dest);
        	}else if(srcName.toLowerCase().endsWith(".doc") || srcName.toLowerCase().endsWith(".docx")){
        		arrStr2[i] =  Word.compareWord(src,dest);
        	}else{
        		arrStr2[i] = fileCompare_excludePartSheet(src,dest,excludeSheetNames);
        	}
        	
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
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
    public static boolean fileCompare_excludePartSheet_Stream(String srcPath, String destPath, String excludeSheetNames) 
    {
    	resultStr="";
        System.out.println(ZIP.NowTime()+"src file："+srcPath);
        System.out.println(ZIP.NowTime()+"dest file："+destPath);
        boolean result = false;
        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/excelcompare.jar -mode stream -file "+ "\"" + srcPath + "\"" + " " + "\"" + destPath + "\"" + " " + "\"" + excludeSheetNames+ "\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            addStreamShowThread(process.getInputStream());
            process.waitFor();
            process.destroy();
            System.out.println(ZIP.NowTime()+"fileCompare_excludePartSheet resultStr："+resultStr);
            if (resultStr.equals("true")) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
        resultStr="";
        if(result==false){
        	return File_Compare.fileCompareRandom_excludePartSheet_Stream( srcPath,  destPath,  excludeSheetNames);
        }
        System.out.println(ZIP.NowTime()+"fileCompare_excludePartSheet result："+result);
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
    public static boolean fileCompare_excludePartSheet(String srcPath, String destPath, String excludeSheetNames) 
    {
    	resultStr="";
        System.out.println(ZIP.NowTime()+"src file："+srcPath);
        System.out.println(ZIP.NowTime()+"dest file："+destPath);
        boolean result = false;
        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/excelcompare.jar -file "+ "\"" + srcPath + "\"" + " " + "\"" + destPath + "\"" + " " + "\"" + excludeSheetNames+ "\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            addStreamShowThread(process.getInputStream());
            process.waitFor();
            process.destroy();
            System.out.println(ZIP.NowTime()+"fileCompare_excludePartSheet resultStr："+resultStr);
            if (resultStr.equals("true")) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
        resultStr="";
        System.out.println(ZIP.NowTime()+"fileCompare_excludePartSheet result："+result);
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
    public static boolean fileCompareStream_excludePartSheet(String srcPath, String destPath, String excludeSheetNames) 
    {
    	resultStr="";
        System.out.println(ZIP.NowTime()+"src file："+srcPath);
        System.out.println(ZIP.NowTime()+"dest file："+destPath);
        boolean result = false;
        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/excelcompare.jar -mode stream -file "+ "\"" + srcPath + "\"" + " " + "\"" + destPath + "\"" + " " + "\"" + excludeSheetNames+ "\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            addStreamShowThread(process.getInputStream());
            process.waitFor();
            process.destroy();
            System.out.println(ZIP.NowTime()+"fileCompare_excludePartSheet resultStr："+resultStr);

            if ("true".equals(resultStr)) {
                result = true;
            }else if ("false".equals(resultStr)){
            	result = false;
            }else{
            	String FileDir = File_Compare.getDir(destPath);
            	String FileName = File_Compare.getFileName(destPath);
            	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
            	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
        resultStr="";
        if(result==false){
        	return File_Compare.fileCompareRandom_excludePartSheet_Stream(srcPath,destPath,excludeSheetNames);
        }
        System.out.println(ZIP.NowTime()+"fileCompare_excludePartSheet result："+result);
        return result;
    } 
  
    /******************************************************************************
	 * 功能名称 ：返回两个excel文件比较的结果
	 * 功能简述 ：比较两个除了部分sheet页不比Excel
	 * 所属类 ：     AW_ExcelCheck
	 * 作者：           shibingwu
	 * 创建日期 : 2013.11.30
	 * 修改日期：
	 *******************************************************************************/
    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
    public static boolean fileCompareMAX_ByFileType_excludePartSheet(String srcPath, String destPath ,String fileType,String excludeSheetNames) 
    {
   	    srcPath = FileHandle.maxTimesPath(srcPath).getAbsolutePath();
	    destPath = FileHandle.maxTimesPath(destPath).getAbsolutePath();
    	return fileCompare_ByFileType_excludePartSheet( srcPath,  destPath , fileType, excludeSheetNames);
    }
    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
    public static boolean fileCompare_ByFileType_excludePartSheet(String srcPath, String destPath ,String fileType,String excludeSheetNames) 
    {
    	resultStr="";
    	File srcFile =new File(srcPath);
    	File destFile =new File(destPath);
        boolean result = false;
    	if(!srcFile.isDirectory()){
    		System.out.println(ZIP.NowTime()+srcFile+" srcfile is not Directory");
    		Assert.assertTrue(srcFile+" is not Directory",false);
    	}
    	if(!destFile.isDirectory()){
    		System.out.println(ZIP.NowTime()+destFile+" srcfile is not Directory");
    		Assert.assertTrue(destFile+" is not Directory",false);
    	}
        String srcPath2 = getMaxFile_Type(srcPath, fileType);
        String destPath2 = getMaxFile_Type(destPath, fileType);
        System.out.println(ZIP.NowTime()+"src file："+srcPath2);
        System.out.println(ZIP.NowTime()+"dest file："+destPath2);

        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/excelcompare.jar -file "+ "\""+ srcPath2 + "\"" + " " + "\""+ destPath2 + "\""+ " " + "\"" + excludeSheetNames+ "\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            addStreamShowThread(process.getInputStream());
            process.waitFor();
            process.destroy();
            System.out.println(ZIP.NowTime()+"fileCompare_ByFileType_excludePartSheet resultStr："+resultStr);
            if (resultStr.equals("true")) {
                result = true;
            }
            if(resultStr.equals("")){
            	String FileDir = File_Compare.getDir(destPath);
            	String FileName = File_Compare.getFileName(destPath);
            	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
            	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
	        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
        resultStr="";
        if(result == false){
        	return File_Compare.fileCompareRandom_excludePartSheet_Stream( srcPath2,  destPath2, excludeSheetNames); 
        }
        System.out.println(ZIP.NowTime()+"fileCompare_ByFileType_excludePartSheet result："+result);
        return result;
    }

    
    /******************************************************************************
	 * 功能名称 ：返回两个excel文件比较的结果---存在精度问题，谨慎使用效率低
	 * 功能简述 ：比较两个除了部分sheet页不比Excel
	 * 所属类 ：     AW_ExcelCheck
	 * 作者：           shibingwu
	 * 创建日期 : 2013.11.30
	 * 修改日期：
	 *******************************************************************************/
    //注意：excludeSheetNames是不包含的sheet页得值例如（excludeSheetNames="首页,某页,尾页"）
    public static boolean fileCompareDeviation_ByFileType_excludePartSheet(String srcPath, String destPath ,String fileType,String excludeSheetNames) 
    {
    	resultStr="";
        boolean result = false;
        srcPath = getMaxFile_Type(srcPath, fileType);
        destPath = getMaxFile_Type(destPath, fileType);
        System.out.println(ZIP.NowTime()+"src file："+srcPath);
        System.out.println(ZIP.NowTime()+"dest file："+destPath);
        String cmd = "java -Xms512m -Xmx1024m -jar " + ConstUrl.getProjectHomePath() + "/CI/excel_jar/ExcelCompare_Deviation.jar -area true -file "+ "\"" + srcPath + "\"" + " " + "\"" + destPath + "\"" + " " + "\"" +excludeSheetNames + "\"";
        System.out.println(ZIP.NowTime()+cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            addStreamShowThread(process.getInputStream());
            process.waitFor();
            process.destroy();
            System.out.println(ZIP.NowTime()+"fileCompareDeviation_ByFileType_excludePartSheet resultStr："+resultStr);
            if (resultStr.equals("true")) {
                result = true;
            }
            if(resultStr.equals("")){
            	String FileDir = File_Compare.getDir(destPath);
            	String FileName = File_Compare.getFileName(destPath);
            	String Maxfile = File_Compare.getMaxFile_Type(FileDir, FileName);
            	result = File_Compare.getResultFlage(FileDir, FileName, Maxfile);
	        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //比较一次后需要将resultStr设置成空字符串，不然就会返回对此比较的结果的字符串连接（这句话非常重要）
        resultStr="";
        System.out.println(ZIP.NowTime()+"fileCompareDeviation_ByFileType_excludePartSheet result："+result);
        return result;
    }
    /******************************************************************************
	 * 功能名称 ："csv文件" 比较 两个文件夹底下有多个文件，比较对应名称相同的csv文件
	 * 功能简述 ："csv文件" 比较两个文件夹底下有多个文件，比较对应名称相同的csv文件
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：            shibingwu
	 * 创建日期 : 2014.08.27
	 * 修改日期：
	 *******************************************************************************/
    //两个文件夹底下有多个文件，比较对应名称相同的csv文件
    public  static  boolean  SameNameCompareInPath_SubString(String standard, String export)
	{
    	standard = new File(standard).getAbsolutePath();
    	export = new File(export).getAbsolutePath();
		File dir = new File(standard);
		String[] arrStr = new String[100];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return false;
		}
		int filesNumber = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				continue;
			}
			/* 当为文件时，递归调用该方法 */
			if (a.isFile()) {
				if(a.getName().startsWith("~$")){
					continue;
				}else{
					arrStr[filesNumber] = a.getName();;
					filesNumber++;	
				}
			}
		}
		System.out.println(ZIP.NowTime()+"filesNumber："+filesNumber);
    	if(filesNumber == 0){
    		return false;
    	}
    	boolean[] arrStr2 = new boolean[filesNumber];	
        for(int i = 0;i < filesNumber;i++){
        	String src  = standard + "/" + arrStr[i];
        	String dest = export + "/" + arrStr[i];
        	arrStr2[i] = csvFileIsEqual_SubString(src,dest);
        }
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}
    //两个文件夹底下有多个文件，比较对应名称相同的csv文件
    public  static  boolean  SameNameCompareInPath_SubString(String standard, String export,int[] O)
	{
    	standard = new File(standard).getAbsolutePath();
    	export = new File(export).getAbsolutePath();
		File dir = new File(standard);
		String[] arrStr = new String[100];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return false;
		}
		int filesNumber = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				continue;
			}
			/* 当为文件时，递归调用该方法 */
			if (a.isFile()) {
				if(a.getName().startsWith("~$")){
					continue;
				}else{
					arrStr[filesNumber] = a.getName();;
					filesNumber++;	
				}
			}
		}
		System.out.println(ZIP.NowTime()+"filesNumber："+filesNumber);
    	if(filesNumber == 0){
    		return false;
    	}
    	boolean[] arrStr2 = new boolean[filesNumber];	
        for(int i = 0;i < filesNumber;i++){
        	String src  = standard + "/" + arrStr[i];
        	String dest = export + "/" + arrStr[i];
        	arrStr2[i] = csvFileIsEqual_SubString(src,dest,O);
        }
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}
    /******************************************************************************
	 * 功能名称 ：返回excel内容比较结果
	 * 功能简述 ： 在srcPath、destPath按照文件名前缀（StarWith）动态匹配文件进行比较，支持目录下多文件情况。
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      shibingwu
	 * 创建日期 : 2014.07.16
	 * 修改日期：
	 *******************************************************************************/
    public static boolean csvFileIsEqual_SubString(String srcPath, String destPath,String[] StarWith,int N) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	arrStr2[i] = csvFileIsEqual_SubString(src,dest,N);
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }   
    public static boolean csvFileIsEqual_SubString(String srcPath, String destPath,String[] StarWith) {

    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( srcPath,  StarWith[i]);
        	String destName = getfileName( destPath,  StarWith[i]);
        	String src  =  new File(srcPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(destPath).getAbsolutePath() + "/" + destName;
        	arrStr2[i] = csvFileIsEqual_SubString(src,dest);
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
    }   
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同
	 * 功能简述 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 创建日期 : 2012.11.5
	 * 修改日期：lizhen 修改 2014.7.7
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual(String stanrdPath, String SrcPath) 
	{
   	   System.out.println(ZIP.NowTime()+"File Baseline:"+ stanrdPath);
  	   System.out.println(ZIP.NowTime()+"File Result:"+ SrcPath);
      	File srcFile =new File(stanrdPath);
    	File destFile =new File(SrcPath);
    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(stanrdPath)+" srcfile is not File");
    		Assert.assertTrue(CommonFile.fileMsg(stanrdPath)+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+CommonFile.fileMsg(SrcPath)+" destfile is not File");
    		Assert.assertTrue(CommonFile.fileMsg(SrcPath)+" is not File",false);
    	}
          String sdate = getCurrentDate();
          boolean bRet = false;
          int nPath = -1;
          int LPath = SrcPath.lastIndexOf(".");
     	  if(-1 == SrcPath.lastIndexOf("/")){
      		  nPath = SrcPath.lastIndexOf("\\");
      	  }else{
      		  nPath = SrcPath.lastIndexOf("/");
      	  }
     	  String resultPath = SrcPath.substring(0, nPath+1);
      	  String reportname = SrcPath.substring(nPath+1,LPath); 
      	  try {
      		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
      			return false;
      		 }
          } catch ( FileNotFoundException e ){
         	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
              e.printStackTrace();
          } catch (IOException e) { 
         	  System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
         	  Pause.pause(5000);
          	  try {
           		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
           			return false;
           		 }
               }catch (IOException ee) {
            	   System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
            	   ee.printStackTrace();  
               }
          } 
         try {
          	 /*从这儿开始，对两个文件具体内容作比较*/
             int Rownum = 0;
             BufferedWriter writerReport = null;
          	 CSVCmp csvcompare = new CSVCmp();
          	 boolean rsflag = csvcompare.csv_Compare(stanrdPath, SrcPath);
             if(rsflag == false){
                 Rownum = csvcompare.getLineNo() + 1; //获取找不到的行号
                 String recsrc = csvcompare.getLineStr() ; //获取找不到的行
                 writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname+ "_"+sdate+ "VerificationReport(false).csv" ))) ;
                 writerReport.write("Results file line "+ Rownum+ ":" + "\n");
                 writerReport.write(recsrc + "\n");
                 writerReport.write("Can not find the file in the baseline."+ "\n");
                 System.out.println(ZIP.NowTime()+"Results file line "+ Rownum+ " detail:" + recsrc + ",Can not find the file in the baseline\n");
                 writerReport.flush();
                 writerReport.close();
                 return false;
             } else {
                 //对比结果OK
                 bRet = true;
                 System.out.println(ZIP.NowTime()+"compare is ok");
         	     writerReport = new BufferedWriter(new FileWriter(new File(resultPath +reportname+ "_"+ sdate + "VerificationReport(true).csv"  ))) ;
           	     writerReport.write("compare is ok");
           	     writerReport.close();
           	     return bRet;
             }
         } catch ( FileNotFoundException e ){
        	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
             e.printStackTrace();
         } catch (IOException e) { 
        	 System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             e.printStackTrace();  
         }  
         return bRet;
	}
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 创建日期 : 2012.11.5
	 * 修改日期：lizhen 修改 2014.7.7
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqualMAX_excludeStartWith(String stanrdPath, String SrcPath, String[] StarWith,String[] excludeStartWith) 
	{
    	stanrdPath = FileHandle.maxTimesPath(stanrdPath).getAbsolutePath();
    	SrcPath = FileHandle.maxTimesPath(SrcPath).getAbsolutePath();
        return csvFileIsEqual_excludeStartWith( stanrdPath,  SrcPath,  StarWith, excludeStartWith);
	}
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 创建日期 : 2012.11.5
	 * 修改日期：lizhen 修改 2014.7.7
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual_excludeStartWith(String stanrdPath, String SrcPath, String[] StarWith,String[] excludeStartWith) 
	{
    	File srcDirec =new File(stanrdPath);
    	if(!srcDirec.isDirectory()){
    		System.out.println(ZIP.NowTime()+stanrdPath+" srcfile is not Directory");
    		Assert.assertTrue(stanrdPath+" is not Directory",false);
    	}
    	File destDirec =new File(SrcPath);
    	if(!destDirec.isDirectory()){
    		System.out.println(ZIP.NowTime()+SrcPath+" srcfile is not Directory");
    		Assert.assertTrue(SrcPath+" is not Directory",false);
    	}
    	boolean[] arrStr2 = new boolean[StarWith.length+1];	
        for(int i = 0;i < StarWith.length;i++){
        	String srcName  = getfileName( stanrdPath,  StarWith[i]);
        	String destName = getfileName( SrcPath,  StarWith[i]);
        	String src  =  new File(stanrdPath).getAbsolutePath() + "/" + srcName;
        	String dest =  new File(SrcPath).getAbsolutePath() + "/" + destName;  
        	arrStr2[i] =  csvFileIsEqual_excludeStartWith(src,dest,excludeStartWith);
     
        }
        for(int i = 0;i < StarWith.length;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 创建日期 : 2012.11.5
	 * 修改日期：lizhen 修改 2014.7.7
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual_excludeStartWith(String stanrdPath, String SrcPath,String[] excludeStartWith) 
	{
   	   System.out.println(ZIP.NowTime()+"File Baseline:"+ stanrdPath);
  	   System.out.println(ZIP.NowTime()+"File Result:"+ SrcPath);
      	File srcFile =new File(stanrdPath);
    	File destFile =new File(SrcPath);
    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+stanrdPath+" srcfile is not File");
    		Assert.assertTrue(stanrdPath+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+SrcPath+" destfile is not File");
    		Assert.assertTrue(SrcPath+" is not File",false);
    	}
          String sdate = getCurrentDate();
          boolean bRet = false;
          int nPath = -1;
          int LPath = SrcPath.lastIndexOf(".");
     	  if(-1 == SrcPath.lastIndexOf("/")){
      		  nPath = SrcPath.lastIndexOf("\\");
      	  }else{
      		  nPath = SrcPath.lastIndexOf("/");
      	  }
     	  String resultPath = SrcPath.substring(0, nPath+1);
      	  String reportname = SrcPath.substring(nPath+1,LPath); 
      	  try {
      		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
      			return false;
      		 }
          } catch ( FileNotFoundException e ){
         	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
              e.printStackTrace();
          } catch (IOException e) { 
         	  System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
         	  Pause.pause(5000);
          	  try {
           		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
           			return false;
           		 }
               }catch (IOException ee) {
            	   System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
            	   ee.printStackTrace();  
               }
          } 
         try {
          	 /*从这儿开始，对两个文件具体内容作比较*/
             int Rownum = 0;
             BufferedWriter writerReport = null;
          	 CSVCmp csvcompare = new CSVCmp();
          	 boolean rsflag = csvcompare.csv_Compare(stanrdPath, SrcPath,excludeStartWith);
             if(rsflag == false){
                 Rownum = csvcompare.getLineNo() + 1; //获取找不到的行号
                 String recsrc = csvcompare.getLineStr() ; //获取找不到的行
                 writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname+ "_"+sdate+ "VerificationReport(false).csv" ))) ;
                 writerReport.write("Results file line "+ Rownum+ ":" + "\n");
                 writerReport.write(recsrc + "\n");
                 writerReport.write("Can not find the file in the baseline."+ "\n");
                 System.out.println(ZIP.NowTime()+"Results file line "+ Rownum+ " detail:" + recsrc + ",Can not find the file in the baseline\n");
                 writerReport.flush();
                 writerReport.close();
                 return false;
             } else {
                 //对比结果OK
                 bRet = true;
                 System.out.println(ZIP.NowTime()+"compare is ok");
         	     writerReport = new BufferedWriter(new FileWriter(new File(resultPath +reportname+ "_"+ sdate + "VerificationReport(true).csv"  ))) ;
           	     writerReport.write("compare is ok");
           	     writerReport.close();
           	     return bRet;
             }
         } catch ( FileNotFoundException e ){
        	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
             e.printStackTrace();
         } catch (IOException e) { 
        	 System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             e.printStackTrace();  
         }  
         return bRet;
	}
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同,去掉前面的第一个逗号前的字符串，适配KPI忙时
	 * 功能简述 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 修改日期：lizhen 修改 2014.7.14
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual_SubString(String stanrdPath, String SrcPath)
	{
    	 System.out.println(ZIP.NowTime()+"File Baseline:"+ stanrdPath);
      	 System.out.println(ZIP.NowTime()+"File Result:"+ SrcPath);
       	File srcFile =new File(stanrdPath);
    	File destFile =new File(SrcPath);
    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+stanrdPath+" srcfile is not File");
    		Assert.assertTrue(stanrdPath+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+SrcPath+" destfile is not File");
    		Assert.assertTrue(SrcPath+" is not File",false);
    	}
         boolean bRet = false;
         //获取时间戳字符串
         String sdate = getCurrentDate() ;
         //获取文件路径和文件名
         int nPath = -1;
         int LPath = SrcPath.lastIndexOf(".");
     	 if(-1 == SrcPath.lastIndexOf("/")){
      		 nPath = SrcPath.lastIndexOf("\\");
      	 }else{
      		 nPath = SrcPath.lastIndexOf("/");
      	 }
     	 String resultPath = SrcPath.substring(0, nPath+1);
      	 String reportname = SrcPath.substring(nPath+1,LPath);      	  
      	 System.out.println(ZIP.NowTime()+"ResultPath:"+ resultPath);
      	 System.out.println(ZIP.NowTime()+"Reportname:"+reportname);
     	  try {
       		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
       			return false;
       		 }
           } catch ( FileNotFoundException e ){
          	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
               e.printStackTrace();
           } catch (IOException e) { 
          	  System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
          	  Pause.pause(5000);
           	  try {
            		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
            			return false;
            		 }
                }catch (IOException ee) {
             	   System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             	   ee.printStackTrace();  
                }
           } 
      	 //对比文件行数
         try {
          	 /*从这儿开始，对两个文件具体内容作比较*/
             int Rownum = 0;
             BufferedWriter writerReport = null;
          	 CSVCmp csvcompare = new CSVCmp();
          	 boolean rsflag = csvcompare.csv_Compare_SubString(stanrdPath, SrcPath);
             if(rsflag == false){
                 Rownum = csvcompare.getLineNo() + 1; //获取找不到的行号
                 String recsrc = csvcompare.getLineStr() ; //获取找不到的行
                 writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname+ "_"+ sdate+ "VerificationReport(false).csv" ))) ;
                
                 writerReport.write("Results file line "+ Rownum+ ":" + "\n");
                 writerReport.write(recsrc + "\n");
                 writerReport.write("Can not find the file in the baseline."+ "\n");
                 System.out.println(ZIP.NowTime()+"Results file line "+ Rownum+ " detail:" + recsrc + ",Can not find the file in the baseline\n");
                 writerReport.flush();
                 writerReport.close();
                 return false;
             }else{
                 //对比结果OK
                 bRet = true;
                 System.out.println(ZIP.NowTime()+"compare is ok");
         	     writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname+ "_"+ sdate+ "VerificationReport(true).csv"  ))) ;
           	     writerReport.write("compare is ok");
           	     writerReport.close();
           	     return bRet;
             }
         } catch ( FileNotFoundException e ){
          	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
             e.printStackTrace();
         }  catch (IOException e) {  
        	 System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             e.printStackTrace();  
         }
         return bRet;
	} 
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同,去掉前面的第N个逗号和N-1个逗号之间的字符串
	 * 功能简述 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 修改日期： 2014.8.18
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual_SubString(String stanrdPath, String SrcPath,int N)
	{
    	 System.out.println(ZIP.NowTime()+"File Baseline:"+ stanrdPath);
      	 System.out.println(ZIP.NowTime()+"File Result:"+ SrcPath);
       	File srcFile =new File(stanrdPath);
    	File destFile =new File(SrcPath);
    	if(!srcFile.isFile()){
    		System.out.println(ZIP.NowTime()+stanrdPath+" srcfile is not File");
    		Assert.assertTrue(stanrdPath+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		System.out.println(ZIP.NowTime()+SrcPath+" destfile is not File");
    		Assert.assertTrue(SrcPath+" is not File",false);
    	}
         boolean bRet = false;
         //获取时间戳字符串
         String sdate = getCurrentDate() ;
         //获取文件路径和文件名
         int nPath = -1;
         int LPath = SrcPath.lastIndexOf(".");
     	 if(-1 == SrcPath.lastIndexOf("/")){
      		 nPath = SrcPath.lastIndexOf("\\");
      	 }else{
      		 nPath = SrcPath.lastIndexOf("/");
      	 }
     	 String resultPath = SrcPath.substring(0, nPath+1);
      	 String reportname = SrcPath.substring(nPath+1,LPath);      	  
      	 System.out.println(ZIP.NowTime()+"ResultPath:"+ resultPath);
      	 System.out.println(ZIP.NowTime()+"Reportname:"+reportname);
     	  try {
       		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
       			return false;
       		 }
           } catch ( FileNotFoundException e ){
          	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
               e.printStackTrace();
               return false;
           } catch (IOException e) { 
          	  System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
          	  Pause.pause(5000);
           	  try {
            		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
            			return false;
            		 }
                }catch (IOException ee) {
             	   System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             	   ee.printStackTrace();  
                }
           } 
      	 //对比文件行数
         try {
          	 /*从这儿开始，对两个文件具体内容作比较*/
             int Rownum = 0;
             BufferedWriter writerReport = null;
          	 CSVCmp csvcompare = new CSVCmp();
          	 boolean rsflag = csvcompare.csv_Compare_SubString(stanrdPath, SrcPath,N);
             if(rsflag == false){
                 Rownum = csvcompare.getLineNo() + 1; //获取找不到的行号
                 String recsrc = csvcompare.getLineStr() ; //获取找不到的行
                 writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname + "_"+ sdate+ "VerificationReport(false).csv"))) ;
                
                 writerReport.write("Results file line "+ Rownum+ ":" + "\n");
                 writerReport.write(recsrc + "\n");
                 writerReport.write("Can not find the file in the baseline."+ "\n");
                 System.out.println(ZIP.NowTime()+"Results file line "+ Rownum+ " detail:" + recsrc + ",Can not find the file in the baseline\n");
                 writerReport.flush();
                 writerReport.close();
                 return false;
             }else{
                 //对比结果OK
                 bRet = true;
                 System.out.println(ZIP.NowTime()+"compare is ok");
         	     writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname+ "_"+ sdate + "VerificationReport(true).csv" ))) ;
           	     writerReport.write("compare is ok");
           	     writerReport.close();
           	     return bRet;
             }
         } catch ( FileNotFoundException e ){
          	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
             e.printStackTrace();
             return false;
         }  catch (IOException e) {  
        	 System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             e.printStackTrace();  
         }
         return bRet;
	}
    
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同,去掉第int[] O列
	 * 功能简述 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 修改日期： 2014.8.18
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual_SubString(String stanrdPath, String SrcPath,int[] O)
	{
         return csvFileIsEqual_SubString( stanrdPath,  SrcPath,-1, O);
	}
    /******************************************************************************
	 * 功能名称 ： 比较两个csv文件的内容是否相同,去掉第int[] O列
	 * 功能简述 ： 比较两个csv文件的内容是否相同
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 修改日期： 2014.8.18
	 *******************************************************************************/
    public  static  boolean  csvFileIsEqual_SubString(String stanrdPath, String SrcPath,int end,int[] O)
	{
    	 System.out.println(ZIP.NowTime()+"File Baseline:"+ stanrdPath);
      	 System.out.println(ZIP.NowTime()+"File Result:"+ SrcPath);
       	File srcFile =new File(stanrdPath);
    	File destFile =new File(SrcPath);
    	if(!srcFile.isFile()){
    		Assert.assertTrue(stanrdPath+" is not File",false);
    	}
    	if(!destFile.isFile()){
    		Assert.assertTrue(SrcPath+" is not File",false);
    	}
         boolean bRet = false;
         //获取时间戳字符串
         String sdate = getCurrentDate() ;
         //获取文件路径和文件名
         int nPath = -1;
         int LPath = SrcPath.lastIndexOf(".");
     	 if(-1 == SrcPath.lastIndexOf("/")){
      		 nPath = SrcPath.lastIndexOf("\\");
      	 }else{
      		 nPath = SrcPath.lastIndexOf("/");
      	 }
     	 String resultPath = SrcPath.substring(0, nPath+1);
      	 String reportname = SrcPath.substring(nPath+1,LPath);      	  
      	 System.out.println(ZIP.NowTime()+"ResultPath:"+ resultPath);
      	 System.out.println(ZIP.NowTime()+"Reportname:"+reportname);
     	  try {
       		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
       			return false;
       		 }
           } catch ( FileNotFoundException e ){
          	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
               e.printStackTrace();
               return false;
           } catch (IOException e) { 
          	  System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
          	  Pause.pause(5000);
           	  try {
            		 if(FileLineIsEqual ( stanrdPath,  SrcPath) == false){
            			return false;
            		 }
                }catch (IOException ee) {
             	   System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             	   ee.printStackTrace();  
                }
           } 
      	 //对比文件行数
         try {
          	 /*从这儿开始，对两个文件具体内容作比较*/
             int Rownum = 0;
             BufferedWriter writerReport = null;
          	 CSVCmp csvcompare = new CSVCmp();
          	 boolean rsflag = csvcompare.csv_Compare_SubString(stanrdPath, SrcPath,end,O);
             if(rsflag == false){
                 Rownum = csvcompare.getLineNo() + 1; //获取找不到的行号
                 String recsrc = csvcompare.getLineStr() ; //获取找不到的行
                 writerReport = new BufferedWriter(new FileWriter(new File(resultPath+reportname + "_"+ sdate + "VerificationReport(false).csv" ))) ;
                
                 writerReport.write("Results file line "+ Rownum+ ":" + "\n");
                 writerReport.write(recsrc + "\n");
                 writerReport.write("Can not find the file in the baseline."+ "\n");
                 System.out.println(ZIP.NowTime()+"Results file line "+ Rownum+ " detail:" + recsrc + ",Can not find the file in the baseline\n");
                 writerReport.flush();
                 writerReport.close();
                 return false;
             }else{
                 //对比结果OK
                 bRet = true;
                 System.out.println(ZIP.NowTime()+"compare is ok");
         	     writerReport = new BufferedWriter(new FileWriter(new File(resultPath  +reportname+ "_"+ sdate+ "VerificationReport(true).csv"  ))) ;
           	     writerReport.write("compare is ok");
           	     writerReport.close();
           	     return bRet;
             }
         } catch ( FileNotFoundException e ){
          	 System.out.println(ZIP.NowTime()+"Baseline or results file does not exist, make sure!");
             e.printStackTrace();
             return false;
         }  catch (IOException e) {  
        	 System.out.println(ZIP.NowTime()+"Baseline or results file read and write errors!");
             e.printStackTrace();  
         }
         return bRet;
	}
    /******************************************************************************
	 * 功能名称 ：比较两个文本文件总行数是否相等
	 * 功能简述 ： 比较两个文本文件总行数是否相等
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             shibingwu
	 * 创建日期 : 2014.8.5
	 * 修改日期：
     * @throws IOException 
	 *******************************************************************************/
    public  static  boolean FileLineIsEqual (String stanrdPath, String SrcPath) throws IOException
    {
        int nPath = -1;
        int LPath = SrcPath.lastIndexOf(".");
        String resultPath = "";
        String sdate = getCurrentDate();
   	     if(-1 == SrcPath.lastIndexOf("/")){
    		  nPath = SrcPath.lastIndexOf("\\");
    	  }else{
    		  nPath = SrcPath.lastIndexOf("/");
    	  }
    	  resultPath = SrcPath.substring(0, nPath+1);
    	  String reportname = SrcPath.substring(nPath+1,LPath);      	  
    	 BufferedWriter writerReport = null;
      	 InputStreamReader frdestSum = new InputStreamReader(new FileInputStream(stanrdPath));  
    	 BufferedReader brdestSum = new BufferedReader(frdestSum);  
    	 InputStreamReader frsrcSum = new InputStreamReader(new FileInputStream(SrcPath));  
    	 BufferedReader brsrcSum = new BufferedReader(frsrcSum); 
     	 int  RownumSrc = 0;//获取基线总行数初始化
       	 int  Rownumdest = 0;//获取结果总行数初始化
       	 while ((brdestSum.readLine()) != null ) {
       		RownumSrc++;//获取基线总行数
       	 }
       	brdestSum.close(); 
       	 while ((brsrcSum.readLine()) != null ) {
       		Rownumdest++;//获取结果总行数
         }
       	brsrcSum.close(); 
     	  System.out.println(ZIP.NowTime()+"Total number of rows baseline file:"+ RownumSrc);
      	  System.out.println(ZIP.NowTime()+"Total number of rows result file:"+ Rownumdest);
      	 //先决条件，总行数相同，如果不同，直接返回false
      	 if(RownumSrc != Rownumdest){
          	  writerReport = new BufferedWriter(new FileWriter(new File(resultPath +reportname + "_"+ sdate + "VerificationReport(false).csv"))) ;
        	  writerReport.write("Baseline and result files are not the same number of lines:" + "\n");
          	  writerReport.write("Total number of rows baseline file:"+ RownumSrc + "\n");
          	  writerReport.write("Total number of rows result file:"+ Rownumdest + "\n");
          	  writerReport.flush();
          	  writerReport.close();
      		  return false;
      	 }
      	 return true;
    }   
    /******************************************************************************
	 * 功能简述 ：当前时间，格式"YYYY-MM-DD-HH24-MM-SS"
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 创建日期 : 2012.11.5
	 * 修改日期：
	 *******************************************************************************/
    public  static  String  getCurrentDate()
    {
    	Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);//得到年 
        int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1 
        int day=cal.get(Calendar.DAY_OF_MONTH);//得到日
        int hh=cal.get(Calendar.HOUR_OF_DAY);//得到时
        int mm=cal.get(Calendar.MINUTE);//得到分
        int ss=cal.get(Calendar.SECOND);//得到秒
        String syear = Integer.toString(year);
        String smonth = Integer.toString(month);
        String sday = 	Integer.toString(day);
        String shh = 	Integer.toString(hh);
        String smm = 	Integer.toString(mm);
        String sss = 	Integer.toString(ss);
        String sdate = syear + "-" + smonth + "-" + sday + "_" + shh + smm + sss+ "_";
    	return sdate ;
    	
    }

    /******************************************************************************
	 * 功能名称 ："csv文件" 比较 两个文件夹底下有多个文件，比较对应名称相同的csv文件
	 * 功能简述 ："csv文件" 比较两个文件夹底下有多个文件，比较对应名称相同的csv文件
	 * 所属类 ：      AW_ExcelCheck
	 * 作者：             曹月龙
	 * 创建日期 : 2012.11.5
	 * 修改日期：
	 *******************************************************************************/
    //两个文件夹底下有多个文件，比较对应名称相同的csv文件
    public  static  boolean  SameNameCompareInPath(String standard, String export,int N)
	{
    	standard = new File(standard).getAbsolutePath();
    	export = new File(export).getAbsolutePath();
		File dir = new File(standard);
		String[] arrStr = new String[500];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return false;
		}
		int filesNumber = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				continue;
			}
			/* 当为文件时，递归调用该方法 */
			if (a.isFile()) {
				if(a.getName().startsWith("~$")){
					continue;
				}else if(a.getName().toLowerCase().endsWith(".csv")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else if(a.getName().toLowerCase().endsWith(".txt")){
					arrStr[filesNumber] = a.getName();
					filesNumber++;	
				}else{
					continue;
				}
			}
		}
		System.out.println(ZIP.NowTime()+"filesNumber："+filesNumber);
    	if(filesNumber == 0){
    		return false;
    	}
    	boolean[] arrStr2 = new boolean[filesNumber];	
        for(int i = 0;i < filesNumber;i++){
        	String src  = standard + "/" + arrStr[i];
        	String dest = export + "/" + arrStr[i];
        	arrStr2[i] = csvFileIsEqual_SubString(src,dest,N);
        }
        for(int i = 0;i < filesNumber;i++){
        	if(arrStr2[i] == false){
        		return false;
        	}else{
        		continue;
        	}
        } 
        return true;
	}


    public static String getfileName(String srcPath,  String startwith)
    {  	
    	File srcFile = new File(srcPath);
    	String fileName="";
        for (File file : srcFile.listFiles() ) {
        	fileName = file.getName();
            if(fileName.startsWith(startwith))
            {            	
            	return fileName ;
            } 
        }
		Assert.assertTrue(srcPath+"目录下找不到以："+startwith+" 开头的文件",false);
        return "对不起，匹配失败，请检查你的参数是否正确"; 
    }     
    public static String getfileName_endWith(String srcPath,  String endWith)
    {  	
    	File srcFile = new File(srcPath);
    	String fileName="";
        for (File file : srcFile.listFiles() ) {
        	fileName = file.getName();
            if(fileName.endsWith(endWith)&&(!fileName.endsWith("(true)"+endWith)||!fileName.endsWith("(false)"+endWith)))
            {            	
            	return fileName ;
            } 
        }
		Assert.assertTrue(srcPath+"目录下找不到以："+endWith+" 结束的文件",false);	
        return "对不起，匹配失败，请检查你的参数是否正确"; 
    } 


    public  static  String  getMaxFile_Type(String srcPath, String fileType)
	{
    	File result = null;
    	System.out.println(ZIP.NowTime()+"srcPath=="+srcPath);
        for (File child :new File(srcPath).listFiles()) {
            if  (child.isDirectory()) { 
            	continue; 
            }else if(child.getName().startsWith("~$")){
					continue;
            }else if(child.getName().endsWith(").xlsx")){
					continue;
            }else if(child.getName().endsWith(").xlsm")){
					continue;
            }else if (child.getName().endsWith(fileType)){
            	 if (null == result) {
 	                result = child;
 	             }
 	            if (child.getName().compareTo(result.getName()) > 0){
 	                result = child;
 	            }
            }
        }
        if(result == null){
        	Assert.assertTrue("endsWith:"+fileType+" not find maxfile:"+srcPath, false);
        }
		return result.getAbsolutePath();
	}

    /******************************************************************************
	 * 功能名称 ：动态excel内容比较
	 * 功能简述 ： 获取两个目录下的同名文件的比较后的输出流内容
	 * 所属类 ：AW_ExcelCheck
	 * 作者：      caoyuelong
	 * 创建日期 : 2012.9.10
	 * 修改日期：
	 *******************************************************************************/
    private static void addStreamShowThread(InputStream inputStream) {

		  final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		  try {
			  resultStr =br.readLine();	
		} catch (IOException e1) {
			// TODO Auto-generated catch block			
			e1.printStackTrace();
		}
    }
    
    public static boolean getProcess(String xxx_exe){
        boolean flag = false; 
        String cmd = "cmd /c tasklist|find /i " + "\""+ xxx_exe + "\"" ;
        try{ 
            Process p = Runtime.getRuntime().exec( cmd); 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            InputStream os = p.getInputStream(); 
            byte b[] = new byte[256]; 
            while(os.read(b)> 0) baos.write(b); 
            String s = baos.toString(); 
//             System.out.println(ZIP.NowTime()+s); 
            if(s.indexOf( xxx_exe)>=0){ 
                System.out.println( ZIP.NowTime()+xxx_exe+" find. "); 
                flag=true; 
            } else{ 
//                System.out.println( ZIP.NowTime()+"no "); 
                flag=false; 
            } 
        }catch(java.io.IOException ioe){
        	ioe.printStackTrace();
        } 
        return flag; 
     }  
    public static boolean getProcess(String windowTitle,String[] xxx_exe){
        boolean flag = false; 
        String cmd = "cmd /c TASKLIST /FI " + "\"WINDOWTITLE eq "+ windowTitle + "\"" ;
//        System.out.println( cmd); 
        try{ 
            Process p = Runtime.getRuntime().exec( cmd); 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            InputStream os = p.getInputStream(); 
            byte b[] = new byte[256]; 
            while(os.read(b)> 0) baos.write(b); 
            String s = baos.toString(); 
//            System.out.println(ZIP.NowTime()+s); 
            for(int i = 0;i< xxx_exe.length;i++){
                if(s.indexOf( xxx_exe[i])>=0){ 
                    System.out.println(ZIP.NowTime()+"windowTitle:"+windowTitle+ " find. "); 
                    flag=true; 
                } else{ 
//                    System.out.println( ZIP.NowTime()+"no "); 
                    flag=false;
                    return flag;
                } 	
            }
        }catch(java.io.IOException ioe){
        	ioe.printStackTrace();
        } 
        return flag; 
         
     }
}
