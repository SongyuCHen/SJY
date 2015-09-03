package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.bean.StatusMsg;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TUser;

/**
 * 权限服务接口
 *
 */
public interface AuthorityService
{
	boolean EXCLUDE_QTBM = true;
	
	String IS_ACCESS = "允许执行此操作";
	
	String IS_NOT_ACCESS = "您的权限不够, 无法执行此操作";
	
	/**
	 * 获取登录用户所拥有的操作
	 * 
	 * @param user	登录用户
	 * @return		操作列表
	 */
	List<TOperation> getAccessOperationByUser(TUser user);
	
	/**
	 * 获取登录用户所能访问的部门
	 * 
	 * @param user	登录用户
	 * @return		部门列表
	 */
	List<TBm> getAccessBmByUser(TUser user);
	
	/**
	 * 获取登录用户所能访问的部门，除去其它部门
	 * 
	 * @param user	登录用户
	 * @return		部门列表
	 */
	List<TBm> getAccessBmByUserExcludeQtbm(TUser user);
	
	/**
	 * 根据用户和部门获取用户能访问的该部门下的人员
	 * 
	 * @param user	登录用户
	 * @param bm	指定部门，null代表所有部门
	 * @return		人员列表
	 */
	List<TUser> getAccessUserByUserBm(TUser user, TBm bm);
	
	/**
	 * 判断是否是可执行的操作，登录用户能不能对目标用户进行操作
	 * 
	 * @param self			登录用户
	 * @param target		目标用户
	 * @param operation		操作名称
	 * @return				状态信息
	 */
	StatusMsg isAccessedOperation(TUser self, TUser target, String operation);
	
	/**
	 * 获取需要考评的人员列表，通过登录用户和指定部门获取
	 * 
	 * @param user		登录用户
	 * @param bm		指定部门
	 * @return			人员列表
	 */
	List<TUser> getAccessNeedEvaluationUserByUserBm(TUser user, TBm bm);
}
