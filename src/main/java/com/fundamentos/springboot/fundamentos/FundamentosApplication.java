package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyOperation;
import com.fundamentos.springboot.fundamentos.component.ComponentDependecy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependecy componentDependecy;  //
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;


		//Vamos a crear nuestro constructor de la clase
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependecy componentDependecy, MyBean myBean, MyBeanWithDependency myBeanWithDependency ){
		this.componentDependecy = componentDependecy;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}


	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {   // el metodo run no muestra toda implementacion de nuestras dependencias
		componentDependecy.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
	}
}
