package common.parameter.platform;

public class SystemManageParameter {
	private String setitemname;//设置项
	private String userid;//用户id
	private String name;//用户名称
	private String[] businessSubjects; //业务专题
	private String[] rats;//制式
	private Operator operator;//运营商
	private String projectname;//工程名称
	private String[] manageoperator;//负责运营商
	private ManageArea managearea;//管理区域
	
	
	
	public SystemManageParameter(String setitemname, String userid) {
		super();
		this.setitemname = setitemname;
		this.userid = userid;
		this.managearea = new ManageArea();
		this.operator = new Operator();
		
	}
	public SystemManageParameter(String setitemname, String userid, String name,
			String[] businessSubjects, String[] rats, Operator operator,
			String projectname, String[] manageoperator, ManageArea managearea) {
		super();
		this.setitemname = setitemname;
		this.userid = userid;
		this.name = name;
		this.businessSubjects = businessSubjects;
		this.rats = rats;
		this.operator = operator;
		this.projectname = projectname;
		this.manageoperator = manageoperator;
		this.managearea = managearea;
	}
	public String getSetitemname() {
		return setitemname;
	}
	public void setSetitemname(String setitemname) {
		this.setitemname = setitemname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getBusinessSubjects() {
		return businessSubjects;
	}
	public void setBusinessSubjects(String[] businessSubjects) {
		this.businessSubjects = businessSubjects;
	}
	public String[] getRats() {
		return rats;
	}
	public void setRats(String[] rats) {
		this.rats = rats;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String[] getManageoperator() {
		return manageoperator;
	}
	public void setManageoperator(String[] manageoperator) {
		this.manageoperator = manageoperator;
	}
	public ManageArea getManagearea() {
		return managearea;
	}
	public void setManagearea(ManageArea managearea) {
		this.managearea = managearea;
	}
	
	
	
	
	
	
}
