/**
 * @copyright 中兵智控（成都）有限公司
 */

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
 * @since 2021/1/26 14:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_shortcut_menu")
public class SysShortcutMenu extends BaseDomain {

    /**
     * 用户快捷菜单名称
     */
    private String shortcutMenuName;

    /**
     * 用户快捷菜单路径
     */
    private String shortcutMenuPath;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 上级id
     */
    private Long shortcutMenuParentId;
}
