package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.rms.base.service.impl.BaseServiceImpl;
import com.rms.model.po.TPrivilege;
import com.rms.model.po.TPrivilegeCustom;
import com.rms.service.IPrivilegeService;
import com.rms.util.BaseUtil;

/**
 * 
 * Title:PrivilegeServiceImpl
 * Description:权限service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:29:34
 *
 */
@Service
public class PrivilegeServiceImpl extends BaseServiceImpl<TPrivilege> implements IPrivilegeService {

	@Override
	public List<TPrivilege> findChildrenPrivileges(Integer parentId) {
		Example example = new Example(TPrivilege.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("parentid", parentId);
		return tPrivilegeMapper.selectByExample(example);
	}

	@Override
	public List<TPrivilegeCustom> findPrivilegesForCascade() {
		return tPrivilegeMapper.findPrivilegesForCascade();
	}

	@Override
	public List<TPrivilege> findTopPrivileges() {
		Example example = new Example(TPrivilege.class);
		Criteria criteria = example.createCriteria();
		criteria.andIsNull("parentid");
		return tPrivilegeMapper.selectByExample(example);
	}

	@Override
	public void updatePrivilegeSeletive(TPrivilege tPrivilege) {
		boolean flag = true;
		if(tPrivilege.getParentid() != null){
			Example example = new Example(TPrivilege.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("id", tPrivilege.getParentid());
			List<TPrivilege> parentPrivileges = tPrivilegeMapper.selectByExample(example);
			if(parentPrivileges != null && parentPrivileges.size() == 1){
				if(tPrivilege.getId().equals(parentPrivileges.get(0).getParentid())){
					parentPrivileges.get(0).setParentid(null);
					tPrivilegeMapper.updateByPrimaryKey(parentPrivileges.get(0));
				}
			}else if(parentPrivileges.size() > 1){
				flag = false;
			}
		}
		if(flag){
			tPrivilegeMapper.updateByPrimaryKeySelective(tPrivilege);
		}
	}

	@Override
	public void deletePrivilegeByIdForRecursion(Integer id) {
		List<TPrivilegeCustom> privilegeCustoms = tPrivilegeMapper.findPrivilegeByIdForCascade(id);
		List<TPrivilege> privileges = recursionPrivilege(privilegeCustoms, new ArrayList<TPrivilege>());
		for (TPrivilege tPrivilege : privileges) {
			tPrivilegeMapper.deleteByPrimaryKey(tPrivilege.getId());
		}
	}
	
	@Override
	public void deletePrivilegeByIdsForRecursion(Integer[] ids) {
		for (Integer id : ids) {
			deletePrivilegeByIdForRecursion(id);
		}
	}
	
	/**
	 * 递归获取权限列表
	 * @param privilegeCustoms 处理数据
	 * @param resultPrivileges 结果数据
	 * @return
	 */
	private List<TPrivilege> recursionPrivilege(List<TPrivilegeCustom> privilegeCustoms,List<TPrivilege> resultPrivileges){
		if(privilegeCustoms != null && privilegeCustoms.size() > 0){
			for (TPrivilegeCustom privilegeCustom : privilegeCustoms) {
				resultPrivileges.add(privilegeCustom);
				recursionPrivilege(privilegeCustom.getChildrenPrivileges(),resultPrivileges);
			}
		}
		return resultPrivileges;
	}
	
	@Override
	public List<Integer> findRoleIdsByPrivilegeId(Integer privilegeId) {
		return tPrivilegeMapper.findRoleIdsByPrivilegeId(privilegeId);
	}

	@Override
	public void updatePrivilegeRoleByPrivilegeId(Integer privilegeId, List<Integer> newRoleIds) {
		List<Integer> oldRoleIds = findRoleIdsByPrivilegeId(privilegeId);
		Map<String, Object> map = BaseUtil.compareArry(oldRoleIds, newRoleIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tPrivilegeMapper.insertPrivilegeRole(privilegeId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tPrivilegeMapper.deletePrivilegeRoleByPrivilegeId(privilegeId, deleteId);
		}
	}
}
