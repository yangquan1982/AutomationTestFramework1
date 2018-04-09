package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import cloud_platform.task.platform.ProjectManageTask;

public class ParamUtil {

	public static void main(String[] args) {
		try {
			Object[][] a = ParamUtil.getObjectArr("Data/suanfa.xlsx", "算法正确性", 15, 3, 0);
			System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object[][] getObjectArr(String path, String sheetName) throws Exception {

		return getObjectArr(path, sheetName, 0);
	}

	/**
	 * <b>Description:</b>获取指定行列信息
	 *
	 * @author lwx242612
	 * @param path
	 * @param sheetName
	 * @param cellStartIndex
	 *            数据开始列
	 * @param dataStartIndex
	 *            数据起始行
	 * @param descRowIndex
	 *            描述行
	 * @return
	 * @throws Exception
	 * @return Object[][]
	 */
	public static Object[][] getObjectArr(String path, String sheetName, int cellStartIndex, int dataStartRowIndex,
			int descRowIndex) throws Exception {

		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}

		InputStream inStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inStream);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			Assert.fail(CommonFile.fileMsg(path) + ",sheetName:" + sheetName + " is not find");
		}
		// int row = sheet.getPhysicalNumberOfRows();
		int row = getValidRowNums(sheet, cellStartIndex, dataStartRowIndex - 1, descRowIndex);
		// int cell = sheet.getRow(0).getPhysicalNumberOfCells() - index;
		int cell = getValidCellNums(sheet, cellStartIndex, dataStartRowIndex, descRowIndex);
		System.out.println("row = " + row + "\t" + "cell = " + cell);
		Object[][] array = new Object[row][cell];

		List<String> paramList = getParameterType(sheet, cell, cellStartIndex, descRowIndex);

