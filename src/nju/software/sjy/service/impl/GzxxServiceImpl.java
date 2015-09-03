package nju.software.sjy.service.impl;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.common.Constants;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzxxService;
import nju.software.sjy.service.KfqxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取规则的规则细项
 *
 */
@Service
public class GzxxServiceImpl implements GzxxService
{
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private KfqxService kfqxService;
	
	public List<TGypz> getGzxx(TGypz tgypz)
	{
		String gzmc = tgypz.getMc();
		
		List<TGypz> gzxxList = null;
		if(Constants.GZSJ.equals(gzmc))
		{
			gzxxList = getGzxxOfGZSJ(tgypz);
		}
		else if(Constants.GZZL.equals(gzmc))
		{
			gzxxList = getGzxxOfGZZL(tgypz);
		}
		else if(Constants.ZFSJ.equals(gzmc) || Constants.GZZF.equals(gzmc))
		{
			gzxxList = getGzxxOfZFSJ(tgypz);
		}
		else if(Constants.JFQX.equals(gzmc))
		{
			gzxxList = getGzxxOfJFQX(tgypz);
		}
		
		return gzxxList;
	}
	
	/**
	 * 获取工作实绩的规则细项
	 * 
	 * @param gypz
	 * @return
	 */
	public List<TGypz> getGzxxOfGZSJ(TGypz gypz)
	{
		String gzmc = gypz.getMc();
		List<TGypz> gzxxpz = gypzService.getGypzByLx(gzmc);
		
		return gzxxpz;
	}
	
	public List<TGypz> getGzxxOfGZZL(TGypz gypz)
	{
		String gzmc = gypz.getMc();
		TGypz kfpz = gypzService.getGypzByLxMc(Constants.KFLB_STR, gzmc);
		
		List<TJfkfxm> kfxmList = kfqxService.getKfxmByGypz(kfpz);
		List<TGypz> gypzList = new ArrayList<TGypz>();
		for(TJfkfxm tkfxm : kfxmList)
		{
			TGypz tgypz = new TGypz();
			String mc = tkfxm.getMc();
			tgypz.setLx(gzmc);
			tgypz.setMc(mc);
			
			gypzList.add(tgypz);
		}
		
		return gypzList;
	}
	
	/**
	 * 作风守纪的规则细项
	 * 
	 * @param gypz
	 * @return
	 */
	public List<TGypz> getGzxxOfZFSJ(TGypz gypz)
	{
		String gzmc = gypz.getMc();
		TGypz kfpz = gypzService.getGypzByLxMc(Constants.KFLB_STR, gzmc);
		
		List<TJfkfxm> kfxmList = kfqxService.getKfxmByGypz(kfpz);
		List<TGypz> gypzList = new ArrayList<TGypz>();
		for(TJfkfxm tkfxm : kfxmList)
		{
			TGypz tgypz = new TGypz();
			String mc = tkfxm.getMc();
			tgypz.setLx(gzmc);
			tgypz.setMc(mc);
			
			gypzList.add(tgypz);
		}
		
		return gypzList;
	}
	
	public List<TGypz> getGzxxOfJFQX(TGypz gypz)
	{
		String gzmc = gypz.getMc();
		List<TGypz> gzxxpz = gypzService.getGypzByLx(gzmc);
		
		return gzxxpz;
	}
}
