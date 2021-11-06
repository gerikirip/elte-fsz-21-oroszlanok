package com.training.ediary.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.ediary.domain.EdiaryUser;
import com.training.ediary.domain.security.EdiaryUserDetails;
import com.training.ediary.repository.EdiaryUserRepo;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EdiaryUserRepo ediaryUserRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EdiaryUser user = ediaryUserRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Hibas user!"); 
		}
		
		
		return new EdiaryUserDetails(user);
	}
}
