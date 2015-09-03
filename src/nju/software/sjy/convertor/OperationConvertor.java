package nju.software.sjy.convertor;

import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.common.Constants;
import nju.software.sjy.mapper.MOperation;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;

public class OperationConvertor
{
	public static MOperation convertSimply(TOperation top)
	{
		if(top == null)
		{
			return null;
		}
		
		MOperation mop = new MOperation();
		TGypz range = top.getRange();
		
		mop.setBh(top.getBh());
		mop.setName(top.getName());
		mop.setAlias(top.getAlias());
		mop.setStatus(top.getStatus());
		
		if(range != null)
		{
			mop.setRange(range.getMc());
		}
		
		return mop;
	}
	
	public static List<MOperation> convertSimply(List<TOperation> tlist)
	{
		List<MOperation> mlist = new ArrayList<MOperation>();
		
		if(tlist != null)
		{
			for(TOperation top : tlist)
			{
				MOperation mop = convertSimply(top);
				
				mlist.add(mop);
			}
		}
		
		return mlist;
	}
	
	/**
	 * 将操作转换为列表树形结构
	 * 如：查看-全院、查看-本庭、查看-个人  to  查看{全院、本庭、个人}
	 * 
	 * @param tlist
	 * @return
	 */
	public static List<MOperation> convertToTree(List<TOperation> tlist)
	{
		List<MOperation> mlist = new ArrayList<MOperation>();
		if(tlist != null)
		{
			for(TOperation top : tlist)
			{
				String topName = top.getName();
				boolean containsName = false;
				for(MOperation mop : mlist)
				{
					String mopName = mop.getName();
					if(mopName.equals(topName))
					{
						containsName = true;
						mop.getRangeList().add(top.getRange().getMc());
						break;
					}
				}
				if(!containsName)
				{
					MOperation mop = convertToTree(top);
					
					mlist.add(mop);
				}
			}
		}
		
		return mlist;
	}
	
	public static MOperation convertToTree(TOperation top)
	{
		if(top == null)
		{
			return null;
		}
		
		MOperation mop = new MOperation();
		mop.setName(top.getName());
		mop.setAlias(top.getAlias());
		
		TGypz range = top.getRange();
		if(range != null)
		{
			mop.getRangeList().add(range.getMc());
		}
		
		return mop;
	}
	
	public static List<TOperation> convertToButton(List<TOperation> tlist)
	{
		List<TOperation> operationList = new ArrayList<TOperation>();
		
		if(tlist != null)
		{
			/* 必须先有查看的权限，然后才能有其他权限 */
			boolean hasSearchPermit = false;
			for(TOperation top : tlist)
			{
				if(top.getName().equals(Constants.SEARCH_CN))
				{
					hasSearchPermit = true;
					break;
				}
			}
			
			if(!hasSearchPermit)
			{
				return operationList;
			}
			
			/* 
			 * 转化为操作按钮 
			 * 1.name相同,range不同的合并
			 * 2.操作按钮不显示查询
			 * 3.将 "编辑" 分解为  "增加","修改","删除"
			 */
			for(TOperation top : tlist)
			{
				String name = top.getName();
				
				if(!containsName(operationList, top))
				{
					if(name.equals(Constants.SEARCH_CN))
					{
						continue;
					}
					else if(name.equals(Constants.EDIT_CN))
					{
						// add edit delete
						TOperation addOperation = new TOperation();
						addOperation.setName(Constants.ADD_CN);
						addOperation.setAlias(Constants.ADD_EN);
						
						TOperation editOperation = new TOperation();
						editOperation.setName(Constants.EDIT_CN);
						editOperation.setAlias(Constants.EDIT_EN);
						
						TOperation deleteOperation = new TOperation();
						deleteOperation.setName(Constants.DELETE_CN);
						deleteOperation.setAlias(Constants.DELETE_EN);
						
						operationList.add(addOperation);
						operationList.add(editOperation);
						operationList.add(deleteOperation);
					}
					else
					{
						TOperation operation = new TOperation();
						operation.setName(top.getName());
						operation.setAlias(top.getAlias());
						
						operationList.add(operation);
					}
				}
			}
		}
		
		return operationList;
	}
	
	private static boolean containsName(List<TOperation> tlist, TOperation operation)
	{
		if(tlist == null || tlist.isEmpty())
		{
			return false;
		}
		
		for(TOperation top : tlist)
		{
			if(top.getName().equals(operation.getName()))
			{
				return true;
			}
		}
		
		return false;
	}
	
}
