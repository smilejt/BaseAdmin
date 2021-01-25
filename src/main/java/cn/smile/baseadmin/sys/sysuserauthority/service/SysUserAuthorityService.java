package cn.smile.baseadmin.sys.sysuserauthority.service;

import cn.smile.baseadmin.common.pojo.Result;
import cn.smile.baseadmin.common.service.CommonService;
import cn.smile.baseadmin.sys.sysuserauthority.pojo.SysUserAuthority;
import cn.smile.baseadmin.sys.sysuserauthority.vo.SysUserAuthorityVo;

import java.util.List;

public interface SysUserAuthorityService extends CommonService<SysUserAuthorityVo, SysUserAuthority, String> {
    Result<List<SysUserAuthorityVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String authorityIdList);
}
