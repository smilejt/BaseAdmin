package cn.smile.jt.entity.vo.sys;

import cn.smile.jt.entity.pojo.sys.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/27 14:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuVo extends SysMenu {

    /**
     * 子节点
     */
    private List<SysMenuVo> children;
}
