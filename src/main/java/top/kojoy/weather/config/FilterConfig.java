package top.kojoy.weather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.kojoy.weather.filter.CorsFilter;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 09/04/2021
 */
@Configuration
public class FilterConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsFilter()).addPathPatterns("/**");
    }
}
