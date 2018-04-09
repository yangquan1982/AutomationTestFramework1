package common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.fest.swing.timing.Pause;
import org.junit.Assert;

import common.constant.file.CopyDataTool;

public class FileHandle {

	/******************************************************************************
	 * 功能名称 ： 文件拷贝 ,单文件拷贝 功能简述 ： 将src拷贝到desc目录下 所属类 ： FileHandle 作者： caoyuelong
	 * 创建日期: 2012.9.10 修改日期：
	 *******************************************************************************/
	public static void copyFile(String formPath,String toPath) {

		System.out.println(formPath);
		System.out.println(toPath);
		
		File fromFile=new File(formPath);
		File toFile =new File(toPath);
		
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		 try{
			 bis=new BufferedInputStream(new FileInputStream(fromFile));
			 bos=new BufferedOutputStream(new FileOutputStream(toFile));
			 byte[]buf=new byte[102400];
			 int readLen=0;
			 while((readLen=bis.read(buf))!=-1){
				 bos.write(buf, 0,readLen);
				 bos.flush();
			 }
			 
		 }
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		 finally{
			 
			 try{
					if(bis!=null){
						bis.close();	
					}
					if(bos!=null){
						bos.close();	
					}
			 }
			 catch(IOException e){
				 e.printStackTrace();
			 }
		 }
	}

	/******************************************************************************
	 * 功能名称 ： 批量拷贝文件 功能简述 ： 将src拷贝到dest目录下 所属类 ： FileHandle 作者： shibingwu 创建日期:
	 * 2014.7.7 修改日期：
	 *******************************************************************************/
	public static void CopyFile(String src, String dest) {

		CopyDataTool.copyDirectory(src, dest);
	}

	/******************************************************************************
	 * 功能名称 ： 文件重命名 功能简述 ： 将文件src重命名为descname名称 所属类 ： FileHandle 作者：caoyuelong
	 * 创建日期 : 2012.9.10 修改日期：
	 *******************************************************************************/
	public static void renameFile(String src, String descname) {

		String cmd = "cmd /c  rename " + src + " " + descname;
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Pause.pause(1000);
	}

	/******************************************************************************
	 * 功能名称 ：找到给定文件夹下名称值最大的文件夹 功能简述 ： 所属类 ： FileHandle 作者： caoyuelong 创建日期
	 * :2012.9.21 修改日期：
	 *******************************************************************************/
	public static File maxTimesPath(File destRoot) {
		File result = null;

		System.out.println(destRoot.listFiles().length);
		for (File child : destRoot.listFiles()) {
			if (child.isDirectory() && (!child.getName().endsWith(".svn"))) {
				if (null == result) {
					result = child;
				} else {
					if (child.getName().compareTo(result.getName()) > 0) {
						result = child;
					}
				}
			} else {
				continue;
			}
		}
		if (result == null) {
			Assert.assertTrue(CommonFile.fileMsg(destRoot.getAbsolutePath()) + " 目录下找不到最大文件夹",
					false);
		}
		return result;
	}

	/******************************************************************************
	 * 功能名称 ：找到给定文件夹下名称值最大的文件夹 功能简述 ： 所属类 ： FileHandle 作者： caoyuelong 创建日期
	 * :2012.9.21 修改日期：
	 *******************************************************************************/
	public static File maxTimesPath(String Path) {
		File destRoot = new File(Path);
		if (!destRoot.isDirectory()) {
			Assert.assertTrue(CommonFile.fileMsg(Path) + " is not Directory", false);
		}
		return maxTimesPath(destRoot);
	}

