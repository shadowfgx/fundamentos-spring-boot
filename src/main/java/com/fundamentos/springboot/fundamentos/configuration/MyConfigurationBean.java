package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean   //Creacion del Bean
    public MyBean beanOperation(){
        return new MyBean2Implement();
    }

    @Bean   //Creacion del Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }

    @Bean   //Creacion del Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
