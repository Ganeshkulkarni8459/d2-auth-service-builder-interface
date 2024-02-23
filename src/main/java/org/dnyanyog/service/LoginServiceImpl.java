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
	
	@Autowired
	EncryptionService encryptionService;

	public LoginResponse validateUser(LoginRequest loginRequest) throws Exception {

		List<Users> liUser = userRepo.findByUsername(loginRequest.getUsername());

		if (liUser.size() == 1) {
			
			Users userData = liUser.get(0);
			
			String encryptedPassword = userData.getPassword();	
			String getEncryptedPassword = encryptionService.encrypt(loginRequest.getPassword());
			
			if(getEncryptedPassword.equalsIgnoreCase(encryptedPassword)) {
				response.setStatus("Success");
				response.setMessage("Login successful");
			} else {
				response.setStatus("Fail");
				response.setMessage("Login failed");
			}
		}
		return response;
	}
}
