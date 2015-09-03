package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MJfkfxm;
import nju.software.sjy.mapper.MJfqx;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TUser;

public class JfqxConvertor
{
	public static List<MJfqx> convertJfqx(List<TJfqx> tjfqxList)
	{
		List<MJfqx> mjfqxList = new ArrayList<MJfqx>();
		for(TJfqx tjfqx : tjfqxList)
		{
			MJfqx mjfqx = convertJfqx(tjfqx);
			
			mjfqxList.add(mjfqx);
		}
		
		return mjfqxList;
	}
	
	/**
	 * 注意jfqx.user.bm未必等于jfqx.bm
	 * jfqx.bm可能是某个人以前所在的部门，而jfqx.user.bm是某人现在所在的部门
	 * 
	 * @param tjfqx
	 * @return
	 */
	public static MJfqx convertJfqx(TJfqx tjfqx)
	{
		MJfqx mjfqx = new MJfqx();
		TUser user = tjfqx.getUser();
		TBm bm = tjfqx.getBm();
		TGypz zt = tjfqx.getZt();
		TJfkfxm tjfxm = tjfqx.getJfxm();
		MJfkfxm mjfxm = JfkfxmConvertor.convertKfxm(tjfxm);
		
		mjfqx.setBh(tjfqx.getBh());
		mjfqx.setRyxm(user.getXm());
		mjfqx.setRybh(user.getRybh());
		mjfqx.setBmmc(bm.getBmmc());
		mjfqx.setBmbh(bm.getBmbh());
		mjfqx.setHdsj(tjfqx.getHdsj());
		mjfqx.setNf(tjfqx.getNf());
		mjfqx.setYf(tjfqx.getYf());
		mjfqx.setJfcs(tjfqx.getJfcs());
		mjfqx.setZt(zt.getMc());
		mjfqx.setComment(tjfqx.getComment());
		mjfqx.setJfxm(mjfxm);
		
		return mjfqx;
	}
	
	//public 
}
