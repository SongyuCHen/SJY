package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.BmDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.service.BmService;

@Service
public class BmServiceImpl implements BmService
{
	@Autowired
	private BmDao bmDao;

	@Cacheable(value="sjyCache")
	public List<TBm> getAllBm()
	{
		return bmDao.getAllBm();
	}

	@Cacheable(value="sjyCache")
	public List<String> getUserXmOfBm(String bmmc)
	{
		return bmDao.getUserXmOfBm(bmmc);
	}

	@Cacheable(value="sjyCache")
	public TBm getBmByBmbh(int bmbh) 
	{
		return bmDao.getBmByBmbh(bmbh);
	}

	@Cacheable(value="sjyCache")
	public TBm getBmByBmmc(String bmmc)
	{
		return bmDao.getBmByBmmc(bmmc);
	}

	@Cacheable(value="sjyCache")
	public List<TBm> getBmByBmlx(TGypz bmlx)
	{
		return bmDao.getBmByBmlx(bmlx);
	}

	@Override
	public Integer getMaxBmbh()
	{
		return bmDao.getMaxBmbh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void saveBm(TBm bm)
	{
		bmDao.saveBm(bm);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateBm(TBm bm)
	{
		bmDao.updateBm(bm);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteBm(TBm bm)
	{
		bmDao.deleteBm(bm);
	}

	@Cacheable(value="sjyCache")
	public List<TBm> getBmExcludeBmlx(TGypz bmlx)
	{
		return bmDao.getBmExcludeBmlx(bmlx);
	}

	@Cacheable(value="sjyCache")
	public TBm getBmByBmid(String bmid)
	{
		return bmDao.getBmByBmid(bmid);
	}

}
