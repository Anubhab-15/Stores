package in.shop.service;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.shop.entity.UserEntity;
import in.shop.repo.UserRepo;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AppUserDtlService implements UserDetailsService  {
	
	private final UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
	  UserEntity existingUser =	userrepo.findByEmail(email)
		        .orElseThrow(() -> new UsernameNotFoundException("Email not foud" +email));
		
		return new User(existingUser.getEmail(),existingUser.getPassword(),Collections.singleton(new SimpleGrantedAuthority(existingUser.getRole())));
	}

	
}

