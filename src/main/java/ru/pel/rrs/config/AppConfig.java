package ru.pel.rrs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private Environment env;

    @Bean
    public LocaleResolver localeResolver(){
//        SessionLocaleResolver slr = new SessionLocaleResolver();
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
//    @Qualifier
    public ReloadableResourceBundleMessageSource validationsMessageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/validation/messages");
        messageSourceConfigurator(messageSource);
        return messageSource;
    }

    @Bean
//    @Qualifier("exceptionsMessageSource")
    public ReloadableResourceBundleMessageSource exceptionsMessageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/exception/messages");
        messageSourceConfigurator(messageSource);
        return messageSource;
    }

    /**
     * ?????????????????????? ?????? ???????? messageSource ?????? ???????? ????????????????.
     * @param messageSource
     */
    private void messageSourceConfigurator(AbstractResourceBasedMessageSource messageSource){
        messageSource.setCacheSeconds(1);
        messageSource.setDefaultLocale(Locale.US);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    }

    @Override
    @Description("???????????????????????????????? ??????????????????. ?????????? ?????? ?????????????????????? ?????????????????? ???? ?????????????? ??????????????????")
    public Validator getValidator() {
//        return WebMvcConfigurer.super.getValidator();
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(validationsMessageSource());
        return validatorFactoryBean;
    }

    @Override
    @Description("?????????????????????? ?????????? ?? ???????????????????????? ?????????????????? ?????? front end")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
//                .addResourceLocations("file:/D:/Java Course/ResourceReservationSystem/src/main/webapp/WEB-INF/files/");
                .addResourceLocations("/WEB-INF/resources/");
    }

    //TODO ???????????????????? ?? viewResolver()
    @Bean
    @Description("?????????????????? REST ???????????????????????? ?????????????????? ?? ???????????????????? ???????????? ?? ???????????????? JSON ?? XML. ?????? ??????????????????????????, " +
            "?????????? ?????????? ???????????????? ?? ???????????? ??????????????")
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
    @Description("???????????????????? ????????????????????")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        Properties mapping = new Properties();
        mapping.setProperty("NoSuchRoomException", "/error"); //FIXME ???????????? /error ???????? ???????????? ?? ?????????? ?????????????????????????? ?? ?????????????????????????? ????????????????

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setExceptionMappings(mapping);         // None by default
        resolver.setDefaultErrorView("/error");         // No default
        resolver.setExceptionAttribute("exception");    // Default is "exception"
//        resolver.setWarnLogCategory();                // No default

        return resolver;
    }

    //TODO ?????????????????????? ?????? ????????????????????, ?????? ???? DAO ?????????? connection ???? ???????? ?????? ??????????????????????????, ?? ?????????? ????????????????????.
    @Bean
    public DataSource dataSource() {
        String dbType = env.getProperty("db.type");
        String dbDriver = env.getProperty(dbType + ".driver");
        String dbUrl = env.getProperty(dbType + ".url");
        String dbUser = env.getProperty(dbType + ".user");
        String dbPass = env.getProperty(dbType + ".pass");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPass);

        return dataSource;
    }

    @Bean
    @Description("Registration filter for hidden http methods in forms")
    public FilterRegistrationBean<Filter> hiddenHttpMethodFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        filter.setUrlPatterns(List.of("/*"));
        return filter;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

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
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
}
