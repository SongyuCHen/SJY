package nju.software.sjy.dao;

import nju.software.sjy.model.xy.LocalBlxq;

public interface LocalBlxqDao {

	
	void update(LocalBlxq localBlxq);
	
	void save(LocalBlxq localBlxq);
	
	LocalBlxq getBlxqById(String fjm,String ahdm,String xh);

}
