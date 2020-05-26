package cn.yj.gen.gencode.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.sql.*;

/**
 * <br>
 * 数据库连接工具
 *
 * @author 永健
 * @since 2019/5/7 14:30
 */
public class LJdbc
{
    private static Logger logger = LoggerFactory.getLogger(LJdbc.class);

    private DbProperties dbProperties;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;


    public LJdbc(DbProperties dbProperties) throws ConnectException
    {
        this.dbProperties = dbProperties;
        getConnection();
    }

    public Connection getConnection() throws ConnectException
    {
        if (connection == null)
        {
            try
            {
                Class.forName(this.dbProperties.getDriverClassName());
                logger.info("@_@!  数据库驱动加载成功");
                connection = DriverManager.getConnection(this.dbProperties.getUrl(), this.dbProperties.getUserName(), this.dbProperties.getPassword());
                connection.setAutoCommit(false);
                logger.info("-----------  @_@!  数据库连接成功  -----------");
            } catch (Exception e)
            {
                logger.error("@_@!  数据库连接失败," + e);
                e.printStackTrace();
                throw new ConnectException("数据库连接异常:" + e.getMessage());
            }
        }
        return connection;
    }


    private PreparedStatement getPreparedStatement(String sql)
    {
        try
        {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ConnectException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String sql) throws SQLException
    {
        return getPreparedStatement(sql).executeUpdate();
    }

    public ResultSet executeQuery(String sql)
    {
        ResultSet resultSet = null;
        try
        {
            resultSet = getPreparedStatement(sql).executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
            logger.error(sql);
        }
        return resultSet;
    }


    public PreparedStatement executePreparedStatement(String sql)
    {
        return getPreparedStatement(sql);
    }

    public void close()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }

            if (preparedStatement != null)
            {
                preparedStatement.close();
            }

            logger.info("------------  关闭数据库连接 ---------------");

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public DbProperties getDbProperties()
    {
        return dbProperties;
    }

    public LJdbc setDbProperties(DbProperties dbProperties)
    {
        this.dbProperties = dbProperties;
        return this;
    }

    public LJdbc setConnection(Connection connection)
    {
        this.connection = connection;
        return this;
    }

    public PreparedStatement getPreparedStatement()
    {
        return preparedStatement;
    }

    public LJdbc setPreparedStatement(PreparedStatement preparedStatement)
    {
        this.preparedStatement = preparedStatement;
        return this;
    }
}