		for (int i = dataStartRowIndex; i < row+dataStartRowIndex; i++) {
			for (int j = cellStartIndex; j < cell + cellStartIndex; j++) {
				System.out.println("row = " + i + "\t" + "cell = " + j);

				if (sheet.getRow(i).getCell(j) == null) {
					if ("string".equalsIgnoreCase(paramList.get(j - cellStartIndex))) {
						array[ i-dataStartRowIndex ][j - cellStartIndex] = null;
						continue;
					} else if ("arraytype:string/".equalsIgnoreCase(paramList.get(j - cellStartIndex))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
						continue;
					} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j - cellStartIndex))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
						continue;
					} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
						continue;
					} else {
						Assert.fail("row:" + (i - 1) + "cell:" + (j - cellStartIndex) + ",参数化EXCEL非String列不可为空");
					}
				}
				if ("".equals(sheet.getRow(i).getCell(j).toString())) {
					if ("arraytype:string/".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
						continue;
					} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
						continue;
					} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
						continue;
					}
					if ("int".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
						Assert.fail("row:" + ( i-dataStartRowIndex) + "cell:" + (j - cellStartIndex) + ",参数化EXCEL非String列不可为空");
					}
					if ("boolean".equalsIgnoreCase(paramList.get(j))) {
						Assert.fail("row:" + ( i-dataStartRowIndex) + "cell:" + (j - cellStartIndex) + ",参数化EXCEL非String列不可为空");
					}
				}

				String text = sheet.getRow(i).getCell(j).toString();
				if ("string".equalsIgnoreCase(paramList.get(j - cellStartIndex))) {
					if ("null".equalsIgnoreCase(text)) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
					} else if ((text.contains("/")) && (!text.contains(":"))) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = text.split("/");
					} else {
						System.out.println(row);
						if(i==6){
							String a = "1";
							
						}
						array[ i-dataStartRowIndex][j - cellStartIndex] = ParamUtil.getString(sheet, i, j);
					}
				} else if ("int".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
					if (text.contains(".")) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = Integer.valueOf(text.split("\\.")[0]);
						continue;
					}
					array[ i-dataStartRowIndex][j - cellStartIndex] = Integer.parseInt(text);
				} else if ("boolean".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
					array[ i-dataStartRowIndex][j - cellStartIndex] = Boolean.parseBoolean(text);
				} else if ("double".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
					array[ i-dataStartRowIndex][j - cellStartIndex] = Double.parseDouble(text);
				} else if ("arraytype:string/".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
					if ("null".equalsIgnoreCase(text)) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
					} else {
						array[ i-dataStartRowIndex][j - cellStartIndex] = text.split("/");
					}
				} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
					if ("null".equalsIgnoreCase(text)) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
					} else {
						array[ i-dataStartRowIndex][j - cellStartIndex] = text.split(",");
					}
				} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j-cellStartIndex))) {
					if ("null".equalsIgnoreCase(text)) {
						array[ i-dataStartRowIndex][j - cellStartIndex] = null;
					} else {
						array[ i-dataStartRowIndex][j - cellStartIndex] = text.split(";");
					}
				}

			}
			for (int j = 0; j < cell; j++) {
				System.out.print("td" + j + ":" + array[ i-dataStartRowIndex][j] + "\t");
				if (j == cell - 1) {
					System.out.println();
				}
			}
			System.out.println();
		}

		return array;
	}

	/**
	 * 
	* @Description: 获取项目名称
	* @param path  excel路径
	* @param sheetName	
	* @param rownum		项目信息行号
	* @param cellstart	项目类型起始列号
	* @return 
	* @return String
	* @author zwx320041
	* @date 2017-2-15
	 */
	public  static String ProjectName(String path, String sheetName, int rownum,int cellstart){
		String projectname = null;
		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}

		XSSFWorkbook workbook;
		try {
			InputStream inStream = new FileInputStream(path);
			workbook = new XSSFWorkbook(inStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				Assert.fail(CommonFile.fileMsg(path) + ",sheetName:" + sheetName + " is not find");
			}
			String[] infor =new String[7];
			Row row = sheet.getRow(rownum);
			Assert.assertFalse(CommonFile.fileMsg(path)+sheetName+"没有找到对应的行"+rownum, row==null);
			for (int i = 0; i < 7; i++) {
				String cellinfor = ExcelUtil.getCellValue(row.getCell(i+cellstart));
				infor[i]=cellinfor;
			}
			projectname = ProjectManageTask.getProjectName(infor[0], infor[2], infor[3], infor[6], infor[4], infor[5]);
		} catch (Exception e) {
		}
		
		return projectname;
	}
	public static Object[][] getObjectArr(String path, String sheetName, int index) throws Exception {

		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}

		InputStream inStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inStream);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			Assert.fail(CommonFile.fileMsg(path) + ",sheetName:" + sheetName + " is not find");
		}
		// int row = sheet.getPhysicalNumberOfRows();
		int row = getValidRowNums(sheet);
		// int cell = sheet.getRow(0).getPhysicalNumberOfCells() - index;
		int cell = getValidCellNums(sheet) - index;
		System.out.println("row = " + row + "\t" + "cell = " + cell);
		Object[][] array = new Object[row - 1][cell];

		List<String> paramList = getParameterType(sheet, cell);

		for (int i = 1; i < row; i++) {
			for (int j = index; j < cell + index; j++) {
				// System.out.println("row = " + i + "\t" + "cell = " + j);

				if (sheet.getRow(i).getCell(j) == null) {
					if ("string".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					} else if ("arraytype:string/".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					} else {
						Assert.fail("row:" + (i - 1) + "cell:" + (j - index) + ",参数化EXCEL非String列不可为空");
					}
				}
				if ("".equals(sheet.getRow(i).getCell(j).toString())) {
					if ("arraytype:string/".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j))) {
						array[i - 1][j - index] = null;
						continue;
					}
					if ("int".equalsIgnoreCase(paramList.get(j))) {
						Assert.fail("row:" + (i - 1) + "cell:" + (j - index) + ",参数化EXCEL非String列不可为空");
					}
					if ("boolean".equalsIgnoreCase(paramList.get(j))) {
						Assert.fail("row:" + (i - 1) + "cell:" + (j - index) + ",参数化EXCEL非String列不可为空");
					}
				}

				String text = sheet.getRow(i).getCell(j).toString();
				if ("string".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i - 1][j - index] = null;
					} else if ((text.contains("/")) && (!text.contains(":"))) {
						array[i - 1][j - index] = text.split("/");
					} else {
						array[i - 1][j - index] = ParamUtil.getString(sheet, i, j);
					}
				} else if ("int".equalsIgnoreCase(paramList.get(j))) {
					if (text.contains(".")) {
						array[i - 1][j - index] = Integer.valueOf(text.split("\\.")[0]);
						continue;
					}
					array[i - 1][j - index] = Integer.parseInt(text);
				} else if ("boolean".equalsIgnoreCase(paramList.get(j))) {
					array[i - 1][j - index] = Boolean.parseBoolean(text);
				} else if ("double".equalsIgnoreCase(paramList.get(j))) {
					array[i - 1][j - index] = Double.parseDouble(text);
				} else if ("arraytype:string/".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i - 1][j - index] = null;
					} else {
						array[i - 1][j - index] = text.split("/");
					}
				} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i - 1][j - index] = null;
					} else {
						array[i - 1][j - index] = text.split(",");
					}
				} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i - 1][j - index] = null;
					} else {
						array[i - 1][j - index] = text.split(";");
					}
				}

			}
			for (int j = 0; j < cell; j++) {
				System.out.print("td" + j + ":" + array[i - 1][j] + "\t");
				if (j == cell - 1) {
					System.out.println();
				}
			}
			System.out.println();
		}

		return array;
	}

	/**
	 * <b>Description:</b> 获取有效参数列数
	 *
	 * @author lwx242612
	 * @param sheet
	 * @param cellStartIndex
	 * @param descRowIndex
	 * @return
	 * @return int
	 */
	public static int getValidCellNums(XSSFSheet sheet, int cellStartIndex, int dataStartRowIndex, int descRowIndex) {
		int maxcell = sheet.getRow(descRowIndex).getPhysicalNumberOfCells();
		int realCellNum = 0;
		for (int i = cellStartIndex; i < maxcell; i++) {
			String value = sheet.getRow(descRowIndex).getCell(i).toString();
			if (StringUtils.isBlank(value)) {
				break;
			} else {
				realCellNum++;
			}
		}
		return realCellNum;
	}

	public static int getValidCellNums(XSSFSheet sheet) {
		int maxcell = sheet.getRow(0).getPhysicalNumberOfCells();
		int realCellNum = 0;
		for (int i = 0; i < maxcell; i++) {
			String value = sheet.getRow(0).getCell(i).toString();
			if (StringUtils.isBlank(value)) {
				break;
			} else {
				realCellNum++;
			}
		}
		return realCellNum;
	}

	/**
	 * <b>Description:</b> 获取参数行长度
	 *
	 * @author lwx242612
	 * @param sheet
	 * @param cellStartIndex
	 *            起始列
	 * @param descRowIndex
	 *            描述行
	 * @return
	 * @return int
	 */
	public static int getValidRowNums(XSSFSheet sheet, int cellStartIndex, int dataStartRowIndex, int descRowIndex) {
		int maxcell = getValidCellNums(sheet, cellStartIndex, dataStartRowIndex, descRowIndex);
		int maxrow = sheet.getPhysicalNumberOfRows();
		int realRowNum = 0;
		for (int i = dataStartRowIndex+1; i < maxrow; i++) {
			Row row = sheet.getRow(i);
			if (isBlankRow(row, maxcell, cellStartIndex)) {
				break;
			} else {
				realRowNum++;
			}
		}
		return realRowNum;
	}

	public static int getValidRowNums(XSSFSheet sheet) {
		int maxcell = getValidCellNums(sheet);
		int maxrow = sheet.getPhysicalNumberOfRows();
		int realRowNum = 0;
		for (int i = 0; i < maxrow; i++) {
			Row row = sheet.getRow(i);
			if (isBlankRow(row, maxcell)) {
				break;
			} else {
				realRowNum++;
			}
		}
		return realRowNum;
	}

	/**
	 * <b>Description:</b>
	 *
	 * @author lwx242612
	 * @param row
	 * @param cell
	 * @return
	 * @return boolean
	 */
	private static boolean isBlankRow(Row row, int cellLength, int cellStartIndex) {
		boolean flag = true;
		for (int i = cellStartIndex; i < cellLength + cellStartIndex; i++) {
			Object ob = row.getCell(i);
			if (ob != null) {
				if (!StringUtils.isBlank(ob.toString())) {
					flag = false;
				}
			}

		}
		return flag;
	}

	private static boolean isBlankRow(Row row, int cell) {
		boolean flag = true;
		for (int i = 0; i < cell; i++) {
			Object ob = row.getCell(i);
			if (ob != null) {
				if (!StringUtils.isBlank(ob.toString())) {
					flag = false;
				}
			}

		}
		return flag;
	}

	/**
	 * <b>Description:</b>获取描述类型
	 *
	 * @author lwx242612
	 * @param sheet
	 * @param cell
	 * @return
	 * @return List<String>
	 */
	private static List<String> getParameterType(XSSFSheet sheet, int cellLength, int cellStartIndex,
			int descRowIndex) {
		List<String> paramList = new ArrayList<String>();
		for (int i = cellStartIndex; i < cellLength + cellStartIndex; i++) {
			String value = sheet.getRow(descRowIndex).getCell(i).toString();
			System.out.println(value);
			
			if (StringUtils.isBlank(value)) {
				break;
			}
			if (value.toLowerCase().contains("[string]")) {
				paramList.add("string");
			} else if (value.toLowerCase().contains("[int]")) {
				paramList.add("int");
			} else if (value.toLowerCase().contains("[double]")) {
				paramList.add("double");
			} else if (value.toLowerCase().contains("[boolean]")) {
				paramList.add("boolean");
			} else if (value.toLowerCase().contains("[arraytype:string]")) {
				if (value.toLowerCase().contains("[separator:,]")) {
					paramList.add("arraytype:string,");
				} else if (value.toLowerCase().contains("[separator:;]")) {
					paramList.add("arraytype:string;");
				} else {
					paramList.add("arraytype:string/");
				}

			} else {
				paramList.add("string");
			}

		}

		return paramList;
	}

	private static List<String> getParameterType(XSSFSheet sheet, int cell) {
		List<String> paramList = new ArrayList<String>();
		for (int i = 0; i < cell; i++) {
			String value = sheet.getRow(0).getCell(i).toString();
			if (StringUtils.isBlank(value)) {
				break;
			}
			if (value.toLowerCase().contains("[string]")) {
				paramList.add("string");
			} else if (value.toLowerCase().contains("[int]")) {
				paramList.add("int");
			} else if (value.toLowerCase().contains("[double]")) {
				paramList.add("double");
			} else if (value.toLowerCase().contains("[boolean]")) {
				paramList.add("boolean");
			} else if (value.toLowerCase().contains("[arraytype:string]")) {
				if (value.toLowerCase().contains("[separator:,]")) {
					paramList.add("arraytype:string,");
				} else if (value.toLowerCase().contains("[separator:;]")) {
					paramList.add("arraytype:string;");
				} else {
					paramList.add("arraytype:string/");
				}

			} else {
				paramList.add("string");
			}

		}

		return paramList;
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
					text = sheet.getRow(i).getCell(j).getNumericCellValue() + "";

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

	/**
	 * 获取单元格 信息
	 * 
	 * @param path
	 * @param sheetName
	 * @param i
	 * @param j
	 * @return
	 * @throws Exception
	 */
	public static String getExcelValue(String path, String sheetName, int i, int j) throws Exception {

		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}
		InputStream inStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inStream);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		String value = ParamUtil.getString(sheet, i, j);
		return value;
	}

	/**
	 * 参数化
	 * 
	 * @param path
	 * @param sheetName
	 * @param prjlable
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getObjectArr(String path, String sheetName, String prjlable) throws Exception {

		return getObjectArr2(path, sheetName, prjlable);
	}

	/**
	 * 数据上传并发测试参数构造
	 * 
	 * @param path
	 * @param sheetName
	 * @param prjlable
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getObjectArr2(String path, String sheetName, String prjlable) throws Exception {
		File srcFile = new File(path);
		if (!srcFile.isFile()) {
			Assert.fail(CommonFile.fileMsg(path) + " is not File");
		}

		InputStream inStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inStream);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			Assert.fail(CommonFile.fileMsg(path) + ",sheetName:" + sheetName + " is not find");
		}
		// int row = sheet.getPhysicalNumberOfRows();
		int row = getValidRowNums(sheet);
		// int cell = sheet.getRow(0).getPhysicalNumberOfCells();
		int cell = getValidCellNums(sheet);
		System.out.println("row = " + row + "\t" + "cell = " + cell);
		int arrayrownum = 0;
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < 1; j++) {
				String text = sheet.getRow(i).getCell(j).toString();
				List<String> prjs = Arrays.asList(text.split("/"));
				if (prjs.contains(prjlable)) {
					arrayrownum++;
					rows.add(i);
				}
			}
		}
		// 对应工程不需要传参数
		if (arrayrownum == 0) {
			Object[][] array = new Object[1][cell];
			for (int j = 0; j < cell; j++) {
				array[0][j] = null;
			}
			return array;
		}
		Object[][] array = new Object[arrayrownum][cell];

		List<String> paramList = getParameterType(sheet, cell);
		int i1 = 0;
		for (Integer rownum : rows) {

			for (int j = 0; j < cell; j++) {
				String text = null;
				if (sheet.getRow(rownum).getCell(j) == null) {
					if ("string".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					} else if ("arraytype:string/".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					} else {
						Assert.fail("row:" + (i1) + "cell:" + (j) + ",参数化EXCEL非String列不可为空");
					}
				}
				if ("".equals(sheet.getRow(rownum).getCell(j).toString())) {
					if ("arraytype:string/".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j))) {
						array[i1][j] = null;
						continue;
					}
					if ("int".equalsIgnoreCase(paramList.get(j))) {
						Assert.fail("row:" + (i1) + "cell:" + (j) + ",参数化EXCEL非String列不可为空");
					}
					if ("boolean".equalsIgnoreCase(paramList.get(j))) {
						Assert.fail("row:" + (i1) + "cell:" + (j) + ",参数化EXCEL非String列不可为空");
					}
				}

				text = sheet.getRow(rownum).getCell(j).toString();
				if ("string".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i1][j] = null;
					} else if ((text.contains("/")) && (!text.contains(":"))) {
						array[i1][j] = text.split("/");
					} else {
						array[i1][j] = text;
					}
				} else if ("int".equalsIgnoreCase(paramList.get(j))) {
					if (text.contains(".")) {
						array[i1][j] = Integer.valueOf(text.split("\\.")[0]);
						continue;
					}
					array[i1][j] = Integer.parseInt(text);
				} else if ("boolean".equalsIgnoreCase(paramList.get(j))) {
					array[i1][j] = Boolean.parseBoolean(text);
				} else if ("double".equalsIgnoreCase(paramList.get(j))) {
					array[i1][j] = Double.parseDouble(text);
				} else if ("arraytype:string/".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i1][j] = null;
					} else {
						array[i1][j] = text.split("/");
					}
				} else if ("arraytype:string,".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i1][j] = null;
					} else {
						array[i1][j] = text.split(",");
					}
				} else if ("arraytype:string;".equalsIgnoreCase(paramList.get(j))) {
					if ("null".equalsIgnoreCase(text)) {
						array[i1][j] = null;
					} else {
						array[i1][j] = text.split(";");
					}
				}

			}

			for (int j = 0; j < cell; j++) {
				System.out.print("td" + j + ":" + array[i1][j] + "\t");
				if (j == cell - 1) {
					System.out.println();
				}
			}

			i1++;
		}

		return array;

	}
}
