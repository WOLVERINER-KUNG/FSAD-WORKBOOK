package com.example.main;

import com.example.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student s1 = (Student) context.getBean("studentConstructor");
        Student s2 = (Student) context.getBean("studentSetter");

        System.out.println(s1);
        System.out.println(s2);
    }
}
