package cn.yj.gen.common.frame.web;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-06 14:21
 */
@Component
public class ShiroRealm extends AuthorizingRealm
{
    private final static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        return new SimpleAuthorizationInfo();

    }

    /**
     * 登陆
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        //加盐 计算盐值 保证每个加密后的 MD5 不一样 登录名+随机字符窜
        //ByteSource credentialsSalt = ByteSource.Util.bytes(user.getId() + Encryption.SALT);
        //return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, this.getName());
        return null;
    }


}
