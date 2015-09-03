package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MBm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;

public class BmConvertor
{
	public static MBm convert(TBm tbm)
	{
		if(tbm == null)
		{
			return null;
		}
		
		MBm mbm = new MBm();
		mbm.setBmbh(tbm.getBmbh());
		mbm.setBmid(tbm.getBmid());
		
		TGypz bmlx = tbm.getBmlx();
		if(bmlx != null)
		{
			mbm.setBmlx(bmlx.getMc());
		}
		
		mbm.setBmmc(tbm.getBmmc());
		
		return mbm;
	}
	
	public static List<MBm> convert(List<TBm> tlist)
	{
		if(tlist == null)
		{
			return null;
		}
		
		List<MBm> mlist = new ArrayList<MBm>();
		
		for(TBm tbm : tlist)
		{
			MBm mbm = convert(tbm);
			mlist.add(mbm);
		}
		
		return mlist;
	}
}
