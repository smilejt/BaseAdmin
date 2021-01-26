package cn.smile.jt.service.user.impl;

import cn.smile.jt.common.base.BaseServiceImpl;
import cn.smile.jt.entity.form.user.UpdatePasswordForm;
import cn.smile.jt.entity.pojo.SysUser;
import cn.smile.jt.entity.vo.SysUserVo;
import cn.smile.jt.mapper.user.SysUserMapper;
import cn.smile.jt.service.user.SysUserService;
import cn.smile.jt.util.MD5Util;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author longjuntao
 * @since 2021/1/26 10:34
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final String USER_NAME_KEY = "user_name";
    private static final String IS_DELETE_KEY = "is_delete";
    private static final int IS_DELETE_VALUE = 0;

    @Override
    public SysUserVo findByLoginName(String username) {

        log.info("[SysUserServiceImpl].[findByLoginName] ------> username = {}", username);

        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq(USER_NAME_KEY, username);
        qw.eq(IS_DELETE_KEY, IS_DELETE_VALUE);

        SysUser sysUser = baseMapper.selectOne(qw);
        if (!StringUtils.isEmpty(sysUser)) {
            SysUserVo vo = new SysUserVo();
            BeanUtils.copyProperties(sysUser, vo);
            return vo;
        }

        return null;
    }

    @Override
    public SysUserVo updatePassword(Long id, UpdatePasswordForm form) {

        log.info("[SysUserServiceImpl].[SysUserServiceImpl] ------> id = {}, form = {}", id, JSON.toJSONString(form));

        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(MD5Util.getMD5(form.getNewPassword()));
        user.setUpdateTime(LocalDateTime.now());
        user.setLastChangePwdTime(new Date());

        if (baseMapper.updateById(user) > 0){
            SysUser tempUser = baseMapper.selectById(id);
            SysUserVo vo = new SysUserVo();
            BeanUtils.copyProperties(tempUser, vo);
            vo.setPassword(null);
            return vo;
        }
        return null;
    }
}
