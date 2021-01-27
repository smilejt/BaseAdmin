package cn.smile.jt.entity.pojo.sys;

import cn.smile.jt.common.base.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/27 11:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_setting")
public class SysSetting extends BaseDomain {

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 系统logo图标
     */
    private String sysLogo;

    /**
     * 系统底部信息
     */
    private String sysBottomText;

    /**
     * 系统颜色
     */
    private String sysColor;

    /**
     * 系统公告
     */
    private String sysNoticeText;

    /**
     * API加密
     */
    private Boolean sysApiEncrypt;

    /**
     * 用户管理：初始、重置密码
     */
    private String userInitPassword;
}
