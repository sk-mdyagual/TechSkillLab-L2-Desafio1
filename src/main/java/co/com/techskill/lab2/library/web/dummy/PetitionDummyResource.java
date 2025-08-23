package co.com.techskill.lab2.library.web.dummy;

import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/dummy/petitions")
public class PetitionDummyResource {
    private final PetitionService petitionService;

    public PetitionDummyResource(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping("/all")
    public Flux<PetitionDTO> getAllPetitions(){
        return petitionService.dummyFindAll();
    }

    @PostMapping("/id")
    public Mono<ResponseEntity<PetitionDTO>> findByPetitionId(@RequestBody PetitionDTO petitionDTO){
        return petitionService.dummyFindById(petitionDTO.getPetitionId())
                .map(ResponseEntity::ok);
    }

    /**
     * Gets petitions with priority greater than 7, simulating a reactive stream processing.
     * @return a Flux of PetitionDTO with high priority.
     */
    @GetMapping("/high-priority")
    public Flux<PetitionDTO> getHighPriorityPetitions() {
        return petitionService.dummyFindAll()
                .doOnNext(petition -> System.out.println("--- Initial stream --- Received: " + petition.getPetitionId() + " | Priority: " + petition.getPriority()))
                .limitRate(20) // First rate limit to control backpressure from the source
                .delayElements(Duration.ofMillis(500)) // Simulate processing time for each element (20 * 50ms = 1s)
                .filter(petition -> petition.getPriority() > 7)
                .doOnNext(filteredPetition -> System.out.println(">>> Filtered stream (Priority > 7) >>> Found: " + filteredPetition.getPetitionId() + " | Priority: " + filteredPetition.getPriority()))
                .limitRate(1) // Second rate limit to control backpressure for the subscriber
                .map(finalPetition -> {
                    System.out.println("<<< Final Mapping Stage <<< Full Info: Petition ID=" + finalPetition.getPetitionId() +
                            ", Type=" + finalPetition.getType() +
                            ", Priority=" + finalPetition.getPriority() +
                            ", Book ID=" + finalPetition.getBookId() +
                            ", Sent At=" + finalPetition.getSentAt());
                    return finalPetition;
                });
    }
}
