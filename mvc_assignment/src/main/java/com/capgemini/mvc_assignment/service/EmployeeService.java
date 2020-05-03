package com.capgemini.mvc_assignment.service;

import java.util.List;

import com.capgemini.mvc_assignment.beans.EmployeeInfo;

public interface EmployeeService {

	EmployeeInfo authenticate(int empId, String password);
	
	EmployeeInfo getEmployee(int empId);
	boolean addEmployee(EmployeeInfo employeeInfo);
	boolean updateEmployee(EmployeeInfo employeeInfo);
	boolean deleteEmployee(int empId);
	List<EmployeeInfo>getAllEmployees();
}
