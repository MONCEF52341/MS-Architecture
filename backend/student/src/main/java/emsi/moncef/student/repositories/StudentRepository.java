package emsi.moncef.student.repositories;

import emsi.moncef.student.enums.Major;
import emsi.moncef.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMajor(Major major);

    Student findBySerial(String serial);

    Student findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    List<Student> findByYearOfStudy(int year);

    List<Student> findByYearOfStudyBetween(int minYear, int maxYear);

    List<Student> findByAddressContainingIgnoreCase(String address);

    Student findByEmailContainingIgnoreCase(String email);

    Student findByPhoneNumber(String phoneNumber);

    List<Student> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
}
