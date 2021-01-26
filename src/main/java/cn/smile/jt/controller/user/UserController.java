package cn.smile.jt.controller.user;

import cn.smile.jt.common.base.BaseController;
import cn.smile.jt.common.response.ResponseCode;
import cn.smile.jt.common.response.ResponseResult;
import cn.smile.jt.entity.form.user.UpdatePasswordForm;
import cn.smile.jt.entity.pojo.SysUser;
import cn.smile.jt.entity.vo.SysUserVo;
import cn.smile.jt.service.user.SysUserService;
import cn.smile.jt.util.MD5Util;
import cn.smile.jt.util.SecurityUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 10:37
 */
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController<SysUser, SysUserService> {

    @GetMapping("userInfo")
    public ModelAndView userInfo() {
        SysUserVo sysUserVo = service.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        sysUserVo.setPassword(null);
        return new ModelAndView("user/userInfo", "user", sysUserVo);
    }

    @GetMapping("shortMenu")
    public ModelAndView shortMenu() {
        return new ModelAndView("user/shortMenu");
    }

    /**
     * 修改密码
     */
    @PostMapping("updatePassword")
    public ResponseResult updatePassword(UpdatePasswordForm form) {

        SysUserVo userVo = service.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        if (!StringUtils.isEmpty(userVo)) {
            //判断原密码是否相同
            if (!userVo.getPassword().equals(MD5Util.getMD5(form.getOldPassword()))) {
                ResponseResult.failure(ResponseCode.USER_OLD_PASSWORD_ERROR);
            }
            //执行更新操作
            SysUserVo vo = service.updatePassword(userVo.getId(), form);
            if (!StringUtils.isEmpty(vo)) {
                return ResponseResult.success(vo);
            }
        }

        return ResponseResult.failure(ResponseCode.FAILURE);
    }
}
