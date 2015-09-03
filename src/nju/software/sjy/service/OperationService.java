package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TZtoperation;

public interface OperationService
{
	int getMaxBh();
	
	/**
	 * 根据操作名称获取操作
	 */
	List<TOperation> getOperationByName(String name);
	
	void saveOperation(TOperation operation);
	
	void updateOperation(TOperation operation);
	
	void deleteOperation(TOperation operation);
	
	TOperation getOperationByBh(int bh);
	
	TOperation getOperationByNameRange(String name, TGypz range);
	
	List<TOperation> getActiveOperation();
	
	List<TZtoperation> getAllZtoperation();
	
	void updateZtoperation(List<TZtoperation> tlist);
	
	TZtoperation getZtoperationByOperation(TOperation operation);
	
	void updateZtoperation(TZtoperation operation);
	
	TZtoperation getZtoperationByZt(TGypz zt);
	
	TGypz getFinalZt();
	
	TGypz getNextZt(TGypz zt);
}
