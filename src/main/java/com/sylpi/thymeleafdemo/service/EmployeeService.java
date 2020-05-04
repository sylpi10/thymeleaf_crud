package com.sylpi.thymeleafdemo.service;

import com.sylpi.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public List<Employee> findAllByOrderByLastNameAsc();

    public Employee findById(int theId);

    public void save (Employee theEmployee);

    public void deleteById(int theId);

}
