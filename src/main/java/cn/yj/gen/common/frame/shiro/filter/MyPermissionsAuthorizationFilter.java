package cn.yj.gen.common.frame.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>
 * 接口权限
 * <p>
 * 动态自定义处理，接口的权限，不依赖与shiro的注解权限，
 * 这样子就无法动态的更改接口权限了
 * </p>
 *
 * @author 永健
 * @since 2020-04-06 15:32
 */
public class MyPermissionsAuthorizationFilter extends AuthorizationFilter
{
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }

        return isPermitted;
    }
}
