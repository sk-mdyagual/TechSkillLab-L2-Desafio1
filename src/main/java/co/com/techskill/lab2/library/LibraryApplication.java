package co.com.techskill.lab2.library;

import co.com.techskill.lab2.library.domain.dto.BookDTO;
import co.com.techskill.lab2.library.service.dummy.BookService;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import co.com.techskill.lab2.library.service.impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class LibraryApplication {

	public static void main(String[] args) {
		PetitionService p = new PetitionService();

		p.findByPriority(7).subscribe(
				peti -> System.out.println(peti)
		);

		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

}
