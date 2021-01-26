package cn.smile.jt.sys.sysuserauthority.service;

import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.common.service.CommonService;
import cn.smile.jt.sys.sysuserauthority.pojo.SysUserAuthority;
import cn.smile.jt.sys.sysuserauthority.vo.SysUserAuthorityVo;

import java.util.List;

public interface SysUserAuthorityService extends CommonService<SysUserAuthorityVo, SysUserAuthority, String> {
    Result<List<SysUserAuthorityVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String authorityIdList);
}
