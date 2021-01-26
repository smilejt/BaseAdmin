package cn.smile.jt.sys.sysuser.repository;

import cn.smile.jt.common.repository.CommonRepository;
import cn.smile.jt.sys.sysuser.pojo.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @author 10834
 */
@Repository
public interface SysUserRepository extends CommonRepository<SysUser, String> {

    /**
     * 查询登录用户
     * @param username 用户名
     * @return 查询结果
     */
    SysUser findByLoginName(String username);
}
