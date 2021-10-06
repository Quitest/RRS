package ru.pel.ResourceReservationSystem.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.*;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public SpringResourceTemplateResolver templateResolver() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setPrefix("/templates/");
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
//        templateResolver.setOrder(0);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    //TODO объединить с viewResolver()
    @Bean
    @Description("Позволяет REST контроллерам принимать и отправлять данные в форматах JSON и XML. При необходимости, " +
            "можно легко добавить и другие форматы")
    public ViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver resolver =
                new ContentNegotiatingViewResolver();

        List<View> views = new ArrayList<>();
        views.add(new MappingJackson2XmlView());
        views.add(new MappingJackson2JsonView());
        resolver.setDefaultViews(views);
        return resolver;
    }

    @Bean
    @Description("Registration filter for hidden http methods in forms")
    public FilterRegistrationBean<Filter> hiddenHttpMethodFilter(){
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        filter.setUrlPatterns(List.of("/*"));
        return filter;
    }

    @Override
    @Description("Подключение путей с необходимыми ресурсами для front end")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
//                .addResourceLocations("file:/D:/Java Course/ResourceReservationSystem/src/main/webapp/WEB-INF/files/");
                .addResourceLocations("/WEB-INF/resources/");
    }

    //TODO реализовать пул соединений, что бы DAO брали connection из пула при необходимости, а потом возвращали.

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/rrs_db");
        dataSource.setUsername("rrs_user");
        dataSource.setPassword("toor");

        return dataSource;
    }

    @Bean
    @Description("Обработчик исключений")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver(){
        Properties mapping = new Properties();
        mapping.setProperty("NoSuchElementException", "/error"); //FIXME вместо /error надо мапить в более информативную и специфическую страницу

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setExceptionMappings(mapping);         // None by default
        resolver.setDefaultErrorView("/error");         // No default
        resolver.setExceptionAttribute("exception");    // Default is "exception"
//        resolver.setWarnLogCategory();                // No default

        return resolver;
    }
}
