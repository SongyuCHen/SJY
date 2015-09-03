package nju.software.sjy.convertor;

import java.util.List;

import nju.software.sjy.mapper.MResource;
import nju.software.sjy.mapper.MRole;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TResource;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.util.StringUtil;

public class RoleConvertor 
{
	public static MRole convert(TRole trole, List<TOperation> operationList)
	{
		if(trole == null)
		{
			return null;
		}
		
		MRole mrole = new MRole();
		mrole.setRolebh(trole.getRolebh());
		mrole.setRolename(trole.getRolename());
		mrole.setLevels(trole.getLevels());
		mrole.setOperationList(operationList);
		
		StringBuffer sb = new StringBuffer();
		if(operationList != null)
		{
			for(TOperation operation : operationList)
			{
				String name = operation.getName();
				TGypz range = operation.getRange();
				String rangeStr = "";
				if(range != null)
				{
					rangeStr = range.getMc();
				}
				sb.append(name).append(":").append(rangeStr).append(",");
			}
		}
		if(sb.length()>0)
		{
			sb.deleteCharAt(sb.length()-1);
		}
		
		mrole.setOperations(sb.toString());
		
		return mrole;
	}
	
	public static MRole convert(TRole trole, List<TOperation> operationList, List<TResource> resList)
	{
		if(trole == null)
		{
			return null;
		}
		
		MRole mrole = new MRole();
		mrole.setRolebh(trole.getRolebh());
		mrole.setRolename(trole.getRolename());
		mrole.setLevels(trole.getLevels());
		mrole.setOperationList(operationList);
		
		StringBuffer operationSb = new StringBuffer();
		if(operationList != null)
		{
			for(TOperation operation : operationList)
			{
				String name = operation.getName();
				TGypz range = operation.getRange();
				String rangeStr = "";
				if(range != null)
				{
					rangeStr = range.getMc();
				}
				if(StringUtil.isNullOrNone(rangeStr))
				{
					operationSb.append(name).append(" ");
				}
				else
				{
					operationSb.append(name).append(":").append(rangeStr).append(" ");
				}
			}
		}
		if(operationSb.length()>0)
		{
			operationSb.deleteCharAt(operationSb.length()-1);
		}
		
		mrole.setOperations(operationSb.toString());
		
		List<MResource> mResList = ResourceConvertor.convert(resList);
		StringBuffer simpleSb = new StringBuffer();
		StringBuffer detailSb = new StringBuffer();
		if(mResList != null)
		{
			for(MResource mres : mResList)
			{
				String resname = mres.getResname();
				
				simpleSb.append(resname).append(" ");
				detailSb.append(resname).append(":");
				
				for(MResource child : mres.getChildrenList())
				{
					detailSb.append(child.getResname()).append(",");
				}
				detailSb.deleteCharAt(detailSb.length()-1).append(" ");
			}
			if(simpleSb.length()>0)
			{
				simpleSb.deleteCharAt(simpleSb.length() - 1);
			}
			if(detailSb.length()>0)
			{
				detailSb.deleteCharAt(detailSb.length() - 1);
			}
		}
		mrole.setSimpleReses(simpleSb.toString());
		mrole.setDetailReses(detailSb.toString());
		
		return mrole;
	}
}
