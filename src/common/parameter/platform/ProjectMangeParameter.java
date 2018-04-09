package common.parameter.platform;

import cloud_platform.page.projectManage.ProjectManageConst;

public class ProjectMangeParameter {
	
	private String projectname;
	private String region;
	private String agent;
	private String operator;
	private String projectLabel;
	private String projectRat;
	private String projectCode;
	private String projectType;
	
	
	public ProjectMangeParameter() {
	}
	
	public ProjectMangeParameter(String region,
			String agent, String operator,String projectLable, String projectRat,
			String projectCode, String projectType) {
		
		this.region = region;
		this.agent = agent;
		this.operator = operator;
		this.projectLabel = projectLable;
		this.projectRat = projectRat;
		this.projectCode = projectCode;
		this.projectType = projectType;
	}
	
	

	public String getProjectLabel() {
		return projectLabel;
	}

	public void setProjectLabel(String projectLabel) {
		this.projectLabel = projectLabel;
	}

	public String getProjectname() {
		String name = agent+operator;
		if (projectType.equals(ProjectManageConst.BusinessType_RAN)) {
			this.projectname = name+projectLabel+"_"+projectCode;
		}else{
			this.projectname = name +projectRat+projectLabel;
		}
		return projectname;
	}
	

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getProjectRat() {
		return projectRat;
	}
	public void setProjectRat(String projectRat) {
		this.projectRat = projectRat;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	

}
