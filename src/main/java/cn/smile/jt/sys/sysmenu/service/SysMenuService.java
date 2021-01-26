package cn.smile.jt.sys.sysmenu.service;

import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.sys.sysmenu.pojo.SysMenu;
import cn.smile.jt.sys.sysmenu.vo.SysMenuVo;
import cn.smile.jt.common.service.CommonService;

import java.util.List;

/**
 * @author 10834
 */
public interface SysMenuService extends CommonService<SysMenuVo, SysMenu, String> {
    Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo);
}
