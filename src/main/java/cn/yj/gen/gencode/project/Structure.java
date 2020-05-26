package cn.yj.gen.gencode.project;

import cn.yj.gen.gencode.ConstVal;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <br>
 * <p>
 * 项目结构：
 * <p>
 * <p>
 * gen-demo
 * cn/yj/demo
 * src/main/java/%s
 * src/main/java/%s/common
 * src/main/java/%s/common/config
 * src/main/java/%s/common/enums
 * src/main/java/%s/common/exception
 * src/main/java/%s/common/frame
 * src/main/java/%s/common/frame/shiro
 * src/main/java/%s/common/frame/filter
 * src/main/java/%s/common/utils
 * src/main/java/%s/common/frame/web
 * src/main/java/%s/common/frame/web/model
 * src/main/java/%s/common/frame/web/po
 * src/main/java/%s/common/frame/web/vo
 * src/main/java/%s/core
 * src/main/java/%s/core/business
 * src/main/java/%s/core/business/service
 * src/main/java/%s/core/business/entity
 * src/main/java/%s/core/business/mapper
 * src/main/java/%s/core/business/controller
 * src/main/java/%s/core/other
 *
 * @author 永健
 * @since 2020-04-23 15:39
 */
public class Structure
{
    /**
     * 代码输出位置
     */
    private transient String outDir= ConstVal.CODE_OUT_DIR;

    private transient String projectName = "%s";
    private transient String projectPackage = "%s";
    private String srcMainJava = projectName + "/" + "src/main/java" + "/" + projectPackage;
    private String resources = projectName + "/" + "src/main/resources";
    private String resourcesMapper = resources + "/" + "mapper";
    private String resourcesStatic = resources + "/" + "static";
    private String resourcesTemplates = resources + "/" + "templates";
    private String common = srcMainJava + "/" + "common";
    private String config = common + "/" + "config";
    private String enums = common + "/" + "enums";
    private String exception = common + "/" + "exception";
    private String frame = common + "/" + "frame";
    private String frameShiro = frame + "/" + "shiro";
    private String frameShiroFilter = frameShiro + "/" + "filter";
    private String utils = common + "/" + "utils";
    private String web = frame + "/" + "web";
    private String webModel = web + "/" + "model";
    private String core = srcMainJava + "/" + "core";
    private String coreBusiness = core + "/" + "business";
    private String coreBusinessService = coreBusiness + "/" + "service";
    private String coreBusinessServiceImpl = coreBusiness + "/" + "service/impl";
    private String coreBusinessEntity = coreBusiness + "/" + "entity";
    private String coreBusinessMapper = coreBusiness + "/" + "mapper";
    private String coreBusinessController = coreBusiness + "/" + "controller";
    private String coreOther = core + "/" + "other";

    // 测试文件夹
    private String test=projectName+"/src/test/java/"+projectPackage;


    public Structure(String projectName, String projectPackage) throws IllegalAccessException
    {
        this(ConstVal.CODE_OUT_DIR,projectName,projectPackage);
    }
    public Structure(String outDir, String projectName, String projectPackage) throws IllegalAccessException
    {
        isBlank(outDir);
        isBlank(projectName);
        isBlank(projectPackage);

        this.outDir = outDir;
        this.projectName = projectName;

        if (projectPackage.indexOf(".") >= 0)
        {
            projectPackage = projectPackage.replaceAll("\\.", "\\/");
        }

        this.projectPackage = projectPackage;

        create();
    }

