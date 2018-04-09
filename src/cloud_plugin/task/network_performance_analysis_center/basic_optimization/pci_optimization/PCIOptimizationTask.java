package cloud_plugin.task.network_performance_analysis_center.basic_optimization.pci_optimization;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.pci_optimization.PCIOptimizationPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class PCIOptimizationTask {

	/**
	 * 基于PCI冲突混淆的优化,的任务创建
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 *            :任务名
	 * @param AnyModel
	 *            :分析模式
	 * @param CfgFile
	 *            :分析数据->配置数据
	 * @param PPFile
	 *            :分析数据->工程参数
	 * @param CellFile
	 *            :分析数据->待优化小区
	 * @param Distance
	 *            :PCI设置->复用距离(m)
	 * @param Range
	 *            :PCI设置->PCI可用范围设置
	 * @param NCLFlage
	 *            :参数设置项->基于NCL限定PCI资源
	 * @param Single2DoubleFlage
	 *            :参数设置项->单向邻区置双向邻区
	 * @return:任务名
	 */
	public static String creatPCIconflictTask(WebDriver driver, String defaultWindowID, String taskName,
			String AnyModel, String[] CfgFile, String[] PPFile, String CellFile, String[] Distance, String[] Range,
			boolean NCLFlage, boolean Single2DoubleFlage) {

		taskName = PCIOptimizationTask.setServiceModule(driver, taskName, "PCI优化", AnyModel, "基于PCI冲突混淆的优化");
		PCIOptimizationTask.setPCIPara(driver, Distance, Range);
		PCIOptimizationTask.setPCIConflictPara(driver, NCLFlage, Single2DoubleFlage);
		if ("全网优化".equalsIgnoreCase(AnyModel.trim())) {
			PCIOptimizationTask.setNetData(driver, defaultWindowID, CfgFile, PPFile, null, null, null, null, null, null,
					null, null, null);
		} else if ("局部优化".equalsIgnoreCase(AnyModel.trim())) {
			PCIOptimizationTask.setNetData(driver, defaultWindowID, CfgFile, PPFile, null, null, null, null, null, null,
					null, null, CellFile);
		} else {
			Assert.fail("分析模式错误,正确的应该是" + "\"" + "全网优化" + "\"" + "或者" + "\"" + "局部优化" + "\"" + "，AnyModel：" + AnyModel);
		}

		PCIOptimizationPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 基于PCI MOD3的优化,的任务创建
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 *            :任务名
	 * @param AnyModel
	 *            :分析模式
	 * @param CfgFile
	 *            :分析数据->配置数据
	 * @param PPFile
	 *            :分析数据->工程参数
	 * @param MatrixFile
	 *            :分析数据->干扰矩阵
	 * @param DTOriginFile
	 *            :分析数据->路测数据
	 * @param DTCSVFile
	 *            :分析数据->扫频数据
	 * @param SigFile
	 *            :分析数据->SIG数据
	 * @param PrfOriginFile
	 *            :分析数据->话统数据
	 * @param PrfU2000File
	 *            :分析数据->U2000话统
	 * @param PrfPRSFile
	 *            :分析数据->PRS话统
	 * @param CellFile
	 *            :分析数据->待优化小区
	 * @param Distance
	 *            :PCI设置->复用距离(m)
	 * @param Range
	 *            :PCI设置->PCI可用范围设置
	 * @param AnnealNum
	 *            :参数设置项->PCI优化循环次数
	 * @param InnerLoopNum
	 *            :参数设置项->PCI优化系数
	 * @param TopNFlage
	 *            :参数设置项->是否TOPN
	 * @param TopN
	 *            :参数设置项->TOPN值
	 * @param NCLFlage
	 *            :参数设置项->是否基于NCL限定PCI资源
	 * @param MOD30Flage
	 *            :参数设置项->是否PCI MOD30优化
	 * @param NotConsiderFlage
	 *            :参数设置项->是否优化时不考虑PCI冲突混淆
	 * @param Quota
	 *            :参数设置项->干扰矩阵设置
	 * @return:任务名
	 */
	public static String creatPCIMOD3Task(WebDriver driver, String defaultWindowID, String taskName, String AnyModel,
			String[] CfgFile, String[] PPFile, String[] MatrixFile, String[] DTOriginFile, String[] DTCSVFile,
			String[] SigFile, String[] PrfOriginFile, String[] PrfU2000File, String[] PrfPRSFile, String CellFile,
			String[] Distance, String[] Range, String AnnealNum, String InnerLoopNum, boolean TopNFlage, String TopN,
			boolean NCLFlage, boolean MOD30Flage, boolean NotConsiderFlage, String[] Quota) {

		taskName = PCIOptimizationTask.setServiceModule(driver, taskName, "PCI优化", AnyModel, "基于PCI MOD3的优化");
		PCIOptimizationTask.setPCIPara(driver, Distance, Range);
		PCIOptimizationTask.setPCIMOD3(driver, AnnealNum, InnerLoopNum, TopNFlage, TopN, NCLFlage, MOD30Flage,
				NotConsiderFlage, Quota);
		if ("全网优化".equalsIgnoreCase(AnyModel.trim())) {
			PCIOptimizationTask.setNetData(driver, defaultWindowID, CfgFile, PPFile, MatrixFile, null, DTOriginFile,
					DTCSVFile, SigFile, PrfOriginFile, PrfU2000File, PrfPRSFile, null);
		} else if ("局部优化".equalsIgnoreCase(AnyModel.trim())) {
			PCIOptimizationTask.setNetData(driver, defaultWindowID, CfgFile, PPFile, MatrixFile, null, DTOriginFile,
					DTCSVFile, SigFile, PrfOriginFile, PrfU2000File, PrfPRSFile, CellFile);
		} else {
			Assert.fail("分析模式错误,正确的应该是" + "\"" + "全网优化" + "\"" + "或者" + "\"" + "局部优化" + "\"" + "，AnyModel：" + AnyModel);
		}

		PCIOptimizationPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 *            :任务名
	 * @param CfgFile
	 *            :分析数据->配置数据
	 * @param PPFile
	 *            :分析数据->工程参数
	 * @param DTOriginFile
	 *            :分析数据->路测数据
	 * @param DTCSVFile
	 *            :分析数据->扫频数据
	 * @param SigFile
	 *            :分析数据->SIG数据
	 * @param PrfOriginFile
	 *            :分析数据->话统数据
	 * @param PrfU2000File
	 *            :分析数据->U2000话统
	 * @param PrfPRSFile
	 *            :分析数据->PRS话统
	 * @param OverburdenFlage
	 *            :专题选择->过覆盖
	 * @param AntennaReverFlage
	 *            :专题选择->天线接反
	 * @param AbnormalCellFlage
	 *            :专题选择->小区经纬度异常
	 * @param AntennaAzimuthFlage
	 *            :专题选择->天线方位角异常
	 * @return:任务名
	 */
	public static String creatRFTask(WebDriver driver, String defaultWindowID, String taskName, String[] CfgFile,
			String[] PPFile, String[] DTRFFile, boolean OverburdenFlage, boolean AntennaReverFlage,
			boolean AbnormalCellFlage, boolean AntennaAzimuthFlage) {

		taskName = PCIOptimizationTask.setServiceModule(driver, taskName, "优化前RF核查", null, null);
		PCIOptimizationTask.setRFPara(driver, OverburdenFlage, AntennaReverFlage, AbnormalCellFlage,
				AntennaAzimuthFlage);
		PCIOptimizationTask.setNetData(driver, defaultWindowID, CfgFile, PPFile, null, DTRFFile, null, null, null, null,
				null, null, null);
		PCIOptimizationPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 任务类型设置
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param TaskType
	 *            ：任务类型
	 * @param AnyModel
	 *            ：分析模式
	 * @param BusScen
	 *            ：业务场景
	 * @return
	 */
	public static String setServiceModule(WebDriver driver, String taskName, String TaskType, String AnyModel,
			String BusScen) {
		// 打开
		NetworkAnalysisCenterTask.openPCIOptimization(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		LoadingPage.Loading(driver, "#select_matrixFilePath", "分析数据:干扰矩阵,选择文件按钮");
		if ("PCI优化".equalsIgnoreCase(TaskType.trim())) {
			CommonJQ.click(driver, PCIOptimizationPage.RBoxTaskPCI, true, "任务类型 :PCI优化");
		} else if ("优化前RF核查".equalsIgnoreCase(TaskType.trim())) {
			CommonJQ.click(driver, PCIOptimizationPage.RBoxTaskRF, true, "任务类型 :优化前RF核查");
		} else {
			Assert.fail(
					"任务类型错误,正确的应该是" + "\"" + "PCI优化" + "\"" + "或者" + "\"" + "优化前RF核查" + "\"" + "，TaskType：" + TaskType);
		}
		if ("PCI优化".equalsIgnoreCase(TaskType.trim())) {
			if ("全网优化".equalsIgnoreCase(AnyModel.trim())) {
				CommonJQ.click(driver, PCIOptimizationPage.RBoxAnayWholeNet, true, "分析模式 :全网优化");
			} else if ("局部优化".equalsIgnoreCase(AnyModel.trim())) {
				CommonJQ.click(driver, PCIOptimizationPage.RBoxAnayPartial, true, "分析模式 :局部优化");
			} else {
				Assert.fail(
						"分析模式错误,正确的应该是" + "\"" + "全网优化" + "\"" + "或者" + "\"" + "局部优化" + "\"" + "，AnyModel：" + AnyModel);
			}

			if ("基于PCI冲突混淆的优化".equalsIgnoreCase(BusScen.trim())) {
				CommonJQ.click(driver, PCIOptimizationPage.RBoxPCIconflict, true, "业务场景 :基于PCI冲突混淆的优化");
			} else if ("基于PCI MOD3的优化".equalsIgnoreCase(BusScen.trim())) {
				CommonJQ.click(driver, PCIOptimizationPage.RBoxMod3, true, "业务场景 :基于PCI MOD3的优化");
			} else {
				Assert.fail("业务场景错误,正确的应该是" + "\"" + "基于PCI冲突混淆的优化" + "\"" + "或者" + "\"" + "基于PCI MOD3的优化" + "\""
						+ "，BusScen：" + BusScen);
			}
		}

		SwitchDriver.switchDriverToSEQ(driver);
		// 选中任务名称
		taskName = PCIOptimizationPage.setTaskName(driver, taskName);
		return taskName;
	}

	/**
	 * PCI设置
	 * 
	 * @param driver
	 * @param Distance
	 *            :复用距离(m)
	 * @param Range
	 *            :PCI可用范围设置
	 */
	public static void setPCIPara(WebDriver driver, String[] Distance, String[] Range) {

		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (Distance != null) {
			for (int i = 0; i < Distance.length; i++) {
				if (i == 0) {
					CommonJQ.value(driver, PCIOptimizationPage.TextDenseAreaDistance, Distance[i], true, "密集城区:复用距离");
				}
				if (i == 1) {
					CommonJQ.value(driver, PCIOptimizationPage.TextAreaDistance, Distance[i], true, "城区:复用距离");
				}
				if (i == 2) {
					CommonJQ.value(driver, PCIOptimizationPage.TextSuburbsDistance, Distance[i], true, "郊区:复用距离");
				}
				if (i == 3) {
					CommonJQ.value(driver, PCIOptimizationPage.TextCountrysideDistance, Distance[i], true, "农村:复用距离");
				}
				if (i == 4) {
					CommonJQ.value(driver, PCIOptimizationPage.TextBorderDistance, Distance[i], true, "边界:复用距离");
				}
				if (i == 5) {
					CommonJQ.value(driver, PCIOptimizationPage.TextRoomDivisionDistance, Distance[i], true, "室分:复用距离");
				}
				if (i == 6) {
					CommonJQ.value(driver, PCIOptimizationPage.TextOtherDistance, Distance[i], true, "其他:复用距离");
				}
				if (i == 7) {
					CommonJQ.value(driver, PCIOptimizationPage.TextSelfDefOneDistance, Distance[i], true, "自定义1:复用距离");
				}
				if (i == 8) {
					CommonJQ.value(driver, PCIOptimizationPage.TextSelfDefTwoDistance, Distance[i], true, "自定义2:复用距离");
				}
			}
		}

		if (Range != null) {
			for (int i = 0; i < Range.length; i++) {
				if (i == 0) {
					CommonJQ.value(driver, PCIOptimizationPage.TextDenseAreaRange, Range[i], true, "密集城区:PCI可用范围设置");
				}
				if (i == 1) {
					CommonJQ.value(driver, PCIOptimizationPage.TextAreaRange, Range[i], true, "城区:PCI可用范围设置");
				}
				if (i == 2) {
					CommonJQ.value(driver, PCIOptimizationPage.TextSuburbsRange, Range[i], true, "郊区:PCI可用范围设置");
				}
				if (i == 3) {
					CommonJQ.value(driver, PCIOptimizationPage.TextCountrysideRange, Range[i], true, "农村:PCI可用范围设置");
				}
				if (i == 4) {
					CommonJQ.value(driver, PCIOptimizationPage.TextBorderRange, Range[i], true, "边界:PCI可用范围设置");
				}
				if (i == 5) {
					CommonJQ.value(driver, PCIOptimizationPage.TextRoomDivisionRange, Range[i], true, "室分:PCI可用范围设置");
				}
				if (i == 6) {
					CommonJQ.value(driver, PCIOptimizationPage.TextOtherRange, Range[i], true, "其他:PCI可用范围设置");
				}
				if (i == 7) {
					CommonJQ.value(driver, PCIOptimizationPage.TextSelfDefOneRange, Range[i], true, "自定义1:PCI可用范围设置");
				}
				if (i == 8) {
					CommonJQ.value(driver, PCIOptimizationPage.TextSelfDefTwoRange, Range[i], true, "自定义2:PCI可用范围设置");
				}
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * 基于PCI MOD3的优化参数设置
	 * 
	 * @param driver
	 * @param AnnealNum
	 *            :PCI优化循环次数
	 * @param InnerLoopNum
	 *            :PCI优化系数
	 * @param TopNFlage
	 *            :是否TOPN
	 * @param TopN
	 *            :TOPN值
	 * @param NCLFlage
	 *            :是否基于NCL限定PCI资源
	 * @param MOD30Flage
	 *            :是否PCI MOD30优化
	 * @param NotConsiderFlage
	 *            :是否优化时不考虑PCI冲突混淆
	 * @param Quota
	 *            :干扰矩阵设置
	 */
	public static void setPCIMOD3(WebDriver driver, String AnnealNum, String InnerLoopNum, boolean TopNFlage,
			String TopN, boolean NCLFlage, boolean MOD30Flage, boolean NotConsiderFlage, String[] Quota) {

		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, PCIOptimizationPage.BtnParaSet, true, "参数设置项 :参数设置");
		// 初始化
		CommonJQ.click(driver, PCIOptimizationPage.CBoxNotConsider, true, "参数设置 :优化时不考虑PCI冲突混淆");
		// 设置

		CommonJQ.value(driver, PCIOptimizationPage.TextAnnealNum, AnnealNum, true, "参数设置:PCI优化循环次数");
		CommonJQ.value(driver, PCIOptimizationPage.TextInnerLoopNum, InnerLoopNum, true, "参数设置:PCI优化系数");
		CommonJQ.value(driver, PCIOptimizationPage.TextInnerLoopNum, InnerLoopNum, true, "参数设置:PCI优化系数");

		if (TopNFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.RBoxExportTypeTopn, true, "参数设置 :导出干扰矩阵->TopN");
			CommonJQ.value(driver, PCIOptimizationPage.TextMatrixTopN, TopN, true, "参数设置:干扰矩阵界面显示TopN");
		} else {
			CommonJQ.click(driver, PCIOptimizationPage.RBoxExportTypeAll, true, "参数设置 :导出干扰矩阵-> 全部");
		}
		if (NCLFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxBaseOnNCLP, true, "参数设置 :基于NCL限定PCI资源");
		}
		if (MOD30Flage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxMod30, true, "参数设置 :PCI MOD30优化");
		}
		if (NotConsiderFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxNotConsider, true, "参数设置 :优化时不考虑PCI冲突混淆");
		}
		if (Quota != null) {
			for (int i = 0; i < Quota.length; i++) {
				if (i == 0) {
					if ("0".equalsIgnoreCase(Quota[i])) {
						CommonJQ.click(driver, PCIOptimizationPage.CBoxBaseDT, true, "干扰矩阵设置 :基于路测数据的干扰矩阵比重");
					} else {
						CommonJQ.value(driver, PCIOptimizationPage.TextBaseDT, Quota[i], true,
								"干扰矩阵设置:基于路测数据的干扰矩阵比重(%)");
					}
				}
				if (i == 1) {
					if ("0".equalsIgnoreCase(Quota[i])) {
						CommonJQ.click(driver, PCIOptimizationPage.CBoxBaseSig, true, "干扰矩阵设置 :基于SIG数据的干扰矩阵比重");
					} else {
						CommonJQ.value(driver, PCIOptimizationPage.TextBaseSig, Quota[i], true,
								"干扰矩阵设置:基于SIG数据的干扰矩阵比重(%)");
					}
				}
				if (i == 2) {
					if ("0".equalsIgnoreCase(Quota[i])) {
						CommonJQ.click(driver, PCIOptimizationPage.CBoxBasePrf, true, "干扰矩阵设置 :基于简单拓扑和切换话统干扰矩阵比重");
					} else {
						CommonJQ.value(driver, PCIOptimizationPage.TextBasePrf, Quota[i], true,
								"干扰矩阵设置:基于简单拓扑和切换话统干扰矩阵比重(%)");
					}
				}
			}
		}
		CommonJQ.click(driver, PCIOptimizationPage.BtnParaOK, true, "参数设置 :确定");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 基于PCI冲突混淆的优化参数设置
	 * 
	 * @param driver
	 * @param NCLFlage
	 *            :基于NCL限定PCI资源
	 * @param Single2DoubleFlage
	 *            :单向邻区置双向邻区
	 */
	public static void setPCIConflictPara(WebDriver driver, boolean NCLFlage, boolean Single2DoubleFlage) {

		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, PCIOptimizationPage.BtnParaSet, true, "参数设置项 :参数设置");
		// 初始化
		CommonJQ.click(driver, PCIOptimizationPage.CBoxSingle2Double, true, "参数设置 :单向邻区置双向邻区");

		// 设置
		if (NCLFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxBaseOnNCLP, true, "参数设置 :基于NCL限定PCI资源");
		}
		if (Single2DoubleFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxSingle2Double, true, "参数设置 :单向邻区置双向邻区");
		}
		CommonJQ.click(driver, PCIOptimizationPage.BtnParaOK, true, "参数设置 :确定");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 专题选择
	 * 
	 * @param driver
	 * @param OverburdenFlage
	 *            :专题选择->过覆盖
	 * @param AntennaReverFlage
	 *            :专题选择->天线接反
	 * @param AbnormalCellFlage
	 *            :专题选择->小区经纬度异常
	 * @param AntennaAzimuthFlage
	 *            :专题选择->天线方位角异常
	 */
	public static void setRFPara(WebDriver driver, boolean OverburdenFlage, boolean AntennaReverFlage,
			boolean AbnormalCellFlage, boolean AntennaAzimuthFlage) {

		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 初始化
		CommonJQ.click(driver, PCIOptimizationPage.CBoxOverburden, true, "专题选择:过覆盖");
		CommonJQ.click(driver, PCIOptimizationPage.CBoxAntennaRever, true, "专题选择:天线接反");
		CommonJQ.click(driver, PCIOptimizationPage.CBoxAbnormalCell, true, "专题选择:小区经纬度异常");
		CommonJQ.click(driver, PCIOptimizationPage.CBoxAntennaAzimuth, true, "专题选择:天线方位角异常");

		// 设置
		if (OverburdenFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxOverburden, true, "专题选择:过覆盖");
		}
		if (AntennaReverFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxAntennaRever, true, "专题选择:天线接反");
		}
		if (AbnormalCellFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxAbnormalCell, true, "专题选择:小区经纬度异常");
		}
		if (AntennaAzimuthFlage) {
			CommonJQ.click(driver, PCIOptimizationPage.CBoxAntennaAzimuth, true, "专题选择:天线方位角异常");
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 分析数据设置
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param CfgFile
	 *            :配置数据
	 * @param PPFile
	 *            :工程参数
	 * @param MatrixFile
	 *            :干扰矩阵
	 * @param DTOriginFile
	 *            :路测数据
	 * @param DTCSVFile
	 *            : 扫频数据
	 * @param SigFile
	 *            :SIG数据
	 * @param PrfOriginFile
	 *            :话统数据
	 * @param PrfU2000File
	 *            :U2000话统
	 * @param PrfPRSFile
	 *            :PRS话统
	 */
	public static void setNetData(WebDriver driver, String defaultWindowID, String[] CfgFile, String[] PPFile,
			String[] MatrixFile, String[] DTRFFile, String[] DTOriginFile, String[] DTCSVFile, String[] SigFile,
			String[] PrfOriginFile, String[] PrfU2000File, String[] PrfPRSFile, String CellFile) {
		// 选择分析数据
		if (CfgFile != null) {
			if (CfgFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, CfgFile, PCIOptimizationPage.BtnCfg, defaultWindowID);
			}
		}
		if (PPFile != null) {
			if (PPFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, PPFile, PCIOptimizationPage.BtnPP, defaultWindowID);
			}
		}
		if (MatrixFile != null) {
			if (MatrixFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MatrixFile, PCIOptimizationPage.BtnMatrix, defaultWindowID);
			}
		}
		if (DTRFFile != null) {
			if (DTRFFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTRFFile, PCIOptimizationPage.BtnDTRF, defaultWindowID);
			}
		}
		if (DTOriginFile != null) {
			if (DTOriginFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTOriginFile, PCIOptimizationPage.BtnDTOrigin, defaultWindowID);
			}
		}

		if (DTCSVFile != null) {
			if (DTCSVFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTCSVFile, PCIOptimizationPage.BtnDTCSV, defaultWindowID);
			}
		}
		if (SigFile != null) {
			if (SigFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, SigFile, PCIOptimizationPage.BtnSig, defaultWindowID);
			}
		}
		if (PrfOriginFile != null) {
			if (PrfOriginFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, PrfOriginFile, PCIOptimizationPage.BtnPrfOrigin,
						defaultWindowID);
			}
		}
		if (PrfU2000File != null) {
			if ("MAX".equalsIgnoreCase(PrfU2000File[0])) {
				String[] MAXFile = {};
				GetDataByTypeTask.chooseTimeFolder(driver, MAXFile, PCIOptimizationPage.BtnPrfU2000, defaultWindowID);
			} else {
				GetDataByTypeTask.chooseTimeFolder(driver, PrfU2000File, PCIOptimizationPage.BtnPrfU2000,
						defaultWindowID);
			}

		}
		if (PrfPRSFile != null) {
			if ("MAX".equalsIgnoreCase(PrfPRSFile[0])) {
				String[] MAXFile = {};
				GetDataByTypeTask.chooseTimeFolder(driver, MAXFile, PCIOptimizationPage.BtnPrfPRS, defaultWindowID);
			} else {
				GetDataByTypeTask.chooseTimeFolder(driver, PrfPRSFile, PCIOptimizationPage.BtnPrfPRS, defaultWindowID);
			}

		}
		if (CellFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#cellFiletoBeOptimized", true, "分析数据:待优化小区");
			CommonFile.ChooseOneFile(CellFile);
			CommonJQ.click(driver, PCIOptimizationPage.BtnMessageOK, true, "上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @param ResultHome
	 *            ：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i], ResultHome);
			} else {
				FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome + "\\" + reportName[i]);
			}
		}

	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @return String：报告完整名称
	 */
	public static String[] downLoad_report(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = CommonJQ.length(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", i);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
