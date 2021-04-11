package top.kojoy.weather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 09/04/2021
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    final static String[] ORIGINS = {"GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods(ORIGINS)
                .maxAge(3600);
    }
}
