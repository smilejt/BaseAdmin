package cn.smile.jt.service.sys.shortcut.impl;

import cn.smile.jt.common.base.BaseServiceImpl;
import cn.smile.jt.entity.form.user.UpdateShortcutMenuForm;
import cn.smile.jt.entity.pojo.sys.SysShortcutMenu;
import cn.smile.jt.entity.vo.sys.SysShortcutMenuVo;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import cn.smile.jt.mapper.sys.shortcut.SysShortcutMenuMapper;
import cn.smile.jt.service.sys.shortcut.ShortcutMenuService;
import cn.smile.jt.service.sys.user.SysUserService;
import cn.smile.jt.util.SecurityUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 14:06
 */
@Service
@Slf4j
public class ShortcutMenuServiceImpl extends BaseServiceImpl<SysShortcutMenuMapper, SysShortcutMenu> implements ShortcutMenuService {

    @Resource
    private SysUserService userService;

    private static final String USER_ID_KEY = "user_id";
    private static final String IS_DELETE_KEY = "is_delete";
    private static final int IS_DELETE_VALUE = 0;
    private static final String ID_KEY = "id";

    @Override
    public List<SysShortcutMenuVo> findByUserId() {
        log.info("[ShortcutMenuServiceImpl].[findByUserId] ------> start");

        List<SysShortcutMenuVo> resultList = Lists.newArrayList();

        SysUserVo userVo = userService.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        if (!StringUtils.isEmpty(userVo)) {
            QueryWrapper<SysShortcutMenu> qw = new QueryWrapper<>();
            qw.eq(USER_ID_KEY, userVo.getId());
            qw.eq(IS_DELETE_KEY, IS_DELETE_VALUE);

            List<SysShortcutMenu> sysShortcutMenus = baseMapper.selectList(qw);
            if (!CollectionUtils.isEmpty(sysShortcutMenus)) {

                Map<Long, List<SysShortcutMenuVo>> menuMap = new HashMap<>();

                for (SysShortcutMenu menu : sysShortcutMenus) {
                    SysShortcutMenuVo menuVo = new SysShortcutMenuVo();
                    BeanUtils.copyProperties(menu, menuVo);
                    menuVo.setChildren(Lists.newArrayList());

                    if (StringUtils.isEmpty(menu.getShortcutMenuParentId())) {
                        resultList.add(menuVo);
                    } else {
                        if (!menuMap.containsKey(menu.getShortcutMenuParentId())) {
                            menuMap.put(menu.getShortcutMenuParentId(), Lists.newArrayList());
                        }

                        menuMap.get(menu.getShortcutMenuParentId()).add(menuVo);
                    }
                }

                //组装返回集合
                assemblyDate(resultList, menuMap);
            }
        }
        return resultList;
    }

    @Override
    public SysShortcutMenuVo shortcutMenuSave(UpdateShortcutMenuForm form) {

        log.info("[ShortcutMenuServiceImpl].[shortcutMenuSave] ------> form = {}", JSON.toJSONString(form));

        SysUserVo userVo = userService.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        if (!StringUtils.isEmpty(userVo)){
            SysShortcutMenu menu = new SysShortcutMenu();
            BeanUtils.copyProperties(form, menu);
            menu.setUserId(userVo.getId());
            menu.setUpdateTime(LocalDateTime.now());

            boolean flag;

            if (StringUtils.isEmpty(menu.getId())){
                menu.setCreateTime(LocalDateTime.now());
                flag = baseMapper.insert(menu) > 0;
            }else {
                flag = baseMapper.updateById(menu) > 0;
            }

            if (flag){
                SysShortcutMenuVo vo = new SysShortcutMenuVo();
                menu = baseMapper.selectById(menu.getId());
                BeanUtils.copyProperties(menu, vo);
                return vo;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        log.info("[ShortcutMenuServiceImpl].[delete] ------> id = {}", id);

        SysUserVo userVo = userService.findByLoginName(SecurityUtil.getLoginUser().getUsername());

        QueryWrapper<SysShortcutMenu> qw = new QueryWrapper<>();
        qw.eq(ID_KEY, id);
        qw.eq(IS_DELETE_KEY, IS_DELETE_VALUE);
        qw.eq(USER_ID_KEY, userVo.getId());
        SysShortcutMenu menu = baseMapper.selectOne(qw);

        if (!StringUtils.isEmpty(menu)){
            SysShortcutMenu updateMenu = new SysShortcutMenu();
            updateMenu.setId(menu.getId());
            updateMenu.setDeleted(true);
            updateMenu.setUpdateTime(LocalDateTime.now());

            return baseMapper.updateById(updateMenu) > 0;
        }
        return false;
    }

    /**
     * 循环外层集合,递归组装子集合
     *
     * @param resultList 外层集合
     * @param menuMap    子节点集合
     */
    private void assemblyDate(List<SysShortcutMenuVo> resultList, Map<Long, List<SysShortcutMenuVo>> menuMap) {
        if (!CollectionUtils.isEmpty(resultList)) {
            for (SysShortcutMenuVo vo : resultList) {
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
    private void handleData(SysShortcutMenuVo vo, Map<Long, List<SysShortcutMenuVo>> menuMap) {
        //判断child集合中是否有当前待设置对象ID对应的Key
        if (menuMap.containsKey(vo.getId())) {
            vo.setChildren(menuMap.get(vo.getId()));

            //循环当前节点的子节点对象,继续递归set
            for (SysShortcutMenuVo childVO : vo.getChildren()) {
                handleData(childVO, menuMap);
            }
        }
    }
}
