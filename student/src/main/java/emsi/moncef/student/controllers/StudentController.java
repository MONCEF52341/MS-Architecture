package emsi.moncef.student.controllers;

import emsi.moncef.student.dtos.StudentDTO;
import emsi.moncef.student.enums.Major;
import emsi.moncef.student.mappers.StudentMapper;
import emsi.moncef.student.models.Student;
import emsi.moncef.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<Student> students = studentService.fetchAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }
    @GetMapping("/by-serial")
    public ResponseEntity<StudentDTO> fetchStudentBySerial(@RequestParam String serial) {
        Optional<Student> studentOptional = studentService.fetchStudentBySerial(serial);
        if (studentOptional.isPresent()) {
            StudentDTO studentDTO = studentMapper.toDto(studentOptional.get());
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/by-major")
    public ResponseEntity<List<StudentDTO>> fetchStudentsByMajor(@RequestParam Major major) {
        List<Student> students = studentService.fetchStudentsByMajor(major);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/by-year")
    public ResponseEntity<List<StudentDTO>> fetchStudentsByYear(@RequestParam int year) {
        List<Student> students = studentService.fetchStudentsByYear(year);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/by-name")
    public ResponseEntity<StudentDTO> fetchStudentByName(@RequestParam String firstname, @RequestParam String lastname) {
        Optional<Student> studentOptional = studentService.fetchStudentByName(firstname, lastname);
        if (studentOptional.isPresent()) {
            StudentDTO studentDTO = studentMapper.toDto(studentOptional.get());
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/by-year-range")
    public ResponseEntity<List<StudentDTO>> fetchStudentsByYearRange(@RequestParam int minYear, @RequestParam int maxYear) {
        List<Student> students = studentService.fetchStudentsByYearRange(minYear, maxYear);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/by-address")
    public ResponseEntity<List<StudentDTO>> fetchStudentsByAddress(@RequestParam String address) {
        List<Student> students = studentService.fetchStudentsByAddress(address);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/by-email")
    public ResponseEntity<StudentDTO> fetchStudentByEmail(@RequestParam String email) {
        Optional<Student> studentOptional = studentService.fetchStudentByEmail(email);
        if (studentOptional.isPresent()) {
            StudentDTO studentDTO = studentMapper.toDto(studentOptional.get());
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/by-phone")
    public ResponseEntity<StudentDTO> fetchStudentByPhoneNumber(@RequestParam String phoneNumber) {
        Optional<Student> studentOptional = studentService.fetchStudentByPhoneNumber(phoneNumber);
        if (studentOptional.isPresent()) {
            StudentDTO studentDTO = studentMapper.toDto(studentOptional.get());
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/by-birthyear")
    public ResponseEntity<List<StudentDTO>> fetchStudentsByBirthYear(@RequestParam int year) {
        List<Student> students = studentService.fetchStudentsByBirthYear(year);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/by-birthyear-range")
    public ResponseEntity<List<StudentDTO>> fetchStudentsByBirthdateRange(@RequestParam int start, @RequestParam int end) {
        List<Student> students = studentService.fetchStudentsByBirthYearRange(start, end);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(students);
        return ResponseEntity.ok(studentDTOs);
    }
}
