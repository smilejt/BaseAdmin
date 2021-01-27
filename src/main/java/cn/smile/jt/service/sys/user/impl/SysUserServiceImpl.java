package cn.smile.jt.service.sys.user.impl;

import cn.smile.jt.common.base.BaseServiceImpl;
import cn.smile.jt.common.constant.CommonConstant;
import cn.smile.jt.entity.form.user.UpdatePasswordForm;
import cn.smile.jt.entity.form.user.UpdateUserForm;
import cn.smile.jt.entity.pojo.sys.SysUser;
import cn.smile.jt.entity.vo.sys.SysUserVo;
import cn.smile.jt.mapper.sys.user.SysUserMapper;
import cn.smile.jt.service.sys.user.SysUserService;
import cn.smile.jt.util.MD5Util;
import cn.smile.jt.util.SecurityUtil;
import cn.smile.jt.util.SysSettingUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
@SuppressWarnings("unused")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final String LOGIN_NAME_KEY = "login_name";
    private static final String IS_DELETE_KEY = "is_delete";
    private static final int IS_DELETE_VALUE = 0;

    @Resource
    private DataSource dataSource;

    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    public SysUserVo findByLoginName(String username) {

        log.info("[SysUserServiceImpl].[findByLoginName] ------> username = {}", username);

        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq(LOGIN_NAME_KEY, username);
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

        log.info("[SysUserServiceImpl].[updatePassword] ------> id = {}, form = {}", id, JSON.toJSONString(form));

        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(MD5Util.getMD5(form.getNewPassword()));
        user.setUpdateTime(LocalDateTime.now());
        user.setLastChangePwdTime(new Date());

        return getSysUserVo(id, user);
    }

    @Override
    public SysUserVo updateUser(UpdateUserForm form) {
        log.info("[SysUserServiceImpl].[updateUser] ------> form = {}", JSON.toJSONString(form));

        SysUserVo vo = this.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        if (!StringUtils.isEmpty(vo)) {
            SysUser user = new SysUser();
            BeanUtils.copyProperties(form, user);
            user.setUpdateTime(LocalDateTime.now());
            user.setId(vo.getId());

            if (baseMapper.updateById(user) > 0) {
                return this.findByLoginName(SecurityUtil.getLoginUser().getUsername());
            }
        }

        return null;
    }

    @Override
    public SysUserVo resetPassword(Long userId) {
        log.info("[SysUserServiceImpl].[resetPassword] ------> userId = {}", userId);

        SysUser user = new SysUser();
        user.setId(userId);
        user.setPassword(MD5Util.getMD5(SysSettingUtil.getSysSetting().getUserInitPassword()));
        user.setUpdateTime(LocalDateTime.now());

        return getSysUserVo(userId, user);
    }

    @Override
    public PersistentTokenRepository getPersistentTokenRepository() {
        log.info("[SysUserServiceImpl].[getPersistentTokenRepository] ------> start");
        return persistentTokenRepository();
    }

    @Override
    public IPage<SysUserVo> getOnLine() {
        log.info("[SysUserServiceImpl].[getOnLine] ------> start");
        List<SysUserVo> sysUserVoList = Lists.newArrayList();
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object allPrincipal : allPrincipals) {
            SysUserVo userVo = new SysUserVo();
            User user = (User) allPrincipal;
            userVo.setLoginName(user.getUsername());
            sysUserVoList.add(userVo);
        }
        IPage<SysUserVo> iPage = new Page<>();
        if (CollectionUtils.isEmpty(sysUserVoList)){
            iPage.setRecords(sysUserVoList);
            iPage.setTotal(sysUserVoList.size());
            iPage.setCurrent(CommonConstant.NUMBER_ONE);
            iPage.setSize(CommonConstant.NUMBER_THOUSAND);
        }

        return iPage;
    }

    @Override
    public boolean forced(String loginName) {

        log.info("[SysUserServiceImpl].[forced] ------> start");
        //清除remember-me持久化tokens
        this.getPersistentTokenRepository().removeUserTokens(loginName);

        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object allPrincipal : allPrincipals) {
            User user = (User) allPrincipal;
            if(user.getUsername().equals(loginName)){
                List<SessionInformation> allSessions = sessionRegistry.getAllSessions(user, true);
                if (allSessions != null) {
                    for (SessionInformation sessionInformation : allSessions) {
                        sessionInformation.expireNow();
                        sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                    }
                }
                break;
            }
        }
        return true;
    }

    private PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }

    private SysUserVo getSysUserVo(Long userId, SysUser user) {
        if (baseMapper.updateById(user) > 0) {
            SysUser newUser = baseMapper.selectById(userId);
            SysUserVo vo = new SysUserVo();
            BeanUtils.copyProperties(newUser, vo);
            vo.setPassword(null);
            return vo;
        }

        return null;
    }
}
