package cn.smile.jt.sys.sysshortcutmenu.repository;

import cn.smile.jt.common.repository.CommonRepository;
import cn.smile.jt.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysShortcutMenuRepository extends CommonRepository<SysShortcutMenu, String> {
    List<SysShortcutMenu> findByUserId(String userId);
}
