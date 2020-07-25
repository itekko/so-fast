package com.sofast.common.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author ekko
 * spring 容器配置类
 */
@Configuration(proxyBeanMethods = false)
public class SpringConfiguration {


    /**
     * JSON格式转换工具 处理JDK8时间格式
     * @return
     */
//    @Bean
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper objectMapper(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePatternContants.DEFAULT_DATE_TIME_FORMAT)));
//        javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePatternContants.DEFAULT_DATE_FORMAT)));
//        javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePatternContants.DEFAULT_TIME_FORMAT)));
//        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePatternContants.DEFAULT_DATE_TIME_FORMAT)));
//        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePatternContants.DEFAULT_DATE_FORMAT)));
//        javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePatternContants.DEFAULT_TIME_FORMAT)));
//        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
//
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        return objectMapper;
//    }

    /*@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

*/



}
