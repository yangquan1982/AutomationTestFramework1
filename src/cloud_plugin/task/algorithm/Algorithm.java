package cloud_plugin.task.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.huawei.webtest.restful.util.Log;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.constant.system.SystemConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class Algorithm {

	
	public static  int lineNum = 0;
	public static String compareCSV = null;
	/**
	 * 下载报告并校验结果文件中
	 * @param driver
	 * @param indexKey
	 * @param indexValue
	 * @param expectKey
	 * @param expectValue
	 * @param taskName 
	 * @param pathBase
	 */
	public static void startCheck(WebDriver driver, String defaultWindowID,
			String taskName, String mark,String[] indexKey, String[] indexValue,
			String[] expectKey, String[] expectValue, String compareFilePath, String txtMark) 
	{
		//下载的报告
		String taskId = "";
//		getTaskIdFromTempFile(taskName,txtMark);
		SeqVMOSTask.DownLoadReport(driver, "下载报告", taskName,taskId);
		
		//遍历找和taskName一致的路径，再找和基线文件一致的文件
		getDReport(taskName,compareFilePath);
		String dowloadReport = compareCSV;
		Log.info("对比的下载报告为："+dowloadReport);
		Assert.assertTrue("任务名称为‘"+taskName+"’的任务,未获取到其下载的报告！", compareCSV!=null);
		
		//字段值位置 匹配索引值
		Map<String, String> configCharacterIndexMap = getFilePathContentCsv(
				compareFilePath, indexKey, indexValue,mark);
		Map<String, String> configCharacterExpectMap = getFilePathContentCsv(
				compareFilePath, expectKey, expectValue,mark);

		//定位行数   
		int rightRowNum = getResultNum(dowloadReport, configCharacterIndexMap,indexKey,mark);

		String compareFile = getCompareFile(compareFilePath);
		//获取字段值内容
		int resultInt = getResultValue(dowloadReport, rightRowNum,
				expectKey, expectValue, configCharacterExpectMap,configCharacterIndexMap,mark,compareFile);

		Assert.assertTrue("对于基线文件"+compareFilePath+",其在对比时异常", resultInt == 1);
	}

	
	
	/**
	 * 获取对比文件的路径
	 * @param compareFilePath
	 * @return
	 */
	private static String getCompareFile(String compareFilePath) 
	{
		if(!compareFilePath.contains("D:")){
			return SystemConstant.AlgorithmBaseFilePath+compareFilePath;
		}else{
			return compareFilePath;
		}
	}
	
	

	/**
	 * 从临时文件中获取任务的taskId
	 * @param taskName
	 * @param txtMark 
	 * @return
	 */
	private static String getTaskIdFromTempFile(String taskName, String txtMark) 
	{
		String str = "";
		BufferedReader bre = null;
		try
		{
			String file = "D:\\Algorithm\\"+txtMark+".txt";
			bre = new BufferedReader(new FileReader(file));//此时获取到的bre就是整个文件的缓存流
			while ((str = bre.readLine())!= null) 
			{
				//判断最后一行不存在，为空结束循环
				String [] list = str.split("|");
				if(list[0].trim().equals(taskName))
				{
					return list[1]; 
				}
				System.out.println(str);
				//原样输出读到的内容
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	//匹配下载的文件
	private static String getDReport(String taskName, String compareFilePath) 
	{
		String csvName = compareFilePath.substring(compareFilePath.lastIndexOf("\\")+1);//获取基线文件名称
		//已下载报告的临时路径
		File f = new File(LTE_VMOS_Const.reportPath);
		if(f.isDirectory())
		{
			File[] fileList = f.listFiles();
			for(int i = 0;i<fileList.length;i++)
			{
				if(fileList[i].getName().trim().equals(taskName))
				{
					//已经定位至taskName目录下
					File [] fileDownloadList = new File(LTE_VMOS_Const.reportPath+"\\"+taskName).listFiles();
					for(int j =0; j<fileDownloadList.length;j++)
					{
						System.out.print(fileDownloadList[j].getPath().toString());
						if(fileDownloadList[j].toString().endsWith(".zip"))
						{
							//解压缩 zip包
							ZIP.depress(fileDownloadList[j].toString(),LTE_VMOS_Const.reportPath+"\\"+taskName);
						}
					}
					
					File [] fileDownloadList2 = new File(LTE_VMOS_Const.reportPath+"\\"+taskName).listFiles();
					return getFilePath(fileDownloadList2,csvName);
				}
			}
		}
		return null;
	}


	/**
	 * 获取和基线文件匹配的csv文件
	 * @param fileDownloadList2
	 * @param csvName
	 * @return
	 */
	private static String getFilePath(File[] fileDownloadList2,String csvName) 
	{
		for(int j = 0;j<fileDownloadList2.length;j++)
		{
			if(fileDownloadList2[j].isDirectory())
			{
				File [] list = new File(fileDownloadList2[j].toString()).listFiles();
//				System.out.println();
//				System.out.println("文件夹为："+fileDownloadList2[j]);
				if(compareCSV!=null){
					break;
				}else{
					getFilePath(list,csvName) ;
				}
			}else
			{
				if(compareCSV==null)
				{
//					System.out.println("文件名称为："+fileDownloadList2[j]);
					if(fileDownloadList2[j].getAbsolutePath().contains("Resolution_Assess_Result.CSV")){
						System.out.println(fileDownloadList2[j].getAbsolutePath());
					}
					if(fileDownloadList2[j].isFile() && fileDownloadList2[j].getName().endsWith("CSV"))
					{
						if(fileDownloadList2[j].getName().equals(csvName)){
							System.out.print(fileDownloadList2[j].getAbsolutePath());
							compareCSV = fileDownloadList2[j].getAbsolutePath();
							return compareCSV;
						}
					}
				}else
				{
					break;
				}
			}
		}
		return compareCSV;
	}


	/**
	 * 匹配基线文件
	 * @param compareFilePath
	 * @param compareFilePath 
	 * @return
	 */
	private static String getDownloadReport(String taskName, String compareFilePath) 
	{
		//已下载报告的临时路径
		File f = new File(SystemConstant.AlgorithmDownloadPath);
		if(f.isDirectory())
		{
			File[] fileList = f.listFiles();
			for(int i = 0;i<fileList.length;i++)
			{
				if(fileList[i].getName().trim().equals(taskName))
				{
					//已经定位至taskName目录下
					File [] fileDownloadList = new File(SystemConstant.AlgorithmDownloadPath+"\\"+taskName).listFiles();
					for(int j =0; j<fileDownloadList.length;j++)
					{
						System.out.print(fileDownloadList[j].getPath().toString());
						if(fileDownloadList[j].toString().endsWith(".zip"))
						{
							//解压缩 zip包
							ZIP.depress(fileDownloadList[j].toString(),SystemConstant.AlgorithmDownloadPath+"\\"+taskName);
						}
					}
					
					String basePath = getBasePath(compareFilePath);
					File [] fileDownloadList2 = new File(SystemConstant.AlgorithmDownloadPath+"\\"+taskName).listFiles();
					//遍历解压的文件夹，对其中的文件进行匹配
					for(int j =0; j<fileDownloadList2.length;j++)
					{
						if(fileDownloadList2[j].isDirectory())
						{
							if(new File(fileDownloadList2[j].getAbsolutePath()+"\\"+basePath).isFile())
							{
								return fileDownloadList2[j].getAbsolutePath()+"\\"+basePath;
							}
						}
					}
				}
			}
		}
		return null;
	}

	
	/**
	 * get基线文件
	 * @param compareFilePath
	 * @return
	 */
	private static String getBasePath(String compareFilePath) 
	{
		String[] baseFileCharList = compareFilePath.split("\\\\\\\\");
		String path = "";
		for(int i = 3;i<baseFileCharList.length;i++)
		{
			path = path +"\\"+ baseFileCharList[i];
		}
		
		return path;
	}


	/**
	 * 读取报告
	 * 
	 * @param dowloadReport
	 * @param sheetName
	 * @return
	 */
	private static Sheet getSheet(String dowloadReport, int sheetName) 
	{
		Sheet sheet = null;
		try 
		{
			FileInputStream fi = new FileInputStream(new File(dowloadReport));
			Workbook wb = WorkbookFactory.create(fi);
			sheet = wb.getSheetAt(sheetName);
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 获取结果值
	 * 
	 * @param dowloadReport
	 * @param rightNum
	 * @param expectValue
	 * @param expectKey
	 * @param configCharacterIndexMap 
	 * @param mark 
	 * @param baseFile 
	 * @param sheet
	 * @param configCharacterIndexMap
	 */
	private static int getResultValue(String dowloadReport, int rightRowNum,
			String[] expectKey, String[] expectValue,
			Map<String, String> configCharacterExpectMap, Map<String, String> configCharacterIndexMap, String mark, String baseFile) 
	{
		if(rightRowNum == -1)
		{
			Assert.fail("在下载报告中没有找到配置文件中索引字段对应值的行数！");
			return -1;
		}
	
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(dowloadReport)); 
			String line = null; 
			lineNum = 0;
			int returnNum =0;
			String lineJudgeTopNumber = "";
			while((line=reader.readLine())!=null)
			{ 
				returnNum = compareContent(mark,rightRowNum,line,configCharacterExpectMap,configCharacterIndexMap,dowloadReport,baseFile,expectValue);
				
				if(lineNum == 0 && mark.equalsIgnoreCase("sum"))
				{
					return returnNum;
				}
				
				if(returnNum == 1)
				{
					return returnNum;
				}
				
				if(mark.equals("judge top number"))
				{
					if(line!=null)
					{
						lineJudgeTopNumber = line;
					}
					lineNum++;
				}
			}
			
			if(mark.equals("judge top number"))
			{
				return CompareAlgorithm.compareJudgeTopNumber(mark, rightRowNum, lineJudgeTopNumber, configCharacterExpectMap);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	/**
	 * 对比content
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 * @param configCharacterIndexMap 
	 * @param baseFile 
	 * @param dowloadReport 
	 * @param expectValue 
	 */
	private static int  compareContent(String mark, int rightRowNum,
			String line, Map<String, String> configCharacterExpectMap, Map<String, String> configCharacterIndexMap, String dowloadReport, String baseFile, String[] expectValue) 
	{
		int re = 0;
		//is not null：验证指定字段列是否不为空
		re = CompareAlgorithm.compareIsNotNull(mark,rightRowNum,line,configCharacterExpectMap);
		if(re==1)return re;
		
		//sum ： 
		re = CompareAlgorithm.compareSum(mark,line,baseFile);
		if(re==1)return re;
		
		//type ： 验证数据类型是否正确
		re = CompareAlgorithm.compareType(mark,rightRowNum,line,configCharacterExpectMap);
		if(re==1)return re;
		
		//Change_Period:
//		re = CompareAlgorithm.compareChange_Period(mark,rightRowNum,line,configCharacterExpectMap);
		
		//range：验证数据范围是否在期望范围内
		re = CompareAlgorithm.compareRange(mark,rightRowNum,line,configCharacterExpectMap);
		if(re==1)return re;
		
		re = CompareAlgorithm.compareNA(mark,rightRowNum,line,configCharacterExpectMap);
		if(re==1)return re;
		
		//duplicate
		re = CompareAlgorithm.compareDuplicate(mark, rightRowNum, line, configCharacterExpectMap);
		if(re==1)return re;
		
		//is delete
		re = CompareAlgorithm.compareIsDelete(mark, rightRowNum, line, configCharacterIndexMap);
		if(re==1)return re;
		
		//is null
//		re = CompareAlgorithm.compareIsNull(mark, rightRowNum, line, configCharacterExpectMap);
		
		//error range 0.05
		re = CompareAlgorithm.compareErrorRange(mark, rightRowNum, line, configCharacterExpectMap, 0.05);
		if(re==1)return re;
		
		return re;
	}


	/**
	 * 定位行数
	 * 
	 * @param dowloadReport
	 * @param configCharacterIndexMap
	 * @param indexKey 
	 * @param mark 
	 */
	private static int getResultNum(String dowloadReport,
			Map<String, String> configCharacterIndexMap, String[] indexKey, String mark) 
	{
		try 
		{
			
			if(configCharacterIndexMap==null){
				LOG.info("配置中索引字段和索引值均为空，进行全量对比。");
				return -2;
			}
			
			if(indexKey[0].equals("NA")){
				return -2;
			}
			
			if(!new File(dowloadReport).isFile())
			{
				Assert.fail("下载的报告中需要检查的文件‘"+dowloadReport+"’不存在！");
				return -1;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(dowloadReport)); 
			String line = null; 
			int lineNum = 0;
			int locationLineNum  = 0;
			while((line=reader.readLine())!=null)
			{ 
				lineNum++;
				if(!mark.equals("judge top number"))
				{
					String[] item = line.split(","); 
					for(String s :configCharacterIndexMap.keySet())
					{
						if(!item[Integer.parseInt(configCharacterIndexMap.get(s).split("_")[0])].trim().equals(configCharacterIndexMap.get(s).split("_")[1].trim()))
						{
							break;
						}
						locationLineNum = lineNum-1;
					}
					
					if(locationLineNum!=0)
					{
						Log.info("基线文件中需要对比的是第"+(lineNum)+"行");
						return locationLineNum;
					}
				}
			}
			
			if(mark.equals("judge top number"))
			{
				return lineNum;
			}
		} catch (EncryptedDocumentException | IOException e) 
		{
			e.printStackTrace();
		}
		
		String log = "索引字段为有：";
		for(String key : indexKey)
		{
			log = log + " "+ key;
		}
		log = log + "； 对应的索引值为：";
		for(String s :configCharacterIndexMap.keySet())
		{
			log = log +configCharacterIndexMap.get(s).split("_")[1].trim();
		}
		Assert.fail("在下载报告'"+dowloadReport+"'中没有匹配到索引字段对应的索引值。具体内容为："+log);
		
		return -1;
	}

	
	/**
	 * 获取任务名称
	 * @param path2
	 * @return
	 */
	public static List<String> getTask(String path2) 
	{
		List<String> s = new ArrayList<String>();
		Sheet sheet = getSheet(path2,0);
		for(int i = 1;i<sheet.getLastRowNum();i++)
		{
			Cell cell = sheet.getRow(i).getCell(2);
			s.add(cell.getStringCellValue());
		}
		return s;
	}
	
	
	/**
	 * 获取CSV文件中字段名的位置
	 * @param compareFilePath
	 * @param listKey
	 * @param listValue
	 * @param mark 
	 * @return
	 */
	private static Map<String, String> getFilePathContentCsv(
			String compareFilePath, String[] listKey, String[] listValue, String mark) 
	{
		Map<String, String> dict = new HashMap<String, String>();
		try 
		{
			if(!new File(compareFilePath).isFile())
			{
				Log.info("该路径下的文件"+compareFilePath+"不存在！");
				Assert.fail("文件不存在！");
				return null;
			}
			if(listKey[0].equals("NA")&&listValue[0].equals("NA"))
			{
				return null;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(compareFilePath)); 
			String line = null; 
			while((line=reader.readLine())!=null)
			{ 
				String[] item = line.split(","); 
				for(int i=0;i<item.length;i++)
				{
					for(int j =0;j<listKey.length;j++)
					{
						String itemValue = "";
						if(i==0)
						{
							char[] str = item[i].trim().toCharArray();
							char[] str2 = listKey[j].trim().toCharArray();
							if(str2[0]!=str[0])
							{
								itemValue = item[i].trim().substring(1);
							}else{
								itemValue = item[i].trim();
							}
							
						}else
						{
							itemValue = item[i].trim();
						}
						
						if((listKey[j].trim()).equals(itemValue))
						{
							System.out.print("listValue 大小："+listValue.length);
							if(listValue.length==0)
							{
								dict.put(listKey[j], i+"_"+"");
							}else if(mark.equals("type"))
							{
								dict.put(listKey[j],i+"_"+listValue[0]);
							}else
							{
								dict.put(listKey[j], i+"_"+listValue[j]);
							}
							
							
//							try {
//								dict.put(listKey[j], i+"_"+Double.parseDouble(listValue[j])+"");
//							} catch (NumberFormatException e) {
//								dict.put(listKey[j], i+"_"+listValue[j]);
//							}
							
						}
					}
				}
				if(dict!=null)
				{
					return dict;
				}else
				{
					Log.info("该CSV文件中在列名地方没有找到和期望匹配的地方.");
					return null;
				}
			}             
		} catch (EncryptedDocumentException 
				| IOException e) {
			e.printStackTrace();
		}
		return dict;
	}
	
	/**
	 * 下载报告
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @return
	 */
	public static String downloadReport(WebDriver driver,String defaultWindowID, String taskName) 
	{
		//点击taskName进入报告中
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskName);
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoad a", true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "评估");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}


	/**
	 * 将taskId写入临时文件中
	 * @param string
	 * @param projectName
	 * @param taskID
	 */
	public static void WriteTaskID(String string, String projectName,
			String taskID) 
	{
		File f = null;
		try 
		{
			if(!new File("D:\\Algorithm").isDirectory())
			{
				new File("D:\\Algorithm").mkdirs();
			}
			
			f = new File("D:\\Algorithm\\"+string+".txt");
			if(!f.exists())
			{
			    f.createNewFile();
			}
			  
			FileWriter fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			fw.write(projectName+" | "+ taskID + "\r\n");
            fw.flush();
			pw.flush();
            pw.close();
            fw.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
	}


	/**
	 * 删除临时文件
	 * @param string
	 */
	public static void deleteTempFile(String fileStr) 
	{
        if(new File(fileStr).isDirectory())
        {
            File [] fileList = new File(fileStr).listFiles();
            for(int i = 0;i<fileList.length;i++)
            {
                if(fileList[i].isFile())
                {
                    fileList[i].delete();
                }
                if(fileList[i].isDirectory())
                {
                	deleteTempFile(fileList[i].getAbsolutePath());
                }
            }
            new File(fileStr).delete();
        }
	}        

}
