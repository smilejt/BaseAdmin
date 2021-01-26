package cn.smile.jt.sys.sysshortcutmenu.controller;

import cn.smile.jt.common.controller.CommonController;
import cn.smile.jt.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import cn.smile.jt.sys.sysshortcutmenu.service.SysShortcutMenuService;
import cn.smile.jt.sys.sysshortcutmenu.vo.SysShortcutMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/sysShortcutMenu/")
public class SysShortcutMenuController extends CommonController<SysShortcutMenuVo, SysShortcutMenu, String> {
    @Autowired
    private SysShortcutMenuService sysShortcutMenuService;
}
