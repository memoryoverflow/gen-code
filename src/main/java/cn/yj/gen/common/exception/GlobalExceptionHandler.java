package cn.yj.gen.common.exception;

import cn.yj.gen.common.enums.Error;
import cn.yj.gen.common.frame.web.model.R;
import cn.yj.gen.common.utils.ServletUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * <p>
 * 全局异常拦截器
 * </p>
 *
 * @author 永健
 * @since 2020-03-14 05:43
 */
@RestControllerAdvice
@RestController
public class GlobalExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleRRException(MissingServletRequestParameterException e)
    {
        e.printStackTrace();
        return R.error(Error.参数绑定异常,"缺少必填参数");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handleRRException(HttpMessageNotReadableException e)
    {
        e.printStackTrace();
        return R.error(Error.参数绑定异常,"参数转换异常，请注意参数格式/类型");
    }

    /**
     * GET,POST 等方法
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleRRException(HttpRequestMethodNotSupportedException e)
    {
        e.printStackTrace();
        return R.error(Error.请求方式错误, "请求方式错误:请试试GET/POST/UPDATE...");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleException(MethodArgumentNotValidException e)
    {
        // 参数绑定异常
        BindingResult bindingResult = e.getBindingResult();
        for (ObjectError error : bindingResult.getAllErrors())
        {
            String defaultMessage = error.getDefaultMessage();
            return R.error(Error.参数绑定异常, "参数绑定异常:" + defaultMessage);
        }
        return R.error(Error.系统异常);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public R handleRRException(ServiceException e)
    {
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e)
    {
        e.printStackTrace();
        return R.error(Error.系统异常);
    }

    /**
     * <br>
     * 404 错误
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.error(Error.请求路径异常, ServletUtils.getRequestURL());
    }

    @ExceptionHandler({AuthorizationException.class})
    public R AuthorizationException(Exception e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.error(Error.权限受限);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public R UnauthorizedException(Exception e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.error(Error.权限受限, "当前请求没有权限");
    }


}
