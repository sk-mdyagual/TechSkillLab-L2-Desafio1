package co.com.techskill.lab2.library.web.dummy;

import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
     * Challenge #1: Endpoint que retorna un flujo de solicitudes de alta prioridad
     * con control de ritmo y transformación de mensajes
     */
    @GetMapping("/high-priority-flow")
    public Flux<String> getHighPriorityPetitionFlow() {
        return petitionService.generateHighPriorityPetitionFlow()
                .doOnNext(message -> System.out.println("[Console Output]: " + message));
    }
    
    /**
     * Challenge #1: Endpoint alternativo con procesamiento asíncrono
     */
    @GetMapping("/high-priority-flow-async")
    public Flux<String> getHighPriorityPetitionFlowAsync() {
        return petitionService.generateHighPriorityPetitionFlowAsync()
                .doOnNext(message -> System.out.println("[Console Output]: " + message));
    }
}
