package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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


    @Bean
    public DataSource dataSource(){   //Esta implementacion es una interfaz  //Configuracion dataSource con properties y clases
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
