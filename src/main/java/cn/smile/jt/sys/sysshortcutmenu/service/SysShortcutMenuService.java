package cn.smile.jt.sys.sysshortcutmenu.service;

import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.common.service.CommonService;
import cn.smile.jt.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import cn.smile.jt.sys.sysshortcutmenu.vo.SysShortcutMenuVo;

import java.util.List;

public interface SysShortcutMenuService extends CommonService<SysShortcutMenuVo, SysShortcutMenu, String> {
    Result<List<SysShortcutMenuVo>> findByUserId(String userId);
}
