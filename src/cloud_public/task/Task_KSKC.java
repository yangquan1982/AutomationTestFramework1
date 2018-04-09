package cloud_public.task;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.upload_data.UpLoad_GuideTask;
import cloud_public.page.KSKC_Page;
import common.constant.constparameter.ConstUrl;
import common.constant.system.SystemConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileCompare;

import common.util.LOG;


public class Task_KSKC {
	
	//public static String basepath="D:\\NACWebAutoTest\\baseline\\tempFileDownLoad\\数据上传\\";
	
	
	public static String GreeeClass=".divgroup.bgcolorFFF";
	public static String GreeeClassTex="divgroup bgcolorFFF";
	public static String YellowClass=".divgroup.bgcolor16c";
	public static String YellowClassTex="divgroup bgcolor16c";
	public static String[] QueryTimeList={"30 天","15 天","7 天","1 天"};
	public static String DataCountStatistic="数据量统计";
	
	
	/**
	 * 
	 * @param driver
	 * @param PrjName
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param neDate
	 * @param neNumber
	 * @param dataNeNumber
	 * @param partNeName
	 * @param NeName
	 * @param NoDataNeName
	 * @param searchText
	 * @param importPath
	 * @param isNofindNeNum
	 * @param mustfindNeNum
	 */
	
	public static void KSKC_FuntionVerify(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType,String neDate,int neNumber,String partNeName,String NeName,
			String NoDataNeName,String searchText, String importPath, int isNofindNeNum,int mustfindNeNum) {
		System.out.println("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");
		
		IndexTask.changePrj(driver, PrjName);		
		UpLoad_GuideTask.selectRat(driver,rat);
		UpLoad_GuideTask.selectDataType(driver,DataType, subDataType);
		//选择可视可查按钮
		selectKSKC(driver);
		//检查界面默认显示 依据网元数
		checkDefaultDisplay(driver,neNumber);
		//检查网元日期显示
		checkNeDate(driver,neDate);
		
		//检查时间可选项
		checkNeTimeQuery(driver,QueryTimeList);
		//显示详情
		isDisplayDetail(driver,true);
		checkPageDisplayNeNumber(driver, neNumber);
		if (subDataType.equals("MR数据")||subDataType.equals("话统数据")||subDataType.equals("反向频谱扫描CHR数据")||subDataType.equals("Nodeb CHR数据")||subDataType.equals("PCHR数据")) {
			checkPartData(driver, partNeName);
		}else{
			checkPartDataNoDisplay(driver,subDataType);
		}
		checkNoData(driver,NoDataNeName);
		checkAllData(driver,NeName);
		
	}
	
	/**
	 * @see 可视可查 上传数据首页数据量检查
	 * @param driver
	 * @param PrjName
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param neDate
	 * @param neNumber
	 * @param dataNeNumber
	 * @param partNeName
	 * @param NeName
	 * @param NoDataNeName
	 * @param searchText
	 * @param importPath
	 * @param isNofindNeNum
	 * @param mustfindNeNum
	 */
	
	public static void KSKC_DataCountVertify(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType,String neNumber,String dataNeNumber) {
		LOG.info_testcase("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");
		
		IndexTask.changePrj(driver, PrjName);		
		UpLoad_GuideTask.selectRat(driver,rat);
		if(DataType.equals(DataCountStatistic)){
			checkDataCountStatistic(driver,subDataType,neNumber,dataNeNumber);
			
		}else{
			checkBlockDataCount(driver,subDataType,neNumber,dataNeNumber);
		}
		
	}
	

