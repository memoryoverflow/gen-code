package cn.yj.gen.common.frame.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Encryption
{
    public static final String SALT = "AAaSDWDssjdfDJKHFUDnbfMNTWj";

    public static String getMD5(String password, String salt)
    {
       // salt = salt + SALT;
        //加密方式
        String hashAlgorithmName = "MD5";
        //密码
        Object credentials = password;
        //使用ByteSource.Util.bytes()计算盐值
        Object Salt = ByteSource.Util.bytes(salt);
        //加密次数
        int hashIterations = 1;
        Object result = new SimpleHash(hashAlgorithmName, credentials, Salt, hashIterations);
        return result.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(getMD5("admin","maj0ixf6"));
    }

}
