package cn.yj.gen.gencode;

import cn.yj.gen.common.frame.web.BaseController;
import cn.yj.gen.common.frame.web.model.R;
import cn.yj.gen.gencode.config.ConfigProperties;
import cn.yj.gen.gencode.engine.ProjectVelocityEngine;
import cn.yj.gen.gencode.engine.ServiceCodeEngine;
import cn.yj.gen.gencode.jdbc.DbProperties;
import cn.yj.gen.gencode.jdbc.LJdbc;
import cn.yj.gen.gencode.jdbc.service.DataBaseExecute;
import cn.yj.gen.gencode.project.JavaFile;
import cn.yj.gen.gencode.project.Structure;
import cn.yj.gen.gencode.table.LTable;
import cn.yj.gen.gencode.table.LTableHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

/**
 * <br>
 *
 * @author 永健
 * @since 2020-05-16 21:03
 */
@RestController
public class CodeController extends BaseController
{
    @PostMapping("/connect")
    public R connect(@RequestBody @Valid DbProperties dbProperties) throws ConnectException, SQLException
    {
        LJdbc lJdbc = new LJdbc(dbProperties);
        List<LTable> tables = new DataBaseExecute(lJdbc).getTables();
        lJdbc.close();
        return success(tables);
    }

    @PostMapping("/code/gen")
    public R codeGen(@Valid DbProperties dbProperties,@Valid ConfigProperties configProperties) throws Exception
    {
        // 项目结构生成
        Structure structure = new Structure(configProperties.getProjectName(), configProperties.getPackageName());
        JavaFile javaFile = new JavaFile(structure);
        ProjectVelocityEngine engine = new ProjectVelocityEngine(javaFile, configProperties);
        engine.execute();

        // 业务代码生成
        new ServiceCodeEngine(new LTableHandler(configProperties).getTable(), configProperties,structure).execute();


        return success();
    }
}
