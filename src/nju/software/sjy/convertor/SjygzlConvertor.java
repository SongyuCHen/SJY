package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MSjygzl;
import nju.software.sjy.mapper.MSjygzlId;
import nju.software.sjy.model.da.ViewDajgSsfzxx;
import nju.software.sjy.model.tdh.SjygzlAjxx;
import nju.software.sjy.model.tdh.SjygzlBlxq;
import nju.software.sjy.model.tdh.SjygzlKtxq;
import nju.software.sjy.model.tdh.SjygzlSdxq;
import nju.software.sjy.model.xy.LocalBlxq;
import nju.software.sjy.model.xy.LocalKtxq;
import nju.software.sjy.model.xy.LocalSdxq;
import nju.software.sjy.model.xy.LocalZdjz;
import nju.software.sjy.util.DateUtil;

public class SjygzlConvertor
{
//	public static MSjygzl convertAjxx(SjygzlAjxx ajxx, VSsjcbmgdWjys vw)
//	{
//		MSjygzl ms = new MSjygzl();
//		ms.setAttr1(ajxx.getAh());
//		ms.setAttr2(vw.getJzlb());
//		if(vw.getWjys()!=null)
//			ms.setAttr3(vw.getWjys().toString());
//		else
//			ms.setAttr3("0");
//		ms.setAttr4(ajxx.getGdjsrq());
//		
//		return ms;
//	}
	
	public static MSjygzl convertAjxx(SjygzlAjxx ajxx, ViewDajgSsfzxx da)
	{
		MSjygzl ms = new MSjygzl();
		ms.setAttr1(ajxx.getAh());
		if(da.getZjys()!=null)
			ms.setAttr2(da.getZjys().toString());
		else
			ms.setAttr2("0");
		if(da.getFjys()!=null)
			ms.setAttr3(da.getFjys().toString());
		else
			ms.setAttr3("0");
		ms.setAttr4(ajxx.getGdjsrq());
		
		return ms;
	}
	
	public static MSjygzl convertBlxq(SjygzlBlxq blxq)
	{
		MSjygzl ms = new MSjygzl();
		if(blxq!=null){
			ms.setAttr1(blxq.getAh());
			ms.setAttr2(blxq.getId().getBlmc());
			ms.setAttr3(blxq.getWjzs().toString());
			String rq = DateUtil.getFormatStr(blxq.getId().getZzrq());
			ms.setAttr4(rq);
		}
		
		return ms;
	}
	
	public static MSjygzl convertLocalBlxq(LocalBlxq blxq)
	{
		MSjygzl ms = new MSjygzl();
		MSjygzlId id = new MSjygzlId();
		if(blxq!=null){
			id.setAhdm(blxq.getId().getAhdm());
			id.setFydm(blxq.getId().getFydm());
			id.setAttr1(blxq.getId().getXh());
			ms.setId(id);
			ms.setAttr1(blxq.getAh());
			ms.setAttr2(blxq.getBlmc());
			ms.setAttr3(blxq.getWjzs().toString());
			String rq = DateUtil.getFormatStr(blxq.getZzrq());
			ms.setAttr4(rq);
			ms.setAttr5(blxq.getSjymc());
			ms.setAttr6(blxq.getXzsjymc());
		}
		
		return ms;
	}
	
	public static MSjygzl convertKtxq(SjygzlKtxq ktxq)
	{
		MSjygzl ms = new MSjygzl();
		if(ktxq!=null){
			ms.setAttr1(ktxq.getAh());
			ms.setAttr2(ktxq.getDd());
			ms.setAttr3(ktxq.getId().getKtrq());
			ms.setAttr4(ktxq.getId().getKssj());
			String dateRange = ktxq.getId().getKssj() + "-" + ktxq.getJssj();
			ms.setAttr4(dateRange);
		}
		
		return ms;
	}
	
	public static MSjygzl convertLocalKtxq(LocalKtxq ktxq){
		MSjygzl ms = new MSjygzl();
		MSjygzlId id = new MSjygzlId();
		if(ktxq!=null){
			id.setAhdm(ktxq.getId().getAhdm());
			id.setFydm(ktxq.getId().getFydm());
			id.setAttr1(ktxq.getId().getKtrq());
			id.setAttr2(ktxq.getId().getKssj());
			ms.setId(id);
			ms.setAttr1(ktxq.getAh());
			ms.setAttr2(ktxq.getDd());
			ms.setAttr3(ktxq.getId().getKtrq());
			ms.setAttr4(ktxq.getId().getKssj());
			String dateRange = ktxq.getId().getKssj() + "-" + ktxq.getJssj();
			ms.setAttr4(dateRange);
			ms.setAttr5(ktxq.getSjymc());
			ms.setAttr6(ktxq.getXzsjymc());
		}
		
		return ms;
	}
	
