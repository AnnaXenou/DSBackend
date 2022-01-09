package gr.hua.dit.ds;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import gr.hua.dit.ds.entities.Authority;
import gr.hua.dit.ds.entities.User;
import gr.hua.dit.ds.repository.UserDetailsRepository;

@SpringBootApplication
public class PostgraduateProjectApplication {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(PostgraduateProjectApplication.class, args);
	}
	
	@PostConstruct
	protected void init() {
		
		List<Authority> authorityList=new ArrayList<>();
		
		authorityList.add(createAuthority("ADMIN","Admin role"));
		authorityList.add(createAuthority("SECRETARY","Secretary role"));
		
		User user=new User();
		
		user.setUserName("admin");
		user.setFirstName("admin");
		user.setLastName("admin");
		
		user.setPassword(passwordEncoder.encode("admin"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		
		//userDetailsRepository.save(user);
	}
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
	
	

}
