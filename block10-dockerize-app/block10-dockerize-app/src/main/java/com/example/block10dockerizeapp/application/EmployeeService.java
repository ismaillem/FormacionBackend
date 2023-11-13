package com.example.block10dockerizeapp.application;

import com.example.block10dockerizeapp.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    void insertEmployee(Employee emp);

    void updateEmployee(Employee emp);

    void executeUpdateEmployee(Employee emp);

    void deleteEmployee(Employee emp);
}
