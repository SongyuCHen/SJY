package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.dao.GzsjxxDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GzsjxxServiceImpl implements GzsjxxService
{
	@Autowired
	private GzsjxxDao gzsjxxDao;

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TGzsjxx Gzsjxx)
	{
		gzsjxxDao.update(Gzsjxx);
	}


	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TGzsjxx Gzsjxx)
	{
		gzsjxxDao.save(Gzsjxx);
	}


	@Override
	public Integer getMaxBh()
	{
		return gzsjxxDao.getMaxBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TGzsjxx Gzsjxx)
	{
		gzsjxxDao.delete(Gzsjxx);
	}

	@Cacheable(value="sjyCache")
	public TGzsjxx getGzsjxxByGzsjGzxx(TGzsj gzsj, TGypz gzxx)
	{
		return gzsjxxDao.getGzsjxxByGzsjGzxx(gzsj, gzxx);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsjxx> getGzsjxxByGzsjGzlx(TGzsj gzsj, String gzlx)
	{
		return gzsjxxDao.getGzsjxxByGzsjGzlx(gzsj, gzlx);
	}

	@Cacheable(value="sjyCache")
	public Integer getMaxSumByGzxxNfJdZt(TGypz gzxx, int nf, int jd,
			TGypz finalZt)
	{
		//int[] months = DateUtil.getRangeOfQuarter(jd);
		//return gzsjxxDao.getMaxSumByGzxxNfJdZt(gzxx, nf, months[0], months[1], finalZt);
		String[] dateRange = DateUtil.getYearMonthRange(nf, jd);
		return gzsjxxDao.getMaxSumByGzxxDateRangeZt(gzxx, dateRange[0], dateRange[1], finalZt);
	}

	@Cacheable(value="sjyCache")
	public Integer getMinSumByGzxxNfJdZt(TGypz gzxx, int nf, int jd,
			TGypz finalZt)
	{
		//int[] months = DateUtil.getRangeOfQuarter(jd);
		//return gzsjxxDao.getMinSumByGzxxNfJdZt(gzxx, nf, months[0], months[1], finalZt);
		String[] dateRange = DateUtil.getYearMonthRange(nf, jd);
		return gzsjxxDao.getMinSumByGzxxDateRangeZt(gzxx, dateRange[0], dateRange[1], finalZt);
	}

	@Cacheable(value="sjyCache")
	public List<Integer> getSzByGzsjlistGzxx(List<TGzsj> tlist, TGypz gzxx)
	{
		return gzsjxxDao.getSzByGzsjlistGzxx(tlist, gzxx);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsjxx> getGzsjxxByGzsj(TGzsj gzsj)
	{
		return gzsjxxDao.getGzsjxxByGzsj(gzsj);
	}


}
