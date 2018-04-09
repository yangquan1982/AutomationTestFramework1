package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;

import common.parameter.CellInfo;


public class PlatfromParamUtil
{
    
    public static void main(String[] args)
    {
        String path = "C:\\Users\\pGenexAutotest\\Desktop\\ss.xlsx";
        String sheetName = "模板";
        String sheetTestID = "testID";
        String rowNumDefinition = "维护人员";
        Object[][] object  = getArrayContent(path,sheetName,sheetTestID,rowNumDefinition);
        
    }

    
    /**
     * <一句话功能简述>获取excel表格中的值
     * <功能详细描述>
     * @param path
     * @param sheetName
     * @param sheetTestID
     * @param rowNum 
     * @see [类、类#方法、类#成员]
     */
    public static Object[][]  getArrayContent(String path, String sheetName, String sheetTestID, String rowNumDefinition)
    {
        Object[][] object = null;
        try
        {
            System.out.println(new File(path).isFile()); 
            Workbook wb = WorkbookFactory.create(new FileInputStream(path));
            Sheet sheet = wb.getSheet(sheetName);
            Sheet sheetTestid = wb.getSheet(sheetTestID);
            int rowNum = sheet.getLastRowNum();
            int rowTestidNum = sheetTestid.getLastRowNum();
            object = getObject(sheet);
            if(rowNum==rowTestidNum){
                for (int i = 2; i < rowNum+1; i++){
                    Row row = sheet.getRow(i);
                    Row rowTestId = sheetTestid.getRow(i);
                    int cellNum = row.getLastCellNum();
                    String ss = ExcelUtil.getCellValue(row.getCell(0));
                    if (rowNumDefinition.equals(ExcelUtil.getCellValue(row.getCell(0)))) {
                    	for (int j = 1; j < cellNum; j++){
                                CellInfo b = new CellInfo();
                                b.setIsRun(setVlaue2(ExcelUtil.getCellValue(row.getCell(j))));
                                b.setTestID(setVlaue(ExcelUtil.getCellValue(rowTestId.getCell(j))));
                                b.setCellNum(j);
                                object[j-1][0] = b;
                        }
					}
                    
                }
            }else{
                System.err.println("用例模板和用例ID模板不一致，请校验模板！！！");
            }
        }
        catch (EncryptedDocumentException | InvalidFormatException | IOException e)
        {
            e.printStackTrace();
        }    
        return object ;
    }


    private static Object[][] getObject(Sheet sheet)
    {
        int cellNum = sheet.getRow(1).getLastCellNum();
        Object[][] o = new Object[cellNum-1][1];
        return o;
    }

 
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param stringCellValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String setVlaue(String stringCellValue)
    {
        if(stringCellValue.equals("") || stringCellValue==null){
            return "";
        }else{
            return stringCellValue;
        }
    }
    
    private static String setVlaue2(String stringCellValue)
    {
        if(stringCellValue.equals("") || stringCellValue==null){
            return null;
        }else{
            return stringCellValue;
        }
    }
    
	public static String getAccount(String path, String sheetName,String accountype) {
		String account = null;
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			Sheet sheet = wb.getSheet(sheetName);
			int rowNum = sheet.getLastRowNum();
			int i = 1;
			for (; i < rowNum+1; i++) {
				Row row = sheet.getRow(i);
				if (accountype.equals(ExcelUtil.getCellValue(row.getCell(0)))) {
					account = setVlaue2(ExcelUtil.getCellValue(row.getCell(1)));
					if(account==null){
						Assert.fail("人员分配表中类型为【"+accountype+"】的人员信息为空！");
					}
					break;
				}
			}
			if (i==rowNum+1) {
				Assert.fail("人员分配表中没有找到类型为【"+accountype+"】的信息！");
			}
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
		return account;
	}
	
	public static String getAddAccount(String path, String sheetName,String accountype) {
		String account = null;
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			Sheet sheet = wb.getSheet(sheetName);
			int rowNum = sheet.getLastRowNum();
			int i = 1;
			for (; i < rowNum+1; i++) {
				Row row = sheet.getRow(i);
				if (accountype.equals(ExcelUtil.getCellValue(row.getCell(0)))) {
					account = setVlaue2(ExcelUtil.getCellValue(row.getCell(2)));
					if(account==null){
						Assert.fail("人员分配表中类型为【"+accountype+"】的待添加人员信息为空！");
					}
					break;
				}
			}
			if (i==rowNum+1) {
				Assert.fail("人员分配表中没有找到类型为【"+accountype+"】的信息！");
			}
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
		return account;
	}
	
	public static String getAccountPwd(String path, String sheetName,String account) {
		String password = null;
		
		if (account!=null&&!StringUtils.isBlank(account)) {
			if (account.contains("@126.com") || account.contains("@163.com")) {
				password = "Sk@123456";
				return password;
			}
			try {
				Workbook wb = WorkbookFactory.create(new FileInputStream(path));
				Sheet sheet = wb.getSheet(sheetName);
				int rowNum = sheet.getLastRowNum();
				int i =1;
				for (; i < rowNum+1; i++) {
					Row row = sheet.getRow(i);
					String ss = ExcelUtil.getCellValue(row.getCell(0));
					if (account.equals(ExcelUtil.getCellValue(row.getCell(0)))) {
						password = setVlaue2(ExcelUtil.getCellValue(row
								.getCell(1)));
						break;
					}
				}
				if (i==rowNum+1) {					
					Assert.fail("用户表格中没有账号："+account+"的信息！");
				}
			} catch (EncryptedDocumentException | InvalidFormatException
					| IOException e) {
				e.printStackTrace();
			}
		}else{
			Assert.fail("提供账号为空！");
		}
		return password;
	}
	
}
