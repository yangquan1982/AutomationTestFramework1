package common.pobjec;

public class ProjectUpDataInfor {
	private Integer rownum;
	private String prjnum;
	private String prjname;
	private String prjstate;
	public ProjectUpDataInfor(Integer rownum, String prjnum, String prjname,
			String prjstate) {
		super();
		this.rownum = rownum;
		this.prjnum = prjnum;
		this.prjname = prjname;
		this.prjstate = prjstate;
	}
	public Integer getRownum() {
		return rownum;
	}
	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}
	public String getPrjnum() {
		return prjnum;
	}
	public void setPrjnum(String prjnum) {
		this.prjnum = prjnum;
	}
	public String getPrjname() {
		return prjname;
	}
	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}
	public String getPrjstate() {
		return prjstate;
	}
	public void setPrjstate(String prjstate) {
		this.prjstate = prjstate;
	}
	
	
	
}
