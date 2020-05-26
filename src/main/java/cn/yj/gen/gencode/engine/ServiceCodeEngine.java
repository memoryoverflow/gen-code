package cn.yj.gen.gencode.engine;

import cn.yj.gen.common.utils.DateTimeUtils;
import cn.yj.gen.gencode.ConstVal;
import cn.yj.gen.gencode.StringPools;
import cn.yj.gen.gencode.config.ConfigProperties;
import cn.yj.gen.gencode.project.Structure;
import cn.yj.gen.gencode.table.LTable;
import cn.yj.gen.gencode.table.TemplateConfig;
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
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * <br>
 *
 * @author 永健
 * @since 2020-05-10 18:40
 */
public class ServiceCodeEngine implements Engine
{
    private static Logger logger = LoggerFactory.getLogger(ServiceCodeEngine.class);
    private VelocityEngine velocityEngine;

    private TemplateConfig templateConfig;

    private Structure structure;

    private ConfigProperties configProperties;

    private List<LTable> tables;

    public ServiceCodeEngine(List<LTable> lTables, ConfigProperties configProperties, Structure structure)
    {
        this.tables = lTables;
        this.structure = structure;
        this.configProperties = configProperties;
        this.templateConfig = new TemplateConfig();
        initEngine();
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

    @Override
    public void execute()
    {
        logger.info("######################");
        logger.info("##  开始生成java类...");
        for (LTable table : tables)
        {
            createEntity(table);
            createMapper(table);
            createService(table);
            createServiceImpl(table);
            createController(table);
            createXml(table);
        }
    }


    private void createEntity(LTable table)
    {
        Template template = velocityEngine.getTemplate(templateConfig.getEntity(), StringPools.UTF8);
        VelocityContext context = getVelocityContext(table);
        writer(template, context,structure.getCoreBusinessEntity() , table.getEntityName() + ConstVal.JAVA_FILE_SUFFIX);
    }

    private void createMapper(LTable table)
    {
        Template template = velocityEngine.getTemplate(templateConfig.getMapper(), StringPools.UTF8);
        VelocityContext context = getVelocityContext(table);
        writer(template, context, structure.getCoreBusinessMapper(), table.getMapperName() + ConstVal.JAVA_FILE_SUFFIX);
    }

    private void createService(LTable table)
    {
        Template template = velocityEngine.getTemplate(templateConfig.getService(), StringPools.UTF8);
        VelocityContext context = getVelocityContext(table);
        writer(template, context, structure.getCoreBusinessService(), table.getServiceName() + ConstVal.JAVA_FILE_SUFFIX);
    }

    private void createServiceImpl(LTable table)
    {
        Template template = velocityEngine.getTemplate(templateConfig.getServiceImpl(), StringPools.UTF8);
        VelocityContext context = getVelocityContext(table);
        writer(template, context,structure.getCoreBusinessServiceImpl(), table.getServiceImplName() + ConstVal.JAVA_FILE_SUFFIX);
    }

    private void createController(LTable table)
    {
        Template template = velocityEngine.getTemplate(templateConfig.getController(), StringPools.UTF8);
        VelocityContext context = getVelocityContext(table);
        writer(template, context, structure.getCoreBusinessController(), table.getControllerName() + ConstVal.JAVA_FILE_SUFFIX);
    }

    private void createXml(LTable table)
    {
        Template template = velocityEngine.getTemplate(templateConfig.getXml(), StringPools.UTF8);
        VelocityContext context = getVelocityContext(table);
        writer(template, context, structure.getResourcesMapper(), table.getMapperXmlJavaName() + ConstVal.XML_FILE_SUFFIX);
    }


    private void writer(Template template, VelocityContext velocityContext, String packages, String fileName)
    {
        //设置输出
        PrintWriter writer = null;
        try
        {
            String p = ConstVal.CODE_OUT_DIR + ConstVal.separator + packages;
            File file = new File(p);
            if (!file.exists())
            {
                file.mkdirs();
            }
            writer = new PrintWriter(p + "/" + fileName);
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


    /**
     * 模版数据共同数据
     *
     * @param table
     * @return
     */
    private VelocityContext getVelocityContext(LTable table)
    {
        VelocityContext param = new VelocityContext();
        param.put("date", DateTimeUtils.format(new Date()));
        param.put("author", configProperties.getAuthor());
        // 表对象
        param.put("table", table);
        param.put("basePackage", configProperties.getPackageName());
        param.put("lombok", configProperties.isLombok());
        param.put("hump", configProperties.isHump());
        return param;
    }
}
