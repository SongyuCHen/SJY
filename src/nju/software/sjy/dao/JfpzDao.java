package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TJfpz;

public interface JfpzDao
{
	List<TJfpz> getAllJfpz();
	
	TJfpz getJfpzByBh(int bh);
	
	void save(TJfpz jfpz);
	
	void update(TJfpz jfpz);
	
	void delete(TJfpz jfpz);
	
	Integer getMaxBh();
	
	Integer getJfzByJb(String jb);
}