	public static MSjygzl convertSdxq(SjygzlSdxq sdxq)
	{
		MSjygzl ms = new MSjygzl();
		if(sdxq!=null){
			ms.setAttr1(sdxq.getAh());
			ms.setAttr2(sdxq.getSddsr());
			ms.setAttr3(sdxq.getSddz());
			if(sdxq.getSdrq().trim().equals("") ||sdxq.getSdrq() == null)
				ms.setAttr4(sdxq.getYsdrq());
			else
				ms.setAttr4(sdxq.getSdrq());
		}
		
		return ms;
	}
	
	public static MSjygzl convertLocalSdxq(LocalSdxq sdxq)
	{
		MSjygzl ms = new MSjygzl();
		MSjygzlId id = new MSjygzlId();
		if(sdxq!=null){
			id.setAhdm(sdxq.getId().getAhdm());
			id.setFydm(sdxq.getId().getFydm());
			id.setAttr1(sdxq.getId().getSdrq());
			id.setAttr2(sdxq.getId().getSddsr());
			ms.setId(id);
			ms.setAttr1(sdxq.getAh());
			ms.setAttr2(sdxq.getId().getSddsr());
			ms.setAttr3(sdxq.getSddz());
			if(sdxq.getId().getSdrq().trim().equals("") ||sdxq.getId().getSdrq() == null)
				ms.setAttr4(sdxq.getYsdrq());
			else
				ms.setAttr4(sdxq.getId().getSdrq());
			ms.setAttr5(sdxq.getSjymc());
			ms.setAttr6(sdxq.getXzsjymc());
		}
		
		return ms;
	}
	
	public static MSjygzl convertLocalZdjz(LocalZdjz zdjz)
	{
		MSjygzl ms = new MSjygzl();
		MSjygzlId id = new MSjygzlId();
		if(zdjz!=null){
			id.setAhdm(zdjz.getId().getAhdm());
			id.setFydm(zdjz.getId().getFydm());
			ms.setId(id);
			ms.setAttr1(zdjz.getAh());
			if(zdjz.getZjys()!=null)
				ms.setAttr2(zdjz.getZjys().toString());
			else
				ms.setAttr2("0");
			if(zdjz.getFjys()!=null)
				ms.setAttr3(zdjz.getFjys().toString());
			else
				ms.setAttr3("0");
			ms.setAttr4(zdjz.getZzsj());
			ms.setAttr5(zdjz.getSjymc());
			ms.setAttr6(zdjz.getXzsjymc());
		}
		
		return ms;
	}
	public static List<MSjygzl> convertBlxq(List<SjygzlBlxq> blxqList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(blxqList != null)
		{
			for(SjygzlBlxq blxq : blxqList)
			{
				MSjygzl ms = convertBlxq(blxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
	
	public static List<MSjygzl> convertLocalBlxq(List<LocalBlxq> blxqList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(blxqList != null)
		{
			for(LocalBlxq blxq : blxqList)
			{
				MSjygzl ms = convertLocalBlxq(blxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
	
	public static List<MSjygzl> convertKtxq(List<SjygzlKtxq> ktxqList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(ktxqList != null)
		{
			for(SjygzlKtxq ktxq : ktxqList)
			{
				MSjygzl ms = convertKtxq(ktxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
	
	public static List<MSjygzl> convertLocalKtxq(List<LocalKtxq> ktxqList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(ktxqList != null)
		{
			for(LocalKtxq ktxq : ktxqList)
			{
				MSjygzl ms = convertLocalKtxq(ktxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
	
	public static List<MSjygzl> convertSdxq(List<SjygzlSdxq> sdxqList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(sdxqList != null)
		{
			for(SjygzlSdxq sdxq : sdxqList)
			{
				MSjygzl ms = convertSdxq(sdxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
	
	public static List<MSjygzl> convertLocalSdxq(List<LocalSdxq> sdxqList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(sdxqList != null)
		{
			for(LocalSdxq sdxq : sdxqList)
			{
				MSjygzl ms = convertLocalSdxq(sdxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
	
	public static List<MSjygzl> convertLocalZdjz(List<LocalZdjz> zdjzList)
	{
		List<MSjygzl> mlist = new ArrayList<MSjygzl>();
		if(zdjzList != null)
		{
			for(LocalZdjz sdxq : zdjzList)
			{
				MSjygzl ms = convertLocalZdjz(sdxq);
				mlist.add(ms);
			}
		}
		
		return mlist;
	}
}
