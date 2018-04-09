package common.util;

import java.awt.Color;
import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtil {


	public static final String[] TITLE  = {"TestCase ID", "TestCase Name", "Operation", "Expected Time", "Actual Time result"};
	
	

	public static final String[] RUNTIME_TITLE = {"dataQueryTime", "gridExportTime", "cellExportTime", "exceptionUserTime", "singleCellDisplayTime", "cellRadarTime", "queryPerformanceCounterTime", "queryTerminalAnalysisTime"};
	

	public static final int ACTUAL_TIME_RESULT_COLS = 4;
	

	public static final int ROWS = 8;
	

	public static final int COLS = 5;
	

	//public static final String DEST_EXCEL_NAME = System.getProperty("user.dir") + File.separator + "perftest_" + getNowDateTime() + ".xlsx";
	public static final String TOTAL_DEST_EXCEL_NAME = "perftest.xlsx";
	
	
	//public static final String TODAY_DEST_EXCEL_NAME = "D:/test_20160607.xlsx";
	public static final String TODAY_DEST_EXCEL_NAME = "perftest_" + getNowDate() + ".xlsx";
	

	public static final Color TITLE_BACKGROUND = new Color(0x6678A7);
	
	public static final Color CELL_BACKGROUND = new Color(0xB5D695);
	
	public static final Color CELL0_BACKGROUND = new Color(0xE4E4E4);
	

	public static final int EXPECTED_TIME_COL = 3;
	
	public static final int RUMTIME_TITLE_COL = 2;
	
	
	public static final int ACTUAL_TIME_COL  = 4;
	

	public static final int TEST_CASE_ID_COL = 0;
	

	public static final int TEST_NAME_ID_COL = 1;

	public static final int RUNTIME_ROW0 = 0;
	public static final int RUNTIME_ROW1 = 1;
	public static final int RUNTIME_ROW2 = 2;
	public static final int RUNTIME_ROW3 = 3;
	public static final int RUNTIME_ROW4 = 4;
	public static final int RUNTIME_ROW5 = 5;
	public static final int RUNTIME_ROW6 = 6;
	public static final int RUNTIME_ROW7 = 7;
	public static final int RUNTIME_ROW8 = 8;
	

	public static final int TITLE_IN_ROW = 0;
	
	public static String getNowDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static long getFileName() {
		
		File file = new File(System.getProperty("user.dir"));
		File[] files = file.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				
				if(pathname.getName().endsWith(".xlsx")) {
					return true;
				}
				return false;
			}
		});
		
		long maxDatetime = Long.parseLong(files[0].getName().substring(files[0].getName().indexOf("_")+1, files[0].getName().indexOf(".")));
		for(int i = 1; i < files.length; i++) {
			
			if(Long.parseLong(files[i].getName().substring(files[i].getName().indexOf("_")+1, files[i].getName().indexOf("."))) > maxDatetime) {
				maxDatetime = Long.parseLong(files[i].getName().substring(files[i].getName().indexOf("_")+1, files[i].getName().indexOf(".")));
			}
		}
		
		return maxDatetime;
	}
	
	public static String getFileLastModified(String filename) {
		File file = new File(filename);
		long modified = file.lastModified();
		Date date = new Date(modified);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	
	public static String getCellValue(Cell cell) {

		// 鏆傚瓨鍗曞厓鏍煎唴瀹�
		String value = "";
		if(cell == null)
		{
			return value;
		}
		// 鍖归厤鍗曞厓鏍煎唴瀹�
		switch (cell.getCellType()) {
		// 鏁版嵁鏍煎紡绫诲瀷
		case Cell.CELL_TYPE_NUMERIC:
			// 鍒ゆ柇鏄惁鏄棩鏈熺被鍨�
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (date != null) {
					// 鏍煎紡鍖栨棩鏈�
					value = new SimpleDateFormat("yyyy-mm-dd").format(date);
				} else {
					value = "";
				}
			} else {
				// 鏍煎紡鍖栨暟鎹�
				value = new DecimalFormat("0").format(cell
						.getNumericCellValue());
			}
			break;
		// 瀛楃涓茬被鍨�
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		// 鍏紡鐢熸垚绫诲瀷
		case XSSFCell.CELL_TYPE_FORMULA:
			// 瀵煎叆鏃跺鏋滀负鍏紡鐢熸垚鐨勬暟鎹垯鏃犲�
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		// 绌虹櫧
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		// 甯冨皵鍨�
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue() + "";
			break;
		// 閿欒鏍煎紡
		case Cell.CELL_TYPE_ERROR:
			value = "";
			break;
		default:
			value = cell.toString();
		}
		return value.trim();
 }
}
