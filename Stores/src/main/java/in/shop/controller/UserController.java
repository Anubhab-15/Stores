package in.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.shop.binding.UserRequest;
import in.shop.binding.UserResponse;
import in.shop.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {
    
	 private final UserService userService;
	 
	 @PostMapping("/registr")
	 public UserResponse registerUser(@RequestBody UserRequest request) {
		 try {
			 return userService.createUser(request);
		 }catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create"+e.getMessage());
		 }
	 }
	 
	 @GetMapping("/users")
		public List<UserResponse> readUsers(){
			return  userService.readUsers();
	    	 }
	 
	 @DeleteMapping("/users/{id}")
	 public void deleteUser(@PathVariable String id) {
		 
		 try {
			 userService.deleteUser(id);
		 }catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		 }
		 
	 }
}
