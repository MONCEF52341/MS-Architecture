package emsi.moncef.student.mappers;

import emsi.moncef.student.dtos.StudentDTO;
import emsi.moncef.student.models.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentDTO toDto(Student student) {
        return new StudentDTO(
                student.getSerial(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getAddress(),
                student.getMajor(),
                student.getYearOfStudy()
        );
    }

    public List<StudentDTO> toDtoList(List<Student> students) {
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setSerial(dto.serial());
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setDateOfBirth(dto.dateOfBirth());
        student.setEmail(dto.email());
        student.setPhoneNumber(dto.phoneNumber());
        student.setAddress(dto.address());
        student.setMajor(dto.major());
        student.setYearOfStudy(dto.yearOfStudy());
        return student;
    }

    public List<Student> toEntitiesList(List<StudentDTO> studentsDTOlist) {
        return studentsDTOlist.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
