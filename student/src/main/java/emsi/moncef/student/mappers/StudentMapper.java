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
}
