package cn.yj.gen.gencode.project;

import org.apache.velocity.VelocityContext;

/**
 * <br>
 *
 * @author 永健
 * @since 2020-04-23 16:21
 */
public class JavaFile
{
    private transient Structure structure;

    private TemJava app;
    private TemJava mpConfig;
    private TemJava myMetaObjectHandler;
    private TemJava webConfig;
    private TemJava error;
    private TemJava lpatterm;
    private TemJava globalExceptionHandler;
    private TemJava myErrorControllerConfiguration;
    private TemJava otherExceptionHandler;
    private TemJava serviceException;
    private TemJava encryption;
    private TemJava shiroConfig;
    private TemJava filterChainDefinitionMapBuider;
    private TemJava loginFilter;
    private TemJava myPermissionsAuthorizationFilter;
    private TemJava myRolesAuthorizationFilter;
    private TemJava checkUtils;
    private TemJava dateTimeUtils;
    private TemJava filterNull;
    private TemJava mapUtils;
    private TemJava requestUtils;
    private TemJava servletUtils;
    private TemJava stringUtils;
    private TemJava zipUtils;
    private TemJava loginModel;
    private TemJava orderBy;
    private TemJava r;
    private TemJava baseController;
    private TemJava loginController;
    private TemJava shiroRealm;
    private TemJava validateCodeUtil;
    private TemJava ConsVal;
    private TemJava stringToDateDeSerializer;
    private TemJava beanConfig;
    private TemJava pom;
    private TemJava baseEntity;
    private TemJava application_yml;
    private TemJava application_dev_yml;
    private TemJava application_prod_yml;
    private TemJava log4j2_spring_xml;
    private TemJava appTests;

    private static transient String TEMPLATES_PATH = "templates/project";


    public JavaFile(Structure structure)
    {
        this.structure = structure;
        this.install();
    }

