package cn.smile.jt.sys.sysauthority.controller;

import cn.smile.jt.common.controller.CommonController;
import cn.smile.jt.sys.sysauthority.pojo.SysAuthority;
import cn.smile.jt.sys.sysauthority.service.SysAuthorityService;
import cn.smile.jt.sys.sysauthority.vo.SysAuthorityVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author longjuntao
 */
@RestController
@RequestMapping("/sys/sysAuthority/")
public class SysAuthorityController extends CommonController<SysAuthorityVo, SysAuthority, String> {
    @Resource
    private SysAuthorityService sysAuthorityService;

    @GetMapping("authority")
    public ModelAndView authority(){
        return new ModelAndView("sys/authority/authority");
    }

}
