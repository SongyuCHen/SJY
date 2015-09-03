package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.dao.JfqxDao;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class JfqxServiceImpl implements JfqxService 
{
	@Autowired
	private JfqxDao jfqxDao;

	@Override
	public Integer getMaxBh()
	{
		return jfqxDao.getMaxBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TJfqx jfqx)
	{
		jfqxDao.save(jfqx);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TJfqx jfqx)
	{
		jfqxDao.update(jfqx);
	}

	@Cacheable(value="sjyCache")
	public TJfqx getJfqxByBh(int bh)
	{
		return jfqxDao.getJfqxByBh(bh);
	}

	@Cacheable(value="sjyCache")
	public List<TJfqx> getAllJfqx() 
	{
		return jfqxDao.getAllJfqx();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void delete(TJfqx jfqx) 
	{
		jfqxDao.delete(jfqx);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateAllJfqx(List<TJfqx> tlist)
	{
		jfqxDao.updateAllJfqx(tlist);
	}

	@Cacheable(value="sjyCache")
	public List<TJfqx> getJfqxByBhArr(Integer[] bhArr)
	{
		return jfqxDao.getJfqxByBhArr(bhArr);
	}

	@Cacheable(value="sjyCache")
	public List<TJfqx> getJfqxByUserDateRangeJfxmZt(TUser user,
			String startTime, String endTime, TJfkfxm jfxm, TGypz zt)
	{
		return jfqxDao.getJfqxByUserDateRangeJfxmZt(user, startTime, endTime, jfxm, zt);
	}

	@Cacheable(value="sjyCache")
	public int getCountOfJfqxByUserNfJdJfxmZt(TUser user, int nf, int jd,
			TJfkfxm jfxm, TGypz zt)
	{		
		String[] dateRange = DateUtil.getDateRange(nf, jd);
		return jfqxDao.getCountOfJfqxByUserDateRangeJfxmZt(user, dateRange[0], dateRange[1], jfxm, zt);
	}

	@Cacheable(value="sjyCache")
	public List<TJfqx> getJfqxByDateRangeExcludeQtbmlx(String startDate,
			String endDate, TGypz qtbmlx)
	{
		return jfqxDao.getJfqxByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
	}

	@Cacheable(value="sjyCache")
	public List<TJfqx> getJfqxByBmDateRange(TBm bm, String startDate,
			String endDate)
	{
		return jfqxDao.getJfqxByBmDateRange(bm, startDate, endDate);
	}

	@Cacheable(value="sjyCache")
	public List<TJfqx> getJfqxByUserDateRange(TUser user, String startDate,
			String endDate)
	{
		return jfqxDao.getJfqxByUserDateRange(user, startDate, endDate);
	}

}
