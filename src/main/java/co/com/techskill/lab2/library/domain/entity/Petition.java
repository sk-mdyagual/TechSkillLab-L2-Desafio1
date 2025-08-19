package co.com.techskill.lab2.library.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "petitions")
public class Petition {
    private String petitionId;
    private String type; //LEND - RETURN
    private Integer priority;
    private String bookId;
    private LocalDate sentAt;
}
