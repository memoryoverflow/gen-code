package cn.yj.gen.common.frame.web;

import cn.yj.gen.common.frame.web.model.LoginModel;
import cn.yj.gen.common.utils.ValidateCodeUtil;
import cn.yj.gen.common.frame.web.model.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-04 23:53
 */
@RestController
public class LoginController extends BaseController
{

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginModel loginModel, HttpSession session)
    {
        return success();
    }


    /**
     * 获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            ValidateCodeUtil randomValidateCode = new ValidateCodeUtil();
            //输出验证码图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e)
        {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }

    @PostMapping("/logout")
    public R logout()
    {
        return success();
    }
}
