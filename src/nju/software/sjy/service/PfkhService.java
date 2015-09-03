package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TUser;

public interface PfkhService 
{	
	Integer getMaxPfkhBh();
	
	void save(TPfkh tpfkh);
	
	void update(TPfkh tpfkh);
	
	TPfkh getPfkhByUserNfJd(TUser user, int nf, int jd);
	
	List<TUser> getTopUserByNfJd(int topNum, int nf, int jd);
	
	List<TPfkh> getPfkhByBmlistNfJd(List<TBm> bmList, int nf, int jd);
	
	List<TPfkh> getPfkhByUserlistNfJd(List<TUser> userList, int year, int quarter);
}
