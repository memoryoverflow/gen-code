package cn.yj.gen.common.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-03-16 16:52
 */
public enum LPATTERM
{
    YMDMS("yyyy/MM/dd HH:mm:ss"),YMDMS_("yyyy-MM-dd HH:mm:ss"), YMD("yyyy-MM-dd"), YMDH("yyyy-MM-dd HH:mm");
    private String expression;

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    LPATTERM(String expression)
    {
        this.expression = expression;
    }
}
