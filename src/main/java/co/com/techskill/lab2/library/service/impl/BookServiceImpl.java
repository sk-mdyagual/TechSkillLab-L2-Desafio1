package co.com.techskill.lab2.library.service.impl;

import co.com.techskill.lab2.library.config.BookMapper;
import co.com.techskill.lab2.library.config.BookMapperImpl;
import co.com.techskill.lab2.library.domain.dto.BookDTO;
import co.com.techskill.lab2.library.repository.IBookRepository;
import co.com.techskill.lab2.library.service.IBookService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
@Profile("mongo")
public class BookServiceImpl implements IBookService {
    private final IBookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookServiceImpl(IBookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.bookMapper = new BookMapperImpl();

    }

    /*Operadores fundamentales
    * Transformación: .map y .flatMap
    * Filtrado: .filter
    * Combinación: .zip .merge .concat */
    @Override
    public Flux<BookDTO> findAll() {
        return bookRepository
                .findAll()
                .map(bookMapper::toDTO);
    }

    @Override
    public Mono<BookDTO> findById(String id) {
        return bookRepository
                .findById(id)
                .map(book -> bookMapper.toDTO(book));
    }

    @Override
    public Mono<BookDTO> save(BookDTO bookDTO) {
        bookDTO.setBookId(UUID.randomUUID().toString().substring(0,10));
        bookDTO.setAvailable(true);
        return bookRepository
                .save(bookMapper.toEntity(bookDTO))
                .map(book -> bookMapper.toDTO(book));
    }
}
