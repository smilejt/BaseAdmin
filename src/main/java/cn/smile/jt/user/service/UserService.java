package cn.smile.jt.user.service;

import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.sys.sysuser.vo.SysUserVo;

public interface UserService {
    Result<SysUserVo> updatePassword(String oldPassword, String newPassword);

    Result<SysUserVo> updateUser(SysUserVo sysUserVo);
}
