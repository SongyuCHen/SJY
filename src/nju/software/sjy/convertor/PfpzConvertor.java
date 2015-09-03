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
	
	public static List<MPfpz> convert(List<TPfpz> tlist, List<TGypz> pzlist)
	{
		List<MPfpz> mlist = new ArrayList<MPfpz>();
		if(tlist == null)
		{
			return mlist;
		}
		
		for(TPfpz tPfpz : tlist)
		{
			MPfpz mpfpz = convert(tPfpz);
			mlist.add(mpfpz);
		}
		
		if(pzlist == null || tlist.size() == pzlist.size())
		{
			return mlist;
		}
		
		if(tlist.size() < pzlist.size())
		{
			for(TGypz gypz : pzlist)
			{
				if(!contains(tlist, gypz))
				{
					MPfpz mpfpz = convert(gypz);
					mlist.add(mpfpz);
				}
			}
		}
		
		return mlist;
	}
	
	public static MPfpz convert(TGypz gypz)
	{
		MPfpz mpfpz = new MPfpz();
		if(gypz == null)
		{
			return mpfpz;
		}
		
		mpfpz.setLx(gypz.getLx());
		mpfpz.setMc(gypz.getMc());
		mpfpz.setFs(0);
		
		return mpfpz;
	}
	
	public static boolean contains(List<TPfpz> tlist, TGypz gypz)
	{
		if(tlist == null)
		{
			return false;
		}
		
		for(TPfpz pfpz : tlist)
		{
			if(pfpz.getGz().getMc().equals(gypz.getMc()))
			{
				return true;
			}
		}
		
		return false;
	}
}
