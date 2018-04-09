package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

public class AppParamUtil {

	/**
	 * 业务APP参数化专用
	 * @param path
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getObjectArr(String path, String sheetName) throws Exception {

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
		int row = getValidRowNums(sheet);
		int cell = getValidCellNums(sheet);
		System.out.println("row = " + row + "\t" + "cell = " + cell);
		Object[][] array = new Object[row - 1][cell];

		List<String> paramList = getParameterType(sheet, cell);

		for (int i = 1; i < row; i++) {
			System.out.print("第" + i + "行 ");
			for (int j = 0; j < cell; j++) {
				String ColumnName = sheet.getRow(0).getCell(j).toString().trim().replace("\n", "");				
				if ("string".equalsIgnoreCase(paramList.get(j))) {
					array[i - 1][j] = AppParamUtil.getString(sheet, i, j);
				}  else if ("boolean".equalsIgnoreCase(paramList.get(j))) {
					array[i - 1][j] = AppParamUtil.getBoolean(sheet, i, j);
				}  else if (paramList.get(j).contains("string:")) {
					array[i - 1][j] = AppParamUtil.getArrayString(sheet,paramList, i, j);
				}else{
					Assert.fail("【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】,参数类型找不到，DataType:"+paramList.get(j));
				}	
			}
			//每行换行处理
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
	private static int getValidCellNums(XSSFSheet sheet) {
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
	private static int getValidRowNums(XSSFSheet sheet) {
		int maxcell = getValidCellNums(sheet);
		int maxrow = sheet.getPhysicalNumberOfRows();
		int realRowNum = 0;
		for (int i = 0; i < maxrow; i++) {
			Row row = sheet.getRow(i);
			if(row !=null){
				if (isBlankRow(row, maxcell)) {
					break;
				} else {
					realRowNum++;
				}	
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

	private static List<String> getParameterType(XSSFSheet sheet, int cell) {
		List<String> paramList = new ArrayList<String>();
		for (int i = 0; i < cell; i++) {
			String value = sheet.getRow(0).getCell(i).toString();
			if (StringUtils.isBlank(value)) {
				break;
			}
			if (value.toLowerCase().contains("[string]")) {
				paramList.add("string");
			} else if (value.toLowerCase().contains("[boolean]")) {
				paramList.add("boolean");
			} else if (value.toLowerCase().contains("[arraytype:string]")) {				
				String separator ="/";
				if (value.toLowerCase().contains("[separator:")) {
					int LPath = value.toLowerCase().lastIndexOf(":");
					separator = value.toLowerCase().substring(LPath+1,LPath+2);
				} 
				paramList.add("string:"+separator);
			} else {
				paramList.add("string");
			}

		}

		return paramList;
	}

	/**
	 * 获取String值
	 * @param sheet
	 * @param i
	 * @param j
	 * @return
	 */
	private static String getString(XSSFSheet sheet,int i, int j) {
		String text =null;
		XSSFCell aa = sheet.getRow(i).getCell(j);
		String ColumnName = sheet.getRow(0).getCell(j).toString().trim().replace("\n", "");
		if(aa==null){
			text =null;
		}else{
			 text = sheet.getRow(i).getCell(j).toString().replace("\n", "");
			int CellType =sheet.getRow(i).getCell(j).getCellType();
			if (CellType == XSSFCell.CELL_TYPE_BLANK) {
				text =null;
			}else{
				if ("null".equalsIgnoreCase(text)) {
					text =null;
				} else {
					if(CellType != XSSFCell.CELL_TYPE_STRING){
						String Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是String";
						if(CellType == XSSFCell.CELL_TYPE_BOOLEAN){
							Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是String,是布尔类型";
						}else if(CellType == XSSFCell.CELL_TYPE_NUMERIC){
							Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是String,是数字类型";
						}
						Assert.fail(Message);
					}else{
						text = sheet.getRow(i).getCell(j).getStringCellValue().replace("\n", "");
					}
				}	
			}	
		}

		System.out.print("【" + (j + 1) + ColumnName+"】" + text + "\t");
		return text;
	}
	/**
	 * 获取Boolean值
	 * @param sheet
	 * @param i
	 * @param j
	 * @return
	 */
	private static boolean getBoolean(XSSFSheet sheet, int i, int j) {
		boolean text =false ;
		int CellType =sheet.getRow(i).getCell(j).getCellType();
		String ColumnName = sheet.getRow(0).getCell(j).toString().trim().replace("\n", "");
		if(CellType != XSSFCell.CELL_TYPE_BOOLEAN){
			String Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是布尔类型";
			if(CellType == XSSFCell.CELL_TYPE_NUMERIC){
				Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是布尔类型,是数字类型";
			}else if(CellType == XSSFCell.CELL_TYPE_STRING){
				Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是布尔类型,是String类型";
			}else if(CellType == XSSFCell.CELL_TYPE_BLANK){
				Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是布尔类型,是空值";
			}
			Assert.fail(Message);
		}else{
			text = sheet.getRow(i).getCell(j).getBooleanCellValue();	
		}
		System.out.print("【" + (j + 1) + ColumnName+"】" + text + "\t");
		return text;
	}
	/**
	 * 获取String数组值
	 * @param sheet
	 * @param paramList
	 * @param i
	 * @param j
	 * @return
	 */
	private static String[] getArrayString(XSSFSheet sheet,List<String> paramList, int i, int j) {
		String[] textVal = null;
		String text = sheet.getRow(i).getCell(j).toString().replace("\n", "");
		int CellType =sheet.getRow(i).getCell(j).getCellType();
		String ColumnName = sheet.getRow(0).getCell(j).toString().trim().replace("\n", "");
		int LPath = paramList.get(j).toLowerCase().lastIndexOf(":");
		String separator = paramList.get(j).toLowerCase().substring(LPath+1,LPath+2);
		if (CellType == XSSFCell.CELL_TYPE_BLANK) {
			textVal = null;
		}else{
			if ("null".equalsIgnoreCase(text)) {
				textVal = null;
			} else {
				if(CellType != XSSFCell.CELL_TYPE_STRING){
					String Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是String";
					if(CellType == XSSFCell.CELL_TYPE_BOOLEAN){
						Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是String,是布尔类型";
					}else if(CellType == XSSFCell.CELL_TYPE_NUMERIC){
						Message = "【" + (j + 1) + ColumnName +" (" + i + "," + j + ")" + "】数据类型不是String,是数字类型";
					}
					Assert.fail(Message);
				}else{
					textVal = text.split(separator);	
				}
			}
		}
		System.out.print("【" + (j + 1) +"[string数组 "+"分隔符"+separator+"]"+ ColumnName+"】"+text + "\t");
		return textVal;
	}
}
