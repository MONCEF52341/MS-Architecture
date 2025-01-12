package emsi.moncef.student;

import com.github.javafaker.Faker;
import emsi.moncef.student.enums.Major;
import emsi.moncef.student.models.Student;
import emsi.moncef.student.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {


    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final Faker faker = new Faker();
    private final Random random = new Random();
    Set<String> usedSerials = new HashSet<>();

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        try {
            loadStudents();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }

    private void loadStudents() {
        for (int i = 0; i < 100; i++) {
            String uniqueSerial;
            do {
                uniqueSerial = "ETD-" + faker.random().hex(8);
            } while (usedSerials.contains(uniqueSerial));
            usedSerials.add(uniqueSerial);

            Student student = Student.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .serial(uniqueSerial)
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
