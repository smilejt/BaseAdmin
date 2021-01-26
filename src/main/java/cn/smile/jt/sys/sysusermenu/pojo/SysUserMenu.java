package cn.smile.jt.sys.sysusermenu.pojo;

import cn.smile.jt.sys.sysmenu.pojo.SysMenu;
import cn.smile.jt.sys.sysuser.pojo.SysUser;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_user_menu")
@Data
public class SysUserMenu implements Serializable {
    @Id
    private String userMenuId;//用户菜单表id

    private String userId;//用户id

    private String menuId;//菜单id

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    @OneToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private SysUser sysUser;//用户

    @OneToOne
    @JoinColumn(name = "menuId",referencedColumnName = "menuId", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private SysMenu sysMenu;//菜单
}
