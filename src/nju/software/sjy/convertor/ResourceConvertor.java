package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.common.Constants;
import nju.software.sjy.mapper.MResource;
import nju.software.sjy.model.xy.TResource;

public class ResourceConvertor
{
	public static MResource convert(TResource tres)
	{
		if(tres == null)
		{
			return null;
		}
		
		MResource mres = new MResource();
		mres.setResbh(tres.getResbh());
		mres.setResname(tres.getResname());
		mres.setType(tres.getType());
		mres.setUrl(tres.getUrl());
		
		return mres;
	}
	
	/**
	 * 转换成一级导航 + 二级导航
	 * 
	 * @param tlist
	 * @return
	 */
	public static List<MResource> convert(List<TResource> tlist)
	{
		if(tlist == null)
		{
			return null;
		}
		
		List<MResource> mlist = new ArrayList<MResource>();
		
		/* 先添加一级导航 */
		for(TResource tres : tlist)
		{
			if(tres.getType().equals(Constants.DH))
			{
				MResource mres = convert(tres);
				
				mlist.add(mres);
			}
		}
		
		/* 再添加二级导航 */
		for(TResource tres : tlist)
		{
			for(MResource mres : mlist)
			{
				/* 二级导航的type等于一级导航的name */
				if(tres.getType().equals(mres.getResname()))
				{
					List<MResource> childrenList = mres.getChildrenList();
					MResource childMres = convert(tres);
					
					childrenList.add(childMres);
				}
			}
		}
		
		return mlist;
	}
}
