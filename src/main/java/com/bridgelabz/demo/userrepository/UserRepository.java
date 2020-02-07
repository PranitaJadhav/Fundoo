package com.bridgelabz.demo.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.demo.model.UserInfo;
import java.lang.String;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByEmailid(String emailid);

}
