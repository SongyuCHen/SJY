package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.LocalBlxq;

public interface LocalBlxqDao {

	List<LocalBlxq> getLocalBlxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
	
	void update(LocalBlxq localBlxq);
	
	void save(LocalBlxq localBlxq);
	
	LocalBlxq getBlxqById(String fjm,String ahdm,String xh);

}
