package co.com.techskill.lab2.library.service.impl;

import co.com.techskill.lab2.library.config.PetitionMapper;
import co.com.techskill.lab2.library.config.PetitionMapperImpl;
import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.repository.IPetitionRepository;
import co.com.techskill.lab2.library.service.IPetitionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class PetitionServiceImpl implements IPetitionService {

    private final IPetitionRepository petitionRepository;
    private final PetitionMapper petitionMapper;

    public PetitionServiceImpl(IPetitionRepository petitionRepository){
        this.petitionRepository = petitionRepository;
        this.petitionMapper = new PetitionMapperImpl();
    }
    @Override
    public Flux<PetitionDTO> findALl() {
        return petitionRepository
                .findAll()
                .map(petitionMapper::toDTO);
    }

    @Override
    public Mono<PetitionDTO> findById(String id) {
        return petitionRepository
                .findByPetitionId(id)
                .map(petitionMapper::toDTO);
    }

    @Override
    public Mono<PetitionDTO> save(PetitionDTO petitionDTO) {
        petitionDTO.setPetitionId(UUID.randomUUID().toString().substring(0,10));
        petitionDTO.setSentAt(LocalDate.now());
        return petitionRepository
                .save(petitionMapper.toEntity(petitionDTO))
                .map(petitionMapper::toDTO);
    }

    //TO-DO: Filter example findByPriority
    @Override
    public Flux<PetitionDTO> findByPriority(Integer p) {
        return petitionRepository.findAll()
                .filter(petition -> petition.getPriority() == 5)
                .map(petition -> petitionMapper.toDTO(petition));
    }

    //TO-DO: Check priorities with a delay of 1 second to show up the processing in console but requested in Swagger UI
    @Override
    public Flux<String> checkPriorities(Integer p) {
        return findByPriority(p)
                .map(petitionDTO -> LocalTime.now() + " - Check priority with level " + p
                + ", Petition ID: " + petitionDTO.getPetitionId()
                +",  For Book ID:  " + petitionDTO.getBookId()+"\n")
                .delayElements(Duration.ofMillis(5000))
                .doOnNext(System.out::println);
    }





}
