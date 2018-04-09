package common.constant.file;

import java.io.File;
import java.io.IOException;

public class CopyDataTool {
	/******************************************************************************
	 * 功能名称 ：获取文件夹 功能简述 ： 
	 * 所属类 ： AW_FileHandle 
	 * 作者： shibingwu 
	 * 创建日期 :2014.02.28
	 * 修改日期： 参数：dir，要复制的路径所对应的File对象
	 *******************************************************************************/
	private static String[][] doGetPath(String resultPath) {
		File dir = new File(resultPath);
		String[][] arrStr = new String[100][2];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return arrStr;
		}
		int i = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {
				arrStr[i][0] = path;
				arrStr[i][1] = a.getName();
				i++;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isFile()) {
				arrStr[i][0] = path;
				arrStr[i][1] = "";
				i++;
			}
		}
		return arrStr;
	}

	/******************************************************************************
	 * 功能名称 ：获取文件 功能简述 ： 所属类 ： AW_FileHandle 作者： shibingwu 创建日期 :2014.02.28
	 * 修改日期： 参数：dir，要复制的路径所对应的File对象
	 *******************************************************************************/
	private static String[] doGetFilePath(File dir) {
		String[] arrStr = new String[100];
		File filelist[] = dir.listFiles();
		/* 如果该目录下不为空，就通过for循环继续查找文件和文件夹 */
		if (!dir.isDirectory()) {
			return arrStr;
		}
		int i = 0;
		for (File a : filelist) {

			String path = a.getAbsolutePath();
			if (path.endsWith(".svn")) {
				continue;
			}
			/* 当为目录时，递归调用该方法 */
			if (a.isDirectory()) {

			} else {
				arrStr[i] = path;
				i++;
			}
		}
		return arrStr;
	}

	/******************************************************************************
	 * 功能名称 ：根据给定的String路径新建一个File对象，deleteFileExcludeSVN 功能简述
	 * ：根据给定的String路径新建一个File对象 所属类 ： AW_FileHandle 作者： shibingwu 创建日期
	 * :2012.11.27 修改日期： 参数：String型的路径
	 *******************************************************************************/
	private static String[] doGetFilePath(String resultPath) {
		File dir = new File(resultPath);
		return doGetFilePath(dir);
	}

	/******************************************************************************
	 * 功能名称 ： 文件拷贝 ，拷贝目录 功能简述 ： 将src拷贝到desc目录下 
	 * 所属类 ： AW_FileHandle 
	 * 作者： shibingwu
	 * 创建日期: 2012.9.10 修改日期：
	 *******************************************************************************/
	public static void copyDirectory(String src, String desc){
		String[][] aa = doGetPath(src);
		for (int i = 0; i < aa.length; i++) {
			if (aa[i][0] == null) {
				break;
			}
			String desc2 = desc;
			if(aa[i][1] == ""){
				 desc2 = desc;
			}else{
				 desc2 = desc + "\\" + aa[i][1];			
			}
			File file2 = new File(desc2);
			if (!file2.exists()) {
				file2.mkdir();
				String[] bb = doGetFilePath(aa[i][0]);
				for (int j = 0; j < bb.length; j++) {
					if (bb[j] == null) {
						break;
					}
					String cmd = "cmd /c copy " + "\"" + bb[j] + "\"" + " "
							+ "\"" +desc2 + "\"" +" /Y";
					try {
						Runtime.getRuntime().exec(cmd);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else{
					String cmd = "cmd /c copy " + "\"" + aa[i][0] + "\"" + " "
							+ "\"" +desc2 + "\"" +" /Y";
					try {
						Runtime.getRuntime().exec(cmd);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}

	}
}
