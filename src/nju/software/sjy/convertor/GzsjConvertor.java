package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MGypz;
import nju.software.sjy.mapper.MGzsj;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TGzsjxxBase;
import nju.software.sjy.model.xy.TUser;

public class GzsjConvertor
{
	public static MGzsj convertGzsj(TGzsj tgzsj)
	{
		if(tgzsj == null)
		{
			return null;
		}
		
		/*人员姓名*/
		TUser user = tgzsj.getUser();
		
		/*部门名称*/
		TBm bm = tgzsj.getBm();
		
		/*获取状态*/
		TGypz zt = tgzsj.getZt();
		String ztmc = null;
		if(zt != null)
		{
			ztmc = zt.getMc();
		}
		
		MGzsj mgzsj = new MGzsj();
		mgzsj.setBh(tgzsj.getBh());
		mgzsj.setXm(user.getXm());
		mgzsj.setBmmc(bm.getBmmc());
		mgzsj.setRq(tgzsj.getRq());
		mgzsj.setZt(ztmc);
		
		return mgzsj;
	}
	
	public static MGzsj convertGzsj(TGzsj tgzsj, int pzSize)
	{
		if(tgzsj == null)
		{
			return null;
		}
		
		/*人员姓名*/
		TUser user = tgzsj.getUser();
		
		/*部门名称*/
		TBm bm = tgzsj.getBm();
		
		/*获取状态*/
		TGypz zt = tgzsj.getZt();
		String ztmc = null;
		if(zt != null)
		{
			ztmc = zt.getMc();
		}
		
		List<TGzsjxx> gzsjxxList = tgzsj.getXxList();
		List<Integer> szList = new ArrayList<Integer>();
		if(gzsjxxList != null)
		{
			int len = gzsjxxList.size() < pzSize ? gzsjxxList.size() : pzSize;
			for(int i=0;i<len;i++)
			{
				szList.add(gzsjxxList.get(i).getSz());
			}
		}
		
		int k;
		for(k = szList.size();k<pzSize;k++)
		{
			szList.add(0);
		}
		
		MGzsj mgzsj = new MGzsj();
		mgzsj.setBh(tgzsj.getBh());
		mgzsj.setXm(user.getXm());
		mgzsj.setBmmc(bm.getBmmc());
		mgzsj.setRq(tgzsj.getRq());
		mgzsj.setZt(ztmc);
		mgzsj.setSzList(szList);
		
		return mgzsj;
	}
	
	
	/**
	 * 工作实绩中需要注意的是，gzsj.user.bm未必等于gzsj.bm
	 * 可能有个人以前在部门A，现在在部门B，而工作实绩中保存的是他在以前部门的时候
	 * 
	 * @param tgzsj
	 * @param gztbList
	 * @param pzSize
	 * @return
	 */
	public static MGzsj convertGzsj(TGzsj tgzsj, List<TGzsjxx> gzsjxxList, List<TGzsjxxBase> gzsjxxBaseList, int pzSize)
	{
		if(tgzsj == null)
		{
			return null;
		}
		/*是否修改工作量*/
		boolean isChange = false;
		/*人员姓名*/
		TUser user = tgzsj.getUser();
		
		/*部门名称*/
		TBm bm = tgzsj.getBm();
		
		/*获取状态*/
		TGypz zt = tgzsj.getZt();
		String ztmc = null;
		if(zt != null)
		{
			ztmc = zt.getMc();
		}
		
		List<Integer> szList = new ArrayList<Integer>();
		List<Integer> szList2 = new ArrayList<Integer>();
		if(gzsjxxList.size()> 0)
		{
			for(TGzsjxx gzsjxx : gzsjxxList)
			{
				szList.add(gzsjxx.getSz());
				
			}
		}
		if(gzsjxxBaseList.size()>0)
		{
			for(TGzsjxxBase gzsjxxBase : gzsjxxBaseList)
			{
				szList2.add(gzsjxxBase.getSz());
				
			}
		}else{
			for(TGzsjxx gzsjxx : gzsjxxList)
			{
				szList2.add(gzsjxx.getSz());
				
			}
		}
		
		int k;
		for(k = szList.size();k<pzSize;k++)
		{
			szList.add(0);
			szList2.add(0);
		}
		//若值相同，则表示没有修改
		for(k = 0;k<pzSize;k++)
		{
			if(szList.get(k)==szList2.get(k)){
				szList.set(k, 0);
			}else{
				isChange = true;
			}
		}
		
		MGzsj mgzsj = new MGzsj();
		mgzsj.setBh(tgzsj.getBh());
		mgzsj.setXm(user.getXm());
		mgzsj.setBmmc(bm.getBmmc());
		mgzsj.setRq(tgzsj.getRq());
		mgzsj.setZt(ztmc);
		mgzsj.setSzList(szList2);
		mgzsj.setSzList2(szList);
		mgzsj.setChange(isChange);
		return mgzsj;
	}
	
	public static MGypz convertGzsjxxBaseToGypz(TGzsjxxBase base)
	{
		if(base == null)
		{
			return null;
		}
		
		MGypz mgypz = new MGypz();
		mgypz.setMc(base.getGzxx().getMc());
		mgypz.setSz(base.getSz());
		
		return mgypz;
	}
	
	public static List<MGypz> convertGzsjxxBaseToGypz(List<TGzsjxxBase> tlist)
	{
		List<MGypz> mlist = new ArrayList<MGypz>();
		if(tlist != null)
		{
			for(TGzsjxxBase base : tlist)
			{
				MGypz mgypz = convertGzsjxxBaseToGypz(base);
				mlist.add(mgypz);
			}
		}
		
		return mlist;
	}
}