    private void install()
    {
        this.app = new TemJava("App.java", TEMPLATES_PATH + "/App.java.vm", structure.getSrcMainJava(), structure);
        this.log4j2_spring_xml = new TemJava("log4j2-spring.xml", TEMPLATES_PATH + "/log4j2-spring.xml.vm", structure.getResources(), structure);
        this.pom = new TemJava("pom.xml", TEMPLATES_PATH + "/pom.xml.vm", structure.getProjectName(), structure);
        this.application_yml = new TemJava("application.yml", TEMPLATES_PATH + "/application.yml.vm", structure.getResources(), structure);
        this.application_dev_yml = new TemJava("application-dev.yml", TEMPLATES_PATH + "/application-dev.yml.vm", structure.getResources(), structure);
        this.application_prod_yml = new TemJava("application-prod.yml", TEMPLATES_PATH + "/application-prod.yml.vm", structure.getResources(), structure);
        this.mpConfig = new TemJava("MpConfig.java", TEMPLATES_PATH + "/MpConfig.java.vm", structure.getConfig(), structure);
        this.myMetaObjectHandler = new TemJava("MyMetaObjectHandler.java", TEMPLATES_PATH + "/MyMetaObjectHandler.java.vm", structure.getConfig(), structure);
        this.webConfig = new TemJava("WebConfig.java", TEMPLATES_PATH + "/WebConfig.java.vm", structure.getConfig(), structure);
        this.beanConfig = new TemJava("BeanConfig.java", TEMPLATES_PATH + "/BeanConfig.java.vm", structure.getConfig(), structure);
        this.stringToDateDeSerializer = new TemJava("StringToDateDeSerializer.java", TEMPLATES_PATH + "/StringToDateDeSerializer.java.vm", structure.getConfig(), structure);
        this.error = new TemJava("Error.java", TEMPLATES_PATH + "/Error.java.vm", structure.getEnums(), structure);
        this.lpatterm = new TemJava("LPATTERM.java", TEMPLATES_PATH + "/LPATTERM.java.vm", structure.getEnums(), structure);
        this.globalExceptionHandler = new TemJava("GlobalExceptionHandler.java", TEMPLATES_PATH + "/GlobalExceptionHandler.java.vm", structure.getException(), structure);
        this.myErrorControllerConfiguration = new TemJava("MyErrorControllerConfiguration.java", TEMPLATES_PATH + "/MyErrorControllerConfiguration.java.vm", structure.getException(), structure);
        this.otherExceptionHandler = new TemJava("OtherExceptionHandler.java", TEMPLATES_PATH + "/OtherExceptionHandler.java.vm", structure.getException(), structure);
        this.serviceException = new TemJava("ServiceException.java", TEMPLATES_PATH + "/ServiceException.java.vm", structure.getException(), structure);
        this.filterChainDefinitionMapBuider = new TemJava("FilterChainDefinitionMapBuider.java", TEMPLATES_PATH + "/FilterChainDefinitionMapBuider.java.vm", structure.getFrameShiroFilter(), structure);
        this.loginFilter = new TemJava("LoginFilter.java", TEMPLATES_PATH + "/LoginFilter.java.vm", structure.getFrameShiroFilter(), structure);
        this.myPermissionsAuthorizationFilter = new TemJava("MyPermissionsAuthorizationFilter.java", TEMPLATES_PATH + "/MyPermissionsAuthorizationFilter.java.vm", structure.getFrameShiroFilter(), structure);
        this.myRolesAuthorizationFilter = new TemJava("MyRolesAuthorizationFilter.java", TEMPLATES_PATH + "/MyRolesAuthorizationFilter.java.vm", structure.getFrameShiroFilter(), structure);
        this.encryption = new TemJava("Encryption.java", TEMPLATES_PATH + "/Encryption.java.vm", structure.getFrameShiro(), structure);
        this.shiroConfig = new TemJava("ShiroConfig.java", TEMPLATES_PATH + "/ShiroConfig.java.vm", structure.getFrameShiro(), structure);
        this.orderBy = new TemJava("OrderBy.java", TEMPLATES_PATH + "/OrderBy.java.vm", structure.getWebModel(), structure);
        this.loginModel = new TemJava("LoginModel.java", TEMPLATES_PATH + "/LoginModel.java.vm", structure.getWebModel(), structure);
        this.r = new TemJava("R.java", TEMPLATES_PATH + "/R.java.vm", structure.getWebModel(), structure);
        this.baseController = new TemJava("BaseController.java", TEMPLATES_PATH + "/BaseController.java.vm", structure.getWeb(), structure);
        this.baseEntity = new TemJava("BaseEntity.java", TEMPLATES_PATH + "/BaseEntity.java.vm", structure.getWebModel(), structure);
        this.loginController = new TemJava("LoginController.java", TEMPLATES_PATH + "/LoginController.java.vm", structure.getWeb(), structure);
        this.shiroRealm = new TemJava("ShiroRealm.java", TEMPLATES_PATH + "/ShiroRealm.java.vm", structure.getWeb(), structure);
        this.validateCodeUtil = new TemJava("ValidateCodeUtil.java", TEMPLATES_PATH + "/ValidateCodeUtil.java.vm", structure.getUtils(), structure);
        this.checkUtils = new TemJava("CheckUtils.java", TEMPLATES_PATH + "/CheckUtils.java.vm", structure.getUtils(), structure);
        this.dateTimeUtils = new TemJava("DateTimeUtils.java", TEMPLATES_PATH + "/DateTimeUtils.java.vm", structure.getUtils(), structure);
        this.filterNull = new TemJava("FilterNull.java", TEMPLATES_PATH + "/FilterNull.java.vm", structure.getUtils(), structure);
        this.mapUtils = new TemJava("MapUtils.java", TEMPLATES_PATH + "/MapUtils.java.vm", structure.getUtils(), structure);
        this.requestUtils = new TemJava("RequestUtils.java", TEMPLATES_PATH + "/RequestUtils.java.vm", structure.getUtils(), structure);
        this.servletUtils = new TemJava("ServletUtils.java", TEMPLATES_PATH + "/ServletUtils.java.vm", structure.getUtils(), structure);
        this.stringUtils = new TemJava("StringUtils.java", TEMPLATES_PATH + "/StringUtils.java.vm", structure.getUtils(), structure);
        this.zipUtils = new TemJava("ZipUtils.java", TEMPLATES_PATH + "/ZipUtils.java.vm", structure.getUtils(), structure);
        this.appTests = new TemJava("AppTests.java", TEMPLATES_PATH + "/AppTests.java.vm", structure.getTest(), structure);

    }


    public Structure getStructure()
    {
        return structure;
    }

    public TemJava getApp()
    {
        return app;
    }

    public JavaFile setApp(TemJava app)
    {
        this.app = app;
        return this;
    }

    public JavaFile setStructure(Structure structure)
    {
        this.structure = structure;
        return this;
    }

    public TemJava getMpConfig()
    {
        return mpConfig;
    }

    public JavaFile setMpConfig(TemJava mpConfig)
    {
        this.mpConfig = mpConfig;
        return this;
    }

    public TemJava getMyMetaObjectHandler()
    {
        return myMetaObjectHandler;
    }

    public JavaFile setMyMetaObjectHandler(TemJava myMetaObjectHandler)
    {
        this.myMetaObjectHandler = myMetaObjectHandler;
        return this;
    }

    public TemJava getWebConfig()
    {
        return webConfig;
    }

    public JavaFile setWebConfig(TemJava webConfig)
    {
        this.webConfig = webConfig;
        return this;
    }

    public TemJava getError()
    {
        return error;
    }

    public JavaFile setError(TemJava error)
    {
        this.error = error;
        return this;
    }

