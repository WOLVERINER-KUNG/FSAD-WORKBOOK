package com.example.config;

import com.example.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Student studentConstructor() {
        return new Student(3, "Bob", "C++", "2023");
    }

    @Bean
    public Student studentSetter() {
        Student s = new Student(4, "Eva", "", "");
        s.setCourse("JavaScript");
        s.setYear("2022");
        return s;
    }
}
