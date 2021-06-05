package utn.tacs.common.correlation;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestHeaderInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/*").exposedHeaders("ETag").allowedOrigins("*");
    }

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        ShallowEtagHeaderFilter shallowEtagHeaderFilter = new ShallowEtagHeaderFilter();
        shallowEtagHeaderFilter.setWriteWeakETag(true);
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean
                = new FilterRegistrationBean<>(shallowEtagHeaderFilter);
        filterRegistrationBean.addUrlPatterns("*");
        filterRegistrationBean.setName("etagFilter");
        return filterRegistrationBean;
    }
}
