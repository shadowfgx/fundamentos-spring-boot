package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependecy {
    @Override
    public void saludar() {
        System.out.println("Hello mundo desde mi componente dos");
    }
}
