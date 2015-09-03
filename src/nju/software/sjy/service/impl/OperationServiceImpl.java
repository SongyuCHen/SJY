package nju.software.sjy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.OperationDao;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TZtoperation;
import nju.software.sjy.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService
{
	@Autowired
	private OperationDao operationDao;
	
	@Override
	public int getMaxBh()
	{
		return operationDao.getMaxBh();
	}

	@Cacheable(value="sjyCache")
	public List<TOperation> getOperationByName(String name)
	{
		return operationDao.getOperationByName(name);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void saveOperation(TOperation operation)
	{
		operationDao.saveOperation(operation);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateOperation(TOperation operation)
	{
		operationDao.updateOperation(operation);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void deleteOperation(TOperation operation)
	{
		operationDao.deleteOperation(operation);
	}

	@Cacheable(value="sjyCache")
	public TOperation getOperationByBh(int bh)
	{
		return operationDao.getOperationByBh(bh);
	}

	@Cacheable(value="sjyCache")
	public TOperation getOperationByNameRange(String name, TGypz range)
	{
		return operationDao.getOperationByNameRange(name, range);
	}

	@Cacheable(value="sjyCache")
	public List<TOperation> getActiveOperation()
	{
		return operationDao.getActiveOperation();
	}

	@Cacheable(value="sjyCache")
	public List<TZtoperation> getAllZtoperation()
	{
		return operationDao.getAllZtoperation();
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateZtoperation(List<TZtoperation> tlist)
	{
		operationDao.updateZtoperation(tlist);
	}

	@Cacheable(value="sjyCache")
	public TZtoperation getZtoperationByOperation(TOperation operation)
	{
		return operationDao.getZtoperationByOperation(operation);
	}

	@CacheEvict(value="sjyCache", allEntries=true)
	public void updateZtoperation(TZtoperation operation)
	{
		operationDao.updateZtoperation(operation);
	}

	@Cacheable(value="sjyCache")
	public TZtoperation getZtoperationByZt(TGypz zt)
	{
		return operationDao.getZtoperationByZt(zt);
	}

	@Cacheable(value="sjyCache")
	public TGypz getFinalZt()
	{
		return operationDao.getFinalZt();
	}

	@Cacheable(value="sjyCache")
	public TGypz getNextZt(TGypz zt)
	{
		return operationDao.getNextZt(zt);
	}

}
