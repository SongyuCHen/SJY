package nju.software.sjy.model.xy;

public class LocalZdjzId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2871722343296113939L;
	private String fydm;
	private String ahdm;
	
	public LocalZdjzId(){
		
	}
	public LocalZdjzId(String fydm, String ahdm) {
		super();
		this.fydm = fydm;
		this.ahdm = ahdm;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ahdm == null) ? 0 : ahdm.hashCode());
		result = prime * result + ((fydm == null) ? 0 : fydm.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalZdjzId other = (LocalZdjzId) obj;
		if (ahdm == null) {
			if (other.ahdm != null)
				return false;
		} else if (!ahdm.equals(other.ahdm))
			return false;
		if (fydm == null) {
			if (other.fydm != null)
				return false;
		} else if (!fydm.equals(other.fydm))
			return false;
		return true;
	}
	
	

}
