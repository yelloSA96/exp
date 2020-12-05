package com.lutoke.pond.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.lutoke.pond.model.employee.Consultant;
import com.lutoke.pond.model.employee.Employee;
import com.lutoke.pond.model.employee.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	private String secretKey;
	private long tokenValidity;

	@Autowired
	public JwtTokenUtil(@Value("${security.jwt.token.secret-key}") String secretKey,
			@Value("${security.jwt.token.expiration}") long tokenValidity) {
		super();
		this.secretKey = secretKey;
		this.tokenValidity = tokenValidity;
	}

	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	public String generateToken(Employee e) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("firstName", e.getFirstName());
		claims.put("lastName", e.getLastName());
		claims.put("role",Role.RoleFromEmployee(e));
		claims.put("employeeId", e.getEmployeeID());
		return doGenerateToken(claims, e.getEmail());
	}
	public String generateToken(EmployeeDetails eDetails) {
		Map<String, Object> claims = new HashMap<>();
		Employee e = eDetails.getEmployee();
		claims.put("firstName", e.getFirstName());
		claims.put("lastName", e.getLastName());
		claims.put("role",Role.RoleFromEmployee(e));
		claims.put("employeeId", e.getEmployeeID());
		return doGenerateToken(claims, eDetails.getUsername());
		
	}
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}