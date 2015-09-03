package nju.software.sjy.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nju.software.sjy.common.Constants;
import nju.software.sjy.common.SessionKey;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;
import nju.software.sjy.service.BmService;
import nju.software.sjy.service.GypzService;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.JfqxService;
import nju.software.sjy.service.KfqxService;
import nju.software.sjy.service.RoleService;
import nju.software.sjy.service.ViewGztbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewGztbServiceImpl implements ViewGztbService
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private JfqxService jfqxService;
	
	@Autowired
	private KfqxService kfqxService;
	
	@Autowired
	private GypzService gypzService;
	
	@Autowired
	private BmService bmService;
	
	@Autowired
	private RoleService roleService;

	@Override
	public List<TGzsj> getGzsjByBmmcDateRange(String bmmc, String startDate, String endDate)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TRole role = user.getRole();
		
		List<TGzsj> tlist = null;
		
		/* 查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			if(range.equals(Constants.GR))
			{
				TBm bm = bmService.getBmByBmmc(bmmc);
				tlist = gzsjService.getGzsjByUserBmDateRange(user, bm, startDate, endDate);
			}
			else
			{
				if(bmmc != null && bmmc.equals(Constants.SYBM))
				{
					TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
					tlist = gzsjService.getGzsjByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
				}
				else
				{
					TBm bm = bmService.getBmByBmmc(bmmc);
					tlist = gzsjService.getGzsjByBmDateRange(bm, startDate, endDate);
				}
			}
		}
		
		return tlist;
	}

	@Override
	public List<TJfqx> getJfqxByBmbhDateRange(int bmbh, String startDate,
			String endDate)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TRole role = user.getRole();
		
		List<TJfqx> tlist = null;
		
		/* 查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			if(range.equals(Constants.GR))
			{
				tlist = jfqxService.getJfqxByUserDateRange(user, startDate, endDate);
			}
			else
			{
				if(bmbh == 0)
				{
					TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
					tlist = jfqxService.getJfqxByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
				}
				else
				{
					TBm bm = bmService.getBmByBmbh(bmbh);
					tlist = jfqxService.getJfqxByBmDateRange(bm, startDate, endDate);
				}
			}
		}
		
		return tlist;
	}

	@Override
	public List<TKfqx> getKfqxByGmbhDateRange(int bmbh, String startDate,
			String endDate)
	{
		TUser user = (TUser)request.getSession().getAttribute(SessionKey.SESSION_USER);
		TRole role = user.getRole();
		
		List<TKfqx> tlist = null;
		
		/* 查询出该角色拥有的查询范围 */
		List<TOperation> operationList = roleService.getOperationByRoleOperationname(role, Constants.SEARCH_CN);
		if(operationList != null && !operationList.isEmpty())
		{
			String range = operationList.get(0).getRange().getMc();
			if(range.equals(Constants.GR))
			{
				tlist = kfqxService.getKfqxByUserDateRange(user, startDate, endDate);
			}
			else
			{
				if(bmbh == 0)
				{
					TGypz qtbmlx = gypzService.getGypzByLxMc(Constants.BMLX, Constants.QT);
					tlist = kfqxService.getKfqxByDateRangeExcludeQtbmlx(startDate, endDate, qtbmlx);
				}
				else
				{
					TBm bm = bmService.getBmByBmbh(bmbh);
					tlist = kfqxService.getKfqxByBmDateRange(bm, startDate, endDate);
				}
			}
		}
		
		return tlist;
	}

}
