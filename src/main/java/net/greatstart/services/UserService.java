package net.greatstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greatstart.model.User;
import net.greatstart.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User findByName(String name){
		return userRepo.findByName(name);
	}
	
	public void save(User user){
		userRepo.save(user);
	}
	
}