	/**
	 * @see   可是可查 默认显示验证，针对小数据在20条配置一下的默认显示查看，需要先上传一部分小规格数据
	 * @param driver
	 * @param PrjName
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param neDate
	 * @param neNumber
	 * @param dataNeNumber
	 * @param partNeName
	 * @param NeName
	 * @param NoDataNeName
	 * @param searchText
	 * @param importPath
	 * @param isNofindNeNum
	 * @param mustfindNeNum
	 */
	public static void KSKC_DefaultDisplayCheck(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType,String neDate,int neNumber,String partNeName,String NeName,
			String NoDataNeName,String searchText, String importPath, String isNofindNeNum,String mustfindNeNum) {
		System.out.println("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");
		
		IndexTask.changePrj(driver, PrjName);		
		UpLoad_GuideTask.selectRat(driver,rat);
		UpLoad_GuideTask.selectDataType(driver,DataType, subDataType);
		//选择可视可查按钮
		selectKSKC(driver);
		//检查界面默认显示 依据网元数
		checkDefaultDisplay(driver);		
	}
	
	/**
	 * @see   可是可查 所有网元信息验证
	 * @param driver
	 * @param PrjName
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param neDate
	 * @param neNumber
	 * @param dataNeNumber
	 * @param partNeName
	 * @param NeName
	 * @param NoDataNeName
	 * @param searchText
	 * @param importPath
	 * @param isNofindNeNum
	 * @param mustfindNeNum
	 */
	
	public static void KSKC_AllInforVertify(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType,String neDate,int neNumber,String partNeName,String NeName,
			String NoDataNeName,String searchText, String importPath, String isNofindNeNum,String mustfindNeNum) {
		System.out.println("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");
		
		IndexTask.changePrj(driver, PrjName);		
		UpLoad_GuideTask.selectRat(driver,rat);
		UpLoad_GuideTask.selectDataType(driver,DataType, subDataType);
		//选择可视可查按钮
		selectKSKC(driver);
		//检查界面默认显示 依据网元数
		checkDefaultDisplay(driver,neNumber);
		//检查网元日期显示
		checkNeDate(driver,neDate);
		
		//检查时间可选项
		checkNeTimeQuery(driver,QueryTimeList);
		//显示详情
		isDisplayDetail(driver,true);
		checkPageDisplayNeNumber(driver, neNumber);
		if (subDataType.equals("MR数据")||subDataType.equals("话统数据")||subDataType.equals("反向频谱扫描CHR数据")||subDataType.equals("Nodeb CHR数据")||subDataType.equals("PCHR数据")) {
			checkPartData(driver, partNeName);
		}else{
			checkPartDataNoDisplay(driver,subDataType);
		}
		checkNoData(driver,NoDataNeName);
		checkAllData(driver,NeName);		
	}
	
	/**
	 * @see   可是可查 自定义界面验证
	 * @param driver 
	 * @param PrjName
	 * @param rat
	 * @param DataType
	 * @param subDataType
	 * @param neDate
	 * @param neNumber
	 * @param dataNeNumber
	 * @param partNeName
	 * @param NeName
	 * @param NoDataNeName
	 * @param searchText
	 * @param importPath
	 * @param isNofindNeNum
	 * @param mustfindNeNum
	 */
	
	public static void KSKC_CustomInforVertify(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType,String neDate,int neNumber,String partNeName,String NeName,
			String NoDataNeName,String searchText, String importPath, String isNofindNeNum,String mustfindNeNum) {
		System.out.println("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");
		
		IndexTask.changePrj(driver, PrjName);		
		UpLoad_GuideTask.selectRat(driver,rat);
		UpLoad_GuideTask.selectDataType(driver,DataType, subDataType);
		//选择可视可查按钮
		selectKSKC(driver);
		isDisplayDetail(driver, true);
		// 用户自定义检查
		clickCustomBTN(driver);
		checkDownLoadTemplate(driver);
		searchNeCheck(driver, searchText);
		checkBatchImport(driver, importPath, isNofindNeNum, mustfindNeNum);
	}
	
	//为稳定性模型测试
	public static void KSKC_StabilityTest(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType,String searchText) {
		System.out.println("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");
		
		IndexTask.changePrj(driver, PrjName);		
		UpLoad_GuideTask.selectRat(driver,rat);
		UpLoad_GuideTask.selectDataType(driver,DataType, subDataType);
		//选择可视可查按钮
		selectKSKC(driver);
		isDisplayDetail(driver, true);
		// 用户自定义检查
		clickCustomBTN(driver);
		searchNeCheck(driver, searchText);
		
	}
	
