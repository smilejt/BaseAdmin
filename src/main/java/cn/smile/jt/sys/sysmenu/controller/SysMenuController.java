package cn.smile.jt.sys.sysmenu.controller;

import cn.smile.jt.annotation.Decrypt;
import cn.smile.jt.annotation.Encrypt;
import cn.smile.jt.common.controller.CommonController;
import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.sys.sysmenu.pojo.SysMenu;
import cn.smile.jt.sys.sysmenu.service.SysMenuService;
import cn.smile.jt.sys.sysmenu.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sys/sysMenu/")
public class SysMenuController extends CommonController<SysMenuVo, SysMenu, String> {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("menu")
    public ModelAndView menu(){
        return new ModelAndView("sys/menu/menu");
    }

    /**
     * 分层级
     */
    @PostMapping("listByTier")
    @Decrypt
    @Encrypt
    public Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo) {
        return sysMenuService.listByTier(entityVo);
    }
}
