package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.PfkhDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.PfkhService;

@Service
public class PfkhServiceImpl implements PfkhService 
{
	@Autowired
	private PfkhDao pfkhDao;

	@Override
	public Integer getMaxPfkhBh()
	{
		return pfkhDao.getMaxPfkhBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TPfkh tpfkh)
	{
		pfkhDao.save(tpfkh);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TPfkh tpfkh)
	{
		pfkhDao.update(tpfkh);
	}

	@Cacheable(value="sjyCache")
	public TPfkh getPfkhByUserNfJd(TUser user, int nf, int jd)
	{
		return pfkhDao.getPfkhByUserNfJd(user, nf, jd);
	}
	
	@Cacheable(value="sjyCache")
	public List<TUser> getTopUserByNfJd(int topNum, int nf, int jd)
	{
		return pfkhDao.getTopUserByNfJd(topNum, nf, jd);
	}

	@Cacheable(value="sjyCache")
	public List<TPfkh> getPfkhByBmlistNfJd(List<TBm> bmList, int nf, int jd)
	{
		return pfkhDao.getPfkhByBmlistNfJd(bmList, nf, jd);
	}

	@Cacheable(value="sjyCache")
	public List<TPfkh> getPfkhByUserlistNfJd(List<TUser> userList, int year, int quarter)
	{
		return pfkhDao.getPfkhByUserlistNfJd(userList, year, quarter);
	}
	
	
}
