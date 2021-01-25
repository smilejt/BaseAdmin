package cn.smile.baseadmin.sys.sysmenu.service;

import cn.smile.baseadmin.common.pojo.Result;
import cn.smile.baseadmin.sys.sysmenu.pojo.SysMenu;
import cn.smile.baseadmin.sys.sysmenu.vo.SysMenuVo;
import cn.smile.baseadmin.common.service.CommonService;

import java.util.List;

/**
 * @author 10834
 */
public interface SysMenuService extends CommonService<SysMenuVo, SysMenu, String> {
    Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo);
}
