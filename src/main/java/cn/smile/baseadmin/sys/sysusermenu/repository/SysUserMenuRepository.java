package cn.smile.baseadmin.sys.sysusermenu.repository;

import cn.smile.baseadmin.common.repository.CommonRepository;
import cn.smile.baseadmin.sys.sysusermenu.pojo.SysUserMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMenuRepository extends CommonRepository<SysUserMenu, String> {
    List<SysUserMenu> findByUserId(String userId);
}
