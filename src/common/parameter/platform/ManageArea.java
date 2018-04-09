package common.parameter.platform;

public class ManageArea {
	
	private String province;
	private String city;
	private boolean selectall;
	
	public ManageArea() {
		super();		
		this.selectall = false;
	}
	public ManageArea(String province, String city) {
		super();
		this.province = province;
		this.city = city;		
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public boolean isSelectall() {
		return selectall;
	}
	public void setSelectall(boolean selectall) {
		this.selectall = selectall;	}
	
	
	
	
	

}
