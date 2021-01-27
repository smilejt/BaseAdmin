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
 * @since 2021/1/27 15:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_menu")
public class SysUserMenu extends BaseDomain {

    private Long userId;

    private Long menuId;
}
