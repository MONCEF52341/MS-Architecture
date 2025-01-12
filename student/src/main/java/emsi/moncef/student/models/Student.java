package emsi.moncef.student.models;


import emsi.moncef.student.enums.Major;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String serial;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private Major major;
    private int yearOfStudy;
}
