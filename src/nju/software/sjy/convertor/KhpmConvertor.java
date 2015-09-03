package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MKhpm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TPfkh;
import nju.software.sjy.model.xy.TUser;

public class KhpmConvertor
{
	public static MKhpm convert(TPfkh tpfkh)
	{
		if(tpfkh == null)
		{
			return null;
		}
		
		MKhpm mkhpm = new MKhpm();
		TUser user = tpfkh.getUser();
		TBm bm = user.getBm();
		TGypz bmlx = bm.getBmlx();
		String bmlxmc = null;
		if(bmlx != null)
		{
			bmlxmc = bmlx.getMc();
		}
		
		mkhpm.setBh(tpfkh.getBh());
		mkhpm.setBmmc(bm.getBmmc());
		mkhpm.setBmlxmc(bmlxmc);
		mkhpm.setRyxm(user.getXm());
		mkhpm.setNf(tpfkh.getNf());
		mkhpm.setJd(tpfkh.getJd());
		mkhpm.setJddf(tpfkh.getJddf());
		
		return mkhpm;
	}
	
	public static List<MKhpm> convert(List<TPfkh> tlist)
	{
		if(tlist == null || tlist.isEmpty())
		{
			return new ArrayList<MKhpm>();
		}
		
		List<MKhpm> mlist = new ArrayList<MKhpm>();
		for(TPfkh tpfkh : tlist)
		{
			MKhpm mkhpm = convert(tpfkh);
			mlist.add(mkhpm);
		}
		
		return mlist;
	}
}
