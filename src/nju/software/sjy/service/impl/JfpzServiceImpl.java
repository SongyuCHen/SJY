package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.JfpzDao;
import nju.software.sjy.model.xy.TJfpz;
import nju.software.sjy.service.JfpzService;

@Service
public class JfpzServiceImpl implements JfpzService
{
	@Autowired
	private JfpzDao jfpzDao;

	@Cacheable(value="sjyCache")
	public List<TJfpz> getAllJfpz()
	{
		return jfpzDao.getAllJfpz();
	}

	@Cacheable(value="sjyCache")
	public TJfpz getJfpzByBh(int bh)
	{
		return jfpzDao.getJfpzByBh(bh);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TJfpz jfpz)
	{
		jfpzDao.save(jfpz);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TJfpz jfpz)
	{
		jfpzDao.update(jfpz);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TJfpz jfpz)
	{
		jfpzDao.delete(jfpz);
	}
	
	public Integer getMaxBh()
	{
		return jfpzDao.getMaxBh();
	}

	@Cacheable(value="sjyCache")
	public Integer getJfzByJb(String jb)
	{
		return jfpzDao.getJfzByJb(jb);
	}

}
