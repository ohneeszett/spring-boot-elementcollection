package work.ohneeszett.spring_boot_hibernate;

import org.springframework.boot.SpringApplication;

public class TestSpringBootHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootHibernateApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
