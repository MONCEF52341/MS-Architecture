package emsi.moncef.student;

import com.github.javafaker.Faker;
import emsi.moncef.student.enums.Major;
import emsi.moncef.student.models.Student;
import emsi.moncef.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {


    private final Faker faker = new Faker();
    private final Random random = new Random();
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        loadStudents();
    }


    private void loadStudents() {
        for (int i = 0; i < 100; i++) {
            Student student = Student.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .dateOfBirth(LocalDate.now().minusYears(random.nextInt(20) + 18))
                    .email(faker.internet().emailAddress())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .address(faker.address().fullAddress())
                    .major(Major.values()[random.nextInt(Major.values().length)])
                    .yearOfStudy(random.nextInt(8) + 1)
                    .build();
            studentRepository.save(student);
        }
    }


}
