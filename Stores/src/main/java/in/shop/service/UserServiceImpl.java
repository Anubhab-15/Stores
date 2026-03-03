package in.shop.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.shop.binding.UserRequest;
import in.shop.binding.UserResponse;
import in.shop.entity.UserEntity;
import in.shop.repo.UserRepo;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo  userRepo; 
	
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserResponse createUser(UserRequest request) {
	
		UserEntity newUser = convertToEntity(request);
		
		newUser = userRepo.save(newUser);
		
		
		return convertToResponse(newUser);
	}

	private UserResponse convertToResponse(UserEntity newUser) {
		// TODO Auto-generated method stub
		return UserResponse.builder()
                    .name(newUser.getName())
                    .email(newUser.getEmail())
                    .userId(newUser.getUserId())
                    .createdAt(newUser.getCreatedAt())
                    .updatedAt(newUser.getUpdatedAt())
                    .role(newUser.getRole())
                    .build();
		
	}

	private UserEntity convertToEntity(UserRequest request) {
		return UserEntity.builder()
	              .userId(UUID.randomUUID().toString())
	              .name(request.getEmail())
	              .password(passwordEncoder.encode(request.getPassword()))
	              .role(request.getRole().toUpperCase())
	              .name(request.getName())
	              .build();
	              
	}

	@Override
	public String getUserRole(String email) {
		// TODO Auto-generated method stub
		UserEntity exitingUser =  userRepo.findByEmail(email)
				                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return exitingUser.getRole();
	}

	@Override
	public List<UserResponse> readUsers() {
		// TODO Auto-generated method stub
		 return  userRepo.findAll()
                 .stream()
                 .map(user ->convertToResponse(user))
                 .collect(Collectors.toList());
		
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		UserEntity existingUser = userRepo.findByUserId(id)
		        .orElseThrow(() -> new UsernameNotFoundException("User not found") );
		userRepo.delete(existingUser);
	}

	
}
