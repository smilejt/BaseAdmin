package cn.smile.jt.sys.syssetting.controller;

import cn.smile.jt.common.controller.CommonController;
import cn.smile.jt.sys.syssetting.pojo.SysSetting;
import cn.smile.jt.sys.syssetting.service.SysSettingService;
import cn.smile.jt.sys.syssetting.vo.SysSettingVo;
import cn.smile.jt.util.SysSettingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sys/sysSetting/")
public class SysSettingController extends CommonController<SysSettingVo, SysSetting, String> {
    @Autowired
    private SysSettingService sysSettingService;

    @GetMapping("setting")
    public ModelAndView setting() {
        return new ModelAndView("sys/setting/setting", "sys", SysSettingUtil.getSysSetting());
    }
}
