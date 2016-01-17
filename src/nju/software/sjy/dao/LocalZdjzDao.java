package nju.software.sjy.dao;

import nju.software.sjy.model.xy.LocalZdjz;

public interface LocalZdjzDao {
	
	void save(LocalZdjz localZdjz);
	void update(LocalZdjz localZdjz);
	LocalZdjz getZdjzById(String fjm,String ahdm);

}
