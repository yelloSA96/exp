package com.lutoke.pond.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lutoke.pond.model.employee.Employee;
import com.lutoke.pond.model.employee.Role;


public class EmployeeDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee employee;
	private Role role;
	public EmployeeDetails(Employee employee) {
		super();
		this.employee = employee;
		this.role = Role.RoleFromEmployee(employee);
	}

	public Employee getEmployee() {
		return employee;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		for(Role r : Role.RolesFromEmployee(employee)) {
			roles.add(new SimpleGrantedAuthority(r.toString().toUpperCase()));
		}
		return roles;
		
//		String roleName = Role.RoleFromEmployee(employee).toString().toUpperCase(); // ROLE_ADMIN
//		return Collections.singleton(new SimpleGrantedAuthority(roleName));
	}

	@Override
	public String getPassword() {
		return this.employee.getPassword();
	}

	@Override
	public String getUsername() {
		return this.employee.getEmail();
	}
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}