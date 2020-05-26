package cn.yj.gen.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-03-16 16:43
 */
public class CheckUtils
{
    private static Hashtable AREACODE = new Hashtable();

    static
    {
        AREACODE.put("11", "北京");
        AREACODE.put("12", "天津");
        AREACODE.put("13", "河北");
        AREACODE.put("14", "山西");
        AREACODE.put("15", "内蒙古");
        AREACODE.put("21", "辽宁");
        AREACODE.put("22", "吉林");
        AREACODE.put("23", "黑龙江");
        AREACODE.put("31", "上海");
        AREACODE.put("32", "江苏");
        AREACODE.put("33", "浙江");
        AREACODE.put("34", "安徽");
        AREACODE.put("35", "福建");
        AREACODE.put("36", "江西");
        AREACODE.put("37", "山东");
        AREACODE.put("41", "河南");
        AREACODE.put("42", "湖北");
        AREACODE.put("43", "湖南");
        AREACODE.put("44", "广东");
        AREACODE.put("45", "广西");
        AREACODE.put("46", "海南");
        AREACODE.put("50", "重庆");
        AREACODE.put("51", "四川");
        AREACODE.put("52", "贵州");
        AREACODE.put("53", "云南");
        AREACODE.put("54", "西藏");
        AREACODE.put("61", "陕西");
        AREACODE.put("62", "甘肃");
        AREACODE.put("63", "青海");
        AREACODE.put("64", "宁夏");
        AREACODE.put("65", "新疆");
        AREACODE.put("71", "台湾");
        AREACODE.put("81", "香港");
        AREACODE.put("82", "澳门");
        AREACODE.put("91", "国外");
    }

    public static boolean isPort(String port)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(port);
        return isNum.matches() && port.length() < 6 && Integer.valueOf(port) >= 1 && Integer.valueOf(port) <= 65535;
    }

    public static boolean isDate(String sStr)
    {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(sStr);
        return m.matches();
    }

    public static boolean isNumber(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    public static boolean isEmail(String str)
    {
        if (str == null)
        {
            return false;
        }
        else
        {
            String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(regEx1);
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }

    public static boolean isIDCard(String sIdCard)
    {
        String errorInfo = "";
        String[] ValCodeArr = new String[]{"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = new String[]{"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        if (sIdCard.length() != 15 && sIdCard.length() != 18)
        {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return false;
        }
        else
        {
            if (sIdCard.length() == 18)
            {
                Ai = sIdCard.substring(0, 17);
            }
            else if (sIdCard.length() == 15)
            {
                Ai = sIdCard.substring(0, 6) + "19" + sIdCard.substring(6, 15);
            }

            if (!isNumber(Ai))
            {
                errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
                return false;
            }
            else
            {
                String strYear = Ai.substring(6, 10);
                String strMonth = Ai.substring(10, 12);
                String strDay = Ai.substring(12, 14);
                if (!isDate(strYear + "-" + strMonth + "-" + strDay))
                {
                    errorInfo = "身份证生日无效。";
                    return false;
                }
                else
                {
                    GregorianCalendar gc = new GregorianCalendar();
                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

                    try
                    {
                        if (gc.get(1) - Integer.parseInt(strYear) > 150 || gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime() < 0L)
                        {
                            errorInfo = "身份证生日不在有效范围。";
                            return false;
                        }
                    } catch (NumberFormatException var14)
                    {
                        var14.printStackTrace();
                    } catch (ParseException var15)
                    {
                        var15.printStackTrace();
                    }

                    if (Integer.parseInt(strMonth) <= 12 && Integer.parseInt(strMonth) != 0)
                    {
                        if (Integer.parseInt(strDay) <= 31 && Integer.parseInt(strDay) != 0)
                        {
                            Hashtable h = AREACODE;
                            if (h.get(Ai.substring(0, 2)) == null)
                            {
                                errorInfo = "身份证地区编码错误。";
                                return false;
                            }
                            else
                            {
                                int TotalmulAiWi = 0;

                                int modValue;
                                for (modValue = 0; modValue < 17; ++modValue)
                                {
                                    TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(modValue))) * Integer.parseInt(Wi[modValue]);
                                }

                                modValue = TotalmulAiWi % 11;
                                String strVerifyCode = ValCodeArr[modValue];
                                Ai = Ai + strVerifyCode;
                                if (sIdCard.length() == 18)
                                {
                                    if (!Ai.equalsIgnoreCase(sIdCard))
                                    {
                                        errorInfo = "身份证无效，不是合法的身份证号码";
                                        return false;
                                    }
                                    else
                                    {
                                        return true;
                                    }
                                }
                                else
                                {
                                    return true;
                                }
                            }
                        }
                        else
                        {
                            errorInfo = "身份证日期无效";
                            return false;
                        }
                    }
                    else
                    {
                        errorInfo = "身份证月份无效";
                        return false;
                    }
                }
            }
        }
    }

}
