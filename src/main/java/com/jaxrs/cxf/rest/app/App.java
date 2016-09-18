package com.jaxrs.cxf.rest.app;

import javax.ws.rs.core.Response;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jaxrs.cxf.rest.bo.Department;
import com.jaxrs.cxf.rest.service.EmployeeServiceClient;

public class App {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {
			EmployeeServiceClient client = (EmployeeServiceClient) context.getBean("employeeServiceClient");
			Response response = client.getDepartmentDetailsByID(102);
			Department department = (Department) response.readEntity(Department.class);
			System.out.println("department: "+ department);
		}
	}
}
