package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student minal = new Student(
                    "Minal",
                    "minalamesur2299@gmail.com",
                    LocalDate.of(1999, Month.FEBRUARY, 2));

            Student tom = new Student(
                    "Tom",
                    "tom@gmail.com",
                    LocalDate.of(1962, Month.JULY, 3)
            );
            studentRepository.saveAll(List.of(minal, tom));
        };
    }
}
