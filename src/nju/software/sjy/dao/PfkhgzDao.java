package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TPfkhgz;

public interface PfkhgzDao
{
	int getMaxbh();
	
	TPfkhgz getPfkhgzByPfkhGz(TPfkh pfkh, TGypz gz);
	
	void savePfkhgz(TPfkhgz pfkhgz);
	
	void updatePfkhgz(TPfkhgz pfkhgz);
	
	List<TPfkhgz> getPfkhgzByPfkhlistGz(List<TPfkh> pfkhList, TGypz gz);
	
	List<TPfkhgz> getPfkhgzByPfkh(TPfkh pfkh);
}
