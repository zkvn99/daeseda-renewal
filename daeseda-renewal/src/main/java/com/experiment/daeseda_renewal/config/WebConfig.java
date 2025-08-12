package com.experiment.daeseda_renewal.config;

import com.experiment.daeseda_renewal.global.util.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheckInterceptor())
            .addPathPatterns("/posts/**")
            .addPathPatterns("/addresses/**")
            .addPathPatterns("/users/**")
            .excludePathPatterns("/users/login", "/users/signup", "/users/find-id",
                                 "/users/find-pw")
            .excludePathPatterns("/posts/list");
  }
}
