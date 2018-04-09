package cloud_plugin.task.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import com.huawei.webtest.restful.util.Log;

public class CompareAlgorithm {

	
	public static int compareIsNotNull(String mark, int rightRowNum, String line, Map<String, String> configCharacterExpectMap) 
	{
		// TODO Auto-generated method stub
		if(mark.equals("is not null"))
		{
			if(Algorithm.lineNum==rightRowNum) //某一行的内容进行对比
			{
				String[] item = line.split(",");
				
				//对比字段名内容 NA
				for (String s : configCharacterExpectMap.keySet()) 
				{
					if ("".equals(item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim()))
					{
						return -1;
					}
				}
				return 1;
			}else if(rightRowNum == -2) //全量对比
			{
				String[] item = line.split(",");
				for(int i =0;i<item.length;i++)
				{
					if("".equals(item[i]))
					{
						return -1;
					}
				}
				return 1;
			}
			Algorithm.lineNum++;
		}
		return -1;
	}

	
	/**
	 * 对比字段名的个数  这个待定
	 * @param baseFile 
	 * @param dowloadReport 
	 * @param mark 
	 * @param mark
	 * @param configCharacterExpectMap 
	 * @param line 
	 * @param rightRowNum 
	 */
	public static int compareSum(String mark, String lineDownload, String baseFile) 
	{
		try 
		{
			if(mark.equals("sum"))
			{
				BufferedReader reader = new BufferedReader(new FileReader(baseFile)); 
				String lineBase = null; 
				int lineNum = 0;
				while((lineBase=reader.readLine())!=null)
				{
					if(lineDownload!=lineBase && lineNum==Algorithm.lineNum)
					{
						Assert.fail("基线文件中字段个数与实际下载报告中字段个数不匹配。");
						return -1;
					}
					return 1;
				}
			}
		} catch ( IOException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 对比数据类型
	 * @param mark
	 * @param configCharacterExpectMap 
	 * @param line 
	 * @param rightRowNum 
	 */
	public static int compareType(String mark, int rightRowNum, String line, Map<String, String> configCharacterExpectMap) 
	{
		// TODO Auto-generated method stub
		if(mark.equals("type"))
		{
			if(Algorithm.lineNum==rightRowNum) //某一行的内容进行对比
			{
				String[] item = line.split(",");
				
				for (String s : configCharacterExpectMap.keySet()) 
				{
					//判断数据类型
					int a = judgeValueType(item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim(),configCharacterExpectMap.get(s).split("_")[1]);
					if(a == -1)
					return -1;
				}
				return 1;
			}else if(rightRowNum==-2 && Algorithm.lineNum>0) //全量对比
			{
				String[] item = line.split(",");
				for (String s : configCharacterExpectMap.keySet()) 
				{
					//判断数据类型
					int a = judgeValueType(item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim(),configCharacterExpectMap.get(s).split("_")[1]);
					if(a == -1)
					return -1;
				}
				return 1;
			}
			Algorithm.lineNum++;
		}
		return -1;
	}

	
	/**
	 * 判断字段值的数据类型
	 * @param trim
	 * @return
	 */
	private static int judgeValueType(String string,String type) 
	{
		//匹配正则
		if(type.equalsIgnoreCase("int") || type.equalsIgnoreCase("float") || type.equalsIgnoreCase("double"))
		{
			Pattern p = Pattern.compile("[a-zA-Z]");
		    Matcher m = p.matcher(string);
		    if(m.find() || "".equals(string))
		    {
		    	return 0;
		    }
		}
		if("".equals(string)){
			return 0;
		}
		
		if(string.length()<2)
		{
			//转换为 ASCII  a-z：97-122 A-Z：65-90 0-9：48-57
			try 
			{
				int ASCIINum = (byte)string.charAt(0);
				if(ASCIINum>47 && ASCIINum<58)
				{
					if(!type.trim().equalsIgnoreCase("int"))
					{
						return -1;
					}
				}else
				{
					if(!type.trim().equalsIgnoreCase("char"))
					{
						return -1;
					}
				}
			} catch (NumberFormatException e) 
			{
				Assert.fail("转换成ASCII时异常");
				return -1;
			}
			return 1;
		}else if(string.contains("."))
		{
			try 
			{
				Float f = Float.parseFloat(string);
				if(!type.trim().equalsIgnoreCase("float"))
				{
					return -1;
				}
			} catch (NumberFormatException e) {
				Log.info("转换Float时异常");
				return -1;
			}
			return 1;
		}else if(string.length()>=2)
		{
			try
			{
				int i = Integer.parseInt(string);
			} catch (NumberFormatException e)
			{
				Log.info("转换int时异常");
				return -1;
			}
			return 1;
		}
		return -1;
	}


	/**
	 * 对比变化参数后的这种情况
	 * @param mark
	 * @param configCharacterExpectMap 
	 * @param line 
	 * @param rightRowNum 
	 */
	public static int compareChange_Period(String mark, int rightRowNum, String line, Map<String, String> configCharacterExpectMap) 
	{
		// TODO Auto-generated method stub
		if(mark.equals("Change_Period"))
		{
			Assert.fail("改变worker字段的用例，和测试对其后，自动化再实现适配。");
		}
		return -1;
	}

	
	/**
	 * 对比数据范围
	 * @param mark
	 * @param configCharacterExpectMap 
	 * @param line 
	 * @param rightRowNum 
	 */
	public static int  compareRange(String mark, int rightRowNum, String line, Map<String, String> configCharacterExpectMap) 
	{
		// TODO Auto-generated method stub
		if(mark.equals("range"))
		{
			if(Algorithm.lineNum==rightRowNum) //某一行的内容进行对比
			{
				String[] item = line.split(",");
				
				for(String s :configCharacterExpectMap.keySet())
				{
					int max = getNumMax(configCharacterExpectMap.get(s).split("_")[1]);
					int min = getNumMin(configCharacterExpectMap.get(s).split("_")[1]);
					int num = Integer.parseInt(item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim());
					if(num>=max && num<=min)
					{
						return -1;
					}
				}
				return 1;
			}else if(rightRowNum == -2)
			{
				String[] item = line.split(",");
				for(String s :configCharacterExpectMap.keySet())
				{
					int max = getNumMax(configCharacterExpectMap.get(s).split("_")[1]);
					int min = getNumMin(configCharacterExpectMap.get(s).split("_")[1]);
					int num = Integer.parseInt(item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim());
					if(num>=max && num<=min)
					{
						Assert.fail("在行数为"+rightRowNum+"列数为"+Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])+"地方，数值超出了期望范围"+configCharacterExpectMap.get(s).split("_")[1]);
						return -1;
					}
				}
			}
			Algorithm.lineNum++;
			
		}
		return -1;
	}

