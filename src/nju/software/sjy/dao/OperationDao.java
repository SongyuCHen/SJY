package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TZtoperation;

public interface OperationDao
{
	int getMaxBh();
	
	List<TOperation> getOperationByName(String name);
	
	void saveOperation(TOperation operation);
	
	void updateOperation(TOperation operation);
	
	void deleteOperation(TOperation operation);
	
	TOperation getOperationByBh(int bh);
	
	TOperation getOperationByNameRange(String name, TGypz range);
	
	List<TOperation> getActiveOperation();
	
	TZtoperation getActiveZtoperationByZt(TGypz zt);
	
	List<TZtoperation> getAllZtoperation();
	
	void updateZtoperation(List<TZtoperation> tlist);
	
	TZtoperation getZtoperationByOperation(TOperation operation);
	
	void updateZtoperation(TZtoperation operation);
	
	TZtoperation getZtoperationByZt(TGypz zt);
	
	TGypz getFinalZt();
	
	TGypz getZtByOperation(TOperation operation);
	
	TGypz getNextZt(TGypz zt);
	
	TGypz getBeforeZt(TGypz zt);
}
