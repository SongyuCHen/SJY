package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.JfkfxmService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.SplcService;
import nju.software.sjy.service.StatisticsService;
import nju.software.sjy.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 统计服务
 * 负责统计各种次数、件数
 * 统计各个规则中规则细项的数量
 * 如
 * 工作实绩-装订卷宗 10 庭审记录5
 * 工作质量-优秀文书7  优秀庭审3
 * 作风守纪-全勤记录30 廉政建设10
 * 加分情形-技能竞赛2  通报表扬5
 * 		   -调研成果3  集体或公益活动6
 * 
 * @author zceolrj
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService
{
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private GzsjxxService gzsjxxService;
	
	@Autowired
	private JfqxService jfqxService;
	
	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private SplcService splcService;
	
	@Autowired
	private JfkfxmService jfkfxmService;
	
	/**
	 * 统计入口，统计user在指定nf、jd下，指定的gzxx的数值
	 */
	public int getCount(TGypz gzxx, TUser user, int nf, int jd)
	{
		/* 规则类型 */
		String lx = gzxx.getLx();
		
		int count = 0;
		
		/* 工作实绩 */
		if(Constants.GZSJ.equals(lx))
		{
			count = getCountOfGZSJ(gzxx, user, nf, jd);
		}
		else if(Constants.GZZL.equals(lx))
		{
			count = getCountOfGZZL(gzxx, user, nf, jd);
		}
		else if(Constants.ZFSJ.equals(lx))
		{
			count = getCountOfZFSJ(gzxx, user, nf, jd);
		}
		else if(Constants.JFQX.equals(lx))
		{
			count = getCountOfJFQX(gzxx, user, nf, jd);
		}
		
		return count;
	}
	
	/**
	 * 获取工作实绩的数值
	 * 
	 * @param gzxx	规则细项
	 * @param user	用户
	 * @param nf	年份
	 * @param jd	季度
	 * @return
	 */
	public int getCountOfGZSJ(TGypz gzxx, TUser user, int nf, int jd)
	{	
		TGypz finalZt = splcService.getFinalZt();
		List<TGzsj> tlist = gzsjService.getGzsjByUserNfJdZt(user, nf, jd, finalZt);
		
		List<Integer> szList = gzsjxxService.getSzByGzsjlistGzxx(tlist, gzxx);
		
		int count = 0;
		if(szList != null)
		{
			for(Integer sz : szList)
			{
				count += sz;
			}
		}
		return count;
	}
	
	public int getCountOfGZZL(TGypz gzxx, TUser user, int nf, int jd)
	{
		String[] dateRange = DateUtil.getDateRange(nf, jd);
		TJfkfxm kfxm = new TJfkfxm();
		kfxm.setMc(gzxx.getMc());
		kfxm.setJfkf(0);
		
		int count = kfqxService.getCountByUserDateRangeKfxm(user, dateRange[0], dateRange[1], kfxm);
		
		return count;
	}
	
	public int getCountOfZFSJ(TGypz gzxx, TUser user, int nf, int jd)
	{
		String[] dateRange = DateUtil.getDateRange(nf, jd);
		TJfkfxm kfxm = new TJfkfxm();
		kfxm.setMc(gzxx.getMc());
		kfxm.setJfkf(0);
		
		int count = kfqxService.getCountByUserDateRangeKfxm(user, dateRange[0], dateRange[1], kfxm);
		
		return count;
	}
	
	/**
	 * 获得加分情形的次数
	 * 
	 * @param gzxx	规则细项：技能竞赛、通报表扬、调研成果等
	 * @param rybh	人员编号
	 * @param nf	年份
	 * @param jd	季度
	 * @return		次数
	 */
	public int getCountOfJFQX(TGypz gzxx, TUser user, int nf, int jd)
	{
		TGypz finalZt = splcService.getFinalZt();
		
		TJfkfxm jfxm = new TJfkfxm();
		jfxm.setLb(gzxx);
		jfxm.setJfkf(1);
		
		int count = jfqxService.getCountOfJfqxByUserNfJdJfxmZt(user, nf, jd, jfxm, finalZt);
		
		return count;
	}

	@Override
	public int getMaxCountOfApprovedGZSJ(TGypz gzxx, int nf, int jd)
	{
		TGypz finalZt = splcService.getFinalZt();
		int count = gzsjxxService.getMaxSumByGzxxNfJdZt(gzxx, nf, jd, finalZt);
		return count;
	}

	@Override
	public int getMinCountOfApprovedGZSJ(TGypz gzxx, int nf, int jd)
	{
		TGypz finalZt = splcService.getFinalZt();
		int count = gzsjxxService.getMinSumByGzxxNfJdZt(gzxx, nf, jd, finalZt);
		return count;
	}
}
