package com.lutoke.pond.model.employee;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public enum Role {
	ROLE_ADMIN,
	ROLE_EMPLOYEE,
	ROLE_CONSULTANT,
	ROLE_MANAGER;
	
	public static Role RoleFromEmployee(Employee e) {
		if(e instanceof Admin)
			return ROLE_ADMIN;
		else if(e instanceof Manager)
			return ROLE_MANAGER;
		else if(e instanceof Consultant)
			return ROLE_CONSULTANT;
		else
			return ROLE_EMPLOYEE;
	}

	public static Collection<Role> RolesFromEmployee(Employee e) {
		Set<Role> roles = new HashSet<Role>();
		if(e instanceof Admin)
			roles.add(ROLE_ADMIN);
		if(e instanceof Manager)
			roles.add(ROLE_MANAGER);
		if(e instanceof Consultant)
			roles.add(ROLE_CONSULTANT);
		
		roles.add(ROLE_EMPLOYEE);
		return roles;
	}

}