	// 为稳定性模型测试
	public static void KSKC_StabilityTest_filterByDate(WebDriver driver, String PrjName,
			String rat, String DataType, String subDataType, String searchText) {
		System.out.println("制式：" + rat + "--数据类型：" + DataType + "--子数据类型："
				+ subDataType + "-可是可查检查--");

		IndexTask.changePrj(driver, PrjName);
		UpLoad_GuideTask.selectRat(driver, rat);
		UpLoad_GuideTask.selectDataType(driver, DataType, subDataType);
		// 选择可视可查按钮
		selectKSKC(driver);
		isDisplayDetail(driver, true);
		//点击日期
		String js = "$('#date2').next().click()";
		CommonJQ.excuteJS(driver, js);
		Task_KSKC.waitForMask(driver);
		//选择今天
		String js2 = "$('iframe').contents().find('#dpTodayInput').click()";
		CommonJQ.excuteJS(driver, js2);
		Task_KSKC.waitForMask(driver);

	}
	
	private static void checkNeDate(WebDriver driver, String neDate) {
		String realDate= CommonJQ.getValue(driver, KSKC_Page.Date2);
		if(!StringUtils.containsIgnoreCase(neDate, realDate)){
			Assert.fail("界面显示日期与参数化表格中最后的更新日期不相符！！！界面："+realDate+";表格参数日期："+neDate);
		}
		
	}
	
	private static void checkDefaultDisplay(WebDriver driver, int neNumber) {
		if(neNumber<20){
			
			Assert.assertTrue("网元数小于20，默认不显示表格详情", CommonJQ.isVisible(driver, KSKC_Page.DetailInfo));
		}
		if (neNumber > 20) {
			Assert.assertTrue("网元数大于20，默认不显示图表", CommonJQ.isVisible(driver, KSKC_Page.LineChart));
		}
		
	}
	
	private static void checkDefaultDisplay(WebDriver driver) {
		
			
			Assert.assertTrue("网元数小于20，默认显示表格详情,没有显示表格详情", CommonJQ.isVisible(driver, KSKC_Page.DetailInfo));
		
			Assert.assertFalse("网元数小于20，默认应显示表格,实际显示chart信息！！！", CommonJQ.isVisible(driver, KSKC_Page.LineChart));
		
	}

	private static void selectKSKC(WebDriver driver) {
		CommonJQ.excuteJS(driver,"$('.fr.mb10>input').eq(0).click();");
		waitForMask(driver);
		
	}

	private static void checkPartDataNoDisplay(WebDriver driver, String subDataType) {
		if(CommonJQ.isExisted(driver, "#partdataDIV", true)){
			Assert.fail("子数据类型:"+subDataType+"部分数据选项存在");
		}
		
	}

	//点击自定义按钮
	private static void clickCustomBTN(WebDriver driver){
		System.out.println("------------用户自定义检查------");
		CommonJQ.excuteJS(driver,"$('#appointnetid').click();");
		waitForMask(driver);
		long b =CommonJQ.excuteJStoLong(driver,"return $('#netList:visible').length;");
		if(b<1){
			Assert.fail("点击自定义无响应！！无对应的网元选择表单 弹出！！");
		}
	}
	
