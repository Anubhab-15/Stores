package in.shop.service;

import java.util.List;

import in.shop.binding.UserRequest;
import in.shop.binding.UserResponse;

public interface UserService {

	UserResponse createUser(UserRequest request);
	
	String getUserRole(String email);
	
	List<UserResponse> readUsers();
	
	void deleteUser(String id);
}
