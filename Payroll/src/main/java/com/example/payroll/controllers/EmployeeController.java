package com.example.payroll.controllers;

import com.example.payroll.assemblers.EmployerModelAssembler;
import com.example.payroll.exceptions.EmployeeNotFoundException;
import com.example.payroll.models.Employee;
import com.example.payroll.repositories.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployerModelAssembler assembler;

    EmployeeController(EmployeeRepository repo,EmployerModelAssembler assembler){
        this.repository = repo;
        this.assembler = assembler;
    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    @GetMapping("/employees/{id}")
    public EntityModel<Employee> findOne(@PathVariable Long id){
        Employee employee = repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
        return assembler.toModel(employee);
    }
    @PutMapping("/employees/{id}")
    ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        Employee updatedEmployee = repository.findById(id)
                .map(employee->{
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(()->{
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
