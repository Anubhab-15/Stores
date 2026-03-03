package in.shop.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.shop.binding.AuthRequest;
import in.shop.binding.AuthResponse;
import in.shop.service.AppUserDtlService;
import in.shop.service.UserService;
import in.shop.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
  
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager ;
	
	private  final AppUserDtlService appUserDetailsService;
	
	private  final JwtUtil jwtUtil;
	
	private  final UserService userService;
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest request)  throws Exception {
		
		authenticate(request.getEmail(),request.getPassword());
		
		final UserDetails  userDatails= appUserDetailsService.loadUserByUsername(request.getEmail());
		
		final String jwtToken = jwtUtil.generateToken(userDatails);
		
		String role = userService.getUserRole(request.getEmail());
		
		return new AuthResponse(request.getEmail(), role, jwtToken);
		
	}
	
	private void authenticate(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}catch(DisabledException e){
			
			throw new Exception("user disabale");
		}catch(BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or Password is incorrect");
		}
		
	}

	@PostMapping("/encode")
	public String encodePassword(@RequestBody Map<String,String> request) {
	
		return passwordEncoder.encode(request.get("password"));
		
		
	}
}
