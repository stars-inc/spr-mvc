package com.bessm.ownspring.controllers;

import java.util.List;
// import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bessm.ownspring.interfaces.EmployeeRepository;
import com.bessm.ownspring.models.Employees;

@RestController
@RequestMapping("/emp")
public class EmployeesController {
  
  // private List<Employees> employees = new ArrayList<>();
  private EmployeeRepository repository;

  public EmployeesController(EmployeeRepository repository) {
    this.repository = repository;
    repository.saveAll(
      List.of(
        new Employees(
          "Alex", "bessm", "+7(909)062-12-13", "alexandr.bessm@gmail.com", true, 0),
        new Employees(
          "Anna", "Vasil", "+7(903)700-00-20", "ann.vas@mail.ru", false, 0),
        new Employees(
          "Nikita", "bessm", "+7(965)343-34-34", "nikit@gmail.com", true, 0)
      )
    );
  }

  @GetMapping
  public Iterable<Employees> getEmployees() {
    return repository.findAll();
  }

  @GetMapping("/{empId}")
  public Employees getEmployeesById(@PathVariable String empId) {
    return repository.findByEmpId(empId);
  }

  @PostMapping("/add")
  public void addEmployee(@RequestBody Employees employee) {
    repository.save(employee);
  }

  @PutMapping("/{empId}")
  public ResponseEntity<Employees> updateEmployee(
    @PathVariable String empId, @RequestBody Employees employee) {
      Employees existingEmployee = repository.findByEmpId(empId);

      if(existingEmployee != null) {
        existingEmployee.setName(employee.getName());
        existingEmployee.setSurname(employee.getSurname());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setActive(employee.getActive());
        existingEmployee.setScore(employee.getScore());

        repository.save(existingEmployee);

        return new ResponseEntity<>(existingEmployee, HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{empId}")
  public void deleteEmployee(@PathVariable String empId) {
    repository.deleteByEmpId(empId);
  }
}
