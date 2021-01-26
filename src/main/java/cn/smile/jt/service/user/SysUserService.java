package cn.smile.jt.service.user;

import cn.smile.jt.common.base.IBaseService;
import cn.smile.jt.entity.form.user.UpdatePasswordForm;
import cn.smile.jt.entity.pojo.SysUser;
import cn.smile.jt.entity.vo.SysUserVo;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 10:31
 */
public interface SysUserService extends IBaseService<SysUser> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 查询结果
     */
    SysUserVo findByLoginName(String username);

    /**
     * 用户修改密码
     * @param form 请求参数
     * @return 修改结果
     */
    SysUserVo updatePassword(Long id, UpdatePasswordForm form);
}
