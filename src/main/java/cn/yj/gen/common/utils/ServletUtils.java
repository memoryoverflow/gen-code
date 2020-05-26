package cn.yj.gen.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 永健
 * 客户端工具类
 * @since 2018/12/11 12:53
 */
public class ServletUtils
{

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static String getRequestURI()
    {
        return getRequestAttributes().getRequest().getRequestURI();
    }

    public static String getRequestURL()
    {
        return getRequestAttributes().getRequest().getRequestURL().toString();
    }


    /**
     * 是否是Ajax异步请求
     *
     * @param
     */
    public static boolean isAjaxRequest()
    {
        HttpServletRequest request = getRequest();
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1)
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml"))
        {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml"))
        {
            return true;
        }

        return false;
    }

    public static Integer getParamInteger(String name)
    {
        String parameter = getRequest().getParameter(name);
        if (StringUtils.isNotBlank(parameter))
        {
            return Integer.parseInt(parameter);
        }
        return null;
    }
}
