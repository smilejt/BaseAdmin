package cn.smile.jt.sys.sysusermenu.repository;

import cn.smile.jt.common.repository.CommonRepository;
import cn.smile.jt.sys.sysusermenu.pojo.SysUserMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMenuRepository extends CommonRepository<SysUserMenu, String> {
    List<SysUserMenu> findByUserId(String userId);
}
