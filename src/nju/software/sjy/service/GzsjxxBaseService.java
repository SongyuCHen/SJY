package nju.software.sjy.service;

import java.util.List;



import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxxBase;

public interface GzsjxxBaseService
{	
	Integer getMaxBh();
	
	void update(TGzsjxxBase gztb);
	
	void save(TGzsjxxBase gztb);
	
	void delete(TGzsjxxBase gztb);
	
	List<TGzsjxxBase> getGzsjxxBaseByGzsj(TGzsj gzsj);
	
	TGzsjxxBase getGzsjxxBaseByGzsjbhAndGzxxbh(int gzsjbh, int gzxxbh);
}
