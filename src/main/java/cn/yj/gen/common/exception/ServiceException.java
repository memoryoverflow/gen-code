package cn.yj.gen.common.exception;


import cn.yj.gen.common.enums.Error;

/**
 * <p>
 * 自定义异常
 * </p>
 *
 * @author 永健
 * @since 2020-03-03 17:41
 */
public class ServiceException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private String msg;
    private Error error;
    private int code = -1;

    public ServiceException(Error error)
    {
        super(error.getErrMsg());
        this.error = error;
        this.code = error.getCode();
        this.msg = error.getErrMsg();
    }

    public ServiceException(Error error, String msg)
    {
        super(msg);
        this.error = error;
        this.code = error.getCode();
        this.msg = error.getErrMsg() + " " + msg;
    }

    public ServiceException()
    {
        super();
    }

    public ServiceException(String msg)
    {
        super(msg);
        this.msg = msg;
    }

    public ServiceException(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }


    public ServiceException(String msg, int code, Throwable e)
    {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public ServiceException setMsg(String msg)
    {
        this.msg = msg;
        return this;
    }

    public int getCode()
    {
        return code;
    }

    public ServiceException setCode(int code)
    {
        this.code = code;
        return this;
    }

    public Error getError()
    {
        return error;
    }
}
