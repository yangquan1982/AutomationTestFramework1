package cloud_public.task;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import common.util.CommonJQ;
import common.util.LanguageUtil;
import common.util.ZIP;
import common.util.LOG;

public class TaskReportTask {

	/**
	 * 搜索任务-老界面
	 * 
	 * @param taskname
	 *            任务名称
	 */
	public static String searchTask(WebDriver driver, String taskname) {
		LoadingPage.Loading(driver, TaskReportPage.CreateTask,"新建任务按钮");
		if (taskname.length() > 20) {
			taskname = taskname.substring(0, 19);
		}
		CommonJQ.value(driver, TaskReportPage.SearchId, taskname,"新建任务界面搜索输入框");
		CommonJQ.click(driver, TaskReportPage.SearchBtn, true,"新建任务界面搜索按钮");
		LoadingPage.Loading(driver);
		Pause.pause(1000);
		String ID = CommonJQ.text(driver, TaskReportPage.TaskColumnText, "", 1);
		return ID;
	}
	/**
	 * 搜索任务-新界面
	 * 
	 * @param taskname
	 *            任务名称
	 */
	public static String NewSearchTask(WebDriver driver, String taskname) {	
		LoadingPage.Loading(driver, "div.appDetailLeftName","任务列表左侧App名称");
		CommonJQ.click_ByText(driver, "li", LanguageUtil.translate("Project Task"), true, "项目任务");
		if (taskname.length() > 20) {
			taskname = taskname.substring(0, 19);
		}
		CommonJQ.value(driver, TaskReportPage.SearchId, taskname,"新建任务界面搜索输入框");
		CommonJQ.siblingsClick(driver, TaskReportPage.SearchId, "span");
//		CommonJQ.click(driver, TaskReportPage.SearchBtn, true,"新建任务界面搜索按钮");
		LoadingPage.Loading(driver);
		Pause.pause(1000);
		String ID = CommonJQ.text(driver, TaskReportPage.NewTaskColumnText, "", 1);
		return ID;
	}
	/**
	 * 搜索任务
	 * 
	 * @param taskname
	 *            任务名称
	 * @param taskStatus
	 *            任务状态
	 */
	public static boolean searchTask(WebDriver driver, String taskname,
			String taskStatus) {
		searchTask(driver, taskname);
		String gettaskStatus = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 4);
		System.out.println(ZIP.NowTime() + "get taskStatus:" + gettaskStatus);
		return gettaskStatus.equals(taskStatus);

	}

	/**
	 * 等待判断任务是否成功
	 * 
	 * @param taskname
	 *            任务名称
	 */
	public static void waittingTaskSucces(WebDriver driver, String taskname) {
		boolean flage = searchTask(driver, taskname,
				LanguageUtil.translate("Succeeded"));
		for (int i = 0; i < 10 * 60 && (!flage); i++) {
			Pause.pause(1000);
			flage = searchTask(driver, taskname,
					LanguageUtil.translate("Succeeded"));
			System.out.println(ZIP.NowTime() + "task flage:" + flage
					+ " LanguageUtil:" + LanguageUtil.translate("Succeeded"));
		}
		Assert.assertTrue("task failure,taskname:" + taskname, flage);
	}

	/**
	 * 删除任务
	 * 
	 * @param taskname
	 *            任务名称
	 */
	public static void deleteTask(WebDriver driver, String taskname) {
		String Search_taskname = "";
		searchTask(driver, taskname);
		Search_taskname = CommonJQ.text(driver, TaskReportPage.TaskColumnText,
				"", 3);
		if (taskname.equals(Search_taskname)) {
			CommonJQ.click(driver, TaskReportPage.ChecKAllBtn, false);
			CommonJQ.click(driver, TaskReportPage.DeleteTask, true);
			boolean flage = CommonJQ.isExisted(driver,
					TaskReportPage.MessageCancelBtn);
			if (flage) {
				CommonJQ.click(driver, TaskReportPage.MessageOKBtn, false);
			} else {
				CommonJQ.click(driver, TaskReportPage.MessageOKBtn, false);
			}
		}
	}

	/**
	 * <b>Description:</b>判断任务初始状态是否合理，获取任务 ID-老界面
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskname
	 *            任务名称
	 * @return void
	 */
	public static String asserTaskInitialState(WebDriver driver,
			String taskname, String ServiceType) {
		TaskReportTask.searchTask(driver, taskname);
		String gettaskStatus = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 4);
		LOG.info_testcase("新建任务状态为：" + gettaskStatus);
		String getServiceType = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 2).trim();
		LOG.info_testcase("新建任务类型为：" + getServiceType);
		if (StringUtils.equals(gettaskStatus, "")) {
			Assert.fail("Creat taskStatus is err,now taskStatus is null");
		}
		if (ServiceType != null) {
			if (!ServiceType.equals("")) {
				if (!StringUtils.equals(getServiceType,
						LanguageUtil.translate(ServiceType))) {
					Assert.assertTrue(
							"ActualValue:" + getServiceType + ",ExpectedValue:"
									+ LanguageUtil.translate(ServiceType),
							false);
				}
			}
		}

		String getID = CommonJQ.text(driver, TaskReportPage.TaskColumnText, "",
				1);
		LOG.info_testcase("新建任务id为：" + getID);

		return getID;
	}
	/**
	 * <b>Description:</b>判断任务初始状态是否合理，获取任务 ID-新界面
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskname
	 *            任务名称
	 * @return void
	 */
	public static String asserNewTaskInitialState(WebDriver driver,
			String taskname, String ServiceType){ 
		NewSearchTask( driver,  taskname);
		String gettaskStatus = CommonJQ.text(driver,
				TaskReportPage.NewTaskColumnText, "", 4);
		LOG.info_testcase("新建任务状态为：" + gettaskStatus);
		String getServiceType = CommonJQ.text(driver,
				TaskReportPage.NewTaskColumnText, "", 2).trim();
		LOG.info_testcase("新建任务类型为：" + getServiceType);
		if (StringUtils.equals(gettaskStatus, "")) {
			Assert.fail("Creat taskStatus is err,now taskStatus is null");
		}
		if (ServiceType != null) {
			if (!ServiceType.equals("")) {
				if (!StringUtils.equals(getServiceType,
						LanguageUtil.translate(ServiceType))) {
					Assert.assertTrue(
							"ActualValue:" + getServiceType + ",ExpectedValue:"
									+ LanguageUtil.translate(ServiceType),
							false);
				}
			}
		}

		String getID = CommonJQ.text(driver, TaskReportPage.NewTaskColumnText, "",
				1);
		LOG.info_testcase("新建任务id为：" + getID);

		return getID;
	}
	/**
	 * <b>Description:</b>判断任务最终状态是否合理，并获取任务 ID-老界面
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskname
	 *            任务名称
	 * @return void
	 */
	public static String asserTaskEndState(WebDriver driver, String taskname) {
		TaskReportTask.searchTask(driver, taskname);
		String getID = CommonJQ.text(driver, TaskReportPage.TaskColumnText, "",
				1);
		String gettaskStatus = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 4);
		if (StringUtils.equals(gettaskStatus, "")) {
			Assert.fail("任务未找到,搜索关键字:"+taskname);
		}
		if (!StringUtils.equals(gettaskStatus,
				LanguageUtil.translate("Succeeded"))) {
			Assert.fail("搜索关键字:"+taskname+",预期任务状态是:" + LanguageUtil.translate("Succeeded")
					+ ",实际任务状态是:" + gettaskStatus);
		}
		return getID;
	}
	/**
	 * <b>Description:</b>判断任务最终状态是否合理，并获取任务 ID-新界面
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskname
	 *            任务名称
	 * @return void
	 */
	public static String asserNewTaskEndState(WebDriver driver, String taskname) {
		TaskReportTask.NewSearchTask(driver, taskname);
		String getID = CommonJQ.text(driver, TaskReportPage.NewTaskColumnText, "",
				1);
		String gettaskStatus = CommonJQ.text(driver,
				TaskReportPage.NewTaskColumnText, "", 4);
		if (StringUtils.equals(gettaskStatus, "")) {
			Assert.fail("任务未找到,搜索关键字:"+taskname);
		}
		if (!StringUtils.equals(gettaskStatus,
				LanguageUtil.translate("Succeeded"))) {
			Assert.fail("搜索关键字:"+taskname+",预期任务状态是:" + LanguageUtil.translate("Succeeded")
					+ ",实际任务状态是:" + gettaskStatus);
		}
		return getID;
	}
	/**
	 * <b>Description:</b>MV判断任务状态是否合理，并获取任务 ID
	 * 
	 * @author pgenexautotest
	 * @param driver
	 * @param taskname
	 *            任务
	 * @param role
	 *            角色
	 * @return void *
	 **/
	public static String asserTaskState(WebDriver driver, String taskname,
			String role) {
		TaskReportTask.searchTask(driver, taskname);
		String getID = CommonJQ.text(driver, TaskReportPage.TaskColumnText, "",
				1);
		String getTaskStatus = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 5);
		if ("Expert".equals(role)) {// role专家
			if (StringUtils.equals(getTaskStatus, "")) {
				Assert.fail("Creat taskStatus is err,now taskStatus is null");
			}
			if (!StringUtils.equals(getTaskStatus,
					LanguageUtil.translate("Expert"))) {
				Assert.fail("任务状态预期是：" + LanguageUtil.translate("Expert")
						+ "，实际值是:" + getTaskStatus);
			}
		} else if ("Consumer_Domestic".equals(role)) { // role 普通用户
			if (StringUtils.equals(getTaskStatus, "")) {
				Assert.fail("Creat taskStatus is err,now taskStatus is null");
			}
			if (!StringUtils.equals(getTaskStatus,
					LanguageUtil.translate("Consumer_Domestic"))) {
				Assert.fail("任务状态预期是："
						+ LanguageUtil.translate("Consumer_Domestic")
						+ "，实际值是:" + getTaskStatus);
			}
		} else if("ExpertSecond".equals(role)) {
			if (StringUtils.equals(getTaskStatus, "")) {
				Assert.fail("Creat taskStatus is err,now taskStatus is null");
			}
			getTaskStatus = CommonJQ.text(driver,
					TaskReportPage.TaskColumnText, "", 4);
			if(!StringUtils.equals(getTaskStatus,
					LanguageUtil.translate("Succeeded"))) {
				Assert.fail("任务状态预期是："
						+ LanguageUtil.translate("Succeeded")
						+ "，实际值是:" + getTaskStatus);
			}
		}
		return getID;
	}

	/**
	 * <b>Description:</b>RAN判断任务状态是否合理，并获取任务 ID
	 * 
	 * @author pgenexautotest
	 * @param driver
	 * @param taskname
	 *            任务
	 * @param role
	 *            角色
	 * @return void *
	 **/
	public static String RANAsserTaskState(WebDriver driver, String taskname,
			String role) {
		TaskReportTask.searchTask(driver, taskname);
		String getID = CommonJQ.text(driver, TaskReportPage.TaskColumnText, "",
				1);
		String getTaskStatus = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 4);
		if ("Expert".equals(role)) {// role专家
			if (StringUtils.equals(getTaskStatus, "")) {
				Assert.fail("Creat taskStatus is err,now taskStatus is null");
			}
			if (!StringUtils.equals(getTaskStatus,
					LanguageUtil.translate("reportState"))) {
				Assert.fail("任务状态预期是：" + LanguageUtil.translate("reportState")
						+ "，实际值是:" + getTaskStatus);
			}
		} else if ("Intefaces".equals(role)) { // role 接口人
			if (StringUtils.equals(getTaskStatus, "")) {
				Assert.fail("Creat taskStatus is err,now taskStatus is null");
			}
			if (!StringUtils.equals(getTaskStatus,
					LanguageUtil.translate("Inteface"))) {
				Assert.fail("任务状态预期是："
						+ LanguageUtil.translate("Consumer_Domestic")
						+ "，实际值是:" + getTaskStatus);
			}
		} else if ("Consumer_Domestic".equals(role)) { // role 普通用户
			if (StringUtils.equals(getTaskStatus, "")) {
				Assert.fail("Creat taskStatus is err,now taskStatus is null");
			}
			if (!StringUtils.equals(getTaskStatus,
					LanguageUtil.translate("Consumer_Domestic"))) {
				Assert.fail("任务状态预期是："
						+ LanguageUtil.translate("Consumer_Domestic")
						+ "，实际值是:" + getTaskStatus);
			}
		}
		return getID;
	}
	/**
	 * 
	 * @param driver
	 * @param AppName
	 */
	public static void GetAppName(WebDriver driver,String AppName ) {
		String ActAppName = CommonJQ.text(driver, "div.appDetailLeftName","",
				true);
		Assert.assertTrue("左侧APP名称预期是：" + AppName + "实际是：" + ActAppName,
				AppName.equalsIgnoreCase(ActAppName));
	}
}
