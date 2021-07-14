package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependecy {
    @Override
    public void saludar() {
        System.out.println("Hola Mundo desde mi componente");
    }
}
