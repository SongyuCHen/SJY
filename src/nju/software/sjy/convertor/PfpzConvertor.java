package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MPfpz;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfpz;

public class PfpzConvertor 
{
	public static List<MPfpz> convert(List<TPfpz> tlist)
	{
		List<MPfpz> mlist = new ArrayList<MPfpz>();
		if(tlist != null)
		{
			for(TPfpz tpfpz : tlist)
			{
				MPfpz mpfpz = convert(tpfpz);
				mlist.add(mpfpz);
			}
		}
		
		return mlist;
	}
	
	public static MPfpz convert(TPfpz tpfpz)
	{
		if(tpfpz == null)
		{
			return null;
		}
		
		MPfpz mpfpz = new MPfpz();
		TGypz gz = tpfpz.getGz();
		mpfpz.setBh(tpfpz.getBh());
		mpfpz.setMc(gz.getMc());
		mpfpz.setLx(gz.getLx());
		mpfpz.setFs(tpfpz.getFs());
		
		return mpfpz;
	}
}
