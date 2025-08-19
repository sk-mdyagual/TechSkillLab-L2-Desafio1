package co.com.techskill.lab2.library.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Book {

    private String bookId;
    private String isbn;
    private String name;
    private Boolean available;
    private Integer amount;
    }