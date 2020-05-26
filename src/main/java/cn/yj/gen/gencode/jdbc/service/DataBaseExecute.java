package cn.yj.gen.gencode.jdbc.service;

import cn.yj.gen.gencode.StringPools;
import cn.yj.gen.gencode.jdbc.LJdbc;
import cn.yj.gen.gencode.table.LTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-11-06 00:25
 */
public class DataBaseExecute extends DataBaseService
{
    private static Logger logger = LoggerFactory.getLogger(DataBaseExecute.class);


    private LJdbc jdbc;

    public DataBaseExecute(LJdbc jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public List<LTable> getTables() throws SQLException, ConnectException
    {

        logger.info("######################");
        logger.info("##  读取数据库中的所有表...");

        List<LTable> tables = new ArrayList<>();

        ResultSet resultSet = jdbc.getConnection().getMetaData().getTables(jdbc.getDbProperties().getDatabaseName(), null, null, new String[]{"TABLE", "VIEW"});
        while (resultSet.next())
        {
            LTable table = new LTable();
            table.setComment(resultSet.getString(StringPools.TABLE_REMARKS));
            table.setTableName(resultSet.getString(StringPools.TABLE_NAME));
            tables.add(table);
        }
        logger.info("##  读取完成！");
        logger.info("######################");
        logger.info("");
        return tables;
    }

    @Override
    public List<String> getTableNames() throws SQLException, ConnectException
    {
        List<LTable> tables = getTables();
        List<String> list = new ArrayList<>();
        tables.forEach(table -> {
            list.add(table.getTableName());
        });
        return list;
    }


}
