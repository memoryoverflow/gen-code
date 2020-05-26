package cn.yj.gen.gencode.table;

import cn.yj.gen.gencode.jdbc.DbProperties;
import cn.yj.gen.gencode.jdbc.LJdbc;
import cn.yj.gen.gencode.jdbc.service.DataBaseExecute;

import java.net.ConnectException;
import java.util.List;

/**
 * <br>
 *
 *  获取数据库的所有表
 *
 * @author 永健
 * @since 2020-05-10 18:51
 */
public class TableParser extends Parser
{
    private LJdbc jdbc;

    @Override
    public List<LTable> parser(DbProperties dbProperties) throws Exception
    {
        initJdbc(dbProperties);
        DataBaseExecute dataBaseExcute = new DataBaseExecute(jdbc);

        List<LTable> tables = dataBaseExcute.getTables();

        return tables;
    }

    private void initJdbc(DbProperties configProperties) throws ConnectException
    {
        this.jdbc =
                new LJdbc(new DbProperties(configProperties.getDatabaseName(), configProperties.getUserName(), configProperties.getPassword(), configProperties.getHost(), configProperties.getPort()));
    }
}
