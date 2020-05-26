package cn.yj.gen.gencode.common;

/**
 * <br>
 * Mysql数据库类型的字段类型枚举
 * @author 永健
 * @since 2019/5/7 14:41
 */
public enum ColumnType
{
    INT("INT","Integer")
    ,VARCHAR("VARCHAR","String")
    ,CHAR("CHAR","String")
    ,BLOB("BLOB","byte[]")
    ,TEXT("TEXT","String")
    ,INTEGER("INTEGER","Long")
    ,TINYINT("TINYINT","Integer")
    ,SMALLINT("SMALLINT","Integer")
    ,MEDIUMINT("MEDIUMINT","Integer")
    ,BIT("BIT","Boolean")
    ,BIGINT("BIGINT","BigInteger")
    ,FLOAT("FLOAT","Float")
    ,DOUBLE("DOUBLE","Double")
    ,DECIMAL("DECIMAL","BigDecimal")
    ,BOOLEAN("BOOLEAN","Integer")
    ,ID("ID","Long")
    ,DATE("DATE","Date")
    ,TIME("TIME","Date")
    ,DATETIME("DATETIME","Date")
    ,TIMESTAMP("TIMESTAMP","Date")
    ,YEAR("YEAR","Date")
    ,LONGTEXT("LONGTEXT","String")
    ,LONGBLOB("LONGBLOB","String");

    private String jdbcType;
    private String javaType;

    ColumnType(String jdbcType, String javaType)
    {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static String getJavaType(String jdbctype){
        ColumnType[] values = values();
        for (ColumnType columnType : values)
        {
            String lowerCase = columnType.getJdbcType().toLowerCase();
            if (lowerCase.equals(jdbctype)){
                return columnType.getJavaType();
            }
        }
        return null;
    }



    public String getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }

    public String getJavaType()
    {
        return javaType;
    }

    public void setJavaType(String javaType)
    {
        this.javaType = javaType;
    }}
