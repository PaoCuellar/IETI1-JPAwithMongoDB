package eci.ieti.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {
    @Id
    int id;
    String description;
    int priority;
    LocalDate dueDate;
    String responsible;
    String status;
}
