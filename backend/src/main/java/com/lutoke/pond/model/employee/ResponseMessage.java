package com.lutoke.pond.model.employee;

public class ResponseMessage {
	Role role;
	long employeeID;
	String employeeFirstName;
	String employeeLastName;

	ResponseMessage(Employee e){
		employeeID = e.getEmployeeID();
		employeeFirstName=e.getFirstName();
		employeeLastName = e.getLastName();
		role = Role.RoleFromEmployee(e);
	}
}
