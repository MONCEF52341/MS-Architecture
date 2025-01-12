package emsi.moncef.student.dtos;

import emsi.moncef.student.enums.Major;

import java.time.LocalDate;

public record StudentDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String email,
        String phoneNumber,
        String address,
        Major major,
        int yearOfStudy) {
}
