package cn.yj.gen.gencode.config;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-19 16:43
 */
public class ConfigProperties
{

    /**
     * 类作者
     */
    private String author;

    /**
     * <br>
     * 项目名
     */
    @NotBlank(message = "项目名不能为空")
    private String projectName;

    @NotBlank(message = "项目的包名不能为空")
    private String groupId;

    @NotBlank(message = "项目的包名不能为空")
    private String artifactId;

    /**
     * Springboot 版本
     */
    private String version;

    /**
     * 表前缀
     */
    private String tablePrefix;

    @NotBlank(message = "请选择包名")
    private String packageName;

    /**
     * 是否驼峰
     */
    private boolean hump;

    /**
     * 是否使用lombok 注解
     */
    private boolean lombok;


    @NotEmpty(message = "请选择表")
    private String[] tables;

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

    public ConfigProperties setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
        return this;
    }

    public String getUserName()
    {
        return userName;
    }

    public ConfigProperties setUserName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public String getPassword()
    {
        return password;
    }

    public ConfigProperties setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public String getHost()
    {
        return host;
    }

    public ConfigProperties setHost(String host)
    {
        this.host = host;
        return this;
    }

    public Integer getPort()
    {
        return port;
    }

    public ConfigProperties setPort(Integer port)
    {
        this.port = port;
        return this;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public ConfigProperties setProjectName(String projectName)
    {
        this.projectName = projectName;
        return this;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public ConfigProperties setGroupId(String groupId)
    {
        this.groupId = groupId;
        return this;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public ConfigProperties setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
        return this;
    }

    public String getVersion()
    {
        return version;
    }

    public ConfigProperties setVersion(String version)
    {
        this.version = version;
        return this;
    }

    public String getAuthor()
    {
        return author;
    }

    public ConfigProperties setAuthor(String author)
    {
        this.author = author;
        return this;
    }

    public String getTablePrefix()
    {
        return tablePrefix;
    }

    public ConfigProperties setTablePrefix(String tablePrefix)
    {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public ConfigProperties setPackageName(String packageName)
    {
        this.packageName = packageName;
        return this;
    }

    public boolean isHump()
    {
        return hump;
    }

    public ConfigProperties setHump(boolean hump)
    {
        this.hump = hump;
        return this;
    }

    public boolean isLombok()
    {
        return lombok;
    }

    public ConfigProperties setLombok(boolean lombok)
    {
        this.lombok = lombok;
        return this;
    }


    public String[] getTables()
    {
        return tables;
    }

    public ConfigProperties setTables(String[] tables)
    {
        this.tables = tables;
        return this;
    }

}
