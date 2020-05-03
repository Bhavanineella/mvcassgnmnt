package com.capgemini.mvc_assignment.dao;

import java.util.List;

import com.capgemini.mvc_assignment.beans.EmployeeInfo;

public interface EmployeeDao {
	
	EmployeeInfo authenticate(int empId, String password);
	
	EmployeeInfo getEmployee(int empId);
	boolean addEmployee(EmployeeInfo employeeInfoBean);
	boolean updateEmployee(EmployeeInfo employeeInfoBean);
	boolean deleteEmployee(int empId);
	List<EmployeeInfo>getAllEmployees();
	
}
