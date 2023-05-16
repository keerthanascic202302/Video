package io.tutorial.users;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public String createUsers(Users users) {
		usersRepository.save(users);
		return "Success";
	}

	@Override
	public Users getByUsersId(Integer userid) {
		Optional<Users> findById=usersRepository.findById(userid);
		if(findById.isPresent()) {
			return findById.get();
		}
		
		return null;
	}
	
	
	@Override
	public List<Users> getAllUsers() {
		
		return usersRepository.findAll();
	}
	
	

	@Override
	public String deleteByUsersId(Integer userid) {
		if(usersRepository.existsById(userid)) {
			usersRepository.deleteById(userid);
			return "Deleted Successfully";
		}else {
			return "No Record Found";
		}
		
	}

	/*
	 * @Override public List<Users> getAllUsersByusername(String username) { // TODO
	 * Auto-generated method stub return usersRepository.findByUsernames(username);
	 * }
	 */

	@Override
	public List<Users> getAllUsersByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return usersRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<Users> getAllUsersByusername(String username) {
		// TODO Auto-generated method stub
		return usersRepository.findByUsername(username);
	}

}
