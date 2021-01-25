package cn.smile.baseadmin.sys.sysshortcutmenu.service;

import cn.smile.baseadmin.common.pojo.Result;
import cn.smile.baseadmin.common.service.CommonService;
import cn.smile.baseadmin.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import cn.smile.baseadmin.sys.sysshortcutmenu.vo.SysShortcutMenuVo;

import java.util.List;

public interface SysShortcutMenuService extends CommonService<SysShortcutMenuVo, SysShortcutMenu, String> {
    Result<List<SysShortcutMenuVo>> findByUserId(String userId);
}
