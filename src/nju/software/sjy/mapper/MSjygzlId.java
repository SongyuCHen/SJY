package nju.software.sjy.mapper;

public class MSjygzlId {
	private String fydm;
	private String ahdm;
	private String attr1;
	private String attr2;
	public MSjygzlId(){
		
	}
	public MSjygzlId(String fydm, String ahdm, String attr1, String attr2) {
		super();
		this.fydm = fydm;
		this.ahdm = ahdm;
		this.attr1 = attr1;
		this.attr2 = attr2;
	}
	public String getFydm() {
		return fydm;
	}
	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	public String getAhdm() {
		return ahdm;
	}
	public void setAhdm(String ahdm) {
		this.ahdm = ahdm;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	

}
