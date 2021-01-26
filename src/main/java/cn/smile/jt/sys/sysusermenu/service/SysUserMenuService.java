package cn.smile.jt.sys.sysusermenu.service;

import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.common.service.CommonService;
import cn.smile.jt.sys.sysmenu.vo.SysMenuVo;
import cn.smile.jt.sys.sysusermenu.pojo.SysUserMenu;
import cn.smile.jt.sys.sysusermenu.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, String> {
    Result<List<SysMenuVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String menuIdList);
}
