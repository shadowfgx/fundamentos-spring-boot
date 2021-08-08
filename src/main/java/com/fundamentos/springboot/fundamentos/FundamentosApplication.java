package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependecy;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependecy componentDependecy;  //
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;


		//Vamos a crear nuestro constructor de la clase
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependecy componentDependecy, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependecy = componentDependecy;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}


	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {   // el metodo run no muestra toda implementacion de nuestras dependencias
		//ejemplosAnteriores();

		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErroTransactional();
	}

	private void saveWithErroTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("esta en una exception dentro del metodo transicional");
		}
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transicional " +user));
	}
	private void getInformationJpqlFromUser (){
		LOGGER.info("Usuario con el metodo findByUserEmail" +
				userRepository.findByUserEmail("fernando@mail.com").orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort" +user));

		userRepository.findByName("Fernando")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method"+ user));

		userRepository.findByNameLike("%F%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike " + user));

		userRepository.findByNameOrEmail(null, "alicia@mail.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));

		userRepository
				.findByBirthDateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 10, 30))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalos de fechas " + user));

		userRepository
				.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado " + user));

	/**	LOGGER.info("El usuario a partir del named parameter es " + userRepository
				.getAllByBirthDateAndEmail(LocalDate.of(2021,05,2), "elvis@email.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")));
		*/
	}

	private void saveUsersInDataBase() {
		User user1 = new User("Fernando", "fernando@mail.com", LocalDate.of(2021,03,10));
		User user2 = new User("Jose", "jose@mail.com", LocalDate.of(2021,05,21));
		User user3 = new User("Elvis", "elvis@mail.com", LocalDate.of(2021,05,2));
		User user4 = new User("Alicia", "alicia@mail.com", LocalDate.of(2021,06,4));
		User user5 = new User("user5", "user5@mail.com", LocalDate.of(2021,07,7));
		User user6 = new User("user6", "user6@mail.com", LocalDate.of(2021,8,9));
		User user7 = new User("user7", "user7@mail.com", LocalDate.of(2021,9,12));
		User user8 = new User("user8", "user8@mail.com", LocalDate.of(2021,10,25));
		User user9 = new User("user9", "user9@mail.com", LocalDate.of(2021,11,30));
		User user10 = new User("user10", "user10@mail.com", LocalDate.of(2021,12,14));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user6, user7, user8, user9, user10);

		list.stream().forEach(userRepository::save);  // Con esto registramos toda la informacion

	}

	private void ejemplosAnteriores(){
		componentDependecy.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());

		try{
			//error
			int value= 10/0;
			LOGGER.info("Mi valor: "+value);
		} catch (Exception e ){
			LOGGER.error("Esto es un error al dividir por cero " +e.getMessage());
		}
	}
}
