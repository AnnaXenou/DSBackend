package gr.hua.dit.ds.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.entities.Application;
import gr.hua.dit.ds.entities.Authority;
import gr.hua.dit.ds.entities.User;
import gr.hua.dit.ds.exception.ResourceNotFoundException;
import gr.hua.dit.ds.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/add")
	public ResponseEntity<User> saveApplication(@RequestBody Map<String, String> json){
		List<Authority> authorityList=new ArrayList<>();
		
		if (json.get("auth").equals("user")) {
			authorityList.add(createAuthority("USER","User role"));
		}else if(json.get("auth").equals("admin")) {
			authorityList.add(createAuthority("ADMIN","Admin role"));
		}else if(json.get("auth").equals("secretary")) {
			authorityList.add(createAuthority("SECRETARY","Secretary role"));
		}else if(json.get("auth").equals("professor")) {
			authorityList.add(createAuthority("PROFESSOR","Professor role"));
		}
		
		User user=new User();
		
		user.setUserName(json.get("userName"));
		user.setFirstName(json.get("firstName"));
		user.setLastName(json.get("lastName"));
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setEmail(json.get("email"));
		user.setPhoneNumber(json.get("phone"));
		
		//will encode pass in front
		user.setPassword(passwordEncoder.encode(json.get("password")));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		
		
		
		return new ResponseEntity<User>(userDetailsRepository.save(user), HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping()
	public List<User> getUsers(){
		return userDetailsRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		userDetailsRepository.deleteById(id);
		return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("{id}")
	public User updateUser(@PathVariable("id") long id
												  ,@RequestBody User user){
		
		User existingUser = userDetailsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", id)); 
		
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		existingUser.setCreatedAt(user.getCreatedAt());
		existingUser.setUpdatedAt(new Date());
		existingUser.setEnabled(user.isEnabled());
		userDetailsRepository.save(existingUser);
		return existingUser;
	}
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
}