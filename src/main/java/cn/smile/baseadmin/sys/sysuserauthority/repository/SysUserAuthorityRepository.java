package cn.smile.baseadmin.sys.sysuserauthority.repository;

import cn.smile.baseadmin.common.repository.CommonRepository;
import cn.smile.baseadmin.sys.sysuserauthority.pojo.SysUserAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserAuthorityRepository extends CommonRepository<SysUserAuthority, String> {
    List<SysUserAuthority> findByUserId(String userId);
}
