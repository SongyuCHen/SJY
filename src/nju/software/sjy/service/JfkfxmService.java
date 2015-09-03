package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;

public interface JfkfxmService
{
	List<TJfkfxm> getJfkfxmByLbJfkf(TGypz lb, int jfkf);
}
