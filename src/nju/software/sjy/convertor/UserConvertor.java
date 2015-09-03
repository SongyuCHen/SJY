package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.mapper.MUser;
import nju.software.sjy.model.xy.TUser;

public class UserConvertor 
{
	public static List<MUser> convert(List<TUser> tlist)
	{
		List<MUser> mlist = new ArrayList<MUser>();
		if(tlist != null)
		{
			for(TUser tuser : tlist)
			{
				MUser muser = convert(tuser);
				
				mlist.add(muser);
			}
		}
		
		return mlist;
	}
	
	public static MUser convert(TUser tuser)
	{
		if(tuser == null)
		{
			return null;
		}
		
		MUser muser = new MUser();
		
		muser.setRybh(tuser.getRybh());
		muser.setXm(tuser.getXm());
		muser.setBmmc(tuser.getBm().getBmmc());
		muser.setUsername(tuser.getUsername());
		muser.setPassword(tuser.getPassword());
		muser.setYhsf(tuser.getYhsf());
		muser.setRolename(tuser.getRole().getRolename());
		
		return muser;
	}
}
