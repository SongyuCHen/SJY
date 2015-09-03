package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.GypzDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.service.GypzService;

@Service
public class GypzServiceImpl implements GypzService
{
	@Autowired
	private GypzDao gypzDao;

	@Cacheable(value="sjyCache")
	public TGypz getGypzByBh(int bh)
	{
		return gypzDao.getGypzByBh(bh);
	}

	@Cacheable(value="sjyCache")
	public List<TGypz> getGypzByLx(String lx)
	{
		return gypzDao.getGypzByLx(lx);
	}

	@Cacheable(value="sjyCache")
	public TGypz getGypzByLxMc(String lx, String mc)
	{
		return gypzDao.getGypzByLxMc(lx, mc);
	}

	@Override
	public Integer getMaxBh()
	{
		return gypzDao.getMaxBh();
	}

	@Override
	public Integer getMaxPzbhByLx(String lx)
	{
		return gypzDao.getMaxPzbhByLx(lx);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TGypz gypz)
	{
		gypzDao.save(gypz);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TGypz gypz)
	{
		gypzDao.update(gypz);
	}

	@Cacheable(value="sjyCache")
	public TGypz getGypzByPzbhLx(int pzbh, String lx)
	{
		return gypzDao.getGypzByPzbhLx(pzbh, lx);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TGypz gypz)
	{
		gypzDao.delete(gypz);
	}

	@Cacheable(value="sjyCache")
	public List<String> getMcByLx(String lx)
	{
		return gypzDao.getMcByLx(lx);
	}
	
}
