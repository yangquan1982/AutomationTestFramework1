package common.parameter.platform;

public class Operator {
	private String region;
	private String agent;
	private String[] operator;
	
	public Operator() {		
	}
	
	public Operator(String region, String agent, String[] operator) {
		super();
		this.region = region;
		this.agent = agent;
		this.operator = operator;
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
	public String[] getOperator() {
		return operator;
	}
	public void setOperator(String[] operator) {
		this.operator = operator;
	}
	
	
	
	
}
