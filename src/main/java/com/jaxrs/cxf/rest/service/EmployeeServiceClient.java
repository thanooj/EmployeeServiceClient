package com.jaxrs.cxf.rest.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jaxrs.cxf.rest.bo.Department;

@Component("employeeServiceClient")
public class EmployeeServiceClient {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceClient.class);

	@Autowired
	@Qualifier("employeeService")
	private org.apache.cxf.jaxrs.client.WebClient webClient;

	public Response getDepartmentDetailsByID(Integer id) {
		Department department = null;
		webClient.reset();
		webClient.path("/employee/getDepartmentDetailsByID/" + id);
		logger.info(" webClient.getCurrentURI() : " + webClient.getCurrentURI());
		Response response = webClient.get();
		if (response.getStatus() == 200) {
			logger.warn("getDepartmentDetailsByEmployeeID, status code :: " + response.getStatus());
			department = (Department) response.readEntity(Department.class);
			response = Response.status(Status.OK).entity(department).build();
		} else {
			response = Response.status(Status.NOT_FOUND).entity("No Department with " + id).build();
		}
		return response;
	}
}
