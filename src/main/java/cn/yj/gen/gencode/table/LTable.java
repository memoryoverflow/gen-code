package cn.yj.gen.gencode.table;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-11-06 10:25
 */
public class LTable
{
    /**
     * <br>
     * 表名
     */
    private String tableName;

    /**
     * <br>
     * 实体名 不包含后缀 例如 User
     */
    private String entityName;

    /**
     * 实体名小写 例如：user
     */
    private String entityName_;

    /**
     * <br>
     * service UserService
     */
    private String serviceName;

    /**
     *  userService
     */
    private String serviceName_;

    /**
     * <br>
     * 实现 UserServiceImpl
     */
    private String serviceImplName;

    /**
     * <br>
     * 控制 UserController
     */
    private String controllerName;

    /**
     * <br>
     * 接口 UserMapper
     */
    private String mapperName;

    /**
     * userMapper
     */
    private String mapperName_;


    /**
     * 父类的权限定包名
     *
     */
    private String baseControllerPackage;

    private String baseEntityPackage;

    private String baseMapperPackage;

    private String baseServicePackage;

    private String baseServiceImplPackage;


    /**
     * 父类的名字
     */
    private String baseControllerJavaName;

    private String baseEntityJavaName;

    private String baseMapperJavaName;

    private String baseServiceJavaName;

    private String baseServiceImplJavaName;


    /**
     * 类的请求跟路径 /user
     */
    private String reqPath;


    /**
     * <br>
     * UserMapper
     */
    private String mapperXmlJavaName;


    /**
     * 项目名字
     */
    private String projectPackage;


    /**
     * <br>
     * 表注释
     */
    private String comment;


    /**
     * 主键名
     */
    private String primaryKeyName;


    /**
     * 主键类型
     */
    private String primaryKeyType;

    /**
     * <br>
     * 数据库的列
     */
    private List<Field> fields;

    public String getProjectPackage()
    {
        return projectPackage;
    }

    public LTable setProjectPackage(String projectPackage)
    {
        this.projectPackage = projectPackage;
        return this;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getMapperName()
    {
        return mapperName;
    }

    public void setMapperName(String mapperName)
    {
        this.mapperName = mapperName;
    }

    public String getEntityName()
    {
        return entityName;
    }

    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceImplName()
    {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName)
    {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName()
    {
        return controllerName;
    }

    public void setControllerName(String controllerName)
    {
        this.controllerName = controllerName;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public List<Field> getFields()
    {
        return fields;
    }

    public void setFields(List<Field> fields)
    {
        this.fields = fields;
    }

    public String getEntityName_()
    {
        return entityName_;
    }

    public LTable setEntityName_(String entityName_)
    {
        this.entityName_ = entityName_;
        return this;
    }

    public String getServiceName_()
    {
        return serviceName_;
    }

    public LTable setServiceName_(String serviceName_)
    {
        this.serviceName_ = serviceName_;
        return this;
    }

    public String getMapperName_()
    {
        return mapperName_;
    }

    public LTable setMapperName_(String mapperName_)
    {
        this.mapperName_ = mapperName_;
        return this;
    }

    public String getBaseControllerPackage()
    {
        return baseControllerPackage;
    }

    public LTable setBaseControllerPackage(String baseControllerPackage)
    {
        this.baseControllerPackage = baseControllerPackage;
        return this;
    }

    public String getBaseEntityPackage()
    {
        return baseEntityPackage;
    }

    public LTable setBaseEntityPackage(String baseEntityPackage)
    {
        this.baseEntityPackage = baseEntityPackage;
        return this;
    }

    public String getBaseMapperPackage()
    {
        return baseMapperPackage;
    }

    public LTable setBaseMapperPackage(String baseMapperPackage)
    {
        this.baseMapperPackage = baseMapperPackage;
        return this;
    }

    public String getBaseServicePackage()
    {
        return baseServicePackage;
    }

    public LTable setBaseServicePackage(String baseServicePackage)
    {
        this.baseServicePackage = baseServicePackage;
        return this;
    }

    public String getBaseServiceImplPackage()
    {
        return baseServiceImplPackage;
    }

    public LTable setBaseServiceImplPackage(String baseServiceImplPackage)
    {
        this.baseServiceImplPackage = baseServiceImplPackage;
        return this;
    }

    public String getBaseControllerJavaName()
    {
        return baseControllerJavaName;
    }

    public LTable setBaseControllerJavaName(String baseControllerJavaName)
    {
        this.baseControllerJavaName = baseControllerJavaName;
        return this;
    }

    public String getBaseEntityJavaName()
    {
        return baseEntityJavaName;
    }

    public LTable setBaseEntityJavaName(String baseEntityJavaName)
    {
        this.baseEntityJavaName = baseEntityJavaName;
        return this;
    }

    public String getBaseMapperJavaName()
    {
        return baseMapperJavaName;
    }

    public LTable setBaseMapperJavaName(String baseMapperJavaName)
    {
        this.baseMapperJavaName = baseMapperJavaName;
        return this;
    }

    public String getBaseServiceJavaName()
    {
        return baseServiceJavaName;
    }

    public LTable setBaseServiceJavaName(String baseServiceJavaName)
    {
        this.baseServiceJavaName = baseServiceJavaName;
        return this;
    }

    public String getBaseServiceImplJavaName()
    {
        return baseServiceImplJavaName;
    }

    public LTable setBaseServiceImplJavaName(String baseServiceImplJavaName)
    {
        this.baseServiceImplJavaName = baseServiceImplJavaName;
        return this;
    }

    public String getReqPath()
    {
        return reqPath;
    }

    public LTable setReqPath(String reqPath)
    {
        this.reqPath = reqPath;
        return this;
    }

    public String getMapperXmlJavaName()
    {
        return mapperXmlJavaName;
    }

    public LTable setMapperXmlJavaName(String mapperXmlJavaName)
    {
        this.mapperXmlJavaName = mapperXmlJavaName;
        return this;
    }

    public String getPrimaryKeyName()
    {
        return primaryKeyName;
    }

    public LTable setPrimaryKeyName(String primaryKeyName)
    {
        this.primaryKeyName = primaryKeyName;
        return this;
    }

    public String getPrimaryKeyType()
    {
        return primaryKeyType;
    }

    public LTable setPrimaryKeyType(String primaryKeyType)
    {
        this.primaryKeyType = primaryKeyType;
        return this;
    }
}
