package cn.yj.gen.common.frame.web.model;

import javax.validation.constraints.NotBlank;

/**
 * </p>
 *
 * @author 永健
 * @since 2020-04-04 23:53
 */
public class LoginModel
{
    @NotBlank(message = "账户不能为空")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "严重码不能为空")
    private String code;

    public String getLoginName()
    {
        return loginName;
    }

    public LoginModel()
    {
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LoginModel(String loginName, String password)
    {
        this.loginName = loginName;
        this.password = password;
    }
}