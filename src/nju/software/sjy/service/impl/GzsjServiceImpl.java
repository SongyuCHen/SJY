package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.dao.GzsjDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GzsjServiceImpl implements GzsjService 
{
	@Autowired
	private GzsjDao gzsjDao;

	@Cacheable(value="sjyCache")
	public TGzsj getGzsjByBh(int bh)
	{
		return gzsjDao.getGzsjByBh(bh);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void saveGzsj(TGzsj gzsj)
	{
		gzsjDao.saveGzsj(gzsj);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateGzsj(TGzsj gzsj)
	{
		gzsjDao.updateGzsj(gzsj);
	}

	@Override
	public Integer getMaxGzsjBh()
	{
		return gzsjDao.getMaxGzsjBh();
	}

	@Cacheable(value="sjyCache")
	public List<TGzsj> getAllGzsj()
	{
		return gzsjDao.getAllGzsj();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteGzsj(TGzsj gzsj)
	{
		gzsjDao.deleteGzsj(gzsj);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateAllGzsj(List<TGzsj> tlist)
	{
		gzsjDao.updateAllGzsj(tlist);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsj> getGzsjByBhArr(Integer[] bhArr)
	{
		return gzsjDao.getGzsjByBhArr(bhArr);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsj> getGzsjByUserNfJdZt(TUser user, int nf, int jd,
			TGypz finalZt)
	{
		String[] dateRange = DateUtil.getYearMonthRange(nf, jd);
		return gzsjDao.getGzsjByUserDateRangeZt(user, dateRange[0], dateRange[1], finalZt);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsj> getGzsjByUserBmDateRange(TUser user, TBm bm,
			String startDate, String endDate)
	{
		return gzsjDao.getGzsjByUserBmDateRange(user, bm, startDate, endDate);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsj> getGzsjByDateRangeExcludeQtbmlx(String startDate,
			String endDate, TGypz qtbmlx)
	{
		return gzsjDao.getGzsjByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
	}

	@Cacheable(value="sjyCache")
	public List<TGzsj> getGzsjByBmDateRange(TBm bm, String startDate,
			String endDate)
	{
		return gzsjDao.getGzsjByBmDateRange(bm, startDate, endDate);
	}

	@Cacheable(value="sjyCache")
	public TGzsj getGzsjByUserDate(TUser user, String date)
	{
		return gzsjDao.getGzsjByUserDate(user, date);
	}


}
