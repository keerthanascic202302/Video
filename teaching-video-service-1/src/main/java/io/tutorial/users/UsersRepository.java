package io.tutorial.users;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UsersRepository extends JpaRepository<Users, Serializable>{

	/*
	 * @Query(value="select * from user_details where username=:username"
	 * ,nativeQuery = true) List<Users> findByUsernames(@Param("username")String
	 * username);
	 */
	
	//List<Users> findByUsername(String username);
	List<Users> findByUsernameAndPassword(String username,String password);
	
	List<Users> findByUsername(String username);
	
}