    public TemJava getLpatterm()
    {
        return lpatterm;
    }

    public JavaFile setLpatterm(TemJava lpatterm)
    {
        this.lpatterm = lpatterm;
        return this;
    }

    public TemJava getGlobalExceptionHandler()
    {
        return globalExceptionHandler;
    }

    public JavaFile setGlobalExceptionHandler(TemJava globalExceptionHandler)
    {
        this.globalExceptionHandler = globalExceptionHandler;
        return this;
    }

    public TemJava getMyErrorControllerConfiguration()
    {
        return myErrorControllerConfiguration;
    }

    public JavaFile setMyErrorControllerConfiguration(TemJava myErrorControllerConfiguration)
    {
        this.myErrorControllerConfiguration = myErrorControllerConfiguration;
        return this;
    }

    public TemJava getOtherExceptionHandler()
    {
        return otherExceptionHandler;
    }

    public JavaFile setOtherExceptionHandler(TemJava otherExceptionHandler)
    {
        this.otherExceptionHandler = otherExceptionHandler;
        return this;
    }

    public TemJava getServiceException()
    {
        return serviceException;
    }

    public JavaFile setServiceException(TemJava serviceException)
    {
        this.serviceException = serviceException;
        return this;
    }

    public TemJava getEncryption()
    {
        return encryption;
    }

    public JavaFile setEncryption(TemJava encryption)
    {
        this.encryption = encryption;
        return this;
    }

    public TemJava getShiroConfig()
    {
        return shiroConfig;
    }

    public JavaFile setShiroConfig(TemJava shiroConfig)
    {
        this.shiroConfig = shiroConfig;
        return this;
    }

    public TemJava getFilterChainDefinitionMapBuider()
    {
        return filterChainDefinitionMapBuider;
    }

    public JavaFile setFilterChainDefinitionMapBuider(TemJava filterChainDefinitionMapBuider)
    {
        this.filterChainDefinitionMapBuider = filterChainDefinitionMapBuider;
        return this;
    }

    public TemJava getLoginFilter()
    {
        return loginFilter;
    }

    public JavaFile setLoginFilter(TemJava loginFilter)
    {
        this.loginFilter = loginFilter;
        return this;
    }

    public TemJava getMyPermissionsAuthorizationFilter()
    {
        return myPermissionsAuthorizationFilter;
    }

    public JavaFile setMyPermissionsAuthorizationFilter(TemJava myPermissionsAuthorizationFilter)
    {
        this.myPermissionsAuthorizationFilter = myPermissionsAuthorizationFilter;
        return this;
    }

    public TemJava getMyRolesAuthorizationFilter()
    {
        return myRolesAuthorizationFilter;
    }

    public JavaFile setMyRolesAuthorizationFilter(TemJava myRolesAuthorizationFilter)
    {
        this.myRolesAuthorizationFilter = myRolesAuthorizationFilter;
        return this;
    }

    public TemJava getCheckUtils()
    {
        return checkUtils;
    }

    public JavaFile setCheckUtils(TemJava checkUtils)
    {
        this.checkUtils = checkUtils;
        return this;
    }

    public TemJava getDateTimeUtils()
    {
        return dateTimeUtils;
    }

    public JavaFile setDateTimeUtils(TemJava dateTimeUtils)
    {
        this.dateTimeUtils = dateTimeUtils;
        return this;
    }

    public TemJava getFilterNull()
    {
        return filterNull;
    }

    public JavaFile setFilterNull(TemJava filterNull)
    {
        this.filterNull = filterNull;
        return this;
    }

    public TemJava getMapUtils()
    {
        return mapUtils;
    }

    public JavaFile setMapUtils(TemJava mapUtils)
    {
        this.mapUtils = mapUtils;
        return this;
    }

    public TemJava getRequestUtils()
    {
        return requestUtils;
    }

    public JavaFile setRequestUtils(TemJava requestUtils)
    {
        this.requestUtils = requestUtils;
        return this;
    }

    public TemJava getServletUtils()
    {
        return servletUtils;
    }

    public JavaFile setServletUtils(TemJava servletUtils)
    {
        this.servletUtils = servletUtils;
        return this;
    }

    public TemJava getStringUtils()
    {
        return stringUtils;
    }

    public JavaFile setStringUtils(TemJava stringUtils)
    {
        this.stringUtils = stringUtils;
        return this;
    }

    public TemJava getZipUtils()
    {
        return zipUtils;
    }

    public JavaFile setZipUtils(TemJava zipUtils)
    {
        this.zipUtils = zipUtils;
        return this;
    }

    public TemJava getLoginModel()
    {
        return loginModel;
    }

    public JavaFile setLoginModel(TemJava loginModel)
    {
        this.loginModel = loginModel;
        return this;
    }

