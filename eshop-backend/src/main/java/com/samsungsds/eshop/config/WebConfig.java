package com.samsungsds.eshop.config;
import com.samsungsds.eshop.security.JwtTokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 작성한 인터셉터를 추가한다.
        registry.addInterceptor(jwtTokenInterceptor())
                // 예제의 경우 전체 사용자를 조회하는 /user/findAll 에 대해 토큰 검사를 진행한다.
                .addPathPatterns("/user/findAll");
    }
    @Bean
    public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>(createHeaderFilter());
        registrationBean.setOrder(Integer.MIN_VALUE);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public HeaderFilter createHeaderFilter() {
        return new HeaderFilter();
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }


}