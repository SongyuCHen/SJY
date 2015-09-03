package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.dao.GzsjxxBaseDao;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxxBase;
import nju.software.sjy.service.GzsjxxBaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GzsjxxBaseServiceImpl implements GzsjxxBaseService
{

	@Autowired
	private GzsjxxBaseDao gzsjxxBaseDao;

	@Override
	public Integer getMaxBh()
	{
		return gzsjxxBaseDao.getMaxBh();
	}

	@Override
	@CacheEvict(value = "sjyCache", allEntries = true)
	public void update(TGzsjxxBase gztb)
	{
		gzsjxxBaseDao.update(gztb);

	}

	@Override
	@CacheEvict(value = "sjyCache", allEntries = true)
	public void save(TGzsjxxBase gztb)
	{
		gzsjxxBaseDao.save(gztb);

	}

	@Override
	@CacheEvict(value = "sjyCache", allEntries = true)
	public void delete(TGzsjxxBase gztb)
	{
		gzsjxxBaseDao.delete(gztb);

	}

	@Override
	@Cacheable(value = "sjyCache")
	public List<TGzsjxxBase> getGzsjxxBaseByGzsj(TGzsj gzsj)
	{
		return gzsjxxBaseDao.getGzsjxxByGzsj(gzsj);
	}

	@Override
	public TGzsjxxBase getGzsjxxBaseByGzsjbhAndGzxxbh(int gzsjbh, int gzxxbh) {
		// TODO Auto-generated method stub
		return gzsjxxBaseDao.getGzsjxxByGzsjbhAndGzxxbh(gzsjbh, gzxxbh);
	}

}
