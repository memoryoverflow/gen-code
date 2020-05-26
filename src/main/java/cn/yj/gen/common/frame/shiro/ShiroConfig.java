package cn.yj.gen.common.frame.shiro;

import cn.yj.gen.common.frame.shiro.filter.FilterChainDefinitionMapBuider;
import cn.yj.gen.common.frame.shiro.filter.LoginFilter;
import cn.yj.gen.common.frame.shiro.filter.MyPermissionsAuthorizationFilter;
import cn.yj.gen.common.frame.shiro.filter.MyRolesAuthorizationFilter;
import cn.yj.gen.common.frame.web.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * shiro  配置类
 * </p>
 *
 * @author 永健
 * @since 2020-04-06 14:10
 */
@Configuration
public class ShiroConfig
{

    @Bean("securityManager")
    public SecurityManager securityManager(ShiroRealm shiroRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(defaultWebSessionManager());
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher()
    {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        //设置加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        return hashedCredentialsMatcher;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(ShiroRealm shiroRealm)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager(shiroRealm));


        // 注意 这个自定义filter 不要放到Spring管理 否则会在在拦截连中拦截所有， login anon 配置了不起作用的
        Map<String, Filter> filterHashMap = new HashMap<>();
        filterHashMap.put("loginFilter", new LoginFilter());
        filterHashMap.put("myRoles", new MyRolesAuthorizationFilter());
        filterHashMap.put("myPerm", new MyPermissionsAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterHashMap);

        // 拦截URL
        shiroFilterFactoryBean.setFilterChainDefinitionMap(FilterChainDefinitionMapBuider.BuiderFilterChainDefinitionMap());

        return shiroFilterFactoryBean;
    }


    @Bean("sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager()
    {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();

        // defaultWebSessionManager.setSessionDAO(ShiroRedis.getRedisSessionDAO());
        // defaultWebSessionManager.setCacheManager(ShiroRedis.getRedisCacheManager());

        // 没5秒检查一次session是否过期
        //defaultWebSessionManager.setSessionValidationInterval(1000);

        //设置session超时时间。一旦超时将其删除
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);

        return defaultWebSessionManager;
    }


    /**
     * 会话id
     */
    @Bean
    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator()
    {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 启用权限注解
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 权限注解处理器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public StaticMethodMatcherPointcutAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

}
