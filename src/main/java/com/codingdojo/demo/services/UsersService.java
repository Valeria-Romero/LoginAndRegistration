package com.codingdojo.demo.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.codingdojo.demo.models.User;
import com.codingdojo.demo.repositories.UserRepository;

@Service
public class UsersService {
	
	private final UserRepository userRepository;
	
	public UsersService( UserRepository userRepository ) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUserByEmail( String email ){
		return userRepository.selectUserByEmail(email);
	}
	
	public void registerUser( String email, String firstname, String lastname, String password ) {
		String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println( encryptPassword );
		userRepository.insertUser(email, firstname, lastname, encryptPassword);
	}
	
	public boolean validateUser( User currentUser, String password ) {
		return BCrypt.checkpw( password, currentUser.getPassword() );
	}
}