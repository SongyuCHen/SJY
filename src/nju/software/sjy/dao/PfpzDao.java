package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfpz;

public interface PfpzDao 
{
	List<TPfpz> getAllPfpz();
	
	TPfpz getPfpzByMc(String mc);
	
	void update(TPfpz pfpz);
	
	Integer getMaxBh();
	
	void save(TPfpz pfpz);
	
	TPfpz getPfpzByGz(TGypz gz);
	
	void delete(TPfpz pfpz);
	
	TPfpz getPfpzByBh(int bh);
	
	int getFsByGz(TGypz gz);
	
	List<TPfpz> getPfpzByGzlx(String lx);
}
