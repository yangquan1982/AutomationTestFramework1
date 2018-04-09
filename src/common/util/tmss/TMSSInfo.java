package common.util.tmss;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

public class TMSSInfo {
	
	public static String sendTestExcuteResultFlag;
	public static String GT3Kuri;
	public static String userName;
	public static String password;
    public static String ServerName;
    public static String ServerPort;
    
	public static void initTmssConfigInfo(String gt3kPath) {
		readGT3KConfigFile(getGt3kConfigFile(gt3kPath));
	}
	
	private static File getGt3kConfigFile(String gt3kPath) {
		File gt3kfile = new File( gt3kPath );
		if (gt3kfile.exists()) {
			return gt3kfile;
		}

		String gt3k = System.getProperty("gt3k");
		if (gt3k != null && new File(gt3k).exists()) {
			return new File(gt3k);
		}
		return null;
	}
    
    
	public static void initTmssConfigInfo() {
		readGT3KConfigFile(getGt3kConfigFile());
	}
	
	private static File getGt3kConfigFile() {
		File gt3kfile = new File("GT3K.ini");
		if (gt3kfile.exists()) {
			return gt3kfile;
		}

		String gt3k = System.getProperty("gt3k");
		if (gt3k != null && new File(gt3k).exists()) {
			return new File(gt3k);
		}
		return null;
	}
	
	/**
	 * ��ȡGT3K.ini�ļ��е�����
	 */
	public static void readGT3KConfigFile(File file)
	{
		try
		{
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			String lineString = "";

			while ((lineString = lnr.readLine()) != null)
			{
				if (lineString.isEmpty())
				{
					continue;
				}

				if (lineString.startsWith("UserName"))
				{
					userName = lineString.split(":")[1].trim();
				}
				else if (lineString.startsWith("Password"))
				{
					password = lineString.split(":")[1].trim();
				}
				else if (lineString.startsWith("SendResult"))
				{
					sendTestExcuteResultFlag = lineString.split(":")[1].trim();
				}
				else if (lineString.startsWith("VersionURI"))
				{
					GT3Kuri = lineString.split(":")[1].trim();
				}
				else if ( lineString.startsWith("ServerName"))
				{
					ServerName = lineString.split(":")[1].trim();
				}
				else if ( lineString.startsWith("ServerPort"))
				{
					ServerPort = lineString.split(":")[1].trim();
				}
			}
			lnr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
