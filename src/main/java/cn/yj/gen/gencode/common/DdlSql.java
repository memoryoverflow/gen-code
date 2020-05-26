package cn.yj.gen.gencode.common;

/**
 * <br>
 *
 * @author 永健
 * @since 2019/5/7 14:51
 */
public enum DdlSql
{
    SHOW_COLUM("show full columns from %s","获取数据库的字段");

    private String ddlSql;
    private String desc;

    DdlSql(String ddlSql, String desc){
        this.ddlSql=ddlSql;
        this.desc=desc;
    }

    public String getDdlSql()
    {
        return ddlSql;
    }

    public void setDdlSql(String ddlSql)
    {
        this.ddlSql = ddlSql;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }}
