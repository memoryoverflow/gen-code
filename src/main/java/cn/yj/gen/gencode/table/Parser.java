package cn.yj.gen.gencode.table;

import cn.yj.gen.gencode.jdbc.DbProperties;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-11-06 10:43
 */
public abstract class Parser
{
   public abstract List<LTable> parser(DbProperties dbProperties) throws Exception;
}
