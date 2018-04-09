package cloud_plugin.page.network_performance_analysis_center.basic_optimization.pci_optimization;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class PCIOptimizationPage {

	private static final String TextTaskName = "#taskName";//Task Name

	public static final String RBoxTaskPCI = "#pciOptimizeRadio";
	

	public static final String RBoxAnayWholeNet = "#wholeNetRadio";
	public static final String RBoxAnayPartial = "#partialRadio";
	
	public static final String RBoxPCIconflict = "#conflictRadio";
	public static final String RBoxMod3 = "#mod3Radio";
	
	public static final String TextDenseAreaDistance = "#denseAreaDistance";
	public static final String TextDenseAreaRange = "#denseAreaRange";
	
	public static final String TextAreaDistance = "#areaDistance";
	public static final String TextAreaRange = "#areaRange";
	
	public static final String TextSuburbsDistance = "#suburbsDistance";
	public static final String TextSuburbsRange = "#suburbsRange";
	
	public static final String TextCountrysideDistance = "#countrysideDistance";
	public static final String TextCountrysideRange = "#countrysideRange";
	
	public static final String TextBorderDistance = "#borderDistance";
	public static final String TextBorderRange = "#borderRange";
	
	public static final String TextRoomDivisionDistance = "#roomDivisionDistance";
	public static final String TextRoomDivisionRange = "#roomDivisionRange";
	
	public static final String TextOtherDistance = "#otherDistance";
	public static final String TextOtherRange = "#otherRange";
	
	public static final String TextSelfDefOneDistance = "#selfDefOneDistance";
	public static final String TextSelfDefOneRange = "#selfDefOneRange";
	
	public static final String TextSelfDefTwoDistance = "#selfDefTwoDistance";
	public static final String TextSelfDefTwoRange = "#selfDefTwoRange";
		
	public static final String BtnParaSet = "#parameter_setting";
	public static final String BtnParaOK = "#ok_Button";
	public static final String BtnParaRecover = "#recover_Task";
	public static final String BtnParaCancel = "#cancel_Button";
	
	public static final String TextAnnealNum = "#annealNum";
	public static final String TextInnerLoopNum = "#innerLoopNum";
	
	public static final String RBoxExportTypeAll = "#exportTypeAll";//
	public static final String RBoxExportTypeTopn = "#exportTypeTopn";
	
	public static final String TextMatrixTopN = "#matrixTopN";
	
	public static final String CBoxBaseOnNCLP = "#baseOnNCLPlanning";
	public static final String CBoxSingle2Double = "#single2DoubleKey";	
	public static final String CBoxMod30 = "#pciModThirtyOptimization";
	public static final String CBoxNotConsider = "#notConsiderConflictAndConfusion";
	
	public static final String CBoxBaseDT = "#dtIntfMatrixQuotaCheck";	
	public static final String CBoxBaseSig = "#sigIntfMatrixQuotaCheck";
	public static final String CBoxBasePrf = "#perfIntfMatrixQuotaCheck";
	
	public static final String TextBaseDT = "#dtIntfMatrixQuota";
	public static final String TextBaseSig = "#sigIntfMatrixQuota";
	public static final String TextBasePrf = "#perfIntfMatrixQuota";
	
	
	
	public static final String BtnCfg = "select_confFilePath";
	public static final String BtnPP = "select_parasFilePath";
	public static final String BtnMatrix = "select_matrixFilePath";
	public static final String BtnDTRF = "select_dtFilePath";
	public static final String BtnDTOrigin = "dtOriginServer";
	public static final String BtnDTCSV = "csvU2000Server";
	public static final String BtnSig = "select_sigFilePath";
	
	public static final String BtnPrfOrigin = "OriginServer";
	public static final String BtnPrfU2000 = "U2000Server";
	public static final String BtnPrfPRS = "PRSServer";
	
	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";
	
	public static final String RBoxTaskRF = "#RFRadio";
	
	public static final String CBoxOverburden = "#overburdenTopics";
	public static final String CBoxAntennaRever = "#antennaReversedTopics";
	public static final String CBoxAbnormalCell = "#abnormalCellLatitudeAndLongitudeTopics";
	public static final String CBoxAntennaAzimuth = "#antennaAzimuthAbnormalTopics";
	
	public static final String BtnCommit = "#submitTask";
	private static final String BtnCancel = "#cancelTask";
	
	
	public static String setTaskName(WebDriver driver,String taskName) {
		taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}
	
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCommit, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancel, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
