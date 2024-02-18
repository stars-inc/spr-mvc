package com.bessm.ownspring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bessm.ownspring.interfaces.EmployeeRepository;
import com.bessm.ownspring.models.Employees;

@Service
public class LoadDataEmployees {

  private EmployeeRepository repository;

  public LoadDataEmployees(EmployeeRepository repository) {
    this.repository = repository;
  }

  public void loadEmployees() {
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
}
