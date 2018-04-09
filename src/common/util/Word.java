package common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Assert;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import common.constant.system.EnvConstant;

public class Word {

	public static String CompareConfigFile = "D:\\bc2.txt";
	public static String CompareResultFile = "D:\\reportTest.txt";

	public static String convertWord2XML(String file) {
		String outFile = file + ".xml";
		String inFile = file;
		try {
			ActiveXComponent app = new ActiveXComponent("Word.Application");
			app.setProperty("Visible", new Variant(false));
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { inFile, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			Dispatch.call(doc, "SaveAs", outFile, 11);
			Dispatch.call(doc, "Close", false);
			app.invoke("Quit", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outFile;
	}

	public static String convertDocx2Doc(String file) {
		String outFile = file.replace("docx", "doc");
		String inFile = file;
		try {
			ActiveXComponent app = new ActiveXComponent("Word.Application");
			app.setProperty("Visible", new Variant(false));
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { inFile, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			Dispatch.call(doc, "SaveAs", outFile, 11);
			Dispatch.call(doc, "Close", false);
			app.invoke("Quit", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outFile;
	}
	public static String convertDocx2Doc(String file,String filetemp) {

		String inFile = file;
		int nPath = -1;
		int LPath = file.lastIndexOf(".");
		if (-1 == file.lastIndexOf("/")) {
			nPath = file.lastIndexOf("\\");
		} else {
			nPath = file.lastIndexOf("/");
		}

		String outFile = EnvConstant.Path_DownLoad + "\\" +file.substring(nPath + 1, LPath)+".doc";
		try {
			ActiveXComponent app = new ActiveXComponent("Word.Application");
			app.setProperty("Visible", new Variant(false));
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { inFile, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			Dispatch.call(doc, "SaveAs", outFile, 11);
			Dispatch.call(doc, "Close", false);
			app.invoke("Quit", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outFile;
	}
	public static boolean compareWord(String file1, String file2) {
		int nPath = -1;
		int LPath = file2.lastIndexOf(".");
		if (-1 == file2.lastIndexOf("/")) {
			nPath = file2.lastIndexOf("\\");
		} else {
			nPath = file2.lastIndexOf("/");
		}
		String sdate = FileCompare.getCurrentDate();
		String resultPath = file2.substring(0, nPath + 1);
		String reportname = file2.substring(nPath + 1, LPath);
		String bc2 = resultPath + "bc2.txt";
		String comparefile = resultPath + reportname + "_" + sdate
				+ "reportTest.txt";
		System.out.println("comparefile:"+comparefile);
		if (file1 == null || file2 == null) {
			Assert.fail("对比文件不能为空:(" + file1 + "," + file2 + ")");
		}
		if ((!file1.endsWith("doc") && !file1.endsWith("docx"))
				|| (!file2.endsWith("doc") && !file2.endsWith("docx"))) {
			Assert.fail("对比文件后缀名必须为doc或docx:(" + file1 + "," + file2 + ")");
		}
		if (file1.endsWith("docx")) {
			file1 = convertDocx2Doc(file1,"");
		}
		if (file2.endsWith("docx")) {
			file2 = convertDocx2Doc(file2);
		}

		makeConfigFile(file1, file2, bc2,comparefile);
		runCompareCommand(bc2);
		return dealResultFile(comparefile);
	}

	public static void makeConfigFile(String file1, String file2, String bc2,String comparefile) {
		String content = "file-report layout:side-by-side options:ignore-unimportant,display-mismatches,line-numbers  output-to:\""
				+ comparefile
				+ "\" \"E:\\NACAuto\\report\\20140905120154000859.doc\" \"E:\\NACAuto\\report\\20140905120154000859-1.doc\"";
		content = content.replace(
				"E:\\NACAuto\\report\\20140905120154000859.doc", file1);
		content = content.replace(
				"E:\\NACAuto\\report\\20140905120154000859-1.doc", file2);
		File file = new File(bc2);
		try {
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(file), "GBK");
			write.write(content);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void runCompareCommand(String bc2) {

		String CompareTool_CPath32 = "C:\\Program Files\\Beyond Compare 2\\bc2.exe";
		String CompareTool_CPath64 = "C:\\Program Files (x86)\\Beyond Compare 2\\bc2.exe";
		
    	File CompareTool32 =new File(CompareTool_CPath32);
    	File CompareTool64 =new File(CompareTool_CPath64);
    	
    	String CompareTool_CPath="";
    	if(CompareTool32.isFile()==true){
    		CompareTool_CPath =CompareTool_CPath32;
    	}else{
        	if(!CompareTool64.isFile()){
        		Assert.assertTrue(CommonFile.fileMsg(CompareTool_CPath64)+" is not File",false);
        	}else{
        		CompareTool_CPath =CompareTool_CPath64;
        	}
    	}
		String cmd = CompareTool_CPath+" " + "@"
				+ "\""+bc2+"\"";
		System.out.println("cmd:" + cmd);
		Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
		try {
			Process p = run.exec(cmd);// 启动另一个进程来执行命令
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null)
				// 获得命令执行后在控制台的输出信息
				System.out.println(lineStr);// 打印输出信息
			// 检查命令是否执行失败。
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)// p.exitValue()==0表示正常结束，1：非正常结束
					System.err.println("命令执行失败!");
			}
			inBr.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("run command fail:" + cmd);
		}
	}

	public static boolean dealResultFile(String comparefile) {
		boolean fagle = false;
		String result = "";
		String fileName = comparefile;
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			line = in.readLine();
			// 循环找到两个.doc的行
			while (line != null) {
				if (line.endsWith(".doc")) {
					line = in.readLine();
					if (line.endsWith(".doc")) {
						// 找到后记录对比结果,只取前10行
						int linenum = 0;
						line = in.readLine();
						while (line != null && linenum < 10) {

							result += line + "\n";
							line = in.readLine();
							linenum++;
						}
						System.out.println("result:" + result);
						if ("".equals(result)) {
							fagle = true;
							return fagle;
						}
					}
				}
				line = in.readLine();
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fagle;
	}

	public static void main(String[] args) {
		// convertDocx2Doc("E:\\NACAuto\\report\\test.docx");
		System.out.println(compareWord(
				"D:\\瓦房宅修改图例--GenexCloudTest中国移动LTE第XX期第XX网格优化报告1.doc",
				"D:\\瓦房宅修改图例--GenexCloudTest中国移动LTE第XX期第XX网格优化报告.doc"));
	}
}
