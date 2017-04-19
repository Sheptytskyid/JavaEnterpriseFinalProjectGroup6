package net.greatstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.greatstart.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);
	
}
