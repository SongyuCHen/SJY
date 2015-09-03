package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MKflb;
import nju.software.sjy.mapper.MKfqx;
import nju.software.sjy.mapper.MKfxm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TKfqx;

public class KfqxConvertor 
{
	
	public static MKflb convertKflb(TGypz kflb)
	{
		MKflb lb = new MKflb();
		lb.setBh(kflb.getBh());
		lb.setPzbh(kflb.getPzbh());
		lb.setMc(kflb.getMc());
		return lb;
	}
	
	public static List<MKflb> convertKflb(List<TGypz> kflbList)
	{
		List<MKflb> retList = new ArrayList<MKflb>();
		if(!kflbList.isEmpty())
		{
			for(TGypz kflb : kflbList)
			{
				retList.add(convertKflb(kflb));
			}
		}
		return retList;
	}
	
	public static MKfxm convertKfxm(TJfkfxm kfxm)
	{
		MKfxm xm = new MKfxm();
		xm.setBh(kfxm.getBh());
		xm.setFs(kfxm.getFs());
		xm.setMc(kfxm.getMc());
		xm.setKflb(convertKflb(kfxm.getLb()));
		return xm;
	}
	
	public static List<MKfxm> convertKfxm(List<TJfkfxm> jfkfxmList)
	{
		List<MKfxm> retList = new ArrayList<MKfxm>();
		if(!jfkfxmList.isEmpty())
		{
			for(TJfkfxm jfkfxm : jfkfxmList)
			{
				retList.add(convertKfxm(jfkfxm));
			}
		}
		return retList;
	}
	
	public static MKfqx convertKfqx(TKfqx kfqx)
	{
		MKfqx mkfqx = new MKfqx();
		mkfqx.setBh(kfqx.getBh());
		mkfqx.setBmmc(kfqx.getBm().getBmmc());
		mkfqx.setBmbh(kfqx.getBm().getBmbh());
		mkfqx.setHdsj(kfqx.getHdsj());
		
		mkfqx.setRyxm(kfqx.getRy().getXm());
		mkfqx.setRybh(kfqx.getRy().getRybh());
		mkfqx.setNf(kfqx.getNf());
		mkfqx.setYf(kfqx.getYf());
		mkfqx.setKfcs(kfqx.getKfcs());
		mkfqx.setComment(kfqx.getComment());
		mkfqx.setKfxm(convertKfxm(kfqx.getKfxm()));
		return mkfqx;
	}
	
	public static List<MKfqx> convertKfqx(List<TKfqx> kfqxList)
	{
		List<MKfqx> retList = new ArrayList<MKfqx>();
		if(!kfqxList.isEmpty())
		{
			for(TKfqx kfqx : kfqxList)
			{
				retList.add(convertKfqx(kfqx));
			}
		}
		return retList;
	}
	
}
