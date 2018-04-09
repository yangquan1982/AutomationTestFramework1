package cloud_plugin.page;

import org.openqa.selenium.WebDriver;

import cloud_public.page.IndexPage;
import cloud_public.task.IndexTask;
import common.util.CommonJQ;

public class NetWorkInsightPage {

	private static final String VideoInsight = "li[id=\"genexspace.plugin.ltevideoInsightPlugStr\"]";

	public static void VideoInsightClick(WebDriver driver, String PrjName) {
		IndexPage.netAppMange(driver);
		CommonJQ.click(driver, VideoInsight, true, "插件未找到，插件名：视频洞察");
		IndexTask.changePrj(driver, PrjName);
	}

	public static void VideoInsightClick(WebDriver driver) {
		CommonJQ.click(driver, VideoInsight, true, "插件未找到，插件名：视频洞察");
	}
}
