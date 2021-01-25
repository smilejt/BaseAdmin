package cn.smile.baseadmin.sys.sysuser.service;

import cn.smile.baseadmin.common.pojo.Result;
import cn.smile.baseadmin.common.service.CommonService;
import cn.smile.baseadmin.sys.sysuser.pojo.SysUser;
import cn.smile.baseadmin.sys.sysuser.vo.SysUserVo;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public interface SysUserService extends CommonService<SysUserVo, SysUser, String> {
    Result<SysUserVo> findByLoginName(String username);
    Result<SysUserVo> resetPassword(String userId);
    PersistentTokenRepository getPersistentTokenRepository2();
}