	//
	private static void searchNeCheck(WebDriver driver,String searchText){
		System.out.println("自定义-网元搜索功能验证！！");
		searchNe(driver, "不存在的网元名称");		
		if(getSearchFoundNeNum(driver)>0){Assert.fail("不存在的网元查询，查询到结果");}
		searchNe(driver,searchText);Pause.pause(1000);Pause.pause(1000);			
		if(getSearchFoundNeNum(driver)==0){Assert.fail("搜索--" + searchText + "--没有搜索到网元：！！");}
		//选择查询到的网元
		addSearchedNeToTable(driver);Pause.pause(1000);Pause.pause(1000);
		if (getFoundTableNeNum(driver) == 0) {
			Assert.fail("--添加网元到  已搜索到的网元 表格失败！！！");
		}
		for (int i = 0; i < getFoundTableNeNum(driver); i++) {
			removeSelectNeByRowNum(driver,i);
		}
		if(getFoundTableNeNum(driver)>0){Assert.fail("移除已经找到的网元失败！！！");}
		
	}
		
	private static void searchNe(WebDriver driver, String searchText) {
		CommonJQ.excuteJS(driver, "$('#searchNet_key').val('"+searchText+"');");
		clickSearchData(driver);
	}
	// 网元备份时间可选项检查
	private static void checkNeTimeQuery(WebDriver driver,String[] optionlist){
		System.out.println("网元备份时间选择项");
		String js = "return $('#timeQuantum').find('option').text().trim();";
		String js2 ="return $('#timeQuantum option[selected=\"selected\"]').text().trim();";
		String selectTimeOption = CommonJQ.excuteJStoString(driver,js);
		String defaultSelect = CommonJQ.excuteJStoString(driver,js2);
		if (optionlist != null) {
			if(!defaultSelect.equals(optionlist[0])){
				Assert.fail("时间默认显示不对！！：默认为："+optionlist[0]+"实际为："+defaultSelect);
			}
			for (int i = 0; i < optionlist.length; i++) {
				Assert.assertTrue("网元备份时间选择无可选项："
				+ optionlist[i], StringUtils.containsIgnoreCase(
								selectTimeOption, optionlist[i]));
			}
		}
	}
	// 检查批量导入
	private static void checkBatchImport(WebDriver driver,String importPath,String isNofindNeNum,String mustfindNeNum){
		bathImportNe(driver,importPath);
		if(getFoundTableNeNum(driver)==0){Assert.fail("搜索到的网元添加失败！！");}
		if(isNofindNeNum!=null){
			if (getNoFoundNeNum(driver) == 0
					|| getNoFoundNeNum(driver) != Integer
							.parseInt(isNofindNeNum)) {
				Assert.fail("根据导入表格中,有"+isNofindNeNum+"个网元为不存在网元，搜索不到 的网元列表中没有这两个网元");
			}
		}
		if(isNofindNeNum!=null){
			checkDownLoadNoFoundNe(driver);
		}
		clickQeuryDataBTN(driver);
		int num = getDetailInfoFindNeNum(driver);
		if(Integer.parseInt(mustfindNeNum)!=num){
			Assert.fail("导入查询网元,查询之后的网元数错误！！预期值："+mustfindNeNum+"实际值"+num);
		}
		checkPageDisplayNeNumber(driver,Integer.parseInt(mustfindNeNum));
	}
	// 
	private static void clickQeuryDataBTN(WebDriver driver) {
		String script = "$('#tdd_CloseButton').click();";
		CommonJQ.excuteJS(driver, script);
		waitForMask(driver);
	}

