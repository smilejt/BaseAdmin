package cn.smile.jt.controller;

import cn.smile.jt.entity.pojo.sys.SysSetting;
import cn.smile.jt.entity.vo.sys.SysMenuVo;
import cn.smile.jt.entity.vo.sys.SysShortcutMenuVo;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import cn.smile.jt.service.sys.menu.MenuService;
import cn.smile.jt.service.sys.setting.SysSettingService;
import cn.smile.jt.service.sys.shortcut.ShortcutMenuService;
import cn.smile.jt.service.sys.user.SysUserService;
import cn.smile.jt.util.RsaUtil;
import cn.smile.jt.util.SecurityUtil;
import cn.smile.jt.util.SysSettingUtil;
import cn.smile.jt.util.VerifyCodeImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/27 10:29
 */
@Slf4j
@Controller
@RequestMapping("/")
@Configuration
public class IndexController {

    @Resource
    private SysSettingService settingService;
    @Resource
    private SysUserService userService;
    @Resource
    private ShortcutMenuService shortcutMenuService;
    @Resource
    private MenuService menuService;

    private static final int IMAGE_WIDTH = 90;
    private static final int IMAGE_HEIGHT = 30;
    private static final int IMAGE_INTER_LINE = 3;

    @Value("${server.servlet.context-path:}")
    private String contextPath;


    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            try {
                //系统启动时获取数据库数据，设置到公用静态集合sysSettingMap
                SysSetting setting = settingService.get(1L);
                if (StringUtils.isEmpty(setting)) {
                    throw new UnknownHostException("Unknown System Configuration");
                }
                SysSettingUtil.setSysSettingMap(setting);

                //获取本机内网IP
                log.info("start success: http://{}:{}{}", InetAddress.getLocalHost().getHostAddress(), port, contextPath);
            } catch (UnknownHostException e) {
                //输出到日志文件中
                log.error("[IndexController].[applicationRunner] ------> error:", e);
            }
        };
    }

    /**
     * 跳转登录页面
     */
    @GetMapping("loginPage")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");

        //系统信息
        modelAndView.addObject("sys", SysSettingUtil.getSysSetting());

        //后端公钥
        String publicKey = RsaUtil.getPublicKey();
        log.info("Server Public Key：{}", publicKey);
        modelAndView.addObject("publicKey", publicKey);

        return modelAndView;
    }

    /**
     * 跳转首页
     */
    @GetMapping("")
    public void indexPage(HttpServletResponse response) {
        //内部重定向
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            //输出到日志文件中
            log.error("[IndexController].[indexPage] ------> error:", e);
        }
    }

    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        //系统信息
        modelAndView.addObject("sys", SysSettingUtil.getSysSetting());

        //登录用户
        SysUserVo sysUserVo = userService.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        //隐藏部分属性
        sysUserVo.setPassword(null);
        modelAndView.addObject("loginUser", sysUserVo);

        //登录用户系统菜单
        List<SysMenuVo> menuVoList = menuService.findByUserId(sysUserVo.getId());
        modelAndView.addObject("menuList", menuVoList);

        //登录用户快捷菜单
        List<SysShortcutMenuVo> shortcutMenuVoList = shortcutMenuService.findByUserId();
        modelAndView.addObject("shortcutMenuList", shortcutMenuVoList);

        //后端公钥
        String publicKey = RsaUtil.getPublicKey();
        log.info("Server Public Key：{}", publicKey);
        modelAndView.addObject("publicKey", publicKey);

        return modelAndView;
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @RequestMapping("getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getOutputStream();
        String verifyCode = VerifyCodeImageUtil.generateTextCode(VerifyCodeImageUtil.TYPE_NUM_UPPER, 4, null);

        //将验证码放到HttpSession里面
        request.getSession().setAttribute("verifyCode", verifyCode);
        log.info("Verify Code = {},Save In HttpSession", verifyCode);

        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeImageUtil.generateImageCode(verifyCode, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_INTER_LINE, true, Color.WHITE, Color.BLACK, null);

        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    /**
     * 跳转实时系统硬件监控
     */
    @GetMapping("monitor")
    public ModelAndView monitor() {
        return new ModelAndView("monitor","port",port);
    }

    /**
     * 跳转实时日志
     */
    @GetMapping("logging")
    public ModelAndView logging() {
        return new ModelAndView("logging","port",port);
    }
}
