package com.halfacode.spring_real_time_learning.util;

import com.halfacode.spring_real_time_learning.entity.Employee;
import com.halfacode.spring_real_time_learning.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            repository.deleteAll();

            repository.save(new Employee(1, "Alice Johnson", "Developer", 55000, "2021-06-15"));
            repository.save(new Employee(2, "Bob Smith", "Manager", 75000, "2019-03-20"));
            repository.save(new Employee(3, "Charlie Brown", "HR", 48000, "2020-11-05"));
            repository.save(new Employee(4, "Diana Prince", "Developer", 60000, "2022-01-12"));
            repository.save(new Employee(5, "Ethan Hunt", "QA Engineer", 52000, "2021-09-07"));
            repository.save(new Employee(6, "Fiona Gallagher", "Team Lead", 80000, "2018-07-19"));
            repository.save(new Employee(7, "George Miller", "Business Analyst", 57000, "2020-02-28"));
            repository.save(new Employee(8, "Hannah Lee", "UI/UX Designer", 61000, "2019-10-15"));
            repository.save(new Employee(9, "Ian Somerhalder", "DevOps Engineer", 68000, "2022-03-03"));
            repository.save(new Employee(10, "Julia Roberts", "Product Manager", 90000, "2017-08-25"));
            repository.save(new Employee(11, "Kevin Hart", "Software Architect", 105000, "2016-12-01"));
            repository.save(new Employee(12, "Laura Palmer", "Data Analyst", 56000, "2020-04-11"));
            repository.save(new Employee(13, "Michael Jordan", "Developer", 62000, "2021-01-20"));
            repository.save(new Employee(14, "Nina Dobrev", "HR Specialist", 49000, "2020-06-30"));
            repository.save(new Employee(15, "Oscar Isaac", "System Admin", 70000, "2019-09-12"));
            repository.save(new Employee(16, "Paula Patton", "Project Coordinator", 53000, "2021-05-21"));
            repository.save(new Employee(17, "Quincy Adams", "Business Analyst", 58000, "2022-02-02"));
            repository.save(new Employee(18, "Rachel Green", "Marketing Manager", 76000, "2018-11-18"));
            repository.save(new Employee(19, "Sam Wilson", "Technical Writer", 45000, "2020-08-05"));
            repository.save(new Employee(20, "Tina Fey", "Scrum Master", 72000, "2019-04-23"));
            repository.save(new Employee(21, "Uma Thurman", "Legal Advisor", 85000, "2017-02-14"));
            repository.save(new Employee(22, "Victor Hugo", "Finance Manager", 93000, "2016-05-06"));
            repository.save(new Employee(23, "Wendy Williams", "QA Engineer", 54000, "2021-12-09"));
            repository.save(new Employee(24, "Xander Cage", "Security Engineer", 69000, "2019-01-29"));
            repository.save(new Employee(25, "Yara Shahidi", "Data Scientist", 95000, "2022-07-01"));

            repository.findAll().forEach(System.out::println);
        };
    }
}
