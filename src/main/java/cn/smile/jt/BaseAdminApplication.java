package cn.smile.jt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * @author longjuntao
 * 开启异步调用
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class BaseAdminApplication {

    public static void main(String[] args) {
        log.info("[BaseAdminApplication].[main] ------> BaseAdminApplication Start <------");
        SpringApplication.run(BaseAdminApplication.class, args);
    }

    /**
     * 解决不能注入session注册表问题
     */
    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
