package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;

public interface GypzDao
{
	
	TGypz getGypzByBh(int bh);
	
	List<TGypz> getGypzByLx(String lx);
	
	Integer getBhByLxAndMc(String lx, String mc);
	
	TGypz getGypzByLxMc(String lx, String mc);
	
	Integer getMaxBh();
	
	Integer getMaxPzbhByLx(String lx);
	
	void save(TGypz gypz);
	
	void update(TGypz gypz);
	
	TGypz getGypzByPzbhLx(int pzbh, String lx);
	
	void delete(TGypz gypz);
	
	List<String> getMcByLx(String lx);
}
