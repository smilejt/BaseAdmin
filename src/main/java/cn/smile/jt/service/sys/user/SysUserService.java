package cn.smile.jt.service.sys.user;

import cn.smile.jt.common.base.IBaseService;
import cn.smile.jt.entity.form.user.UpdatePasswordForm;
import cn.smile.jt.entity.form.user.UpdateUserForm;
import cn.smile.jt.entity.pojo.sys.SysUser;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.List;

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
     * @param id 请求参数
     * @param form 请求参数
     * @return 修改结果
     */
    SysUserVo updatePassword(Long id, UpdatePasswordForm form);

    /**
     * 更新用户信息
     * @param form 请求参数
     * @return 更新结果
     */
    SysUserVo updateUser(UpdateUserForm form);

    /**
     * 重置密码
     * @param userId 用户ID
     * @return 重置结果
     */
    SysUserVo resetPassword(Long userId);

    /**
     * 还不知道获取什么Token
     * @return 结果
     */
    PersistentTokenRepository getPersistentTokenRepository();

    /**
     * 获取在线用户列表
     * @return 获取结果
     */
    IPage<SysUserVo> getOnLine();

    /**
     * 强制下线
     * @param loginName 用户名
     * @return 下线结果
     */
    boolean forced(String loginName);
}
