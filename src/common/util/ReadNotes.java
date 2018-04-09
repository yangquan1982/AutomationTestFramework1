package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import cloud_platform.page.DataCenterConst;
import common.constant.system.SystemConstant;
import common.pobjec.ProjectUpDataInfor;

import common.util.LOG;

public class ReadNotes {

	/**
	 * 读Excel
	 * 
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public Workbook readExcelHead(String path) throws InvalidFormatException,
			IOException {
		FileInputStream fileIS;
		Workbook wb;
		fileIS = new FileInputStream(path);
		wb = WorkbookFactory.create(fileIS);
		return wb;
	}

	/**
	 * 读取配置文件Excel中第一个页签参数，判断下载模板是否正确
	 * 
	 * @param rat
	 * @param downloadFormworkType
	 */
	public String readExcelProperty(String formworkPath, String downloadPath,
			String rat, String downloadFormworkType) 
	{
		LOG.info_aw("开始对比文件");
		Workbook wbFormwork;
		Workbook wbDownload;
		try 
		{
			wbFormwork = new ReadNotes().readExcelHead(formworkPath);
			wbDownload = new ReadNotes().readExcelHead(downloadPath);
			Sheet sheetFormwork = wbFormwork.getSheet(rat);
			Sheet sheetDownload = wbDownload.getSheet(rat);
			
			int rowInt = sheetFormwork.getPhysicalNumberOfRows();
			int cellInt = sheetFormwork.getRow(0).getPhysicalNumberOfCells();
			
			int cellIntDownload = sheetDownload.getRow(0).getPhysicalNumberOfCells();
			
			System.out.println("row = " + rowInt + "\t" + "cell = " + cellInt);
			for (int i = 0; i < rowInt; i++) 
			{
				for (int j = 0; j < cellInt; j++) 
				{
					// 获取下载文件和正确模板单元格中的内容，进行对比
					Row rowFormwork = sheetFormwork.getRow(i);
					String formworkCellStr = "单元格内容：【"+rowFormwork.getCell(j).getStringCellValue()+"】"; //单元格内容
					RichTextString cellComment1 = rowFormwork.getCell(j).getCellComment().getString();//获取批注内容
					formworkCellStr += "批注：【"+cellComment1+"】";
					System.out.println("模板的第" + i + "行，第" + j + "个单元格，单元格中的内容为：" + formworkCellStr);

					
					String compareResult = getDownLoadContent(sheetDownload,cellIntDownload,formworkCellStr);
					
					// 下载模板异常 则将模板保存至某一路径下，将名字也作出相应的更改
					if (compareResult!=null) 
					{
						String newFileName = setName(rat, downloadFormworkType);
						String path = SystemConstant.ErrorParamTemplatePath; // 问题模板的存储路径
						if (!new File(path).isDirectory()) 
						{
							new File(path).mkdirs();
						}
						path += newFileName;
						copyFile(downloadPath, path);
						LOG.info_testcase("下载的模板和正常模板匹配不上，下载模板已保存，获取路径为："
								+ path);
						return compareResult;
					}
				}
			}
		} catch (InvalidFormatException | IOException e) 
		{
			return "在读取下载报告时异常!"+e.getMessage();
		}
		return null;
	}

	
	/**
	 * 和基线对比下载模板内容
	 * @param sheetDownload
	 * @param cellIntDownload
	 * @param formworkCellStr
	 */
	private String getDownLoadContent(Sheet sheetDownload, int cellIntDownload, String formworkCellStr) 
	{
		Row rowDownload = sheetDownload.getRow(0); 
		for(int i = 0;i<cellIntDownload;i++)
		{
			String downloadCellStr = "单元格内容：【"+rowDownload.getCell(i).getStringCellValue()+"】"; //单元格内容
			RichTextString cellComment2 = rowDownload.getCell(i).getCellComment().getString(); //批注内容
			downloadCellStr += "批注：【"+cellComment2+"】";
			System.out.println("下载的第" +0 + "行，第" + i+ "个单元格，单元格中的内容为：" + downloadCellStr);
			if(formworkCellStr.equals(downloadCellStr))
			{
				return null;
			}
		}
		return "下载报告中找不到"+formworkCellStr+"！";
	}

	/**
	 * 处理字符串
	 * 
	 * @param downloadFormworkType
	 * @return
	 */
	private String setName(String TemplateName, String downloadFormworkType) {
		String str = DataCenterConst.downloadFileName + TemplateName;
		str = str + "-" + downloadFormworkType + ".xlsx";
		return str;
	}