    private void create() throws IllegalAccessException
    {
        Class<? extends Structure> aClass = this.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields)
        {
            field.setAccessible(true);
            Object value = field.get(this);

            String modifier = Modifier.toString(field.getModifiers())==null?"":Modifier.toString(field.getModifiers());

            if (modifier.contains("transient"))
            {
                continue;
            }
            if (value != null)
            {
                // System.out.println(value.toString());
                String format = String.format(value.toString(), this.projectName, this.projectPackage);
                File file = new File(outDir + "/" + format);
                // System.out.println(file.getAbsolutePath());
                field.set(this, format);
                if (!file.exists())
                {
                    file.mkdirs();
                }
            }
        }
    }

    private void isBlank(String str)
    {
        if (str == null || "".equals(str))
        {
            throw new IllegalArgumentException("参数不允许为空：" + str + " is null");
        }
    }


    public String getProjectName()
    {
        return projectName;
    }

    public Structure setProjectName(String projectName)
    {
        this.projectName = projectName;
        return this;
    }

    public String getSrcMainJava()
    {
        return srcMainJava;
    }

    public Structure setSrcMainJava(String srcMainJava)
    {
        this.srcMainJava = srcMainJava;
        return this;
    }

    public String getProjectPackage()
    {
        return projectPackage;
    }

    public Structure setProjectPackage(String projectPackage)
    {
        this.projectPackage = projectPackage;
        return this;
    }

    public String getCommon()
    {
        return common;
    }

    public Structure setCommon(String common)
    {
        this.common = common;
        return this;
    }

    public String getConfig()
    {
        return config;
    }

    public Structure setConfig(String config)
    {
        this.config = config;
        return this;
    }

    public String getEnums()
    {
        return enums;
    }

    public Structure setEnums(String enums)
    {
        this.enums = enums;
        return this;
    }

    public String getFrame()
    {
        return frame;
    }

    public Structure setFrame(String frame)
    {
        this.frame = frame;
        return this;
    }

    public String getFrameShiro()
    {
        return frameShiro;
    }

    public Structure setFrameShiro(String frameShiro)
    {
        this.frameShiro = frameShiro;
        return this;
    }

    public String getUtils()
    {
        return utils;
    }

    public Structure setUtils(String utils)
    {
        this.utils = utils;
        return this;
    }

    public String getWeb()
    {
        return web;
    }

    public Structure setWeb(String web)
    {
        this.web = web;
        return this;
    }

    public String getWebModel()
    {
        return webModel;
    }

    public Structure setWebModel(String webModel)
    {
        this.webModel = webModel;
        return this;
    }

    public String getCoreBusinessServiceImpl()
    {
        return coreBusinessServiceImpl;
    }

    public Structure setCoreBusinessServiceImpl(String coreBusinessServiceImpl)
    {
        this.coreBusinessServiceImpl = coreBusinessServiceImpl;
        return this;
    }

    public String getCore()
    {
        return core;
    }

    public Structure setCore(String core)
    {
        this.core = core;
        return this;
    }

    public String getCoreBusiness()
    {
        return coreBusiness;
    }

    public Structure setCoreBusiness(String coreBusiness)
    {
        this.coreBusiness = coreBusiness;
        return this;
    }

    public String getCoreBusinessService()
    {
        return coreBusinessService;
    }

    public Structure setCoreBusinessService(String coreBusinessService)
    {
        this.coreBusinessService = coreBusinessService;
        return this;
    }

    public String getCoreBusinessEntity()
    {
        return coreBusinessEntity;
    }

    public Structure setCoreBusinessEntity(String coreBusinessEntity)
    {
        this.coreBusinessEntity = coreBusinessEntity;
        return this;
    }

    public String getCoreBusinessMapper()
    {
        return coreBusinessMapper;
    }

    public Structure setCoreBusinessMapper(String coreBusinessMapper)
    {
        this.coreBusinessMapper = coreBusinessMapper;
        return this;
    }

    public String getCoreBusinessController()
    {
        return coreBusinessController;
    }

    public Structure setCoreBusinessController(String coreBusinessController)
    {
        this.coreBusinessController = coreBusinessController;
        return this;
    }

    public String getCoreOther()
    {
        return coreOther;
    }

    public Structure setCoreOther(String coreOther)
    {
        this.coreOther = coreOther;
        return this;
    }

    public String getOutDir()
    {
        return outDir;
    }

    public Structure setOutDir(String outDir)
    {
        this.outDir = outDir;
        return this;
    }

    public String getException()
    {
        return exception;
    }

    public Structure setException(String exception)
    {
        this.exception = exception;
        return this;
    }

    public String getFrameShiroFilter()
    {
        return frameShiroFilter;
    }

    public Structure setFrameShiroFilter(String frameShiroFilter)
    {
        this.frameShiroFilter = frameShiroFilter;
        return this;
    }

    public String getResources()
    {
        return resources;
    }

    public Structure setResources(String resources)
    {
        this.resources = resources;
        return this;
    }

    public String getResourcesStatic()
    {
        return resourcesStatic;
    }

    public Structure setResourcesStatic(String resourcesStatic)
    {
        this.resourcesStatic = resourcesStatic;
        return this;
    }

    public String getResourcesTemplates()
    {
        return resourcesTemplates;
    }

    public Structure setResourcesTemplates(String resourcesTemplates)
    {
        this.resourcesTemplates = resourcesTemplates;
        return this;
    }

    public String getResourcesMapper()
    {
        return resourcesMapper;
    }

    public Structure setResourcesMapper(String resourcesMapper)
    {
        this.resourcesMapper = resourcesMapper;
        return this;
    }

    public String getTest()
    {
        return test;
    }

    public Structure setTest(String test)
    {
        this.test = test;
        return this;
    }
}



