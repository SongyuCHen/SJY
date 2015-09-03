package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.PfkhgzDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TPfkhgz;
import nju.software.sjy.service.PfkhgzService;

@Service
public class PfkhgzServiceImpl implements PfkhgzService
{
	@Autowired
	private PfkhgzDao pfkhgzDao;
	
	@Override
	public int getMaxbh()
	{
		return pfkhgzDao.getMaxbh();
	}

	@Cacheable(value="sjyCache")
	public TPfkhgz getPfkhgzByPfkhGz(TPfkh pfkh, TGypz gz)
	{
		return pfkhgzDao.getPfkhgzByPfkhGz(pfkh, gz);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void savePfkhgz(TPfkhgz pfkhgz)
	{
		pfkhgzDao.savePfkhgz(pfkhgz);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updatePfkhgz(TPfkhgz pfkhgz)
	{
		pfkhgzDao.updatePfkhgz(pfkhgz);
	}

	@Cacheable(value="sjyCache")
	public List<TPfkhgz> getPfkhgzByPfkhlistGz(List<TPfkh> pfkhList, TGypz gz)
	{
		return pfkhgzDao.getPfkhgzByPfkhlistGz(pfkhList, gz);
	}

	@Cacheable(value="sjyCache")
	public List<TPfkhgz> getPfkhgzByPfkh(TPfkh pfkh)
	{
		return pfkhgzDao.getPfkhgzByPfkh(pfkh);
	}

}
