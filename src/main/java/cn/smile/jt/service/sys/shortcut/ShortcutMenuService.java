package cn.smile.jt.service.sys.shortcut;

import cn.smile.jt.common.base.IBaseService;
import cn.smile.jt.entity.form.user.UpdateShortcutMenuForm;
import cn.smile.jt.entity.pojo.sys.SysShortcutMenu;
import cn.smile.jt.entity.vo.sys.SysShortcutMenuVo;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 14:06
 */
public interface ShortcutMenuService extends IBaseService<SysShortcutMenu> {

    /**
     * 查询登录用户快捷菜单
     * @return 查询结果
     */
    List<SysShortcutMenuVo> findByUserId();

    /**
     * 保存快捷菜单
     * @param form 请求参数
     * @return 保存结果
     */
    SysShortcutMenuVo shortcutMenuSave(UpdateShortcutMenuForm form);

    /**
     * 删除
     * @param id 快捷菜单ID
     * @return 删除结果
     */
    boolean delete(Long id);
}
