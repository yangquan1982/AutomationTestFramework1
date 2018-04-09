package common.util.tmss;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import common.util.CommonFile;
import common.util.ZIP;

public class StartBackFillTMSS {

	public static void backFill(String gt3kid, boolean result, String gt3kPath) {

		
		if(gt3kPath!=null){
			File destRoot = new File(gt3kPath);
			if (destRoot.isFile()==false) {
				Assert.fail(CommonFile.fileMsg(gt3kPath) + " is not File");
			}
		}else{
			backFill(gt3kid, result);
			return;	
		}
		System.out.println(ZIP.NowTime() + "TMSS BackFill Start...");
		if ((gt3kid != null)&&("".equalsIgnoreCase(gt3kid)==false)) {
			TMSSInfo.initTmssConfigInfo(gt3kPath);
			if (!"1".equals(TMSSInfo.sendTestExcuteResultFlag)) {
				System.out.println(ZIP.NowTime() + "TMSS not Backfill...");
				return;
			}
			if (TMSSInfo.GT3Kuri != null) {
				if (result) {
					TMSS.SetTestCaseResult(TMSSInfo.GT3Kuri, gt3kid, "151", "");
				} else {
					TMSS.SetTestCaseResult(TMSSInfo.GT3Kuri, gt3kid, "152", "");
				}
			} else {
				System.out.println(ZIP.NowTime() + "TMSS not find GT3Kuri...");
			}

		}

		System.out.println(ZIP.NowTime() + "TMSS BackFill End...");
	}

	public static void backFill(String gt3kid, boolean result) {
		System.out.println(ZIP.NowTime() + "TMSS BackFill Start...");
		if (gt3kid != null) {
			TMSSInfo.initTmssConfigInfo();
			if (!"1".equals(TMSSInfo.sendTestExcuteResultFlag)) {
				System.out.println(ZIP.NowTime() + "TMSS not Backfill...");
				return;
			}
			if (TMSSInfo.GT3Kuri != null) {
				if (result) {
					TMSS.SetTestCaseResult(TMSSInfo.GT3Kuri, gt3kid, "151", "");
				} else {
					TMSS.SetTestCaseResult(TMSSInfo.GT3Kuri, gt3kid, "152", "");
				}
			} else {
				System.out.println(ZIP.NowTime() + "TMSS not find GT3Kuri...");
			}

		}

		System.out.println(ZIP.NowTime() + "TMSS BackFill End...");
	}

	public static void main(String[] args) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("01_SQI.01_L_KQI_RanPro.08_Data_index.04_PA2DW.187", "152");
		StartBackFillTMSS.backFill("T001_HW_UMTS_AccessAnalysis_NeAll_Day_RABSetupFailure_SrvAll_FrqAll_EnvAll", false);
	}
}
