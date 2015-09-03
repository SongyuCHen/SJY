package nju.software.sjy.model.tdh;

public class EajJzId implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ahdm;
	private String xh;
	
	public EajJzId(){
		
	}
	public EajJzId(String ahdm, String xh) {
		this.ahdm = ahdm;
		this.xh = xh;
	}
	public String getAhdm() {
		return ahdm;
	}
	public void setAhdm(String ahdm) {
		this.ahdm = ahdm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ahdm == null) ? 0 : ahdm.hashCode());
		result = prime * result + ((xh == null) ? 0 : xh.hashCode());
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
		EajJzId other = (EajJzId) obj;
		if (ahdm == null) {
			if (other.ahdm != null)
				return false;
		} else if (!ahdm.equals(other.ahdm))
			return false;
		if (xh == null) {
			if (other.xh != null)
				return false;
		} else if (!xh.equals(other.xh))
			return false;
		return true;
	}
	
}
