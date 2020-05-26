package cn.yj.gen.gencode.jdbc;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-19 13:09
 */
public class DbProperties
{
    /**
     * <br>
     * 数据库
     */
    @NotBlank(message = "数据库名称不能为空")
    private String databaseName;

    /**
     * <br>
     * 账号
     */
    @NotBlank(message = "数据库账户不能为空")
    private String userName;

    /**
     * <br>
     * 账户密码
     */
    @NotBlank(message = "数据库密码不能为空")
    private String password;

    /**
     * <br>
     * 数据库 host
     */
    @NotBlank(message = "数据库连接地址")
    private String host;

    /**
     * <br>
     * 数据库连接端口
     */
    @NotNull(message = "数据库端口不能为空")
    private Integer port;

    public DbProperties(@NotBlank(message = "数据库名称不能为空") String databaseName, @NotBlank(message = "数据库账户不能为空") String userName, @NotBlank(message = "数据库密码不能为空") String password, @NotBlank(message = "数据库连接地址") String host, @NotNull(message = "数据库端口不能为空") Integer port)
    {
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public String getDriverClassName()
    {
        return "com.mysql.jdbc.Driver";
    }

    public String getUrl()
    {
        return "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.databaseName + "?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public DbProperties setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
        return this;
    }

    public String getUserName()
    {
        return userName;
    }

    public DbProperties setUserName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public String getPassword()
    {
        return password;
    }

    public DbProperties setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public String getHost()
    {
        return host;
    }

    public DbProperties setHost(String host)
    {
        this.host = host;
        return this;
    }

    public Integer getPort()
    {
        return port;
    }

    public DbProperties setPort(Integer port)
    {
        this.port = port;
        return this;
    }
}
