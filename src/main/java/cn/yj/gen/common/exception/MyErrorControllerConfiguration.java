package cn.yj.gen.common.exception;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2020-04-06 21:24
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ResourceProperties.class })
public class MyErrorControllerConfiguration
{
    private final ServerProperties serverProperties;

    private final List<ErrorViewResolver> errorViewResolvers;

    public MyErrorControllerConfiguration(ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers)
    {
        this.serverProperties = serverProperties;
        this.errorViewResolvers = errorViewResolvers;
    }

    @Bean
    public OtherExceptionHandler otherException(ErrorAttributes errorAttributes){
        return new OtherExceptionHandler(errorAttributes,serverProperties.getError(),errorViewResolvers);
    }

}
