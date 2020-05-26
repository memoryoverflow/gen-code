package cn.yj.gen.gencode.table;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-19 17:26
 */
public class TemplateConfig
{

    /**
     * entity 模版
     */
    private String entity = "templates/entity.java.vm";


    /**
     * service 模版
     */
    private String service =  "templates/service.java.vm";

    /**
     * 实现类模版
     */
    private String serviceImpl = "templates/serviceImpl.java.vm";

    /**
     * mapper 接口
     */
    private String mapper = "templates/mapper.java.vm";

    /**
     * Controler
     */
    private String controller = "templates/Controller.java.vm";

    /**
     * XML模版
     */
    private String xml = "templates/mapper.xml.vm";


    public String getEntity()
    {
        return entity;
    }

    public TemplateConfig setEntity(String entity)
    {
        this.entity = entity;
        return this;
    }

    public String getService()
    {
        return service;
    }

    public TemplateConfig setService(String service)
    {
        this.service = service;
        return this;
    }

    public String getServiceImpl()
    {
        return serviceImpl;
    }

    public TemplateConfig setServiceImpl(String serviceImpl)
    {
        this.serviceImpl = serviceImpl;
        return this;
    }

    public String getMapper()
    {
        return mapper;
    }

    public TemplateConfig setMapper(String mapper)
    {
        this.mapper = mapper;
        return this;
    }

    public String getXml()
    {
        return xml;
    }

    public TemplateConfig setXml(String xml)
    {
        this.xml = xml;
        return this;
    }

    public String getController()
    {
        return controller;
    }

    public TemplateConfig setController(String controller)
    {
        this.controller = controller;
        return this;
    }
}
