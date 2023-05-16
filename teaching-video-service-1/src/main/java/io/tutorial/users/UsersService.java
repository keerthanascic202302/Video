package io.tutorial.users;

import java.util.List;



public interface UsersService {
	
	public String createUsers(Users users);
	
	public Users getByUsersId(Integer userid);
	
	public List<Users> getAllUsers();
	
	public String deleteByUsersId(Integer userid);
	
	//public List<Users> getAllUsersByusername(String username);
	
	public List<Users> getAllUsersByusername(String username);
	
	public List<Users> getAllUsersByUsernameAndPassword(String username,String password);

}