    public TemJava getR()
    {
        return r;
    }

    public JavaFile setR(TemJava r)
    {
        this.r = r;
        return this;
    }

    public TemJava getBaseController()
    {
        return baseController;
    }

    public JavaFile setBaseController(TemJava baseController)
    {
        this.baseController = baseController;
        return this;
    }

    public TemJava getLoginController()
    {
        return loginController;
    }

    public JavaFile setLoginController(TemJava loginController)
    {
        this.loginController = loginController;
        return this;
    }

    public TemJava getShiroRealm()
    {
        return shiroRealm;
    }

    public JavaFile setShiroRealm(TemJava shiroRealm)
    {
        this.shiroRealm = shiroRealm;
        return this;
    }

    public TemJava getValidateCodeUtil()
    {
        return validateCodeUtil;
    }

    public JavaFile setValidateCodeUtil(TemJava validateCodeUtil)
    {
        this.validateCodeUtil = validateCodeUtil;
        return this;
    }

    public TemJava getConsVal()
    {
        return ConsVal;
    }

    public JavaFile setConsVal(TemJava consVal)
    {
        ConsVal = consVal;
        return this;
    }

    public TemJava getPom()
    {
        return pom;
    }

    public JavaFile setPom(TemJava pom)
    {
        this.pom = pom;
        return this;
    }

    public TemJava getBaseEntity()
    {
        return baseEntity;
    }

    public JavaFile setBaseEntity(TemJava baseEntity)
    {
        this.baseEntity = baseEntity;
        return this;
    }

    public TemJava getApplication_yml()
    {
        return application_yml;
    }

    public JavaFile setApplication_yml(TemJava application_yml)
    {
        this.application_yml = application_yml;
        return this;
    }

    public TemJava getApplication_dev_yml()
    {
        return application_dev_yml;
    }

    public JavaFile setApplication_dev_yml(TemJava application_dev_yml)
    {
        this.application_dev_yml = application_dev_yml;
        return this;
    }

    public TemJava getApplication_prod_yml()
    {
        return application_prod_yml;
    }

    public JavaFile setApplication_prod_yml(TemJava application_prod_yml)
    {
        this.application_prod_yml = application_prod_yml;
        return this;
    }

    public TemJava getOrderBy()
    {
        return orderBy;
    }

    public JavaFile setOrderBy(TemJava orderBy)
    {
        this.orderBy = orderBy;
        return this;
    }

    public TemJava getStringToDateDeSerializer()
    {
        return stringToDateDeSerializer;
    }

    public JavaFile setStringToDateDeSerializer(TemJava stringToDateDeSerializer)
    {
        this.stringToDateDeSerializer = stringToDateDeSerializer;
        return this;
    }

    public TemJava getBeanConfig()
    {
        return beanConfig;
    }

    public JavaFile setBeanConfig(TemJava beanConfig)
    {
        this.beanConfig = beanConfig;
        return this;
    }

    public TemJava getLog4j2_spring_xml()
    {
        return log4j2_spring_xml;
    }

    public JavaFile setLog4j2_spring_xml(TemJava log4j2_spring_xml)
    {
        this.log4j2_spring_xml = log4j2_spring_xml;
        return this;
    }

    public TemJava getAppTests()
    {
        return appTests;
    }

    public JavaFile setAppTests(TemJava appTests)
    {
        this.appTests = appTests;
        return this;
    }

    public static class TemJava
    {

        /**
         * 文件名
         */
        private String javaFileName;

        /**
         * 模版
         */
        private String templates;

        /**
         * 所在包名
         */
        private String packageName;

        private VelocityContext velocityContext;

        public TemJava(String javaFileName, String templates, String packageName, Structure structure)
        {
            this.javaFileName = javaFileName;
            this.templates = templates;
            this.packageName = packageName;
            this.velocityContext = new VelocityContext();
            this.velocityContext.put("basePackage", structure.getProjectPackage().replaceAll("\\/","."));
            this.velocityContext.put("currentPackage", packageName.replace(structure.getSrcMainJava(),"").replace("\\/","").replaceAll("\\/","."));
        }

        public String getJavaFileName()
        {
            return javaFileName;
        }

        public TemJava setJavaFileName(String javaFileName)
        {
            this.javaFileName = javaFileName;
            return this;
        }

        public String getTemplates()
        {
            return templates;
        }

        public TemJava setTemplates(String templates)
        {
            this.templates = templates;
            return this;
        }

        public String getPackageName()
        {
            return packageName;
        }

        public TemJava setPackageName(String packageName)
        {
            this.packageName = packageName;
            return this;
        }

        public VelocityContext getVelocityContext()
        {
            return velocityContext;
        }

        public TemJava setVelocityContext(VelocityContext velocityContext)
        {
            this.velocityContext = velocityContext;
            return this;
        }


    }
}


