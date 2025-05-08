package com.example.damcurvity.config;
import com.example.damcurvity.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class LoginFliterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter())
                //拦截的路径
                .addPathPatterns("/api/**")
                //排除登录接口
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/login/oauth2")
                .excludePathPatterns("/api/SystemConstant/getSystemName")
                .excludePathPatterns("/api/SystemConstant/getSystemBgUrl")
                .excludePathPatterns("/api/InnerTempSensorInfo/addByRemoteDev")
                .excludePathPatterns("/api/InsulationSensorInfo/addByRemoteDev");
    }
}
