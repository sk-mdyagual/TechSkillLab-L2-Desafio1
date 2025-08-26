package co.com.techskill.lab2.library.service.impl;


import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.service.IPetitionService;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class PetitionServiceImpl implements IPetitionService {

    private final PetitionService petitionRepository;

    public PetitionServiceImpl(PetitionService petitionRepository){
        this.petitionRepository = petitionRepository;
    }
    @Override
    public Flux<PetitionDTO> findALl() {
        return petitionRepository
                .dummyFindAll();
    }

    @Override
    public Mono<PetitionDTO> findById(String id) {
        return petitionRepository
                .dummyFindById(id);
    }

    @Override
    public Mono<PetitionDTO> save(PetitionDTO petitionDTO) {
        petitionDTO.setPetitionId(UUID.randomUUID().toString().substring(0,10));
        petitionDTO.setSentAt(LocalDate.now());
        return petitionRepository
                .dummySave(petitionDTO);
    }

    //TO-DO: Filter example findByPriority
    @Override
    public Flux<PetitionDTO> findByPriority(Integer p) {
        return petitionRepository.dummyFindAll()
                .filter(filterPriorityBy(p));
    }

    private Predicate<PetitionDTO> filterPriorityBy(Integer priority){
        return petitionDTO -> petitionDTO.getPriority().equals(priority);
    }

    //TO-DO: Check priorities with a delay of 1 second to show up the processing in console but requested in Swagger UI
    @Override
    public Flux<String> checkPriorities(Integer p) {
        return findByPriority(p)
                .limitRate(3)
                .map(petitionDTO -> LocalTime.now() + " - Check priority with level " + p
                + ", Petition ID: " + petitionDTO.getPetitionId()
                +",  For Book ID:  " + petitionDTO.getBookId()+"\n")
                .delayElements(Duration.ofMillis(5000))
                .doOnNext(System.out::println);
    }






}
