package cn.smile.jt.sys.sysuser.service;

import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.common.service.CommonService;
import cn.smile.jt.sys.sysuser.pojo.SysUser;
import cn.smile.jt.sys.sysuser.vo.SysUserVo;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public interface SysUserService extends CommonService<SysUserVo, SysUser, String> {
    Result<SysUserVo> findByLoginName(String username);
    Result<SysUserVo> resetPassword(String userId);
    PersistentTokenRepository getPersistentTokenRepository2();
}
