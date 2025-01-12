package emsi.moncef.student.services;

import emsi.moncef.student.enums.Major;
import emsi.moncef.student.models.Student;
import emsi.moncef.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> fetchStudentBySerial(String serial) {
        return Optional.ofNullable(studentRepository.findBySerial(serial));
    }

    public List<Student> fetchStudentsByMajor(Major major) {
        return studentRepository.findByMajor(major);
    }

    public List<Student> fetchStudentsByYear(int year) {
        return studentRepository.findByYearOfStudy(year);
    }

    public Optional<Student> fetchStudentByName(String firstname, String lastname) {
        return Optional.ofNullable(studentRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstname, lastname));
    }


    public List<Student> fetchStudentsByYearRange(int minYear, int maxYear) {
        return studentRepository.findByYearOfStudyBetween(minYear, maxYear);
    }

    public List<Student> fetchStudentsByAddress(String address) {
        return studentRepository.findByAddressContainingIgnoreCase(address);
    }

    public Optional<Student> fetchStudentByEmail(String email) {
        return Optional.ofNullable(studentRepository.findByEmailContainingIgnoreCase(email));
    }

    public Optional<Student> fetchStudentByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(studentRepository.findByPhoneNumber(phoneNumber));
    }

    public List<Student> fetchStudentsByBirthYear(int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);
        return studentRepository.findByDateOfBirthBetween(startOfYear, endOfYear);
    }

    public List<Student> fetchStudentsByBirthYearRange(int startYear, int endYear) {
        LocalDate startOfYear = LocalDate.of(startYear, 1, 1);
        LocalDate endOfYear = LocalDate.of(endYear, 12, 31);
        return studentRepository.findByDateOfBirthBetween(startOfYear, endOfYear);
    }

}