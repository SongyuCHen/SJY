package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TUser;

public interface PfkhDao 
{
	
	Integer getMaxPfkhBh();
	
	void save(TPfkh tpfkh);
	
	void update(TPfkh tpfkh);
	
	TPfkh getPfkhByRybhNfJd(int rybh, int nf, int jd);
	
	List<TPfkh> getAllPfkh();
	
	TPfkh getPfkhByUserNfJd(TUser user, int nf, int jd);
	
	List<TUser> getTopUserByNfJd(int topNum, int nf, int jd);
	
	List<TPfkh> getPfkhByBmlistNfJd(List<TBm> bmList, int nf, int jd);
	
	List<TPfkh> getPfkhByUserlistNfJd(List<TUser> userList, int year, int quarter);
}
