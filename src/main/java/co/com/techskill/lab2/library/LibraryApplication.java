package co.com.techskill.lab2.library;

import co.com.techskill.lab2.library.service.dummy.PetitionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class LibraryApplication {

	public static void Ejercicio (String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	public static void main(String[] args) {
		System.out.println("Inicia la peticion ");
		PetitionService petitionService = new PetitionService();
		petitionService.reto1 ();
		System.out.println("peticion completada.") ;
	}


}




