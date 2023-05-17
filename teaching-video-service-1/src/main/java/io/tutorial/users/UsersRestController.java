package io.tutorial.users;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UsersRestController {
	
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	
	@RequestMapping(value = "/upload",
			method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException
	{
		File convertFile = new File("D:/work/" + file.getOriginalFilename());
		convertFile.createNewFile();

		try (FileOutputStream fout = new FileOutputStream(convertFile))
		{
			fout.write(file.getBytes());
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		return "File has uploaded successfully";
	}
	
	
	
	@PostMapping("/user")
	public ResponseEntity<String> createUsers(@RequestBody Users users){
		String status=usersService.createUsers(users);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<Users> getByUsersId(@PathVariable Integer userid){
		Users users=usersService.getByUsersId(userid);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers(@RequestParam(required = false) String username,String password){
		List<Users> users=new ArrayList<Users>();
		try {
		//List<Users> users=usersService.getAllUsers();
		//List<Users> users=usersService.getAllUsersByusername(username);
			if(username!=null && password!=null) {
		users=usersService.getAllUsersByUsernameAndPassword(username, password);
			}else {
				users=usersService.getAllUsers();
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(users,HttpStatus.OK);
		
	}
	
    @PutMapping("/user")
	public ResponseEntity<String> updateUsers(@RequestBody Users users){
		String status=usersService.createUsers(users);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
    
    @DeleteMapping("/user/{userid}")
    public ResponseEntity<String> deleteUsers(@PathVariable Integer userid){
    	String status=usersService.deleteByUsersId(userid);
		return new ResponseEntity<>(status,HttpStatus.OK);
    }
	
}
