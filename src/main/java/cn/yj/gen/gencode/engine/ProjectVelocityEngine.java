package cn.yj.gen.gencode.engine;

import cn.yj.gen.common.utils.DateTimeUtils;
import cn.yj.gen.gencode.ConstVal;
import cn.yj.gen.gencode.StringPools;
import cn.yj.gen.gencode.config.ConfigProperties;
import cn.yj.gen.gencode.project.JavaFile;
import cn.yj.gen.gencode.project.Structure;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <br>
 * <p>
 * 项目文件生成的 处理
 *
 * @author 永健
 * @since 2020-04-23 17:14
 */
public class ProjectVelocityEngine implements Engine
{
    private static Logger logger = LoggerFactory.getLogger(Engine.class);

    private VelocityEngine velocityEngine;

    private JavaFile javaFile;
    private Structure structure;

    private VelocityContext velocityContext;


    public ProjectVelocityEngine(JavaFile javaFile, ConfigProperties configProperties)
    {
        this.javaFile = javaFile;
        initContext(configProperties);
    }

    private void initContext(ConfigProperties configProperties)
    {
        this.velocityContext = new VelocityContext();
        // 类作者
        velocityContext.put("author", configProperties.getAuthor());
        // 类创建时间
        velocityContext.put("date", DateTimeUtils.format(new Date()));

        // application.yml 生成的数据库连接
        velocityContext.put("dbHost",configProperties.getHost());
        velocityContext.put("dbPort",configProperties.getPort());
        velocityContext.put("dbName",configProperties.getDatabaseName());
        velocityContext.put("dbRoot",configProperties.getUserName());
        velocityContext.put("dbPwd", configProperties.getPassword());
        velocityContext.put("tablePrefix", configProperties.getTablePrefix());
        velocityContext.put("hump", configProperties.isHump());

        // 项目相关
        Map<String, Object> project = new HashMap<>();
        project.put("version", configProperties.getVersion());
        project.put("groupId", configProperties.getGroupId());
        project.put("artifactId", configProperties.getArtifactId());
        project.put("name", configProperties.getProjectName());
        velocityContext.put("project", project);
    }
    @Override
    public void execute() throws IllegalAccessException
    {
        // 创建项目文件结构
        this.structure = javaFile.getStructure();

        initEngine();

        createFile();

    }

    /**
     * 创建文件
     */
    private void createFile() throws IllegalAccessException
    {
        Class<? extends JavaFile> aClass = javaFile.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields)
        {
            field.setAccessible(true);
            Object value = field.get(javaFile);
            String modifier = Modifier.toString(field.getModifiers());
            if (value == null || modifier.contains("transient"))
            {
                continue;
            }
            JavaFile.TemJava temJava = (JavaFile.TemJava) value;
            Template template = velocityEngine.getTemplate(temJava.getTemplates(), StringPools.UTF8);


            VelocityContext javaVelocityContext = temJava.getVelocityContext();
            Object[] keys = javaVelocityContext.getKeys();

            for (Object key:keys){
                velocityContext.put(key.toString(),javaVelocityContext.get(key.toString()));
            }

            writer(template, this.velocityContext, temJava.getPackageName(), temJava.getJavaFileName());
        }
    }


    private void writer(Template template, VelocityContext velocityContext, String packages, String fileName)
    {
        //设置输出
        PrintWriter writer = null;
        try
        {
            String path = this.structure.getOutDir() + ConstVal.separator + packages;
            System.out.println(path);
            File filePath = new File(path);
            if (!filePath.exists())
            {
                filePath.mkdirs();
            }

            File file = new File(filePath, fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        //将环境数据转化输出
        template.merge(velocityContext, writer);
        writer.close();
    }


    private void initEngine()
    {
        Properties p = new Properties();
        p.setProperty(ConstVal.VM_LOAD_PATH_KEY, ConstVal.VM_LOAD_PATH_VALUE);
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPools.EMPTY);
        p.setProperty(Velocity.ENCODING_DEFAULT, StringPools.UTF8);
        p.setProperty(Velocity.INPUT_ENCODING, StringPools.UTF8);
        p.setProperty("file.resource.loader.unicode", StringPools.TRUE);
        VelocityEngine ve = new VelocityEngine(p);
        ve.init();
        this.velocityEngine = ve;
    }
}
