package ejemplo.ejemplo;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"ejemplo.controladores", "ejemplo.ejemplo"})
public class ParqueaderoCeibaApiApp {

	public static void main(String[] args) {
		
		SpringApplication.run(ParqueaderoCeibaApiApp.class, args); //Este metodo hace que comience
		//la aplicacion.
		
	}

}
