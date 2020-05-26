package cn.yj.gen.gencode.table;

import cn.yj.gen.common.exception.ServiceException;
import cn.yj.gen.common.utils.StringUtils;
import cn.yj.gen.gencode.ConstVal;
import cn.yj.gen.gencode.StringPools;
import cn.yj.gen.gencode.common.ColumnType;
import cn.yj.gen.gencode.common.DdlSql;
import cn.yj.gen.gencode.config.ConfigProperties;
import cn.yj.gen.gencode.jdbc.DbProperties;
import cn.yj.gen.gencode.jdbc.LJdbc;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 *
 * @author 永健
 * @since 2020-05-10 20:34
 */
public class LTableHandler
{
    private ConfigProperties configProperties;
    private TableParser tableParser;
    private LJdbc jdbc;
    private DbProperties dbProperties;

    public LTableHandler(ConfigProperties configProperties) throws ConnectException
    {
        this.configProperties = configProperties;
        this.jdbc =
                new LJdbc(dbProperties = new DbProperties(configProperties.getDatabaseName(), configProperties.getUserName(), configProperties.getPassword(), configProperties.getHost(), configProperties.getPort()));
        tableParser = new TableParser();
    }

    public List<LTable> getTable() throws Exception
    {
        List<LTable> tables = tableParser.parser(dbProperties);

        List<LTable> filterTable = new ArrayList<>();

        String[] tableNames = configProperties.getTables();

        if (StringUtils.isEmpty(tableNames)){
            throw new ServiceException("请选择表");
        }

        for (int i = 0; i < tables.size(); i++)
        {
            for (String tableName : tableNames)
            {
                if (tableName.equals(tables.get(i).getTableName()))
                {
                    LTable table = tables.get(i);

                    // 表字段
                    ResultSet resultSet = jdbc.executeQuery(String.format(DdlSql.SHOW_COLUM.getDdlSql(), table.getTableName()));
                    // 解析表
                    installFields(resultSet, table, configProperties.isHump(), configProperties.isLombok());

                    handlerJavaName(table);

                    filterTable.add(table);
                }
            }
        }

        return filterTable;

    }

    private void installFields(ResultSet resultSet, LTable table, boolean isHump, boolean lombok) throws SQLException
    {
        List<Field> fields = new ArrayList<>();
        while (resultSet.next())
        {
            String colunmName = resultSet.getString(StringPools.FIELD);
            String fieldName = colunmName;
            String jdbcType = subColumType(resultSet.getString(StringPools.COLUMN_TYPE));
            String javaType = ColumnType.getJavaType(jdbcType);
            boolean pri = false;
            if (ConstVal.COLUMN_PK_val.equals(resultSet.getString(StringPools.COLUMN_PK)))
            {
                pri = true;
                table.setPrimaryKeyName(colunmName);
                table.setPrimaryKeyType(javaType);
            }

            boolean auto = false;
            if (ConstVal.COLUMN_PK_AUTO.equals(resultSet.getString(StringPools.IS_AUTOINCREMENT)))
            {
                auto = true;
            }
            String comment = resultSet.getString(StringPools.COMMENT);

            String setName = "";
            String getName = "";
            // 属性 驼峰转换
            if (isHump)
            {
                fieldName = StringUtils.lineToHump(colunmName);
            }

            if (!lombok)
            {
                // 开启驼峰
                if (isHump)
                {
                    setName = "set" + StringUtils.firstUp(StringUtils.lineToHump(colunmName));
                    getName = "get" + StringUtils.firstUp(StringUtils.lineToHump(colunmName));
                }
                else
                {
                    setName = "set" + StringUtils.firstUp(colunmName);
                    getName = "get" + StringUtils.firstUp(colunmName);
                }
            }


            fields.add(new Field().setName(fieldName)
                    .setJdbcType(jdbcType)
                    .setPrimaryKey(pri)
                    .setAuto(auto)
                    .setComment(comment)
                    .setJavaType(javaType)
                    .setColumn(colunmName)
                    .setSetName(setName)
                    .setGetName(getName));
        }

        table.setFields(fields);
    }

    protected String subColumType(String type)
    {
        return type.substring(0, type.indexOf("(") == -1 ? type.length() : type.indexOf("("));
    }

    private void handlerJavaName(LTable table)
    {
        String entityName = "";
        if (StringUtils.isNotBlank(configProperties.getTablePrefix()))
        {
            String replace = table.getTableName().replace(configProperties.getTablePrefix(), "");
            entityName = StringUtils.firstUp(StringUtils.lineToHump(replace));

        }

        // mapperNmae,serviceName ,ImplName,controllerName
        table.setEntityName(entityName);
        table.setMapperName(entityName + "Mapper");
        table.setServiceName(entityName + "Service");
        table.setServiceImplName(entityName + "ServiceImpl");
        table.setControllerName(entityName + "Controller");
        table.setMapperName(entityName + "Mapper");

        table.setMapperXmlJavaName(entityName + "Mapper");


        // 实体小写，mapper 小写 ，service小写
        table.setEntityName_(StringUtils.toLowerCaseFirstOne(entityName));
        table.setMapperName_(StringUtils.toLowerCaseFirstOne(table.getMapperName()));
        table.setServiceName_(StringUtils.toLowerCaseFirstOne(table.getServiceName()));

        // controller请求路径
        table.setReqPath(StringUtils.toLowerCaseFirstOne(entityName));

        // 当前生成的类 放在该包路径下
        table.setProjectPackage(configProperties.getPackageName());


    }


}
