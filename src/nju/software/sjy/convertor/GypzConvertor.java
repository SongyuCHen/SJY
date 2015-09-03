package nju.software.sjy.convertor;

import nju.software.sjy.mapper.MGypz;
import nju.software.sjy.model.xy.TGypz;

public class GypzConvertor
{
	public static MGypz convertGypz(TGypz tgypz)
	{
		if(tgypz == null)
		{
			return null;
		}
		
		MGypz mgypz = new MGypz();
		mgypz.setBh(tgypz.getBh());
		mgypz.setPzbh(tgypz.getPzbh());
		mgypz.setLx(tgypz.getLx());
		mgypz.setMc(tgypz.getMc());
		
		return mgypz;
	}
}
