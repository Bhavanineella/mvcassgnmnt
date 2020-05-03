package com.capgemini.mvc_assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.capgemini.mvc_assignment.beans.EmployeeInfo;

@Repository
public class EmployeeDaoImplements implements EmployeeDao{

	
	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public EmployeeInfo getEmployee(int empId) {
		EntityManager manager = factory.createEntityManager();
	EmployeeInfo employeeInfo = manager.find(EmployeeInfo.class, empId);
		manager.close();
		return employeeInfo;
	}

	@Override
	public boolean addEmployee(EmployeeInfo employeeInfo) {
		
		boolean isAdded = false;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(employeeInfo);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return isAdded;
	}

	@Override
	public boolean updateEmployee(EmployeeInfo employeeInfo) {
		
		EntityManager manager = factory.createEntityManager();
		EmployeeInfo bean = manager.find(EmployeeInfo.class, employeeInfo.getEmpId());
		boolean isUpdated = false;
		if (bean != null) {
			try {
				System.out.println("in update"+employeeInfo.getEmpId());
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				manager.remove(bean);
				manager.persist(employeeInfo);
				transaction.commit();
				isUpdated = true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				manager.close();
			}

		}
		return isUpdated;
	}

	@Override
	public boolean deleteEmployee(int empId) {
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isDeleted = false;
		try {
			transaction.begin();
			EmployeeInfo productInfoBean = manager.find(EmployeeInfo.class, empId);
			System.out.println(productInfoBean.getEmpId());
			System.out.println(productInfoBean.getName());
			manager.remove(productInfoBean);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return isDeleted;
	}

	@Override
	public List<EmployeeInfo> getAllEmployees() {
		
		EntityManager manager = factory.createEntityManager();
		String jpql = "Select e from EmployeeInfoBean e";
		Query query = manager.createQuery(jpql);
		List<EmployeeInfo> list = query.getResultList();
		return list;
	}

	@Override
	public EmployeeInfo authenticate(int empId, String password) {
		
		//String ipql = " from EmployeeInfoBean where empId := empId and password := pwd";
		
		EmployeeInfo employeeInfo = getEmployee(empId);
		if(employeeInfo != null && employeeInfo.getPassword().equals(password)) {
			return employeeInfo;
		}
		return null;
	}

	
}
