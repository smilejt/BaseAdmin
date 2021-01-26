/**
 * @copyright 中兵智控（成都）有限公司
 */

package cn.smile.jt.entity.pojo;

import cn.smile.jt.common.base.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 10:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class SysUser extends BaseDomain {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 限制允许登录的IP集合
     */
    private String limitedIp;

    /**
     * 账号失效时间，超过时间将不能登录系统
     */
    private Date expiredTime;

    /**
     * 最近修改密码时间，超出时间间隔，提示用户修改密码
     */
    private Date lastChangePwdTime;

    /**
     * 是否允许账号同一个时刻多人在线，Y/N
     */
    private Boolean limitMultiLogin;
}
