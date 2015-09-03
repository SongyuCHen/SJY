package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.PfpzDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfpz;
import nju.software.sjy.service.PfpzService;

@Service
public class PfpzServiceImpl implements PfpzService 
{
	@Autowired
	private PfpzDao pfpzDao;

	@Cacheable(value="sjyCache")
	public List<TPfpz> getAllPfpz()
	{
		return pfpzDao.getAllPfpz();
	}

	@Cacheable(value="sjyCache")
	public TPfpz getPfpzByMc(String mc)
	{
		return pfpzDao.getPfpzByMc(mc);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TPfpz pfpz)
	{
		pfpzDao.update(pfpz);
	}

	@Override
	public Integer getMaxBh() 
	{
		return pfpzDao.getMaxBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TPfpz pfpz) 
	{
		pfpzDao.save(pfpz);
	}

	@Cacheable(value="sjyCache")
	public TPfpz getPfpzByGz(TGypz gz) 
	{
		return pfpzDao.getPfpzByGz(gz);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TPfpz pfpz) 
	{
		pfpzDao.delete(pfpz);
	}

	@Cacheable(value="sjyCache")
	public TPfpz getPfpzByBh(int bh) 
	{
		return pfpzDao.getPfpzByBh(bh);
	}

	@Override
	public int getFsByGz(TGypz gz)
	{
		return pfpzDao.getFsByGz(gz);
	}
	
	@Cacheable(value="sjyCache")
	public List<TPfpz> getPfpzByGzlx(String lx)
	{
		return pfpzDao.getPfpzByGzlx(lx);
	}
}
