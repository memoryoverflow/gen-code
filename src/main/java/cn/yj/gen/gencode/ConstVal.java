package cn.yj.gen.gencode;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-11-06 10:39
 */
public interface ConstVal
{


    String CODE_OUT_DIR="/Users/yongjian/work/codeGen/project";

    String separator = "/";
    String STRING_FORMAT = "%s";
    String SPRINGBOOT_VERSION = "2.2.6.RELEASE";




    /**
     * <br>
     * 数据库相关常量值
     */
    String COLUMN_PK_val = "PRI";
    String COLUMN_PK_AUTO = "auto_increment";


    String VM_LOAD_PATH_KEY = "file.resource.loader.class";
    String VM_LOAD_PATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";


    /**
     * 文件后缀
     */
    String XML_FILE_SUFFIX = ".xml";
    String JAVA_FILE_SUFFIX = ".java";



}
