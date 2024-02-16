package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
	
	@GeneratedValue
	@Id
	@Column(name = "user_code", nullable = false, updatable = false, insertable = false)
	private long user_code;
	
	@Column(name = "userId", nullable = false, updatable = false)
	private long userId;
	
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private String age;
	
	public static Users getInstance() {
		return new Users();
	}

	public String getUsername() {
		return username;
	}

	public Users setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Users setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Users setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getAge() {
		return age;
	}

	public Users setAge(String age) {
		this.age = age;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public Users setUserId(long userId) {
		this.userId = userId;
		return this;
	}

}