	/******************************************************************************
	 * 功能名称 ：文件中是否存在给定得字符串 功能简述 ：判断给定的filePath.TXT文件中是否存在给定得字符串 所属类 ： FileHandle
	 * 作者： caoyuelong 创建日期 :2012.11.2 修改日期：
	 *******************************************************************************/
	public static boolean isIncludeString(String fileName, String strValue) {

		boolean flag = false;
		File file = new File(fileName);
		StringBuffer sbf;
		if (!file.exists()) {
			System.out.println("文件不存在");
			return flag;
		}
		if (file.isDirectory()) {
			System.out.println("对不起，给定的路径不是文件类型");
			return flag;
		}
		if (!file.getName().endsWith(".txt")) {
			System.out.println("对不起，给定的文件只能是.txt类型的文件");
			return flag;
		} else {
			FileReader fr = null;
			BufferedReader bfr = null;
			String line = "";
			sbf = new StringBuffer();
			try {
				fr = new FileReader(file);
				bfr = new BufferedReader(fr);
				line = bfr.readLine();
				while (!(line == null)) {
					sbf.append(line);
					if ((sbf.toString()).indexOf(strValue) >= 0) {
						flag = true;
						break;
					}
					line = bfr.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/******************************************************************************
	 * 功能名称 ：删除该目录下除了.svn文件夹的所有文件和文件夹 功能简述
	 * ：删除result目录下某个用例的具体该路径下的除.svn外其他所有文件和文件夹 所属类 ： FileHandle 作者： lizhen 创建日期
	 * :2012.11.27 修改日期： 参数：dir，要删除的路径所对应的File对象
	 *******************************************************************************/
	public static void doRemovePath(File dir) {
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return;
		}
		for (File a : filelist) {
			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				doRemovePath(a);
			} else {
				/* 当为文件时，remove it */
				a.delete();
			}
		}
		/* 如果文件目录为空，就直接delete */
		if (dir.delete()) {
		}
	}

	/******************************************************************************
	 * 功能名称 ：根据给定的String路径新建一个File对象，deleteFileExcludeSVN 功能简述
	 * ：根据给定的String路径新建一个File对象 所属类 ： FileHandle 作者： lizhen 创建日期 :2012.11.27
	 * 修改日期： 参数：String型的路径
	 *******************************************************************************/
	public static void delDir(String resultPath) {
		File dir = new File(resultPath);
		doRemovePath(dir);
	}

	/******************************************************************************
	 * 功能名称 ：根据给定的String路径新建一个File对象，deleteFileExcludeSVN 功能简述
	 * ：根据给定的String路径新建一个File对象 所属类 ： FileHandle 作者： lizhen 创建日期 :2012.11.27
	 * 修改日期： 参数：String型的路径
	 *******************************************************************************/
	public static void delSubFile(String resultPath) {
		File dir = new File(resultPath);
		doRemovePath(dir);
		FileHandle.makeDirectory(resultPath);
	}

	/******************************************************************************
	 * 功能名称 ： 创建文件夹 功能简述 ： 所属类 ： FileHandle 作者： caoyuelong 创建日期 :2012.9.19 修改日期：
	 *******************************************************************************/
	public static void makeDirectory(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/******************************************************************************
	 * 功能名称 ：通过流，向文件中写信息 功能简述 ：通过流，向文件中写信息 所属类 ： FileHandle 作者： liulanlan 创建日期
	 * :2013.01.04 修改日期： 参数：String型的路径
	 *******************************************************************************/
	public static void WriterFile(String resultPath, ArrayList<String> result) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					resultPath)));
			for (int i = 0; i < result.toArray().length; i++) {
				writer.write(result.get(i) + ",");
			}
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void WriterFile2(String resultPath, String input,
			String regex, String replacement) {
		BufferedWriter bw = null;
		String buf = null;
		try {
			bw = new BufferedWriter(new FileWriter(resultPath));
			buf = input.replaceAll(regex, replacement);
			bw.write(buf);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/******************************************************************************
	 * 功能名称 ：通过流，将字符串信息写入txt文件中 功能简述 ：通过流，向文件中写信息 所属类 ： FileHandle 作者： liulanlan
	 * 创建日期 :2013.05.09 修改日期： 参数：String型的路径
	 * 
	 *******************************************************************************/
	public static void saveToTxt(String input, String resultPath) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(resultPath));
			bw.write(input);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <b>Description:</b>通过流，将字符串信息追加写入txt文件中
	 * 
	 * @author sWX198085
	 * @param result
	 * @param path
	 * @throws IOException
	 * @return void
	 */
	public static void writeTaskInfo(String input, String filePath){
		FileHandle.makeDirectory(filePath);
		filePath= filePath+"\\taskName_ID.txt";
	
		FileReader reader = null;
		try {
			reader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			try {
				new FileWriter(new File(filePath));
				reader = new FileReader(filePath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = null;
		ArrayList<String> allLine = new ArrayList<String>();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				allLine.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* 再写进去 */
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (String s : allLine) {
			if (!s.contains(input.subSequence(0, (input.indexOf(",") + 1)))) {

				try {
					writer.write(s);
					writer.write("\r\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// new add

		try {
			writer.write(input);
			writer.write("\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过任务名称获取任务id
	 * 
	 * @param operpath
	 *            文档路径
	 * @param taskname
	 *            任务名称
	 * @return
	 */
	public static String getID(String filePath, String taskname) {
		FileReader reader;
		String line = null;
		String taskid = null;
		filePath= filePath+"\\taskName_ID.txt";
		try {
			reader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(reader);
			try {
				while ((line = bufferedReader.readLine()) != null) {
					String[] taskinfo = line.split(",");
					System.out.println("taskinfo[0]:" + taskinfo[0]
							+ "taskinfo[1]:" + taskinfo[1]);
					if (taskinfo[0].startsWith(taskname)) {
						taskid = taskinfo[1];
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bufferedReader.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskid;
	}
}
