package com.bridgelabz.demo.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.demo.model.User;
import java.lang.String;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//UserInfo findByEmailid(String emailid);
	//UserInfo  findByEmailid(String emailid);
	//Optional<UserInfo> findById( int id);

	
	Optional<User> findByEmailid(String emailid);


	


}
