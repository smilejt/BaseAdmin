package cn.smile.jt.sys.sysuserauthority.repository;

import cn.smile.jt.common.repository.CommonRepository;
import cn.smile.jt.sys.sysuserauthority.pojo.SysUserAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserAuthorityRepository extends CommonRepository<SysUserAuthority, String> {
    List<SysUserAuthority> findByUserId(String userId);
}
