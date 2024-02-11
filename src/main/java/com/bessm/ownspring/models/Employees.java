package com.bessm.ownspring.models;

import java.util.Random;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employees {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String empId, name, surname, phone, email;
  private boolean active;
  private int score, code;

  public Employees(String name, String surname, String phone, String email, boolean active, int score) {
    this.empId = UUID.randomUUID().toString();
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.email = email;
    this.active = active;
    this.score = score;
    this.code = new Random().nextInt(999) + 1;
  }

  public Employees() {
    this.empId = UUID.randomUUID().toString();
    this.code = new Random().nextInt(999) + 1;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
