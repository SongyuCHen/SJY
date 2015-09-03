package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.JfkfxmDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.service.JfkfxmService;

@Service
public class JfkfxmServiceImpl implements JfkfxmService
{
	@Autowired
	private JfkfxmDao jfkfxmDao;
	
	@Cacheable(value="sjyCache")
	public List<TJfkfxm> getJfkfxmByLbJfkf(TGypz lb, int jfkf)
	{
		return jfkfxmDao.getJfkfxmByLbJfkf(lb, jfkf);
	}

}
