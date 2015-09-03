package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.KfqxConvertor;
import nju.software.sjy.dao.KfqxDao;
import nju.software.sjy.mapper.MKflb;
import nju.software.sjy.mapper.MKfqx;
import nju.software.sjy.mapper.MKfxm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.KfqxService;

@Service
public class KfqxServiceImpl implements KfqxService 
{
	@Autowired
	private KfqxDao kfqxDao;
	
	@Override
	public Integer getMaxBh()
	{
		return kfqxDao.getMaxBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void save(TKfqx kfqx)
	{
		kfqxDao.save(kfqx);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void update(TKfqx kfqx)
	{
		kfqxDao.update(kfqx);
	}

	@Cacheable(value="sjyCache")
	public List<MKfqx> getAllKfqx() 
	{
		return KfqxConvertor.convertKfqx(kfqxDao.getAllKfqx());
	}

	@Cacheable(value="sjyCache")
	public List<MKflb> getAllKflb() 
	{
		return KfqxConvertor.convertKflb(kfqxDao.getAllKflb());
	}

	@Cacheable(value="sjyCache")
	public List<MKfxm> getAllKfxm() 
	{
		return KfqxConvertor.convertKfxm(kfqxDao.getAllKfxm());
	}

	@Override
	public int getMaxKflbPzbh() 
	{
		return kfqxDao.getMaxKflbPzbh(Constants.KFLB_STR);
	}

	@Override
	public int getMaxKflbBh() 
	{
		return kfqxDao.getMaxKflbBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteKflb(TGypz gypzByBh) 
	{
		kfqxDao.deleteKflb(gypzByBh);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void editKflb(TGypz kflb) 
	{
		kfqxDao.editKflb(kflb);
	}

	@Override
	public int getMaxKfxmBh() 
	{
		return kfqxDao.getMaxKfxmBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void addKfxm(TJfkfxm kfxm) 
	{
		kfqxDao.addKfxm(kfxm);
	}

	@Cacheable(value="sjyCache")
	public TJfkfxm getKfxmByBh(int bh) 
	{
		return kfqxDao.getKfxmByBh(bh);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void editKfxm(TJfkfxm kfxm) 
	{
		kfqxDao.editKfxm(kfxm);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteKfxm(TJfkfxm kfxm) 
	{
		kfqxDao.deleteKfxm(kfxm);
	}

	@Override
	public int getMaxKfqxBh() 
	{
		return kfqxDao.getMaxKfqxBh();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void addKfqx(TKfqx kfqx) 
	{
		kfqxDao.addKfqx(kfqx);
	}

	@Cacheable(value="sjyCache")
	public TKfqx getKfqxByBh(int bh) 
	{
		return kfqxDao.getKfqxByBh(bh);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteKfqx(TKfqx kfqx) 
	{
		kfqxDao.deleteKfqx(kfqx);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void editKfqx(TKfqx kfqx) 
	{
		kfqxDao.editKfqx(kfqx);
	}

	@Cacheable(value="sjyCache")
	public List<MKflb> getAllJflb() 
	{
		return KfqxConvertor.convertKflb(kfqxDao.getAllJflb());
	}

	@Override
	public int getMaxJflbPzbh() 
	{
		return kfqxDao.getMaxKflbPzbh(Constants.JFLB_STR);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void addKflb(TGypz kflb) 
	{
		kfqxDao.save(kflb);
	}

	@Cacheable(value="sjyCache")
	public List<MKfxm> getAllJfxm() 
	{
		return KfqxConvertor.convertKfxm(kfqxDao.getAllJfxm());
	}

	@Cacheable(value="sjyCache")
	public double getFsByLbMc(TGypz gypz, String mc)
	{
		return kfqxDao.getFsByLbMc(gypz, mc);
	}

	@Cacheable(value="sjyCache")
	public List<TJfkfxm> getKfxmByGypz(TGypz gypz)
	{
		return kfqxDao.getKfxmByGypz(gypz);
	}

	@Cacheable(value="sjyCache")
	public List<TKfqx> getKfqxByUserDateRangeKfxm(TUser user, String startTime,
			String endTime, TJfkfxm kfxm)
	{
		return kfqxDao.getKfqxByUserDateRangeKfxm(user, startTime, endTime, kfxm);
	}

	@Cacheable(value="sjyCache")
	public Integer getCountByUserDateRangeKfxm(TUser user, String startTime,
			String endTime, TJfkfxm kfxm)
	{
		return kfqxDao.getCountByUserDateRangeKfxm(user, startTime, endTime, kfxm);
	}

	@Cacheable(value="sjyCache")
	public List<TKfqx> getKfqxByUserDateRange(TUser user, String startDate,
			String endDate)
	{
		return kfqxDao.getKfqxByUserDateRange(user, startDate, endDate);
	}

	@Cacheable(value="sjyCache")
	public List<TKfqx> getKfqxByDateRangeExcludeQtbmlx(String startDate,
			String endDate, TGypz qtbmlx)
	{
		return kfqxDao.getKfqxByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
	}

	@Cacheable(value="sjyCache")
	public List<TKfqx> getKfqxByBmDateRange(TBm bm, String startDate,
			String endDate)
	{
		return kfqxDao.getKfqxByBmDateRange(bm, startDate, endDate);
	}
	
}
