package nju.software.sjy.dao;

import java.util.List;




import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxxBase;


public interface GzsjxxBaseDao
{
	
	void update(TGzsjxxBase gztb);
	
	void save(TGzsjxxBase gztb);
	
	Integer getMaxBh();
	
	void delete(TGzsjxxBase gztb);

	List<TGzsjxxBase> getGzsjxxByGzsj(TGzsj gzsj);
	
	TGzsjxxBase getGzsjxxByGzsjbhAndGzxxbh(int gzsjbh, int gzxxbh);
}
