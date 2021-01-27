package cn.smile.jt.service.sys.menu;

import cn.smile.jt.common.base.IBaseService;
import cn.smile.jt.entity.pojo.sys.SysMenu;
import cn.smile.jt.entity.vo.sys.SysMenuVo;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/27 14:42
 */
public interface MenuService extends IBaseService<SysMenu> {

    /**
     * 根据用户ID查询菜单
     * @param userId 用户ID
     * @return 查询结果
     */
    List<SysMenuVo> findByUserId(Long userId);
}
