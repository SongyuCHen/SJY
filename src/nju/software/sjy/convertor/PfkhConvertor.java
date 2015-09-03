package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MPfkh;
import nju.software.sjy.mapper.MPfkhgz;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TPfkhgz;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.util.JsonUtil;
import nju.software.sjy.util.NumberUtil;

public class PfkhConvertor
{
	public static MPfkh convert(TPfkh tpfkh)
	{
		if(tpfkh == null)
		{
			return null;
		}
		
		MPfkh mpfkh = new MPfkh();
		TUser user = tpfkh.getUser();
		
		mpfkh.setBh(tpfkh.getBh());
		mpfkh.setRyxm(user.getXm());
		mpfkh.setBmmc(user.getBm().getBmmc());
		mpfkh.setNf(tpfkh.getNf());
		mpfkh.setJd(tpfkh.getJd());
		mpfkh.setJddf(tpfkh.getJddf());
		
		return mpfkh;
	}
	
	public static MPfkh convert(TPfkh tpfkh, List<MPfkhgz> pfkhgzList)
	{
		if(tpfkh == null)
		{
			return null;
		}
		
		MPfkh mpfkh = new MPfkh();
		TUser user = tpfkh.getUser();
		
		mpfkh.setBh(tpfkh.getBh());
		mpfkh.setRyxm(user.getXm());
		mpfkh.setBmmc(user.getBm().getBmmc());
		mpfkh.setNf(tpfkh.getNf());
		mpfkh.setJd(tpfkh.getJd());
		mpfkh.setJddf(tpfkh.getJddf());
		mpfkh.setPfkhgzList(pfkhgzList);
		
		String pfkhgzJsonStr = JsonUtil.getJsonStr(pfkhgzList);
		mpfkh.setPfkhgzJsonStr(pfkhgzJsonStr);
		
		return mpfkh;
	}
	
	public static List<MPfkh> convert(List<TPfkh> tlist)
	{
		if(tlist == null)
		{
			return null;
		}
		
		List<MPfkh> mlist = new ArrayList<MPfkh>();
		for(TPfkh tpfkh : tlist)
		{
			MPfkh mpfkh = convert(tpfkh);
			mlist.add(mpfkh);
		}
		
		return mlist;
	}
	
	public static List<MPfkhgz> convertTPfkhgz(List<TPfkhgz> tlist)
	{
		if(tlist == null || tlist.isEmpty())
		{
			return new ArrayList<MPfkhgz>();
		}
		
		List<MPfkhgz> mlist = new ArrayList<MPfkhgz>();
		for(TPfkhgz tpfkhgz : tlist)
		{
			MPfkhgz mpfkhgz = convertTPfkhgz(tpfkhgz);
			mlist.add(mpfkhgz);
		}
		
		return mlist;
	}
	
	public static MPfkhgz convertTPfkhgz(TPfkhgz tpfkhgz)
	{
		if(tpfkhgz == null)
		{
			return null;
		}
		
		MPfkhgz mpfkhgz = new MPfkhgz();
		mpfkhgz.setBh(tpfkhgz.getBh());
		
		TPfkh pfkh = tpfkhgz.getPfkh();
		if(pfkh != null)
		{
			mpfkhgz.setRyxm(pfkh.getUser().getXm());
			mpfkhgz.setBmmc(pfkh.getUser().getBm().getBmmc());
			mpfkhgz.setNf(pfkh.getNf());
			mpfkhgz.setJd(pfkh.getJd());
		}
		
		TGypz gz = tpfkhgz.getGz();
		if(gz != null)
		{
			mpfkhgz.setGzmc(gz.getMc());
		}
		
		double gzdf = NumberUtil.formatDouble(tpfkhgz.getGzdf());
		
		mpfkhgz.setGzdf(gzdf);
		
		return mpfkhgz;
	}
}
