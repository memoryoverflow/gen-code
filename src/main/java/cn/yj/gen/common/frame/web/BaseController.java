package cn.yj.gen.common.frame.web;

import cn.yj.gen.common.utils.ServletUtils;
import cn.yj.gen.common.frame.web.model.OrderBy;
import cn.yj.gen.common.frame.web.model.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-19 13:51
 */
public abstract class BaseController<E>
{

    protected <T> T getCurrentUserId() {return null;}

    protected <T> T getCurrentUser()
    {
        return null;
    }


    protected <T> T getUserRoles()
    {
        return null;
    }

    protected <T> T getUserToken()
    {
        return null;
    }


    protected Map<String, Object> getMap()
    {
        return new HashMap<>(16);
    }

    protected Map<String, Object> getMap(String key, String val)
    {
        Map<String, Object> map = getMap();
        map.put(key, val);
        return map;
    }


    /**
     * 分页数据 默认使用更新时间降序
     */
    protected Page<E> page(OrderBy orderBy)
    {
        return getPage(orderBy);
    }

    protected Page<E> page(OrderBy orderBy, int current, int size)
    {
        Page<E> page = getPage(orderBy);
        page.setSize(size);
        page.setCurrent(current);
        return page;
    }

    /**
     * 自定义分页数据 默认使用更新时间降序
     */
    protected Page<E> page()
    {
        // return getPage(new OrderBy(OrderBy.Direction.ASC, "id"));
        return getPage(new OrderBy(OrderBy.Direction.DESC, "create_time"));
    }

    /**
     * <br>
     * 自定义分页条件
     */
    private Page<E> getPage(OrderBy orderBy)
    {
        Integer size = ServletUtils.getParamInteger("pageSize");

        if (size != null && size == -1)
        {
            size = Integer.MAX_VALUE;
        }

        Integer pageNum = ServletUtils.getParamInteger("pageNum");
        Page<E> page = new Page<>(pageNum == null ? 0 : pageNum, size == null ? 15 : size);
        if (orderBy.getDirection().isAscending())
        {
            page.setAsc(orderBy.getColumns());
        }
        else
        {
            page.setDesc(orderBy.getColumns());
        }
        return page;
    }

    protected Page pageMap(OrderBy orderBy)
    {
        Integer size = ServletUtils.getParamInteger("pageSize");

        if (size != null && size == -1)
        {
            size = Integer.MAX_VALUE;
        }

        Integer pageNum = ServletUtils.getParamInteger("pageNum");
        Page<Map<String, Object>> page = new Page<>(pageNum == null ? 0 : pageNum, size == null ? 15 : size);
        if (orderBy.getDirection().isAscending())
        {
            page.setAsc(orderBy.getColumns());
        }
        else
        {
            page.setDesc(orderBy.getColumns());
        }
        return page;
    }


    /**
     * 返回成功
     */
    protected R success()
    {
        return R.success();
    }

    /**
     * 返回失败消息
     */
    protected R error()
    {
        return R.error();
    }

    /**
     * 返回成功消息
     */
    protected R successMsg(String message)
    {
        return R.success(message);
    }

    /**
     * 返回成功消息
     */
    protected R success(Object data)
    {
        return R.success(data);
    }

    /**
     * 返回失败消息
     */
    protected R error(String message)
    {
        return R.error(message);
    }

    /**
     * 根据修改搜影响的行数返回结果
     */
    protected R result(boolean flag)
    {
        return flag == true ? success() : error();
    }

    protected R result(boolean flag, String msg)
    {
        return flag == true ? successMsg(msg) : error();
    }


    protected R result(int row)
    {
        return row > 0 ? success() : error();
    }

    protected R result(int row, String msg)
    {
        return row > 0 ? successMsg(msg) : error();
    }
}
