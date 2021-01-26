package cn.smile.jt.controller.user;

import cn.smile.jt.annotation.Decrypt;
import cn.smile.jt.annotation.Encrypt;
import cn.smile.jt.common.base.BaseController;
import cn.smile.jt.common.response.ResponseCode;
import cn.smile.jt.common.response.ResponseResult;
import cn.smile.jt.entity.form.user.UpdatePasswordForm;
import cn.smile.jt.entity.form.user.UpdateShortcutMenuForm;
import cn.smile.jt.entity.form.user.UpdateUserForm;
import cn.smile.jt.entity.pojo.sys.SysUser;
import cn.smile.jt.entity.vo.sys.SysShortcutMenuVo;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import cn.smile.jt.service.sys.shortcut.ShortcutMenuService;
import cn.smile.jt.service.sys.user.SysUserService;
import cn.smile.jt.util.MD5Util;
import cn.smile.jt.util.SecurityUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private ShortcutMenuService shortcutMenuService;

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

    /**
     * 修改部分用户属性
     */
    @PostMapping("updateUser")
    public ResponseResult updateUser(UpdateUserForm form) {
        SysUserVo vo = service.updateUser(form);
        if (!StringUtils.isEmpty(vo)){
            return ResponseResult.success(vo);
        }
        return ResponseResult.failure(ResponseCode.FAILURE);
    }

    /**
     * 分层级
     */
    @PostMapping("shortcutMenuListByTier")
    public ResponseResult shortcutMenuListByTier() {
        List<SysShortcutMenuVo> voList = shortcutMenuService.findByUserId();
        if (!CollectionUtils.isEmpty(voList)){
            return ResponseResult.success(voList);
        }
        return ResponseResult.failure(ResponseCode.FAILURE);
    }

    /**
     * 保存
     */
    @PostMapping("shortcutMenuSave")
    @Decrypt
    @Encrypt
    public ResponseResult shortcutMenuSave(UpdateShortcutMenuForm form) {
        SysShortcutMenuVo vo = shortcutMenuService.shortcutMenuSave(form);
        if (!StringUtils.isEmpty(vo)){
            return ResponseResult.success(vo);
        }
        return ResponseResult.failure(ResponseCode.FAILURE);
    }

    /**
     * 删除
     * @param id 删除对象ID
     * @return 删除结果
     */
    @DeleteMapping("shortcutMenuDelete/{id}")
    public ResponseResult shortcutMenuDelete(@PathVariable("id") Long id) {
        if (shortcutMenuService.delete(id)){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResponseCode.FAILURE);
    }
}
