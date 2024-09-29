package test_task.students_application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Integer id;

    @NotBlank(message = "Can't be null")
    private String first_name;

    @NotBlank(message = "Can't be null")
    private String last_name;

    @NotBlank(message = "Can't be null")
    private String fathers_name;

    @NotNull(message = "Invalid date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;

    @NotBlank(message = "Can't be null")
    private String group_name;
}
