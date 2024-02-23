package com.bessm.ownspring.employeesDataJpaTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bessm.ownspring.interfaces.EmployeeRepository;
import com.bessm.ownspring.models.Employees;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@DataJpaTest
public class EmployeesDataJpaTests {

  String id;

  @Autowired
  EntityManager manager;
  
  @Autowired 
  private EmployeeRepository repository;

  @Test
  void employeesRepositoryShouldBeEmpty() {
    assertEquals(this.repository.count(), 0);
  }

  @Test
  void addNewEmpoyeeToRepository() {
    Employees employee = 
      new Employees("Olga", "Olgovna", "+7(288)343-34-23", "olg.o.gmail.com", true, 0);
    
    this.manager.persist(employee);
    this.id = employee.getEmpId();

    assertThat(employee.getName()).isEqualTo("Olga");
    assertThat(employee.getScore()).isGreaterThanOrEqualTo(0);
    assertThat(this.repository.count()).isEqualTo(1);
  }

  @Test 
  @Transactional
  void deleteEmployesById() {
    Employees empoloyee = this.repository.findByEmpId(id);

    if(empoloyee != null) {
      assertThat(empoloyee.getName()).isEqualTo("Olga");
      this.repository.deleteByEmpId(id);
    }

    assertThat(this.repository.count()).isZero();
  }
}