	// 下载模板检查
	private static void checkDownLoadTemplate(WebDriver driver) {
		System.out.println("自定义下载模板检查");
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		String script = "$('.datatype-text-up').click();";	
		CommonJQ.excuteJS(driver, script);
		waitForMask(driver);
		//String FileName = "";
		//if (Parameter.Language.contains("ZH")) {
		String	FileName = "GENEXCloud数据上传指定网元模板.xlsx";
		//} else {
		//	FileName = "GENEXCloud data upload template of a specified NE.xlsx";
		//}
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, FileName);
		boolean flag = FileCompare.fileCompare(
				SystemConstant.getProjectHomePath()
						+ "\\Data\\KSKC\\template\\GENEXCloud数据上传指定网元模板.xlsx",
						ConstUrl.DownLoadPath + "\\" + FileName);
		if (!flag) {
			Assert.fail("下载模板信息与基线模板信息不一致！！！");

		}
	}
	// 下载没有查询到的网元批量下载
	private static void checkDownLoadNoFoundNe(WebDriver driver) {
			System.out.println("导出没有查询到的网元");
			CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
			String js = "$('.export-txt').find('a').click();";		
			CommonJQ.excuteJS(driver,js);
			Pause.pause(5*1000);
			String FileName = "无法搜索到网元.csv";
			CommonFile.checkExistFiles(ConstUrl.DownLoadPath, FileName);
		}
	
	// 导入查询网元
	private static void bathImportNe(WebDriver driver,String path) {
		//点击批量导入网元
		String jscmd = "$('#importNet').click();";
		CommonJQ.excuteJS(driver,jscmd);
		waitForMask(driver);
		System.out.println("导入文件路径:"+path);
		driver.findElement(By.id("importNetFile")).sendKeys(path);
		waitForMask(driver);
		//	点击导入按钮
		CommonJQ.excuteJS(driver,"$('#sub_updata').click();");	
		waitForMask(driver);
	}

	// 获取查询到的网元数
	private static long getSearchFoundNeNum(WebDriver driver) {
		String jscmd = "return $('#searchList>li:visible').length;";
		return CommonJQ.excuteJStoLong(driver,jscmd);
	}
	// 获取查询到的网元 List 数
	private static long getFoundTableNeNum(WebDriver driver){
		String jscmd = "return $('#foundNetTr').find('tr').length;";
		return CommonJQ.excuteJStoLong(driver,jscmd);
	}

	// 获取没有找到的网元数
	private static int getNoFoundNeNum(WebDriver driver) {
		String jscmd = "return $('#noFoundNetTr').find('tr').length;";
		return CommonJQ.excuteJStoInt(driver,jscmd);
	}
	
	//选择查询到的网元
	private static void addSearchedNeToTable(WebDriver driver){
		String jscmd = "$('#searchList>li:visible').eq(0).click();";
		CommonJQ.excuteJS(driver,jscmd);
		waitForMask(driver);
		
	}
	// 按行移除所选网元
	private static void removeSelectNeByRowNum(WebDriver driver,int rowNum){
		String jscmd = "$('#foundNetTr').find('tr').eq("+rowNum+").find('a').click();";
		CommonJQ.excuteJS(driver,jscmd);
	}
	
	//点击查询数据
	private static void clickSearchData(WebDriver driver){
		//String jscmd = "$('#tdd_CloseButton').click();";
		String jscmd = "$('#searchNet_key').next().click();";
		CommonJQ.excuteJS(driver,jscmd);
		waitForMask(driver);
	}
	//批量导出未查询到的文件
	private static void batchExportxt(WebDriver driver){
		String jscmd = "$('.export-txt>a').click();";
		CommonJQ.excuteJS(driver,jscmd);
		Pause.pause(5*1000);
	}
	
	
	//选择 部分时段
	private static boolean isDisplaytimePartCheck(WebDriver driver){
		String jscmd = "return $('#timePartCheck:visible').length;";
		long b= CommonJQ.excuteJStoLong(driver,jscmd);
		return b>0;
	}
	
	// 设置24小时 ，默认为24小时
	private static void setTimePartOneDay(WebDriver driver){
		String jscmd = "$('#timePartCheck:visible').click();";
		CommonJQ.excuteJS(driver,jscmd);
		Pause.pause(1000);
		CommonJQ.excuteJS(driver,"$('#oneDay').click();");
	}
	
	// 设置筛选的时段
	private static void setTimePartHours(WebDriver driver,String[] hoursList){
		String jscmd = "$('#timePartCheck:visible').click();";
		CommonJQ.excuteJS(driver,jscmd);
		Pause.pause(1000);
		CommonJQ.excuteJS(driver,"$('#TimePart').click();");
		boolean timetotimevisible=CommonJQ.excuteJStoBoolean(driver,"return $('#timeToTime').is(':visible');");
		if(!timetotimevisible){
			Assert.fail("时间段选择不可见！！");
		}
		
		if(hoursList!=null){
			for (int i = 0; i < hoursList.length; i++) {
				String timeindex = String.valueOf(i+1);
				CommonJQ.excuteJS(driver,"$('#texttime"+timeindex+"').val('"+hoursList[i]+"')");
			}
		}
		//点击确定
		CommonJQ.excuteJS(driver,"$('#confirmButton').click();");
		
	}
	
	// 捕获告警信息
	public static void tipMessage(WebDriver driver){
		String jscmd="return $('.panel.window.messager-window').length;";
		long b= CommonJQ.excuteJStoLong(driver,jscmd);
		if(b>0){
			String message=CommonJQ.excuteJStoString(driver,"return $('.panel.window.messager-window').find('.messageContentDiv').text();");
			Assert.fail(message);
		}
	}
	
	// 获取告警信息
	private static String getWarningMessage(WebDriver driver){
		return CommonJQ.excuteJStoString(driver,"return $('#warning').text();");
	}
	
	// 等待页面信息加载  ，公共方法
	public static void waitForMask(WebDriver driver) {
		final int  minute= 2;
		final int timeout = minute*60 * 1000;
		int times = 0;
		boolean iswaitMask=CommonJQ.excuteJStoBoolean(driver,"return $('#my_mask_layer').is(':visible');");
		while (iswaitMask) {			
			Pause.pause(100);
			times++;
			Assert.assertTrue("页面等待超时"+minute+"分钟",times < timeout / 100);
			iswaitMask=CommonJQ.excuteJStoBoolean(driver,"return $('#my_mask_layer').is(':visible');");
		}
	}
	
	// 是否显示详情。true 显示 
	private static void isDisplayDetail(WebDriver driver,boolean isDisplay){
		
		String text= CommonJQ.excuteJStoString(driver,"return $('#changeTitle span').text().trim();");
		if(StringUtils.equalsIgnoreCase(text, "显示详细信息")&&isDisplay){
			CommonJQ.excuteJS(driver,"$('#changeTitle').click();");
			waitForMask(driver);
		}
	}
	//获取相信信息 获取到的网元数
	private static int getDetailInfoFindNeNum(WebDriver driver){
		String script= "return $('#NetWorkElement').find('div.checkRow').length;";
		return CommonJQ.excuteJStoInt(driver,script);
	}
	
	//显示全部数据网元的格子数
	private static boolean getAllDataDivNum(WebDriver driver,String NeName){
		int index = CommonJQ.excuteJStoInt(driver,"return $('#NetWorkElement > #"+NeName+"').index();");
		System.out.println("index:"+index);
		if(index<0){
			Assert.fail("全部网元检查没有找到网元："+NeName+"----!!!!!!!!");
			
		}
		for (int i = index; i < index+31; i++) {
			String CellClass = CommonJQ.excuteJStoString(driver,"return $('#NetWorkElement > div').eq("+String.valueOf(i)+").attr('class');");
			if(CellClass.equals(GreeeClassTex)){
				return true;
			}
			
		}
		System.out.println("Over!!!!!!!!!!!!!");
		return false;
	}
	
	//  显示部分数据网元的格子数
	private static boolean getPartDataDivNum(WebDriver driver,String partNeName){
		int index = CommonJQ.excuteJStoInt(driver,"return $('#NetWorkElement > #"+partNeName+"').index();");
		if(partNeName!=null){			
			
			if(index<0){
				Assert.fail("部分网元检查没有找到网元："+partNeName+"----!!!!!!!!");
				
			}
			for (int i = index; i < index+31; i++) {
				String CellClass = CommonJQ.excuteJStoString(driver,"return $('#NetWorkElement > div').eq("+String.valueOf(i)+").attr('class');");
				if(CellClass.equals(YellowClassTex)){
					return true;
				}
				
			}
			return false;
		}else{
			if(index>0){
				Assert.fail("配置文件中不存在部分数据网元,但是界面中存在部分数据网元，需确认正确性!!!!!!!!");
				
			}
		}
		
		return true;
	}
	
	// 检查 NoData 表格显示
	private static void checkNoData(WebDriver driver,String NoDataNeName) {
		selectNoData(driver);
		if (NoDataNeName!=null) {
			//是否存在部分数据判断
			int index = CommonJQ.excuteJStoInt(driver,"return $('"+GreeeClass+"').length;");
			if (index>0) {
				Assert.fail("无数据表格渲染颜色不正确，存在黄色单元格！！！");
			}
			int index2 = CommonJQ.excuteJStoInt(driver,"return $('"+YellowClass+"').length;");
			if (index2>0) {
				Assert.fail("无数据表格渲染颜色不正确，存在绿色单元格！！！");
			}
		}else{
			if(getDetailInfoFindNeNum(driver)>0){
				Assert.fail("配置文件中不存在无数据据网元,但是界面中存在无数据数据网元，需确认正确性!!!!!!!!");
			}
		}
		
		CommonJQ.excuteJS(driver,"$('#nonedata').click();");
		waitForMask(driver);
	}
	// 检查 PartData 表格显示	
	private static void checkPartData(WebDriver driver,String NeName) {
		selectPartData(driver);
		//是否存在部分数据判断
		if(getPartDataDivNum(driver,NeName)){
			Assert.fail("选择部分数据,网元["+NeName+"]，不存在黄色单元格！！！");
		}
		CommonJQ.excuteJS(driver,"$('#partdata').click();");
		waitForMask(driver);
	}
	// 检查 AllData 表格显示	
	private static void checkAllData(WebDriver driver,String NeName) {
		selectAllData(driver);
		//是否存在部分数据判断
		if(getAllDataDivNum(driver,NeName)){
			Assert.fail("选择部分数据,网元["+NeName+"]，不存在黄色单元格！！！");
		}
		CommonJQ.excuteJS(driver,"$('#alldata').click();");
		waitForMask(driver);
	}
	
	// 选择对应复选框
	private static void selectAllData(WebDriver driver) {
		
		if (!isCheckAllData(driver)) {
			CommonJQ.excuteJS(driver,"$('#alldata').click();");
			waitForMask(driver);
		}
		if (isCheckPartData(driver)) {
			CommonJQ.excuteJS(driver,"$('#partdata').click();");
			waitForMask(driver);
		}
		if (isCheckNoData(driver)) {
			CommonJQ.excuteJS(driver,"$('#nonedata').click();");
			waitForMask(driver);
		}
	}
	private static void selectNoData(WebDriver driver) {
		
		if (isCheckAllData(driver)) {
			CommonJQ.excuteJS(driver,"$('#alldata').click();");
			waitForMask(driver);
		}
		if (isCheckPartData(driver)) {
			CommonJQ.excuteJS(driver,"$('#partdata').click();");
			waitForMask(driver);
		}
		if (!isCheckNoData(driver)) {
			CommonJQ.excuteJS(driver,"$('#nonedata').click();");
			waitForMask(driver);
		}
	}
	private static void selectPartData(WebDriver driver) {
		
		if (isCheckAllData(driver)) {
			CommonJQ.excuteJS(driver,"$('#alldata').click();");
			waitForMask(driver);
		}
		if (!isCheckPartData(driver)) {
			CommonJQ.excuteJS(driver,"$('#partdata').click();");
			waitForMask(driver);
		}
		if (isCheckNoData(driver)) {
			CommonJQ.excuteJS(driver,"$('#nonedata').click();");
			waitForMask(driver);
		}
	}
	
	// 对应复选框是否被选择
	private static boolean isCheckPartData(WebDriver driver) {
		return CommonJQ.excuteJStoBoolean(driver,"return $('#partdata').is(':checked');");
	}
	private static boolean isCheckNoData(WebDriver driver) {
		return CommonJQ.excuteJStoBoolean(driver,"return $('#nonedata').is(':checked');");
	}
	private static boolean isCheckAllData(WebDriver driver) {
		return CommonJQ.excuteJStoBoolean(driver,"return $('#alldata').is(':checked');");
	}
	
	// 检查页面下汇总网元数 与实际结果是否一致
	private static void checkPageDisplayNeNumber(WebDriver driver,int expectNumber){
		String script ="return $('#page_data_2').find('em').text().trim()";
		int realNeNumber = CommonJQ.excuteJStoInt(driver, script);
		if(realNeNumber!=expectNumber){
			Assert.fail("页面底部显示网元总数与实际不想符合！！预期值："+expectNumber+"实际值："+realNeNumber);
		}
		
	}

	// 检查单个数据统计信息
	private static void checkBlockDataCount(WebDriver driver,
			String subDataType, String neNumber, String dataNeNumber) {
		String isExsistDataTypeBlock = "return $('.block_part').find('span:contains("+subDataType+")').filter(':visible').length;";
		if(CommonJQ.excuteJStoInt(driver, isExsistDataTypeBlock)==0){
			Assert.fail("界面显示无子数据块："+subDataType);
			return;
		}
		String moveToSubBlock ="$('.block_part').find('span:contains("+subDataType+")').mouseover();";
		String moveOutSubBlock ="$('.block_part').find('span:contains("+subDataType+")').mouseout();";
		CommonJQ.excuteJS(driver, moveToSubBlock);
		String getcfgNeNumber ="return $('.block_part').find('span:contains("+subDataType+")').next().find('div[class=\"width100 mt25\"]').find('div[class=\"tr\"]').text().trim();";
		String getDataNeNumber ="return $('.block_part').find('span:contains("+subDataType+")').next().find('div[class=\"width100 mt-57\"]').find('div[class=\"tr mb5\"]').text().trim();";
		String realCfgNeNumber = CommonJQ.excuteJStoString(driver, getcfgNeNumber);
		String realDataNeNumber = CommonJQ.excuteJStoString(driver, getDataNeNumber);
		Assert.assertTrue(subDataType+"网元数显示有误！！！真实值："+realCfgNeNumber+"期望值："+Integer.getInteger(neNumber), Integer.getInteger(realCfgNeNumber).equals(Integer.getInteger(neNumber)));
		Assert.assertTrue(subDataType+"数据的网元数显示有误！！！真实值："+realDataNeNumber+"期望值："+Integer.getInteger(dataNeNumber), Integer.getInteger(realDataNeNumber).equals(Integer.getInteger(dataNeNumber)));
		
		CommonJQ.excuteJS(driver, moveOutSubBlock);
		
	}

	//检查数据量统计信息
	private static void checkDataCountStatistic(WebDriver driver,
			String dataCount, String dataSpan, String neNumber) {
		
		String getdataCount ="return $('span:contains(数据量)').parent().next().children().text().trim();";
		String getDateSpan ="return $('span:contains(时间跨度)').parent().next().children().text().trim();";
		String getNeNumber ="return $('span:contains(网元数)').parent().next().children().text().trim();";
		String realDataCount=CommonJQ.excuteJStoString(driver, getdataCount);
		String realDateSpan=CommonJQ.excuteJStoString(driver, getDateSpan);
		String realNeNumber=CommonJQ.excuteJStoString(driver, getNeNumber);
		Assert.assertTrue("数据量统计有误！！！真实值："+realDataCount+";期望值："+dataCount,realDataCount.equals(dataCount));
		Assert.assertTrue("时间跨度统计有误！！！真实值："+realDateSpan+";期望值："+dataSpan, realDataCount.equals(dataCount));
		Assert.assertTrue("网元数统计有误！！！真实值："+realNeNumber+";期望值："+Integer.getInteger(neNumber), Integer.getInteger(realNeNumber).equals(Integer.getInteger(neNumber)));
		
	}

	
	
	
	
	
}
