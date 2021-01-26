/**
 * @copyright 中兵智控（成都）有限公司
 */

package cn.smile.jt.entity.form.user;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 11:03
 */
@Data
public class UpdatePasswordForm {

    private Long id;

    /**
     * 修改密码时输入的旧密码
     */
    private String oldPassword;

    /**
     * 修改密码时输入的新密码
     */
    private String newPassword;
}
