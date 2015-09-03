package nju.software.sjy.common;

import java.util.Comparator;

import nju.software.sjy.mapper.MPfkh;
import nju.software.sjy.mapper.MPfkhgz;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;

/**
 * 比较器
 * 
 * @author zceolrj
 *
 */
public class ComparatorTools
{
	
	public static Comparator<MPfkh> PFKH_DESC_COMP = new Comparator<MPfkh>(){

		@Override
		public int compare(MPfkh o1, MPfkh o2)
		{
			return -(o1.getJddf().compareTo(o2.getJddf()));
		}
		
	};
	
	public static Comparator<MPfkhgz> PFKHGZ_DESC_COMP = new Comparator<MPfkhgz>(){

		@Override
		public int compare(MPfkhgz o1, MPfkhgz o2)
		{
			return -(o1.getGzdf().compareTo(o2.getGzdf()));
		}
		
	};
	
	/**
	 * 角色权限比较器
	 */
	public static Comparator<TRole> ROLE_COMP = new Comparator<TRole>(){

		@Override
		public int compare(TRole o1, TRole o2)
		{
			if(o1.getLevels() == null || o2.getLevels() == null)
			{
				return 0;
			}
			
			return -(o1.getLevels().compareTo(o2.getLevels()));
		}
		
	};
	
	/**
	 * 操作范围比较器
	 * 权限高的配置编号小
	 * 全院——1         本庭——2    个人——3
	 */
	public static Comparator<TGypz> FW_COMP = new Comparator<TGypz>(){

		@Override
		public int compare(TGypz o1, TGypz o2)
		{
			Integer pzbh1 = o1.getPzbh();
			Integer pzbh2 = o2.getPzbh();
			
			return -(pzbh1.compareTo(pzbh2));
		}
		
	};
	
	public static Comparator<TUser> USER_COMP = new Comparator<TUser>(){

		@Override
		public int compare(TUser o1, TUser o2)
		{
			if(o1 == null)
			{
				return -1;
			}
			
			if(o2 == null)
			{
				return 1;
			}
			
			TBm bm1 = o1.getBm();
			TBm bm2 = o2.getBm();
			
			TRole role1 = o1.getRole();
			TRole role2 = o2.getRole();
			
			int bmResult = BM_COMP.compare(bm1, bm2);
			if(bmResult == 0)
			{
				int roleResult = -ROLE_COMP.compare(role1, role2);
				
				return roleResult;
			}
			else
			{
				return bmResult;
			}
		}
		
	};
	
	public static Comparator<TBm> BM_COMP = new Comparator<TBm>(){

		@Override
		public int compare(TBm o1, TBm o2)
		{
			if(o1 == null)
			{
				return -1;
			}
			
			if(o2 == null)
			{
				return 1;
			}
			
			int result = o1.getBmmc().compareTo(o2.getBmmc());
			
			return result;
		}
		
	};
	
}