	/**
	 * 获取范围的最小值
	 * @param string
	 * @return
	 */
	private static int getNumMin(String string) 
	{
		int a = Integer.parseInt(string.substring(string.indexOf("[")+1,string.indexOf(",")).trim());
		return a;
	}


	/**
	 * 获取范围的最大值
	 * @param string
	 * @return
	 */
	private static int getNumMax(String string) {
		int a = Integer.parseInt(string.substring(string.indexOf(",")+1,string.indexOf("]")).trim());
		return a;
	}


	/**
	 * 依照索引值，对下载报告进行对比
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 */
	public static int compareNA(String mark, int rightRowNum, String line, Map<String, String> configCharacterExpectMap) 
	{
		if(mark.equals("NA"))
		{
			if(Algorithm.lineNum==rightRowNum)
			{
				Log.info("在下载的报告中已经匹配到第"+(Algorithm.lineNum+1)+"行,开始验证正确性。");
				String[] item = line.split(",");
				
				//对比字段名内容 NA
				for (String s : configCharacterExpectMap.keySet()) 
				{
					if (!item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim().equals(configCharacterExpectMap.get(s).split("_")[1].trim()))
					{
						Assert.fail("在行数为："+(rightRowNum+1)+",列数为"+(Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])+1)+"，和期望值匹配异常！配置文件中的期望值为："+configCharacterExpectMap.get(s).split("_")[1].trim()+",下载报告中的实际值为："+item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim());
						return -1;
					}
				}
				return 1;
			}
			Algorithm.lineNum++;
			
		}
		return -1;
	}


	/**
	 * 判断数据量是否小于期望值
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 * @return
	 */
	public static int compareJudgeTopNumber(String mark, int rightRowNum,
			String line, Map<String, String> configCharacterExpectMap) 
	{
		if(mark.equals("judge top number"))
		{
			if(Algorithm.lineNum>0) //某一行的内容进行对比
			{
				String [] item = line.split(",");
				for(String str :configCharacterExpectMap.keySet())
				{
					if(configCharacterExpectMap.get(str).split("_")[1].contains("."))
					{
						Double d = Double.parseDouble(configCharacterExpectMap.get(str).split("_")[1]);
						if(Integer.parseInt(item[Integer.parseInt(configCharacterExpectMap.get(str).split("_")[0])])>d)
						{
							Assert.fail("下载的报告中，超出期望值"+Integer.parseInt(configCharacterExpectMap.get(str).split("_")[1])+"!");
							return -1;
						}else{
							return 1;
						}
					}else
					{
						int i = Integer.parseInt(configCharacterExpectMap.get(str).split("_")[1]);
						if(Integer.parseInt(item[Integer.parseInt(configCharacterExpectMap.get(str).split("_")[0])])>i)
						{
							Assert.fail("下载的报告中，超出期望值"+Integer.parseInt(configCharacterExpectMap.get(str).split("_")[1])+"!");
							return -1;
						}else{
							return 1;
						}
					}
				}
				return -1;
			}
		}
		return -1;
	}


	
	private static List<String> contentList = new ArrayList<String>();
	/**
	 * 某一字段名下的字段值唯一
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 * @return
	 */
	public static int compareDuplicate(String mark, int rightRowNum,
			String line, Map<String, String> configCharacterExpectMap) 
	{
		if(mark.equals("deplicate"))
		{
			if(Algorithm.lineNum==rightRowNum) //某一行的内容进行对比
			{
				Assert.fail("该项检查的是某列所有值，不是对某一行进行check，请检查算法参数化表");
				return -1;
			}else if(rightRowNum == -2) //全量对比
			{
				String[] item = line.split(",");
				for(String str :configCharacterExpectMap.keySet())
				{
					for(String s : contentList){
						if(item[Integer.parseInt(configCharacterExpectMap.get(str).split("_")[0])].trim().equals(s))
						{
							Assert.fail("在检查列中存在相同数值");
							return -1;
						}
					}
				}
				return 1;
			}
			Algorithm.lineNum++;
			
		}
		return -1;
	}


	/**
	 * 是否删除
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 * @return
	 */
	public static int compareIsDelete(String mark, int rightRowNum,
			String line, Map<String, String> configCharacterIndexMap) 
	{
		if(mark.equals("is delete"))
		{
			if(rightRowNum==-2)
			{
				String[] item = line.split(",");
				
				//对比字段名内容 NA
				for (String s : configCharacterIndexMap.keySet()) 
				{
					if (item[Integer.parseInt(configCharacterIndexMap.get(s).split("_")[0])].trim().equals(configCharacterIndexMap.get(s).split("_")[1].trim()))
					{
						Assert.fail("在实际报告中，数据未被删除，报告异常！");
						return -1;
					}
				}
				return 1;
			}
			Algorithm.lineNum++;
			
		}
		return -1;
	}


	
	/**
	 * 是否为空
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 * @return
	 */
	public static int compareIsNull(String mark, int rightRowNum, String line,
			Map<String, String> configCharacterExpectMap) 
	{
		if(mark.equals("is null"))
		{
			if(rightRowNum==-2)
			{
				String[] item = line.split(",");
				
				//对比字段名内容 NA
				for (String s : configCharacterExpectMap.keySet()) 
				{
					if (!item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim().equals(configCharacterExpectMap.get(s).split("_")[1].trim()))
					{
						Assert.fail("在检查列，存在不为null的情况");
						return -1;
					}
				}
				return 1;
			}else if(Algorithm.lineNum==rightRowNum)
			{
				String[] item = line.split(",");
				//对比字段名内容 NA
				for (String s : configCharacterExpectMap.keySet()) 
				{
					if (!item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim().equals(configCharacterExpectMap.get(s).split("_")[1].trim()))
					{
						Assert.fail("在检查列，存在不为null的情况");
						return -1;
					}
				}
			}
			Algorithm.lineNum++;
		}
		return -1;
	}


	/**
	 * 验证数据误差是否在误差范围内
	 * @param mark
	 * @param rightRowNum
	 * @param line
	 * @param configCharacterExpectMap
	 * @param d
	 * @return
	 */
	public static int compareErrorRange(String mark, int rightRowNum,
			String line, Map<String, String> configCharacterExpectMap, double d) 
	{
		if(mark.contains("error range"))
		{
			if(rightRowNum==-2)
			{
				String[] item = line.split(",");
				
//				//对比字段名内容 NA
//				for (String s : configCharacterExpectMap.keySet()) 
//				{
//					if (!item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim().equals(configCharacterExpectMap.get(s).split("_")[1].trim()))
//					{
//						Assert.fail("在检查列，存在不为null的情况");
//						return -1;
//					}
//				}
				return 1;
			}else if(Algorithm.lineNum==rightRowNum)
			{
				String[] item = line.split(",");
//				//对比字段名内容 NA
//				for (String s : configCharacterExpectMap.keySet()) 
//				{
//					if (!item[Integer.parseInt(configCharacterExpectMap.get(s).split("_")[0])].trim().equals(configCharacterExpectMap.get(s).split("_")[1].trim()))
//					{
//						Assert.fail("在检查列，存在不为null的情况");
//						return -1;
//					}
//				}
			}
			Algorithm.lineNum++;
		}
		return -1;
	}

}
