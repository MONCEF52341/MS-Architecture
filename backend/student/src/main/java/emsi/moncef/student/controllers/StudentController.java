package emsi.moncef.student.controllers;

import emsi.moncef.student.dtos.StudentDTO;
import emsi.moncef.student.enums.Major;
import emsi.moncef.student.mappers.StudentMapper;
import emsi.moncef.student.models.Student;
import emsi.moncef.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
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

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        Student newStudent = studentService.addStudent(student);
        return ResponseEntity.ok(studentMapper.toDto(newStudent));
    }


    @PutMapping("/{serial}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String serial, @RequestBody StudentDTO studentDTO) {
        Student updatedStudentEntity = studentMapper.toEntity(studentDTO);
        Optional<Student> updatedStudent = studentService.updateStudent(serial, updatedStudentEntity);
        return updatedStudent.map(student -> ResponseEntity.ok(studentMapper.toDto(student)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{serial}")
    public ResponseEntity<StudentDTO> patchStudent(@PathVariable String serial, @RequestBody StudentDTO studentDTO) {
        Student partialStudentEntity = studentMapper.toEntity(studentDTO);
        Optional<Student> patchedStudent = studentService.patchStudent(serial, partialStudentEntity);
        return patchedStudent.map(student -> ResponseEntity.ok(studentMapper.toDto(student)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{serial}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String serial) {
        boolean deleted = studentService.deleteStudent(serial);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
