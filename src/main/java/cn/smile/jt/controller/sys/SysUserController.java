package cn.smile.jt.controller.sys;

import cn.smile.jt.annotation.Decrypt;
import cn.smile.jt.annotation.Encrypt;
import cn.smile.jt.common.base.BaseController;
import cn.smile.jt.common.pojo.PageInfo;
import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.common.response.ResponseCode;
import cn.smile.jt.common.response.ResponseResult;
import cn.smile.jt.entity.form.user.ResetPasswordForm;
import cn.smile.jt.entity.pojo.sys.SysUser;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import cn.smile.jt.service.sys.user.SysUserService;
import cn.smile.jt.util.SysSettingUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 16:25
 */
@RestController
@RequestMapping("/sys/sysUser/")
public class SysUserController extends BaseController<SysUser, SysUserService> {

    @GetMapping("user")
    public ModelAndView user() {
        return new ModelAndView("sys/user/user", "initPassword", SysSettingUtil.getSysSetting().getUserInitPassword());
    }

    @PostMapping("resetPassword")
    public ResponseResult resetPassword(ResetPasswordForm form){

        if (StringUtils.isEmpty(form) || StringUtils.isEmpty(form.getId())){
            return ResponseResult.failure(ResponseCode.PARAM_NOT_COMPLETE);
        }

        SysUserVo vo = service.resetPassword(form.getId());
        if (!StringUtils.isEmpty(vo)){
            return ResponseResult.success(vo);
        }
        return ResponseResult.failure(ResponseCode.FAILURE);
    }

    @PostMapping("pageOnLine")
    public ResponseResult pageOnLine(){
        IPage<SysUserVo> onLine = service.getOnLine();
        return ResponseResult.success(onLine);
    }

    @DeleteMapping("forced/{loginName}")
    public ResponseResult forced(@PathVariable("loginName") String loginName) {

        if (StringUtils.isEmpty(loginName)){
            return ResponseResult.failure(ResponseCode.PARAM_IS_INVALID);
        }

        if (service.forced(loginName)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResponseCode.FAILURE);
    }
}
