package cn.yj.gen.gencode.table;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-11-06 10:27
 */
public class Field
{
    /**
     * <br>
     * 列名
     */
    private String name;


    /**
     * <br>
     * set方法
     */
    private String setName;

    /**
     * <br>
     * get 方法
     */
    private String getName;

    /**
     * <br>
     * 列名
     */
    private String column;

    /**
     * <br>
     * 数据库的类型
     */
    private String jdbcType;

    private String javaType;

    /**
     * <br>
     * 是否是主键
     */
    private boolean primaryKey;

    /**
     * <br>
     * 是否自增
     */
    private boolean auto;

    /**
     * <br>
     * 列注释
     */
    private String comment;

    public String getName()
    {
        return name;
    }

    public Field setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getJdbcType()
    {
        return jdbcType;
    }

    public Field setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
        return this;
    }

    public boolean isPrimaryKey()
    {
        return primaryKey;
    }

    public Field setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
        return this;
    }

    public boolean isAuto()
    {
        return auto;
    }

    public Field setAuto(boolean auto)
    {
        this.auto = auto;
        return this;
    }

    public String getComment()
    {
        return comment;
    }

    public String getJavaType()
    {
        return javaType;
    }

    public Field setJavaType(String javaType)
    {
        this.javaType = javaType;
        return this;
    }

    public Field setComment(String comment)
    {
        this.comment = comment;
        return this;
    }

    public String getColumn()
    {
        return column;
    }

    public Field setColumn(String column)
    {
        this.column = column;
        return this;
    }

    public String getSetName()
    {
        return setName;
    }

    public Field setSetName(String setName)
    {
        this.setName = setName;
        return this;
    }

    public String getGetName()
    {
        return getName;
    }

    public Field setGetName(String getName)
    {
        this.getName = getName;
        return this;
    }


}
