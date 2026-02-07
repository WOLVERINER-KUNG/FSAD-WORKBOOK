package com.example.main;

import com.example.config.AppConfig;
import com.example.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student student1 = (Student) context.getBean("studentConstructor");
        Student student2 = (Student) context.getBean("studentSetter");

        System.out.println(student1);
        System.out.println(student2);
    }
}
