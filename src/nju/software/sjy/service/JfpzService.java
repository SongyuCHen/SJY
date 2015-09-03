package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TJfpz;

public interface JfpzService
{
	List<TJfpz> getAllJfpz();
	
	TJfpz getJfpzByBh(int bh);
	
	void save(TJfpz jfpz);
	
	void update(TJfpz jfpz);
	
	void delete(TJfpz jfpz);
	
	Integer getMaxBh();
	
	Integer getJfzByJb(String jb);
}
