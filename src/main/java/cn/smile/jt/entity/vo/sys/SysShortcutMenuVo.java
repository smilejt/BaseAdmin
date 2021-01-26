package cn.smile.jt.entity.vo.sys;

import cn.smile.jt.entity.pojo.sys.SysShortcutMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 14:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysShortcutMenuVo extends SysShortcutMenu {

    /**
     * 子节点
     */
    private List<SysShortcutMenuVo> children;
}
