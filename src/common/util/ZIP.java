package common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZIP {
	private static final int BUFFER = 1024;

	public static void depress(String srcFile, String dstDir)  {
		try {
			CheckedInputStream cis = new CheckedInputStream(new FileInputStream(
					srcFile), new CRC32());
			Charset charset = Charset.forName("CP936");
			ZipInputStream zis = new ZipInputStream(cis,charset);
			decompress(new File(dstDir), zis);
			zis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 文件 解压缩
	 * 
	 * @param destFile
	 *            目标文件
	 * @param zis
	 *            ZipInputStream
	 * @throws Exception
	 */
	private static void decompress(File destFile, ZipInputStream zis)
			throws Exception {
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {
			// 文件
			String dir = destFile.getPath() + File.separator + entry.getName();
			File dirFile = new File(dir);
			// 文件检查
			fileProber(dirFile);
			if (entry.isDirectory()) {
				dirFile.mkdirs();
			} else {
				decompressFile(dirFile, zis);
			}
			zis.closeEntry();
		}
	}

	/**
	 * 文件探针
	 * 
	 * 
	 * 当父目录不存在时，创建目录！
	 * 
	 * 
	 * @param dirFile
	 */
	private static void fileProber(File dirFile) {
		File parentFile = dirFile.getParentFile();
		if (!parentFile.exists()) {
			// 递归寻找上级目录
			fileProber(parentFile);
			parentFile.mkdir();
		}
	}

	/**
	 * 文件解压缩
	 * 
	 * @param destFile
	 *            目标文件
	 * @param zis
	 *            ZipInputStream
	 * @throws Exception
	 */
	private static void decompressFile(File destFile, ZipInputStream zis)
			throws Exception {
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFile));
		int count;
		byte data[] = new byte[BUFFER];
		while ((count = zis.read(data, 0, BUFFER)) != -1) {
			bos.write(data, 0, count);
		}
		bos.close();
	}
	/**
	 * 功能：获取当前时间，日志打印使用
	 * @param dialogTitle  dialog的title属性
	 */
	public static String NowTime() 
	{
		long longtime = System.currentTimeMillis();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
		String Time = "[JQuery] "+"["+ZIP.IP()+"] "+"["+sdf.format(longtime)+"] ";
		return Time;
	}
	/**
	 * 功能：获取当前时间
	 */
	public static String hhmmss() 
	{
		long longtime = System.currentTimeMillis();
		SimpleDateFormat sdf =new SimpleDateFormat("HHmmss");
		String Time = sdf.format(longtime);
		return Time;
	}
	
	public static String MMddhhmmssms() 
	{
		long longtime = System.currentTimeMillis();
		SimpleDateFormat sdf =new SimpleDateFormat("MMddHHmmssms");
		String Time = sdf.format(longtime);
		return Time;
	}
	
	public static String GetTime(String formatString) 
	{
		long longtime = System.currentTimeMillis();
		SimpleDateFormat sdf =new SimpleDateFormat(formatString);
		String Time = sdf.format(longtime);
		return Time;
	}
	/**
	 * 功能：获取IP
	 */
	public static String IP() 
	{
		String ip = "";
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();//
		} catch (UnknownHostException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
		return ip;
	}
}
