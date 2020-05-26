package cn.yj.gen;

import cn.yj.gen.gencode.ConstVal;
import cn.yj.gen.gencode.config.ConfigProperties;
import cn.yj.gen.gencode.engine.ProjectVelocityEngine;
import cn.yj.gen.gencode.engine.ServiceCodeEngine;
import cn.yj.gen.gencode.project.JavaFile;
import cn.yj.gen.gencode.project.Structure;
import cn.yj.gen.gencode.table.LTableHandler;
import org.junit.jupiter.api.Test;

/**
 * <br>
 *
 * @author 永健
 * @since 2020-04-23 15:59
 */
public class TestMain
{
    @Test
    public void t() throws Exception
    {

        // 增删改生要生成的表
        String[] tables = {"tb_comment", "tb_cover", "tb_order", "tb_product", "tb_type", "tb_user"};

        // 全局配置
        ConfigProperties configProperties = new ConfigProperties();
        configProperties
                .setAuthor("永健")
                .setVersion(ConstVal.SPRINGBOOT_VERSION)
                .setGroupId("cn.yj")
                .setArtifactId("gen-demo")
                .setProjectName("gen-demo")
                .setTablePrefix("tb_")
                .setPackageName("cn.yj.demo")
                .setHump(true)
                .setLombok(true)
                .setTables(tables)
                // 数据库
                .setDatabaseName("kfc")
                .setUserName("root")
                .setPassword("tiger")
                .setPort(3306)
                .setHost("127.0.0.1");


        // 项目结构生成
        Structure structure = new Structure(ConstVal.CODE_OUT_DIR,configProperties.getProjectName(), configProperties.getPackageName());
        JavaFile javaFile = new JavaFile(structure);
        ProjectVelocityEngine engine = new ProjectVelocityEngine(javaFile, configProperties);
        engine.execute();


        // 业务代码生成
        new ServiceCodeEngine(new LTableHandler(configProperties).getTable(), configProperties,structure).execute();


    }

}
