package cn.smile.jt.service.sys.menu.impl;

import cn.smile.jt.common.base.BaseServiceImpl;
import cn.smile.jt.entity.pojo.sys.SysMenu;
import cn.smile.jt.entity.pojo.sys.SysUserMenu;
import cn.smile.jt.entity.vo.sys.SysMenuVo;
import cn.smile.jt.mapper.sys.menu.SysMenuMapper;
import cn.smile.jt.mapper.sys.menu.SysUserMenuMapper;
import cn.smile.jt.service.sys.menu.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/27 14:47
 */
@Service
@Slf4j
public class MenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements MenuService {

    @Resource
    private SysUserMenuMapper userMenuMapper;

    private static final String USER_ID_KEY = "user_id";
    private static final String ID_KEY = "id";
    private static final String IS_DELETE_KEY = "is_delete";
    private static final int IS_DELETE_VALUE = 0;

    @Override
    public List<SysMenuVo> findByUserId(Long userId) {

        List<SysMenuVo> resultList = Lists.newArrayList();
        if (!StringUtils.isEmpty(userId)){

            List<Long> menuIdList = Lists.newArrayList();

            //创建查询对象
            QueryWrapper<SysUserMenu> qw = new QueryWrapper<>();
            qw.eq(USER_ID_KEY, userId);
            qw.eq(IS_DELETE_KEY, IS_DELETE_VALUE);

            //查询用户ID所属的菜单ID列表
            List<SysUserMenu> sysUserMenus = userMenuMapper.selectList(qw);
            if(!CollectionUtils.isEmpty(sysUserMenus)){

                //获取菜单ID集合
                sysUserMenus.forEach(userMenu -> menuIdList.add(userMenu.getUserId()));

                //创建查询对象
                QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
                queryWrapper.in(ID_KEY, menuIdList);
                queryWrapper.eq(IS_DELETE_KEY, IS_DELETE_VALUE);

                //查询对应的菜单
                List<SysMenu> sysMenus = baseMapper.selectList(queryWrapper);
                if (!CollectionUtils.isEmpty(sysMenus)){
                    Map<Long, List<SysMenuVo>> menuMap = new HashMap<>();

                    //组装用于递归菜单的Map
                    for (SysMenu menu: sysMenus) {
                        if (!menuMap.containsKey(menu.getMenuParentId())){
                            menuMap.put(menu.getMenuParentId(), Lists.newArrayList());
                        }

                        SysMenuVo vo = new SysMenuVo();
                        BeanUtils.copyProperties(menu, vo);
                        vo.setChildren(Lists.newArrayList());

                        if (StringUtils.isEmpty(vo.getMenuParentId())){
                            resultList.add(vo);
                        }else {
                            menuMap.get(menu.getMenuParentId()).add(vo);
                        }
                    }

                    //组装菜单树
                    assemblyDate(resultList, menuMap);
                }
            }
        }

        return resultList;
    }

    /**
     * 循环外层集合,递归组装子集合
     *
     * @param resultList 外层集合
     * @param menuMap    子节点集合
     */
    private void assemblyDate(List<SysMenuVo> resultList, Map<Long, List<SysMenuVo>> menuMap) {
        if (!CollectionUtils.isEmpty(resultList)) {
            for (SysMenuVo vo : resultList) {
                handleData(vo, menuMap);
            }
        }
    }

    /**
     * 递归组装子节点
     *
     * @param vo      需要添加子节点的对象
     * @param menuMap 子节点集合
     */
    private void handleData(SysMenuVo vo, Map<Long, List<SysMenuVo>> menuMap) {
        //判断child集合中是否有当前待设置对象ID对应的Key
        if (menuMap.containsKey(vo.getId())) {
            vo.setChildren(menuMap.get(vo.getId()));

            //循环当前节点的子节点对象,继续递归set
            for (SysMenuVo childVO : vo.getChildren()) {
                handleData(childVO, menuMap);
            }
        }
    }
}
