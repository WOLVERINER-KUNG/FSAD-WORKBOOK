package com.example.main;

import com.example.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student1 = (Student) context.getBean("studentConstructor");
        Student student2 = (Student) context.getBean("studentSetter");

        System.out.println(student1);
        System.out.println(student2);
    }
}
