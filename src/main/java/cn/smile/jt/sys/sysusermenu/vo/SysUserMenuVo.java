package cn.smile.jt.sys.sysusermenu.vo;

import cn.smile.jt.common.pojo.PageCondition;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import cn.smile.jt.sys.sysmenu.vo.SysMenuVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserMenuVo extends PageCondition implements Serializable {
    private String userMenuId;//用户菜单表id

    private Long userId;//用户id

    private String menuId;//菜单id

    private SysUserVo sysUser;//用户

    private SysMenuVo sysMenu;//菜单

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private String menuIdList;////新增、修改用户菜单时菜单id集合
}
