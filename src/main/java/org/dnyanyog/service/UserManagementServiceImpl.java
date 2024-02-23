package org.dnyanyog.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService  {

	Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	@Autowired
	UsersRepository userRepo; 
	
	@Autowired
	AddUserResponse userResponse;
	
	@Autowired
	private List<Long> userIds;
	
	@Autowired
	EncryptionService encryptionService;
	
	@Override
	public Optional<AddUserResponse> addUpdateUser(AddUserRequest request) throws Exception {

		Users usersTable = Users.getInstance()
				.setUserId(request.getUserId())
				.setAge(request.getAge())
				.setEmail(request.getEmail())
				.setPassword(encryptionService.encrypt(request.getPassword()))
				.setUsername(request.getUsername());

		usersTable = userRepo.save(usersTable); 
		
		userResponse.setMessage("User added successfuly"); 
		userResponse.setStatus("Success");
		userResponse.getUserData().setUserId(usersTable.getUserId()); 
		userResponse.setStatus("Success");
		userResponse.setMessage("User found");
		
		
		userResponse.getUserData().setEmail(usersTable.getEmail());
		userResponse.getUserData().setUsername(usersTable.getUsername());
		userResponse.getUserData().setPassword(encryptionService.decrypt(usersTable.getPassword()));
		userResponse.getUserData().setAge(usersTable.getAge());

		return Optional.of(userResponse);
	}
	
	@Override
	public AddUserResponse getSingleUser(Long userId) throws Exception {

		Optional<Users> receivedData = userRepo.findById(userId);

		if (receivedData.isEmpty()) {
			userResponse.setStatus("Fail");
			userResponse.setMessage("User not found");
		} else {
			Users user = receivedData.get();	
					
			userResponse.setStatus("Success");
			userResponse.setMessage("User found");
			userResponse.getUserData().setUserId(user.getUserId());
			userResponse.getUserData().setEmail(user.getEmail());
			userResponse.getUserData().setUsername(user.getUsername());
			userResponse.getUserData().setPassword(encryptionService.decrypt(user.getPassword()));
			userResponse.getUserData().setAge(user.getAge());
		}
		return userResponse;
	}
	
	@Override
	public List<Users> getAllUser() {
		return userRepo.findAll();
	}
	
	@Override
	public List<Long> getAllUserIds() {

		List<Users> users = userRepo.findAll();

		for (Users user : users) {
			if (nonNull(user)) {
				userIds.add(user.getUserId());
			}
		}
		return userIds;
	}
}
