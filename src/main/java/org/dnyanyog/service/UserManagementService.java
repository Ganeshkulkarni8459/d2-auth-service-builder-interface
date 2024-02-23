package org.dnyanyog.service;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;

public interface UserManagementService {
	
	public Optional<AddUserResponse> addUpdateUser(AddUserRequest request) throws Exception;
	
	public AddUserResponse getSingleUser(Long userId) throws Exception;
	
	public List<Users> getAllUser();
	
	public List<Long> getAllUserIds();
}
