package nju.software.sjy.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.AuthorityService;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.ViewService;
import nju.software.sjy.util.DateUtil;

@Service
public class ViewServiceImpl implements ViewService
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private RoleService roleService;
	
	public List<TBm> getAllBmList()
	{
		List<TBm> bmList = bmService.getAllBm();
		
		return bmList;
	}
	
	public List<TBm> getAllWrappedBmList()
	{
		List<TBm> bmList = getAllBmList();
		bmList.add(0, new TBm(0, Constants.SYBM));
		
		return bmList;
	}
	
	/**
	 * 
	 */
	@Override
	public List<TBm> getBmList()
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TBm> bmList = authorityService.getAccessBmByUserExcludeQtbm(user);
		
		return bmList;
	}
	
	public List<TBm> getWrappedBmList()
	{
		List<TBm> bmList = getBmList();
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TRole role = user.getRole();
		List<TOperation> oplist = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		if(oplist != null)
		{
			for(TOperation operation : oplist)
			{
				TGypz range = operation.getRange();
				if(range != null && range.getMc().equals(Constants.QY))
				{
					bmList.add(0, new TBm(0, Constants.SYBM));
					break;
				}
			}
		}
		
		return bmList;
	}

	@Override
	public List<String> getBmlxmcList()
	{
		List<String> bmlxmcList = gypzService.getMcByLx(Constants.BMLX);
		
		return bmlxmcList;
	}
	
	public List<String> getWrappedBmlxmcList()
	{
		List<String> bmlxmcList = getBmlxmcList();
		bmlxmcList.add(0, Constants.SYBM);
		
		return bmlxmcList;
	}

	@Override
	public List<TUser> getUserList(List<TBm> bmList)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TBm bm = null;
		if(bmList != null && !bmList.isEmpty())
		{
			bm = bmList.get(0);
		}
		
		List<TUser> userList = authorityService.getAccessNeedEvaluationUserByUserBm(user, bm);
		
		return userList;
	}
	
	public List<TUser> getUserList(String bmmc, List<TBm> bmList)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		
		TBm bm = null;
		if(bmmc != null)
		{
			bm = bmService.getBmByBmmc(bmmc);
		}
		else
		{
			if(bmList != null && !bmList.isEmpty())
			{
				bm = bmList.get(0);
			}
		}
		List<TUser> userList = authorityService.getAccessNeedEvaluationUserByUserBm(user, bm);
		
		return userList;
	}
	
	public List<TRole> getAllRoleList()
	{
		List<TRole> roleList = roleService.getAllRoles();
		
		return roleList;
	}
	
	public List<TRole> getAllWrappedRoleList()
	{
		List<TRole> roleList = getAllRoleList();
		roleList.add(0, new TRole(0, Constants.SYJS));
		
		return roleList;
	}
	
	public String getBmmc(String bmmc)
	{
		if(bmmc == null)
		{
			TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
			TBm bm = user.getBm();
			List<TBm> bmList = getWrappedBmList();
			
			/* 如果用户自身的部门在部门列表中，则部门名称等于用户部门的部门名称 */
			if(bmList != null && bmList.contains(bm))
			{
				bmmc = bm.getBmmc();
			}
			/* 如果不在，则取部门列表第一个部门的部门名称 */
			else if(bmList != null && !bmList.isEmpty())
			{
				bmmc = bmList.get(0).getBmmc();
			}
		}
		
		return bmmc;
	}
	
	public int getBmbh(int bmbh)
	{
		if(bmbh == -1)
		{
			TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
			TBm bm = user.getBm();
			List<TBm> bmList = getWrappedBmList();
			
			/* 如果用户自身的部门在部门列表中，则部门编号等于用户部门的部门编号 */
			if(bmList != null && bmList.contains(bm))
			{
				bmbh = bm.getBmbh();
			}
			/* 如果不在，则取部门列表第一个部门的部门名称 */
			else if(bmList != null && !bmList.isEmpty())
			{
				bmbh = bmList.get(0).getBmbh();
			}
		}
		
		return bmbh;
	}

	@Override
	public int[] getYears()
	{
		return DateUtil.getPastYears();
	}

	@Override
	public int[] getMonths()
	{
		return Constants.MONTHS;
	}

	@Override
	public int[] getQuarters()
	{
		return Constants.QUARTERS;
	}

	@Override
	public int getYear(int year)
	{
		return year == 0 ? DateUtil.getCurrentYear() : year;
	}

	@Override
	public int getMonth(int month)
	{
		return month == 0 ? DateUtil.getCurrentMonth() : month;
	}
	
	public int getQuarter(int quarter)
	{
		return quarter == 0 ? DateUtil.getCurrentQuarter() : quarter;
	}

	@Override
	public List<TOperation> getOperationList()
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		List<TOperation> operationList =  authorityService.getAccessOperationByUser(user);
		
		return operationList;
	}

	@Override
	public List<TGypz> getKhzbList()
	{
		List<TGypz> gzList = gypzService.getGypzByLx(Constants.GZ);
		
		return gzList;
	}

	@Override
	public List<TGypz> getWrappedKhzbList()
	{
		List<TGypz> gzList = getKhzbList();
		gzList.add(0, new TGypz(0, 0, Constants.GZ, Constants.SYZB));
		
		return gzList;
	}

	@Override
	public String getKhzbmc(String khzbmc)
	{
		return khzbmc == null ? Constants.SYZB : khzbmc;
	}

	@Override
	public String getBmlxmc(String bmlxmc)
	{
		return bmlxmc == null ? Constants.SYBM : bmlxmc;
	}

	@Override
	public String getRolename(String rolename)
	{
		return rolename == null ? Constants.SYJS : rolename;
	}

	@Override
	public String getSimpleBmmc(String bmmc)
	{
		return bmmc == null ? Constants.SYBM : bmmc;
	}

	@Override
	public String getStartYearMonth(String startDate)
	{
		if(startDate == null)
		{
			startDate = DateUtil.getCurrentYearMonth();
		}
		return startDate;
	}

	@Override
	public String getEndYearMonth(String endDate)
	{
		if(endDate == null)
		{
			endDate = DateUtil.getCurrentYearMonth();
		}
		return endDate;
	}
	
	public String getStartDate(String startDate)
	{
		if(startDate == null)
		{
			startDate = DateUtil.getStartDateOfCurMonth();
		}
		
		return startDate;
	}
	
	public String getEndDate(String endDate)
	{
		if(endDate == null)
		{
			endDate = DateUtil.getCurDate();
		}
		
		return endDate;
	}
}
