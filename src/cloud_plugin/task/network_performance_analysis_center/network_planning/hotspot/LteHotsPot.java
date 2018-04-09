package cloud_plugin.task.network_performance_analysis_center.network_planning.hotspot;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.topn_cell_processing.TopnCellProcessingPage;
import cloud_plugin.page.network_performance_analysis_center.network_planning.hotspot.LteHotSpotPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.LOG;
import common.util.SwitchDriver;

public class LteHotsPot {

	public static String creatLteHotspotTask(WebDriver driver,
			String defaultWindowID, String taskName, String[] cfgfile,
			String[] Parafile, String[] Mrfile, String[] Pfmfile,
			String[] FeatureFile, String NeGroupData, String ZhishiChoose,
			String DingweiType, String ShangeJingdu, String IsOutDoor,
			String ShanGeSvPingguType, String RuoFugaiDoor,
			String UserAddYinzi, String UserAddYinziFile,
			String RouFuGaiShibieStyle, String ShanGeDabiaoDoor,
			String DianPingDoor, String CQIDoor,String DanUserBaoZhang, String DanUserBaoZhangFile,
			String MRclean, String[] HighSet) {
		// 打开TOPN
		NetworkAnalysisCenterTask.openLTEHotSpot(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 输入任务名称
		String Name = LteHotSpotPage.setTaskName(driver, taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		// 数据选择
		ChooseData(driver, defaultWindowID, cfgfile, Parafile, Mrfile, Pfmfile,
				FeatureFile, NeGroupData);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		ParameterSet(driver, ZhishiChoose, DingweiType, ShangeJingdu,
				IsOutDoor, ShanGeSvPingguType, RuoFugaiDoor, UserAddYinzi,
				UserAddYinziFile, RouFuGaiShibieStyle, ShanGeDabiaoDoor,
				DianPingDoor, CQIDoor,  DanUserBaoZhang,DanUserBaoZhangFile, MRclean, HighSet);
		SwitchDriver.switchDriverToSEQ(driver);
		TopnCellProcessingPage.commitBtnClick(driver);
		return Name;
	}

	private static void ParameterSet(WebDriver driver, String ZhishiChoose,
			String DingweiType, String ShangeJingdu, String IsOutDoor,
			String ShanGeSvPingguType, String RuoFugaiDoor,
			String UserAddYinzi, String UserAddYinziFile,
			String RouFuGaiShibieStyle, String ShanGeDabiaoDoor,
			String DianPingDoor, String CQIDoor,String DanUserBaoZhang, String DanUserBaoZhangFile,
			String MRclean, String[] HighSet) {
		if (ZhishiChoose != null) {
			LOG.info_aw("修改 制式" );
			CommonJQ.click(driver, LteHotSpotPage.TddSingleChooseBtn, true);
		}
		if (DingweiType != null) {
			LOG.info_aw("修改 定位方式" );
			if (DingweiType.equalsIgnoreCase("特征库定位")) {
				CommonJQ.click(driver, LteHotSpotPage.PosiObjLi_PATH, true);
				CommonJQ.click(driver, LteHotSpotPage.FeaturePosit, true);
			}
			if (DingweiType.equalsIgnoreCase("快速定位")) {
				CommonJQ.click(driver, LteHotSpotPage.PosiObjLi_PATH, true);
				CommonJQ.click(driver, LteHotSpotPage.FastPosit, true);
			}
		}
		if (ShangeJingdu != null) {
			LOG.info_aw("修改  栅格精度" );
			CommonJQ.click(driver, LteHotSpotPage.ShanGe_PATH, true);
			CommonJQ.click(driver, LteHotSpotPage.ShanGe20, true);

		}
		if (IsOutDoor != null) {
			LOG.info_aw("设置  是否区分室内外" );
			CommonJQ.click(driver, LteHotSpotPage.QufenOutIn, false);
			CommonJQ.click(driver, LteHotSpotPage.IsOutDoor, true);
		}
		if (ShanGeSvPingguType != null) {
			LOG.info_aw("修改 栅格速率评估方式" );
			CommonJQ.click(driver, LteHotSpotPage.ShangeEvaluation, true);
			CommonJQ.click(driver, LteHotSpotPage.DxsmbsvEvaluation, true);
		}
		if (RuoFugaiDoor != null) {
			LOG.info_aw("修改 RSRP弱覆盖门限：" + RuoFugaiDoor);
			CommonJQ.value(driver, LteHotSpotPage.RsrpDoor, RuoFugaiDoor, true);
		}
		if (UserAddYinzi != null) {
			LOG.info_aw("修改 用户增长因子：" + UserAddYinzi);
			CommonJQ.value(driver, LteHotSpotPage.UserAddyinzi, UserAddYinzi, true);
		}
		if (UserAddYinziFile != null) {
			LOG.info_aw("选择 用户增长因子文件" );
			CommonJQ.click(driver, LteHotSpotPage.VillageFile, true);
			CommonFile.ChooseOneFile(UserAddYinziFile);
		}
		if (RouFuGaiShibieStyle != null) {
			LOG.info_aw("修改 弱覆盖识别方式" );
			CommonJQ.click(driver, LteHotSpotPage.ShibieStye, true);
			CommonJQ.click(driver, LteHotSpotPage.ShibieStyeRfg, true);
		}
		if (ShanGeDabiaoDoor != null) {
			LOG.info_aw("修改 弱覆盖栅格达标门限："+ShanGeDabiaoDoor);
			CommonJQ.value(driver, LteHotSpotPage.RfgDaBiaoDoor, ShanGeDabiaoDoor, true);
		}
		if (DianPingDoor != null) {
			LOG.info_aw("修改 电平达标门限："+DianPingDoor);
			CommonJQ.value(driver, LteHotSpotPage.DianPingDoor, DianPingDoor, true);
		}
		if (CQIDoor != null) {
			LOG.info_aw("修改 CQI门限："+CQIDoor);
			CommonJQ.value(driver, LteHotSpotPage.CQIDoor, CQIDoor, true);
		}
		if (DanUserBaoZhang != null) {
			LOG.info_aw("修改 单用户保障速率："+DanUserBaoZhang);
			CommonJQ.value(driver, LteHotSpotPage.Dyhbz, DanUserBaoZhang, true);
		}
		if (DanUserBaoZhangFile != null) {
			LOG.info_aw("选择 单用户报告增长因子文件" );
			CommonJQ.click(driver, LteHotSpotPage.SingleUserFile, true);
			CommonFile.ChooseOneFile(DanUserBaoZhangFile);
		}
		if (MRclean != null) {
			LOG.info_aw("修改  是否进行MR清洗" );
			CommonJQ.click(driver, LteHotSpotPage.MrClear, false);
			CommonJQ.click(driver, LteHotSpotPage.NoMrClear, true);
		}
		if (HighSet != null ) {
				for (int i = 0; i < HighSet.length; i++) {
					if (!HighSet[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改高级设置第"+i+"个参数为" + HighSet[i]);
						CommonJQ.value(driver,
								LteHotSpotPage.GaoJiCanShu[i],
								HighSet[i], true);
					}
					else
					{
						continue;					
					}
				}
				
			}	
	}

	/**
	 * 数据选择
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgfile
	 * @param Pfmfile
	 * @param Parafile
	 * @param Warnfile
	 * @param operatefile
	 * @param OutChrfile
	 * @param InChrfile
	 */
	private static void ChooseData(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] Parafile, String[] Mrfile,
			String[] Pfmfile, String[] FeatureFile, String NeGroupData) {
		if (cfgfile != null && cfgfile.length != 0) {
			LOG.info_aw("选择配置数据：" + cfgfile);
			GetDataByTypeTask.chooseFolder(driver, cfgfile,
					LteHotSpotPage.CfgBtn, defaultWindowID);

		}
		if (Parafile != null && Parafile.length != 0) {
			LOG.info_aw("选择工参数据：" + Parafile);
			GetDataByTypeTask.chooseFolder(driver, Parafile,
					LteHotSpotPage.EparaBtn, defaultWindowID);
		}

		if (Mrfile != null && Mrfile.length != 0) {
			LOG.info_aw("选择Mr数据：" + Mrfile);
			GetDataByTypeTask.chooseFolder(driver, Mrfile,
					LteHotSpotPage.MrBtn, defaultWindowID);
		}
		if (Pfmfile != null && Pfmfile.length != 0) {
			LOG.info_aw("选择话统数据：" + Pfmfile);
			GetDataByTypeTask.chooseFolder(driver, Pfmfile,
					LteHotSpotPage.PfmBtn, defaultWindowID);
		}

		if (FeatureFile != null && FeatureFile.length != 0) {
			LOG.info_aw("选择特征库数据：" + FeatureFile);
			GetDataByTypeTask.chooseFolder(driver, FeatureFile,
					LteHotSpotPage.FeatureBtn, defaultWindowID);
		}

		if (NeGroupData != null) {
			LOG.info_aw("选择网元组" + NeGroupData);	
			CommonJQ.excuteJS(driver, "$('iframe[name=\"main\"]').contents().find('#neGroupData').click()");
			CommonFile.ChooseOneFile(NeGroupData);
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteHotSpotPage.ChooseNeGroupBtnOk, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, LteHotSpotPage.NextStep_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

}
