package nju.software.sjy.service.impl;

import java.util.List;

import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.GzsjxxService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.PfpzService;
import nju.software.sjy.service.ScoreService;
import nju.software.sjy.service.SplcService;
import nju.software.sjy.service.StatisticsService;
import nju.software.sjy.util.DateUtil;
import nju.software.sjy.util.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评分服务，计算规则的得分
 * 
 */
@Service
public class ScoreServiceImpl implements ScoreService
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
	private GypzService gypzService;
	
	@Autowired
	private PfpzService pfpzService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private SplcService splcService;
	
	/**
	 * 计算规则得分
	 * 
	 * @param gz		规则
	 * @param gzxxList	规则细项
	 * @param user		用户
	 * @param nf		年份
	 * @param jd		季度
	 * @return			得分
	 */
	public double getScore(TGypz gz, List<TGypz> gzxxList, TUser user, int nf, int jd)
	{
		if(!DateUtil.validDate(nf, jd))
		{
			return 0;
		}
		
		//规则名称
		String gzmc = gz.getMc();
		double score = 0;
		
		/*工作实绩(工作实值)*/
		if(Constants.GZSJ.equals(gzmc))
		{
			score = getScoreOfGZSJ(gzxxList, user, nf, jd);
		}
		/*工作质量*/
		else if(Constants.GZZL.equals(gzmc))
		{
			score = getScoreOfGZZL(gz, user, nf, jd);
			
		}
		/*作风守纪(工作作风)*/
		else if(Constants.ZFSJ.equals(gzmc) || Constants.GZZF.equals(gzmc))
		{
			score = getScoreOfZFSJ(gz, user, nf, jd);
			
		}
		/*加分情形*/
		else if(Constants.JFQX.equals(gzmc))
		{
			score = getScoreOfJFQX(gz, gzxxList, user, nf, jd);
		}
		
		/* 保留一位小数 */
		score = NumberUtil.formatDouble(score);
		
		return score;
	}
	
	/**
	 * 计算工作实绩的得分——所有规则细项的总得分
	 */
	public double getScoreOfGZSJ(List<TGypz> gzxxList, TUser user, int nf, int jd)
	{
		double score = 0;
		for(int i=0;i<gzxxList.size();i++)
		{
			score += getScoreOfGZSJ(gzxxList.get(i), user, nf, jd);
		}
		return score;
	}
	
	/**
	 * 获取工作实绩单项规则的得分
	 */
	public double getScoreOfGZSJ(TGypz gzxx, TUser user, int nf, int jd)
	{
		String gzxxmc = gzxx.getMc();
		
		double score = 0;
		/*装订卷宗*/
		if(Constants.ZDJZ.equals(gzxxmc))
		{
			score = getScoreOfZDJZ(user, nf, jd, gzxx);
		}
		/*庭审记录*/
		else if(Constants.TSJL.equals(gzxxmc))
		{
			score = getScoreOfTSJL(user, nf, jd, gzxx);
		}
		
		return score;
	}
	
	/**
	 * 计算装订卷宗的得分
	 */
	public double getScoreOfZDJZ(TUser user, int nf, int jd, TGypz gzxx)
	{
		int curNum = statisticsService.getCountOfGZSJ(gzxx, user, nf, jd);
		
		int maxNum = statisticsService.getMaxCountOfApprovedGZSJ(gzxx, nf, jd);
		int minNum = statisticsService.getMinCountOfApprovedGZSJ(gzxx, nf, jd);
		
		int zdjz_max_score = pfpzService.getFsByGz(gzxx);
		
//		double score = 0;
//		if(curNum == 0)
//		{
//			score = 0;
//		}
//		else if((maxNum - minNum) != 0)
//		{
//			score = (double)((curNum - minNum) * zdjz_max_score) / (maxNum - minNum);
//		}
		
		double score = calculateScore(curNum, maxNum, minNum, zdjz_max_score, RATE);
		
		return score;
	}
	
	/**
	 * 计算庭审记录的得分
	 */
	public double getScoreOfTSJL(TUser user, int nf, int jd, TGypz gzxx)
	{
		int curNum = statisticsService.getCountOfGZSJ(gzxx, user, nf, jd);
		int maxNum = statisticsService.getMaxCountOfApprovedGZSJ(gzxx, nf, jd);
		int minNum = statisticsService.getMinCountOfApprovedGZSJ(gzxx, nf, jd);
		
		int tsjl_max_score = pfpzService.getFsByGz(gzxx);
		
//		double score = 0;
//		if(curNum == 0)
//		{
//			score = 0;
//		}
//		else if((maxNum - minNum) != 0)
//		{
//			score = (double)((curNum - minNum) * tsjl_max_score) / (maxNum - minNum);
//		}
		double score = calculateScore(curNum, maxNum, minNum, tsjl_max_score, RATE);
		
		
		return score;
	}
	
	/**
	 * 计算工作质量的得分
	 */
	public double getScoreOfGZZL(TGypz gz, TUser user, int nf, int jd)
	{
		int gzzl_base_score = pfpzService.getFsByGz(gz);
		double baseScore = gzzl_base_score;
		double kf = 0;
		
		String[] dateRange = DateUtil.getDateRange(nf, jd);
		
		TGypz gypz = gypzService.getGypzByLxMc(Constants.KFLB_STR, gz.getMc()); 
		
		TJfkfxm kfxm = new TJfkfxm();
		kfxm.setLb(gypz);
		kfxm.setJfkf(0);// 0表示扣分
		
		List<TKfqx> list = kfqxService.getKfqxByUserDateRangeKfxm(user, dateRange[0], dateRange[1], kfxm);
		if(list != null)
		{
			for(TKfqx kfqx : list)
			{
				TJfkfxm tkfxm = kfqx.getKfxm();
				double score = tkfxm.getFs();
				int kfcs = kfqx.getKfcs();
				
				kf += score * kfcs;
			}
		}
		
		double score = baseScore - kf;
		
		if(gzzl_base_score > 0)
		{
			score = score > 0 ? score : 0;
		}
		
		return score;
	}
	
	/**
	 * 计算作风守纪的得分
	 * 
	 * @param gz
	 * @param rybh
	 * @param nf
	 * @param jd
	 * @return
	 */
	public double getScoreOfZFSJ(TGypz gz, TUser user, int nf, int jd)
	{
		int zfsj_base_score = pfpzService.getFsByGz(gz);
		double baseScore = zfsj_base_score;
		double kf = 0;
		
		String[] dateRange = DateUtil.getDateRange(nf, jd);
		
		TGypz gypz = gypzService.getGypzByLxMc(Constants.KFLB_STR, gz.getMc()); 
		
		TJfkfxm kfxm = new TJfkfxm();
		kfxm.setLb(gypz);
		kfxm.setJfkf(0);
		
		List<TKfqx> list = kfqxService.getKfqxByUserDateRangeKfxm(user, dateRange[0], dateRange[1], kfxm);
		if(list != null)
		{
			for(TKfqx kfqx : list)
			{
				TJfkfxm tkfxm = kfqx.getKfxm();
				double score = tkfxm.getFs();
				int kfcs = kfqx.getKfcs();
				
				kf += score * kfcs;
			}
		}
		
		double score = baseScore - kf;
		if(zfsj_base_score > 0)
		{
			score = score > 0? score : 0;
		}
		
		return score;
	}
	
	/**
	 * 计算加分情形的得分
	 * 
	 * @param gzxxList
	 * @param user
	 * @param nf
	 * @param jd
	 * @return
	 */
	public double getScoreOfJFQX(TGypz gz, List<TGypz> gzxxList, TUser user, int nf, int jd)
	{
		double score = 0;
		if(gzxxList != null)
		{
			for(TGypz gzxx : gzxxList)
			{
				score += getScoreOfJFQX(gzxx, user, nf, jd);
			}
		}
		
		int jfqx_max_score = pfpzService.getFsByGz(gz);
		if(jfqx_max_score > 0)
		{
			score = score > jfqx_max_score ? jfqx_max_score : score;
		}
		
		return score;
	}
	
	/**
	 * 计算加分情形单项规则的得分
	 */
	public double getScoreOfJFQX(TGypz gzxx, TUser user, int nf, int jd)
	{
		double jf = 0;
		
		String[] dateRange = DateUtil.getDateRange(nf, jd);
		
		TGypz gypz = gypzService.getGypzByLxMc(Constants.JFLB_STR, gzxx.getMc());
		
		TJfkfxm jfxm = new TJfkfxm();
		jfxm.setLb(gypz);
		jfxm.setJfkf(1);
			
		TGypz finalZt = splcService.getFinalZt();
		List<TJfqx> list = jfqxService.getJfqxByUserDateRangeJfxmZt(user, dateRange[0], dateRange[1], jfxm, finalZt);
		
		if(list != null)
		{
			for(TJfqx tjfqx : list)
			{
				double fs = tjfqx.getJfxm().getFs();
				int jfcs = tjfqx.getJfcs();
				
				jf += fs * jfcs;
			}
		}
		
		double score = jf;
		
		return score;
	}
	
	private double calculateScore(int curNum, int maxNum, int minNum, int maxScore, double rate)
	{
		double maxFs = maxScore;
		double minFs = maxScore * rate;
		
		// (cur-min)/(max-min)=(fs-minFs)/(maxFs-minFs);
		double fs = 0.0;
		if((maxNum - minNum) != 0)
		{
			fs = (maxFs - minFs) * (curNum - minNum) / (maxNum - minNum) + minFs;
		}
		else
		{
			fs = maxScore;
		}
		
		return fs;
	}
}
