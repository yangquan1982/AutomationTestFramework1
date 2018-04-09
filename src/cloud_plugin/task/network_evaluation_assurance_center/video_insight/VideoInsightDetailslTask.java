package cloud_plugin.task.network_evaluation_assurance_center.video_insight;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_evaluation_assurance_center.video_insight.VideoInsightDetailslPage;
import cloud_plugin.page.network_evaluation_assurance_center.video_insight.VideoInsightPage;
import cloud_plugin.task.network_evaluation_assurance_center.NetworkSafeGuardCenterTask;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.util.LanguageUtil;
import common.util.SwitchDriver;

public class VideoInsightDetailslTask {

	public static void checkDetails(WebDriver driver, String defaultWindowID, String taskname, String SEQAllNet,
			String OTTSheet, String ISPSheet, String SEQCell, String SEQGrid, String Cfg, String Epara, String Sig,
			String NoEncryVideo, String EncryVideo, String eMap, String OTT, String NoToLow, String LVToH,
			String LVUser, String TopTNum) {
		// 打开
		NetworkSafeGuardCenterTask.openVideoInsight(driver);

		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		VideoInsightDetailslTask.checkAysNetData(driver, SEQAllNet, OTTSheet, ISPSheet, SEQCell, SEQGrid, Cfg, Epara,
				Sig, NoEncryVideo, EncryVideo, eMap, OTT);
		VideoInsightDetailslTask.checkPara(driver, NoToLow, LVToH, LVUser, TopTNum);

		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param SEQAllNet
	 *            :SEQ整网级指标
	 * @param OTTSheet
	 *            :OTT协议映射表
	 * @param ISPSheet
	 *            :ISP流向映射表
	 * @param SEQCellFile
	 *            :SEQ小区级指标
	 * @param SEQGridFile
	 *            :SEQ栅格级指标
	 * @param CfgFile
	 *            :配置数据
	 * @param paraFile
	 *            :工参数据
	 * @param SigFile
	 *            :SIG数据
	 * @param NoEncryVideoFile
	 *            :非加密视频源
	 * @param EncryVideoFile
	 *            :加密视频源
	 * @param eMapFile
	 *            :电子地图
	 * @param OTTFile
	 *            :OTT
	 */
	public static void checkAysNetData(WebDriver driver, String SEQAllNet, String OTTSheet, String ISPSheet,
			String SEQCell, String SEQGrid, String Cfg, String Epara, String Sig, String NoEncryVideo,
			String EncryVideo, String eMap, String OTT) {

		// 进入数据选择
		VideoInsightDetailslPage.DataClick(driver);
		// SEQ整网级指标
		if (SEQAllNet != null) {
			String ActVal = VideoInsightDetailslPage.SeqAllNetVal(driver);
			Assert.assertTrue("SEQ整网级指标，实际值：" + ActVal + "预期值：" + SEQAllNet, SEQAllNet.equals(ActVal));
		}
		// OTT协议映射表
		if (OTTSheet != null) {
			String ActVal = VideoInsightDetailslPage.OTTSheetVal(driver);
			Assert.assertTrue("OTT协议映射表，实际值：" + ActVal + "预期值：" + OTTSheet, OTTSheet.equals(ActVal));
		}
		// ISP流向映射表
		if (ISPSheet != null) {
			String ActVal = VideoInsightDetailslPage.ISPSheetVal(driver);
			Assert.assertTrue("ISP流向映射表，实际值：" + ActVal + "预期值：" + ISPSheet, ISPSheet.equals(ActVal));
		}

		// SEQ小区级指标
		if (SEQCell != null) {
			String ActVal = VideoInsightDetailslPage.SeqCellVal(driver);
			Assert.assertTrue("SEQ小区级指标，实际值：" + ActVal + "预期值：" + SEQCell, SEQCell.equals(ActVal));
		}
		// SEQ栅格级指标
		if (SEQGrid != null) {
			String ActVal = VideoInsightDetailslPage.SeqGridVal(driver);
			Assert.assertTrue("SEQ栅格级指标，实际值：" + ActVal + "预期值：" + SEQGrid, SEQGrid.equals(ActVal));
		}
		// 配置数据
		if (Cfg != null) {
			String ActVal = VideoInsightDetailslPage.CfgVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + Cfg + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("配置数据，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
		// 工参数据
		if (Epara != null) {
			String ActVal = VideoInsightDetailslPage.EparaVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + Epara + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("工参数据，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
		// SIG数据
		if (Sig != null) {
			String ActVal = VideoInsightDetailslPage.SigVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + Sig + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("SIG数据，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
		// 非加密视频源
		if (NoEncryVideo != null) {
			String ActVal = VideoInsightDetailslPage.NoEncryVideoVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + NoEncryVideo + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("非加密视频源，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
		// 加密视频源
		if (EncryVideo != null) {
			String ActVal = VideoInsightDetailslPage.EncryVideoVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + EncryVideo + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("非加密视频源，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
		// 电子地图
		if (eMap != null) {
			String ActVal = VideoInsightDetailslPage.EMapVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + eMap + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("电子地图，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
		// OTT
		if (OTT != null) {
			String ActVal = VideoInsightDetailslPage.OTTVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + " " + OTT + " "
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("OTT，实际值：" + ActVal + "预期值：" + Evalue, Evalue.equals(ActVal));
		}
	}

	/**
	 * 
	 * @param driver
	 * @param NoToLow
	 *            :非视频用户向低流量视频用户迁移比例
	 * @param LVToH
	 *            :低视频用户向高流量视频用户迁移比例
	 * @param LVUser
	 *            :低流量视频用户门限
	 * @param TopTNum
	 *            :TOP终端个数
	 */
	public static void checkPara(WebDriver driver, String NoToLow, String LVToH, String LVUser, String TopTNum) {

		// 进入参数设置
		VideoInsightDetailslPage.ParaClick(driver);
		// 非视频用户向低流量视频用户迁移比例（%）
		if (NoToLow != null) {
			String ActVal = VideoInsightDetailslPage.NoToLowVal(driver);
			Assert.assertTrue("非视频用户向低流量视频用户迁移比例，实际值：" + ActVal + "预期值：" + NoToLow, NoToLow.equals(ActVal));
		}
		// 低视频用户向高流量视频用户迁移比例（%）
		if (LVToH != null) {
			String ActVal = VideoInsightDetailslPage.LVToHVal(driver);
			Assert.assertTrue("低视频用户向高流量视频用户迁移比例，实际值：" + ActVal + "预期值：" + LVToH, LVToH.equals(ActVal));
		}
		// 低流量视频用户门限（MB）
		if (LVUser != null) {
			String ActVal = VideoInsightDetailslPage.LVUserVal(driver);
			Assert.assertTrue("低流量视频用户门限，实际值：" + ActVal + "预期值：" + LVUser, LVUser.equals(ActVal));
		}
		// TOP终端个数
		if (TopTNum != null) {
			String ActVal = VideoInsightDetailslPage.TopTNumVal(driver);
			Assert.assertTrue("TOP终端个数，实际值：" + ActVal + "预期值：" + TopTNum, TopTNum.equals(ActVal));
		}

	}

	/**
	 * 整网洞察->报告详情入口结果核查
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 * @param TrafficInsight
	 * @param VideoSource
	 * @param ValueUser
	 * @param ValueTerminal
	 * @param TrafficModel
	 * @param E2EDelay
	 */
	public static void checkResults(WebDriver driver, String defaultWindowID, String taskname,
			String TrafficInsightLeft, String TrafficInsightRight, String VideoSource, String ValueUser,
			String ValueTerminal, String TrafficModelTrend, String TrafficModel3G4GStat, String TrafficModelVideoStat,
			String TrafficModelCDNStat, String E2EDelay) {
		// 打开
		NetworkSafeGuardCenterTask.openVideoInsight(driver);
		TaskReportTask.asserTaskEndState(driver, taskname);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 整网洞察->报告详情入口
		String nowWinID2 = VideoInsightPage.WholeNetWorkDetailsClick(driver);
		SwitchDriver.switchDriverToSEQ(driver);
		VideoInsightPage.checkTrafficInsight_Left(driver, TrafficInsightLeft);
		VideoInsightPage.checkTrafficInsight_Right(driver, TrafficInsightRight);
		VideoInsightPage.checkVideoSource(driver, VideoSource);
		VideoInsightPage.checkValueUser(driver, ValueUser);
		VideoInsightPage.checkValueTerminal(driver, ValueTerminal);
		VideoInsightPage.checkTrafficModel_Trend(driver, TrafficModelTrend);
		VideoInsightPage.checkTrafficModel_3G4GStat(driver, TrafficModel3G4GStat);
		VideoInsightPage.checkTrafficModel_VideoStat(driver, TrafficModelVideoStat);
		VideoInsightPage.checkTrafficModel_CDNStat(driver, TrafficModelCDNStat);
		VideoInsightPage.checkE2EDelay(driver, E2EDelay);
		SwitchDriver.winIDClose(driver, nowWinID2);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
