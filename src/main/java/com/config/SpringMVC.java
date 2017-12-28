package com.config;

import java.text.SimpleDateFormat;
import java.util.List;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
*@see SpringMVC springMVC的javaconfig
*@author eric
*@version 
*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.spring.controller"})
public class SpringMVC implements WebMvcConfigurer    {
    public SpringMVC () {}

    //Json转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
        .indentOutput(true)
        .dateFormat(new SimpleDateFormat("yyyy-mm-dd"))
        .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
    
    //视图转换器，在RestController中一般不适用，但modelandview是适合的
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver jspView = new InternalResourceViewResolver();
        jspView.setViewClass(JstlView.class);
         jspView.setPrefix("/WEB-INF/jsp/");
         jspView.setSuffix(".jsp");
        return jspView;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        
        configurer.enable();
    }

}