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

    public Student addStudent(Student student) {

        studentRepository.save(student);
        return student;

    }

    public Optional<Student> updateStudent(String serial, Student updatedStudent) {
        Optional<Student> existingStudent = fetchStudentBySerial(serial);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
            student.setAddress(updatedStudent.getAddress());
            student.setDateOfBirth(updatedStudent.getDateOfBirth());
            student.setYearOfStudy(updatedStudent.getYearOfStudy());
            student.setMajor(updatedStudent.getMajor());
            return Optional.of(studentRepository.save(student));
        }
        return Optional.empty();
    }

    public Optional<Student> patchStudent(String serial, Student partialUpdate) {
        Optional<Student> existingStudent = fetchStudentBySerial(serial);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            if (partialUpdate.getFirstName() != null) student.setFirstName(partialUpdate.getFirstName());
            if (partialUpdate.getLastName() != null) student.setLastName(partialUpdate.getLastName());
            if (partialUpdate.getEmail() != null) student.setEmail(partialUpdate.getEmail());
            if (partialUpdate.getPhoneNumber() != null) student.setPhoneNumber(partialUpdate.getPhoneNumber());
            if (partialUpdate.getAddress() != null) student.setAddress(partialUpdate.getAddress());
            if (partialUpdate.getDateOfBirth() != null) student.setDateOfBirth(partialUpdate.getDateOfBirth());
            if (partialUpdate.getYearOfStudy() != 0) student.setYearOfStudy(partialUpdate.getYearOfStudy());
            if (partialUpdate.getMajor() != null) student.setMajor(partialUpdate.getMajor());
            return Optional.of(studentRepository.save(student));
        }
        return Optional.empty();
    }

    public boolean deleteStudent(String serial) {
        Optional<Student> existingStudent = fetchStudentBySerial(serial);
        if (existingStudent.isPresent()) {
            studentRepository.delete(existingStudent.get());
            return true;
        }
        return false;
    }
}