package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    @Value("${value.name}") //Esta notacion nos permite llamar a los valores que hayamos inicializado previamiente en las properties

    private String name;

    @Value("${value.apellido}") //Esta notacion nos permite llamar a los valores que hayamos inicializado previamiente en las properties

    private String apellido;


    @Value("${value.random}") //Esta notacion nos permite llamar a los valores que hayamos inicializado previamiente en las properties

    private String random;


    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, apellido);
    }
}
