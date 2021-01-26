package cn.smile.jt.entity.form.user;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 15:06
 */
@Data
public class UpdateShortcutMenuForm {

    private Long id;

    /**
     * 用户快捷菜单名称
     */
    private String shortcutMenuName;

    /**
     * 用户快捷菜单路径
     */
    private String shortcutMenuPath;

    /**
     * 上级id
     */
    private Long shortcutMenuParentId;
}
