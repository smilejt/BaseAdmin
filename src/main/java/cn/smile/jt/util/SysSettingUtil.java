package cn.smile.jt.util;

import cn.smile.jt.entity.pojo.sys.SysSetting;
import cn.smile.jt.sys.syssetting.vo.SysSettingVo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统设置工具类
 * 系统启动时获取数据库数据，设置到公用静态集合sysSettingMap
 * 更新系统设置时同步更新公用静态集合sysSettingMap
 * @author longjuntao
 */
public class SysSettingUtil {

    /**
     * 使用线程安全的ConcurrentHashMap来存储系统设置
     */
    private static ConcurrentHashMap<String, SysSetting> sysSettingMap = new ConcurrentHashMap<>();

    /**
     * 从公用静态集合sysSettingMap获取系统设置
     * @return sysSettingMap
     */
    public static SysSetting getSysSetting(){
        return sysSettingMap.get("sysSetting");
    }

    /**
     * 更新公用静态集合sysSettingMap
     * @param setting 数据库查询结果
     */
    public static void setSysSettingMap(SysSetting setting){
        if(sysSettingMap.isEmpty()){
            sysSettingMap.put("sysSetting",setting);
        }else{
            sysSettingMap.replace("sysSetting",setting);
        }
    }
}
