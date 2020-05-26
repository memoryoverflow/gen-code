package cn.yj.gen.common.frame.web.model;

import cn.yj.gen.common.enums.Error;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-19 13:44
 */
public class R
{
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = -1;
    private static final String SUCCESS_R = "操作成功！";
    private static final String ERROR_R = "操作失败！";


    /**
     * 业务结果码
     */
    private int code;

    /**
     * 信息提示
     */
    private String msg;

    /**
     * 结果集
     */
    private Object data;

    private R() {}

    public static R build()
    {
        return new R();
    }


    public static R success()
    {

        return build()
                .setCode(SUCCESS_CODE)
                .setMsg(SUCCESS_R);
    }

    public static R success(Object data)
    {
        return build()
                .setCode(SUCCESS_CODE)
                .setMsg(SUCCESS_R)
                .setData(data);
    }

    public static R success(String msg)
    {

        return build().setCode(SUCCESS_CODE)
                .setMsg(msg);
    }


    public static R error()
    {

        return build().setCode(ERROR_CODE)
                .setMsg(ERROR_R);

    }

    public static R error(String msg)
    {

        return build().setCode(ERROR_CODE)
                .setMsg(msg);

    }

    public static R error(int code, String msg)
    {

        return build().setCode(code)
                .setMsg(msg);

    }

    public static R error(Error error,String msg)
    {

        return build().setCode(error.getCode())
                .setMsg(error.getErrMsg()+" "+msg);

    }

    public static R error(Error error)
    {

        return build().setCode(error.getCode())
                .setMsg(error.getErrMsg());

    }

    public int getCode()
    {
        return code;
    }

    public R setCode(int code)
    {
        this.code = code;
        return this;
    }

    public String getMsg()
    {
        return msg;
    }

    public R setMsg(String msg)
    {
        this.msg = msg;
        return this;
    }

    public Object getData()
    {
        return data;
    }

    public R setData(Object data)
    {
        this.data = data;
        return this;
    }
}
