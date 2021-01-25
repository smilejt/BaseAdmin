package cn.smile.baseadmin.user.service;

import cn.smile.baseadmin.common.pojo.Result;
import cn.smile.baseadmin.sys.sysuser.vo.SysUserVo;

public interface UserService {
    Result<SysUserVo> updatePassword(String oldPassword, String newPassword);

    Result<SysUserVo> updateUser(SysUserVo sysUserVo);
}
