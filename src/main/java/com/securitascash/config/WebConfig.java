// package com.securitascash.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.filter.HiddenHttpMethodFilter;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(new LoginInterceptor())
//                 .addPathPatterns("/contas/**", "/correntistas/**") // protege ambas as rotas
//                 .excludePathPatterns("/usuario/login", "/"); // libera login e home
//     }

//     @Bean
//     public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
//         return new HiddenHttpMethodFilter();
//     }
// }
