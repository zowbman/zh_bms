package com.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.rms.model.po.TMenu;
import com.rms.model.po.TMenuCustom;

/**
 * 
 * Title:TMenuMapper
 * Description:菜单mapper接口
 * @author    zwb
 * @date      2016年9月29日 下午12:01:12
 *
 */
public interface TMenuMapper extends Mapper<TMenu>  {
	
	@Select("SELECT * FROM t_menu WHERE status = 0 AND menuType = 1 AND parentId is Null")
	@Results({
		@Result(property = "slaveChildrenMenus", 
				column = "id",
				many = @Many(select = "findTopSlaveMenusByParentId"))
	})
	public List<TMenuCustom> findTopSlaveMenus();
	
	@Select("SELECT * FROM t_menu WHERE status = 0 AND parentId = #{parentId}")
	@Results({
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusByParentId"))
	})
	public List<TMenuCustom> findTopSlaveMenusByParentId(Integer parentId);
}