package cn.yj.gen.common.utils;

import com.alibaba.fastjson.JSONObject;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-03-16 18:09
 */
public class RequestUtils
{

    private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";


    /**
     * 从请求头中获取 操作系统
     */
    public static String getOperateSys()
    {
        UserAgent agent = UserAgent.parseUserAgentString(getUserAgent());
        OperatingSystem sys = agent.getOperatingSystem();
        return sys.getName();
    }


    /**
     * 获取host
     */
    public static String getHost()
    {
        return ServletUtils.getRequest().getRemoteHost();
    }




    /**
     * 从请求头中获取 浏览器类型
     */
    public static String getOperateBrowser()
    {
        UserAgent agent = UserAgent.parseUserAgentString(getUserAgent());
        Browser browser = agent.getBrowser();
        return browser.getName();
    }

    /**
     * @描述 获取请求头
     * @date 2018/9/20 0:12
     */
    public static String getUserAgent()
    {
        HttpServletRequest request = ServletUtils.getRequest();
        String header = request.getHeader("User-Agent");
        return header;
    }

    /**
     * 获取Ip
     */
    public static String getIp()
    {
        HttpServletRequest request = ServletUtils.getRequest();
        if (request == null)
        {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取查询结果
     */
    public static String sendPost(String content, String encoding)
    {
        URL url = null;
        HttpURLConnection connection = null;
        try
        {
            url = new URL(IP_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e)
        {
            logger.error("温馨提醒：您的主机已经断网，请您检查主机的网络连接");
            logger.error("根据IP获取所在位置----------错误消息=[{}]", e.getMessage());
        } finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 获取ip所在地
     */
    public static String getIpAddress()
    {
        String ip=getIp();
        String address = "";
        try
        {
            address = sendPost("ip=" + ip, "UTF-8");

            JSONObject json = JSONObject.parseObject(address);
            JSONObject object = json.getObject("data", JSONObject.class);
            String region = object.getString("region");
            String country = object.getString("country");
            String city = object.getString("city");
            address = country + " " + region + " " + city;
        } catch (Exception e)
        {
            logger.error("根据IP获取所在位置----------错误消息：[{}]", e.getMessage());
        }
        return address == null ? "XXX" : address;
    }


    public static String getIpMac(String ipAddress) throws SocketException, UnknownHostException
    {
        String str = "";
        String macAddress = "";
        String LOOPBACK_ADDRESS = "127.0.0.1";
        if ("127.0.0.1".equals(ipAddress)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < mac.length; ++i) {
                if (i != 0) {
                    sb.append("-");
                }

                String s = Integer.toHexString(mac[i] & 255);
                sb.append(s.length() == 1 ? 0 + s : s);
            }

            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        } else {
            try {
                Process p = Runtime.getRuntime().exec("nbtstat -A " + ipAddress);
                InputStreamReader ir = new InputStreamReader(p.getInputStream());
                BufferedReader br = new BufferedReader(ir);

                while((str = br.readLine()) != null) {
                    if (str.indexOf("MAC") > 1) {
                        macAddress = str.substring(str.indexOf("MAC") + 9, str.length());
                        macAddress = macAddress.trim();
                        break;
                    }
                }

                p.destroy();
                br.close();
                ir.close();
            } catch (IOException var9) {
            }

            return macAddress;
        }
    }

    public static Map<String, String> strParamToMap(String params) {
        HashMap<String, String> mpParam = new HashMap();
        if (StringUtils.isNotBlank(params)) {
            String[] arrParam = params.split("&");
            String[] var3 = arrParam;
            int var4 = arrParam.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String sparam = var3[var5];
                String[] arrKeyValue = sparam.split("=");
                mpParam.put(arrKeyValue[0], arrKeyValue[1]);
            }
        }

        return mpParam;
    }

    public static String mapToStrParam(Map<String, String> params) {
        String sParams = "";

        Map.Entry entry;
        for(Iterator var2 = params.entrySet().iterator(); var2.hasNext(); sParams = sParams + (String)entry.getKey() + "=" + (String)entry.getValue()) {
            entry = (Map.Entry)var2.next();
            if (StringUtils.isNotBlank(sParams)) {
                sParams = sParams + "&";
            }
        }

        return sParams;
    }





}
