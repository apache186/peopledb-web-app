package ch.akros.peopledbweb.biz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;

    @Past(message = "Date of birth must be in the past.")
    @NotNull(message = "Date of birth must be specified.")
    private LocalDate dob;

    @Email(message = "Invalid email address entered.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @PositiveOrZero(message = "Salary cannot be negative.")
    @NotNull(message = "Salary cannot be empty.")
    private BigDecimal salary;

    private String photoFileName;

    public static Person parse(String csvLine) {
        String[] fields = csvLine.split(",\\s*");
        LocalDate dob = LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("M/d/yyyy"));
        return new Person(null,fields[2], fields[4], dob, fields[6], new BigDecimal(fields[25]), null);
    }
}
