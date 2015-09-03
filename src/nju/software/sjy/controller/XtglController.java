package nju.software.sjy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nju.software.sjy.common.Constants;
import nju.software.sjy.convertor.OperationConvertor;
import nju.software.sjy.mapper.MOperation;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TZtoperation;
import nju.software.sjy.service.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统管理控制器
 *
 */
@Controller
@RequestMapping("/xtgl")
public class XtglController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OperationService operationService;
	
	/**
	 * 审批流程主页面
	 */
	@RequestMapping("/splc")
	public ModelAndView splc()
	{
		/* 查询出所有的审批操作 */
		List<TOperation> tlist = operationService.getOperationByName(Constants.SP);
		
		/* 转换成方便在页面显示的列表 */
		List<MOperation> mlist = OperationConvertor.convertSimply(tlist);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("splc");
		
		mav.addObject("mlist", mlist);
		
		return mav;
	}
	
	/**
	 * 审批流程的配置
	 */
	@RequestMapping("/splcpz")
	public ModelAndView splcpz()
	{
		//获取页面选中的审批操作
		String[] spbhArr = request.getParameterValues("splcbh");
		
		//1.设置所有审批status=0
		List<TOperation> tlist = operationService.getOperationByName(Constants.SP);
		if(tlist != null)
		{
			for(TOperation operation : tlist)
			{
				operation.setStatus(0);
				operationService.updateOperation(operation);
			}
		}
		
		//2.设置TZtoperation表中status=0
		List<TZtoperation> ztOpList = operationService.getAllZtoperation();
		if(ztOpList != null)
		{
			for(TZtoperation ztOp : ztOpList)
			{
				ztOp.setStatus(0);
			}
		}
		
		operationService.updateZtoperation(ztOpList);
		
		//2.设置checked审批status=1，设置TZtoperation表中相应的status=1
		if(spbhArr != null)
		{
			for(String spbhStr : spbhArr)
			{
				int bh = Integer.parseInt(spbhStr);
				TOperation operation = operationService.getOperationByBh(bh);
				operation.setStatus(1);
				operationService.updateOperation(operation);
				
				TZtoperation ztop = operationService.getZtoperationByOperation(operation);
				ztop.setStatus(1);
				
				operationService.updateZtoperation(ztop);
			}
		}
		
		ModelAndView mav = splc();
		
		return mav;
	}
}
