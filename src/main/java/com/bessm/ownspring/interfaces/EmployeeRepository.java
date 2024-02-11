package com.bessm.ownspring.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.bessm.ownspring.models.Employees;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends CrudRepository<Employees, Long> {
  Employees findByEmpId(String empId);

  @Transactional
  void deleteByEmpId(String empId);
}
