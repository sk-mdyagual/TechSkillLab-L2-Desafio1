package co.com.techskill.lab2.library.challenge;

import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runner para ejecutar el Challenge #1 al iniciar la aplicación
 * Demuestra el uso de Flux, filter, map, limitRate y flatMap
 */
@Component
public class Challenge1Runner implements CommandLineRunner {
    
    private final PetitionService petitionService;
    
    public Challenge1Runner(PetitionService petitionService) {
        this.petitionService = petitionService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("========================================");
        System.out.println("Tarea #1 ");
        System.out.println("========================================");

        
        // Ejecutar el flujo y esperar a que complete
        petitionService.generateHighPriorityPetitionFlow()
                .doOnNext(message -> System.out.println("✅ " + message))
                .doOnComplete(() -> {
                    System.out.println("========================================");
                    System.out.println(" Tarea 1, completa");
                    System.out.println("========================================");
                    System.out.println();
                    runAsyncVersionNonBlocking();
                })
                .doOnError(error -> System.err.println(" Error: " + error.getMessage()))
                .blockLast();
    }
    
    private void runAsyncVersionNonBlocking() {
        System.out.println("========================================");
        System.out.println("========================================");
        
        // Suscribirse sin bloquear
        petitionService.generateHighPriorityPetitionFlowAsync()
                .doOnNext(message -> System.out.println("🔀 " + message))
                .doOnComplete(() -> {
                    System.out.println("========================================");
                    System.out.println("========================================");
                })
                .doOnError(error -> System.err.println(" Error en versión async: " + error.getMessage()))
                .subscribe();
    }
}
