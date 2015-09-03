package nju.software.sjy.convertor;

import nju.software.sjy.mapper.MGypz;
import nju.software.sjy.mapper.MJfkfxm;
import nju.software.sjy.model.xy.TJfkfxm;

public class JfkfxmConvertor
{
	public static MJfkfxm convertKfxm(TJfkfxm jfkfxm)
	{
		if(jfkfxm == null)
		{
			return null;
		}
		
		MJfkfxm xm = new MJfkfxm();
		
		MGypz lb = GypzConvertor.convertGypz(jfkfxm.getLb());
		
		xm.setBh(jfkfxm.getBh());
		xm.setFs(jfkfxm.getFs());
		xm.setMc(jfkfxm.getMc());
		xm.setLb(lb);
		
		return xm;
	}
}
