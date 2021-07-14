package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {
    //Llamar a otra dependencia dentro de una dependencia ---> Inyeccion de dependecias

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero = 1;
        System.out.println(myOperation.suma(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
