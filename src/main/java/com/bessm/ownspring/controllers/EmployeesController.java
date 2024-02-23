package com.bessm.ownspring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bessm.ownspring.interfaces.EmployeeRepository;
import com.bessm.ownspring.models.Employees;
import com.bessm.ownspring.services.LoadDataEmployees;

import jakarta.annotation.PostConstruct;

@Controller
public class EmployeesController {
  
  private EmployeeRepository repository;
  private LoadDataEmployees loadDataEmployees;

  public EmployeesController(EmployeeRepository repository, LoadDataEmployees loadDataEmployees) {
    this.repository = repository;
    this.loadDataEmployees = loadDataEmployees;
  }

  @PostConstruct
  public void initEmployees() {
    loadDataEmployees.loadEmployees();
  }

  @GetMapping({"/", "/index"})
  public ModelAndView homePage() {
    ModelMap model = new ModelMap();
    model.addAttribute("name", "Alex");
    return new ModelAndView("home", model);
  }

  @GetMapping("/employees")
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
