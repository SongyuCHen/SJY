package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;

public interface JfkfxmDao
{
	List<TJfkfxm> getJfkfxmByLbJfkf(TGypz lb, int jfkf);
}
