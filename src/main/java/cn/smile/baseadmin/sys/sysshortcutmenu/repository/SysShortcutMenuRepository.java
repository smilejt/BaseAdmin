package cn.smile.baseadmin.sys.sysshortcutmenu.repository;

import cn.smile.baseadmin.common.repository.CommonRepository;
import cn.smile.baseadmin.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysShortcutMenuRepository extends CommonRepository<SysShortcutMenu, String> {
    List<SysShortcutMenu> findByUserId(String userId);
}