	/**
	 * 将工参数据部分有问题的下载模板保存起来
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				// 文件存在时
				InputStream inStream = new FileInputStream(oldPath);
				// 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					// 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// String a =
		// "D:\\GENEXCloud_V3_P1\\Data\\EngineeringParameterFormWork\\工参模板\\LTE\\GENEXCloud平台LTE工参模板-ACP.xlsx";
		// String path
		// ="D:\\GENEXCloud_V3_P1\\Data\\temp\\GENEXCloud平台LTE工参模板.xlsx";
		// new ReadNotes().readExcelProperty(a,path, "LTE", "ACP");

		// try
		// {
		// File file1 = new
		// File("D:\\GENEXCloud_V3_P1\\Data\\EngineeringParameterFormWork\\工参模板\\LTE\\GENEXCloud平台LTE工参模板-ACP.xlsx");
		// File file2 = new
		// File("D:\\GENEXCloud_V3_P1\\Data\\temp\\GENEXCloud平台LTE工参模板.xlsx");
		// boolean b = FileUtils.contentEquals(file1, file2);
		// System.out.print("模板对比结果:"+b);
		// }
		// catch (IOException e)
		// {
		// e.printStackTrace();
		// }

	}

	private static String getString(XSSFSheet sheet, int i, int j) {
		String text = sheet.getRow(i).getCell(j).toString();
		if ("null".equalsIgnoreCase(text)) {
			return null;
		} else {
			switch (sheet.getRow(i).getCell(j).getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				text = sheet.getRow(i).getCell(j).getStringCellValue();
				// System.out.println("AAAAA:"+text);
				return text;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				text = sheet.getRow(i).getCell(j).toString();
				return text;
			case XSSFCell.CELL_TYPE_NUMERIC:
				if (text.endsWith(".0")) {
					text = text.split("\\.")[0];
					text = String.valueOf(Integer.parseInt(text));
				} else {
					text = sheet.getRow(i).getCell(j).getNumericCellValue()
							+ "";

				}
				// System.out.println("BBBBB:"+text);
				return text;
			case XSSFCell.CELL_TYPE_BLANK:
				return null;
			default:
				break;
			}
		}
		return text;
	}

	public static String getExcelValue(String path, String sheetName, int i,
			int j) throws Exception {

		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}
		InputStream inStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inStream);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		String value = ReadNotes.getString(sheet, i, j);
		return value;
	}

	public static int getRowNum(String path, String sheetname, String value,
			int cellNum) throws Exception {

		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}
		InputStream inStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inStream);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		for (int k = 1; k < sheet.getLastRowNum(); k++) {
			String ss = sheet.getRow(k).getCell(cellNum).getStringCellValue();
			if (StringUtils.equalsIgnoreCase(ss, value)) {
				return k;
			}
		}
		return -1;
	}

	public static int prjnumPos = 0;
	public static int prjnamePos = 1;
	public static int prjstatePos = 7;

	public static ArrayList<ProjectUpDataInfor> getProjectInfor(String path,
			String sheetname)  {
		try {
			Runtime.getRuntime().exec("taskkill /f /im excel.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ProjectUpDataInfor> pd = new ArrayList<ProjectUpDataInfor>();
		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}
		try {
			InputStream inStream = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(inStream);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			int validrow = ParamUtil.getValidRowNums(sheet);
			for (int k = 1; k < validrow; k++) {

				String prjnum = sheet.getRow(k).getCell(prjnumPos)
						.getStringCellValue();
				String prjname = sheet.getRow(k).getCell(prjnamePos)
						.getStringCellValue();
				String prjstate = sheet.getRow(k).getCell(prjstatePos)
						.getStringCellValue();
				ProjectUpDataInfor pdtemp = new ProjectUpDataInfor(k, prjnum,
						prjname, prjstate);
				pd.add(pdtemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pd;
	}

	public static ProjectUpDataInfor getNotUpLoadProject(String path, String sheetname) {
		ArrayList<ProjectUpDataInfor> tempProjectUpDataInfor =getProjectInfor(path, sheetname);
		
		int i = 0;
		for (; i < tempProjectUpDataInfor.size(); i++) {
			ProjectUpDataInfor temp = tempProjectUpDataInfor.get(i);
			if (StringUtils.containsIgnoreCase(
					temp.getPrjstate(), "否"))
				return temp;
		}
		if(i==tempProjectUpDataInfor.size()){
			ReadNotes.setAllProjectState(path, sheetname);
			ArrayList<ProjectUpDataInfor> tempProjectUpDataInfor2 =getProjectInfor(path, sheetname);
			for (ProjectUpDataInfor projectUpDataInfor2 : tempProjectUpDataInfor2) {
				if (StringUtils.containsIgnoreCase(
						projectUpDataInfor2.getPrjstate(), "否"))
					return projectUpDataInfor2;
			}
		}
		return null;
	}

	public static void setProjectState(String reportPathName, String sheetname,
			ProjectUpDataInfor pstate) {

		try {
			FileInputStream fs = new FileInputStream(reportPathName);
			Workbook workbookReport = WorkbookFactory.create(fs);
			Sheet sheetReport = workbookReport.getSheet(sheetname);
			if(pstate!=null){
				int rownum = pstate.getRownum();
				sheetReport.getRow(rownum).getCell(prjstatePos).setCellValue("是");
			}
			FileOutputStream fileOut = new FileOutputStream(reportPathName);
			workbookReport.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("修改项目信息文件失败！！");
			System.out.println("已运行 xlCreate() : " + e);
		}

	}
	
	public static void setAllProjectState(String reportPathName, String sheetname) {

		try {
			FileInputStream fs = new FileInputStream(reportPathName);
			Workbook workbookReport = WorkbookFactory.create(fs);
			Sheet sheetReport = workbookReport.getSheet(sheetname);
			for (int i = 1; i < sheetReport.getPhysicalNumberOfRows(); i++) {
				sheetReport.getRow(i).getCell(prjstatePos).setCellValue("否");
			}			
			FileOutputStream fileOut = new FileOutputStream(reportPathName);
			workbookReport.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("修改项目信息文件失败！！");
			System.out.println("已运行 xlCreate() : " + e);
		}

	}
	

}
