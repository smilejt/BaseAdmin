package cn.smile.baseadmin.sys.sysusermenu.service;

import cn.smile.baseadmin.common.pojo.Result;
import cn.smile.baseadmin.common.service.CommonService;
import cn.smile.baseadmin.sys.sysmenu.vo.SysMenuVo;
import cn.smile.baseadmin.sys.sysusermenu.pojo.SysUserMenu;
import cn.smile.baseadmin.sys.sysusermenu.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, String> {
    Result<List<SysMenuVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String menuIdList);
}
