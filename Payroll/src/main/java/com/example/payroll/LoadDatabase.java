package com.example.payroll;

import com.example.payroll.models.Employee;
import com.example.payroll.models.Order;
import com.example.payroll.models.Status;
import com.example.payroll.repositories.EmployeeRepository;
import com.example.payroll.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository){
        return args->{
            employeeRepository.save(new Employee("Bilbo","Baggins","thief"));
            employeeRepository.save(new Employee("Lera","Bushueva","Kitten"));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded "+employee));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

        };
    }

}
