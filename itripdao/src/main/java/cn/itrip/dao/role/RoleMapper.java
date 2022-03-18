package cn.itrip.dao.role;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

public interface RoleMapper {

	public Role getRoleById(@Param(value = "id") Long id)throws Exception;

	public List<Role>	getRoleListByMap(Map<String, Object> param)throws Exception;

	public Integer getRoleCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertRole(Role role)throws Exception;

	public Integer updateRole(Role role)throws Exception;

	public Integer deleteRoleById(@Param(value = "id") Long id)throws Exception;

}
