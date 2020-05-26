package cn.yj.gen.gencode.jdbc.service;

import cn.yj.gen.gencode.table.LTable;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-11-06 00:23
 */
public abstract class DataBaseService
{
    public abstract List<LTable> getTables() throws SQLException, ConnectException;

    public abstract List<String> getTableNames() throws SQLException, ConnectException;
}
