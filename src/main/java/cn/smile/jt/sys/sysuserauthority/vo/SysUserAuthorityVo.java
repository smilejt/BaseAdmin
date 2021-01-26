package cn.smile.jt.sys.sysuserauthority.vo;

import cn.smile.jt.common.pojo.PageCondition;
import cn.smile.jt.sys.sysauthority.vo.SysAuthorityVo;
import cn.smile.jt.sys.sysuser.vo.SysUserVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserAuthorityVo extends PageCondition implements Serializable {
    private String userAuthorityId;//用户权限表id

    private String userId;//用户id

    private String authorityId;//权限id

    private SysUserVo sysUser;//用户

    private SysAuthorityVo sysAuthority;//权限

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private String authorityIdList;//新增、修改用户权限时权限id集合
}
