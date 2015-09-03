package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.bean.ZtStatusMsg;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TUser;

public interface SplcService
{
	String NOT_NULL = "请选中记录后再进行审批！";
	
	String NOT_CONSISTENT = "您选中的记录中有些不符合审批条件，请核实！";
	
	String APPROVAL_AGAIN = "记录已完成审批，无法再次审批！";
	
	String APPROVAL_SUCCESS = "审批成功！";
	
	ZtStatusMsg approvalGzsj(List<TGzsj> tlist, TUser user);
	
	ZtStatusMsg approvalJfqx(List<TJfqx> tlist, TUser user);
	
	boolean approvalFinished(TGypz zt);
	
	TGypz getFinalZt();
	
	StatusMsg canEdit(TGypz zt);
}
