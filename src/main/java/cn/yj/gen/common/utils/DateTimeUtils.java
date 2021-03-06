package cn.yj.gen.common.utils;

import cn.yj.gen.common.enums.LPATTERM;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-03-03 17:41
 */
public class DateTimeUtils extends DateUtils
{


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date)
    {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time)
    {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒
     *
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time)
    {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time)
    {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern)
    {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param pattern
     * @return
     */
    public static String nowString(String pattern)
    {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取当前时间的指定格式
     *
     * @return
     */
    public static String nowString()
    {
        return formatTime(LocalDateTime.now(), LPATTERM.YMDH.getExpression());
    }


    public static String format(Date date, LPATTERM lpatterm)
    {
        if (StringUtils.isNull(date))
        {
            return null;
        }
        String format = DateFormatUtils.format(date, lpatterm.getExpression());

        return format;
    }

    public static Date format(String str) throws ParseException
    {
        return format(str, LPATTERM.YMD);
    }

    public static Date format(String str, LPATTERM lpatterm) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat(lpatterm.getExpression());
        return format.parse(str);
    }



    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field)
    {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field)
    {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field)
    {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS)
        {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS)
        {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }


    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time)
    {
        return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time)
    {
        return time.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * <br>
     * 获取多少天前或者后的日期
     * 负数 多少天前
     */
    public static Date getDateDayAgoOrAfter(Integer day)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * <br>
     * 获取多少小时前或者后的日期
     */
    public static Date getDateHoursAgoOrAfter(Integer hours)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }


    public static String format(Date date)
    {
        String format = DateFormatUtils.format(date, LPATTERM.YMDMS_.getExpression());
        return format;
    }

    /**
     * <br>
     * 得到本月的第一天
     */
    public static Date getMonthFirstDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat firstDay = new SimpleDateFormat(LPATTERM.YMDMS_.getExpression());
        return calendar.getTime();
    }

    /**
     * <br>
     * 得到本月的最后一天
     */
    public static Date getMonthLastDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat lastDay = new SimpleDateFormat(LPATTERM.YMDMS_.getExpression());
        return calendar.getTime();
    }

    public static Date timestampToDate(long timstamp) throws ParseException
    {
        String str = DateFormatUtils.format(timstamp, LPATTERM.YMDMS_.getExpression());
        SimpleDateFormat format = new SimpleDateFormat(LPATTERM.YMDMS_.getExpression());
        return format.parse(str);

    }

    public static Date getFirstDayOfMonth(int month)
    {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime()) + " 00:00:00";
        return cal.getTime();
    }

    /**
     * 获得该月最后一天
     *
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int month)
    {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = 0;
        //2月的平年瑞年天数
        if (month == 2)
        {
            lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
        }
        else
        {
            lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime()) + " 23:59:59";
        return cal.getTime();
    }


    public static void main(String[] args) throws ParseException
    {
        System.out.println(getFirstDayOfMonth(1));
        ;
        System.out.println(getFirstDayOfMonth(2));
        ;
        System.out.println(getFirstDayOfMonth(3));
        ;

        System.out.println(getLastDayOfMonth(1));
        ;
        System.out.println(getLastDayOfMonth(2));
        ;
        System.out.println(getLastDayOfMonth(3));
        ;
    }

}
