package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsersRepository userRepo;
	
	@Autowired
	LoginResponse response;

	public LoginResponse validateUser(LoginRequest loginRequest) {

		List<Users> liUser = userRepo.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

		if (liUser.size() == 1) {
			response.setStatus("Success");
			response.setMessage("Login successful");
		} else {
			response.setStatus("Fail");
			response.setMessage("Login failed");
		}
		return response;
	}
}
