package gr.hua.dit.ds.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.entities.Application;
import gr.hua.dit.ds.services.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
	private ApplicationService applicationService;
	
	

	public ApplicationController(ApplicationService applicationService) {
		super();
		this.applicationService = applicationService;
	}

	// build create application REST API
	@PostMapping()
	public ResponseEntity<Application> saveApplication(@RequestBody Application application){
		return new ResponseEntity<Application>(applicationService.saveApplication(application), HttpStatus.CREATED);
	}
	
	// build get all application REST API
	@GetMapping
	public List<Application> getAllApplications(){
		return applicationService.getAllApplications();
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Application> getApplicationByStudent(@PathVariable("studentId") String studentId){
		return new ResponseEntity<Application>(applicationService.getApplicationByStudent(studentId), HttpStatus.OK);
	}
	
	// build get application by id REST API
	// http://localhost:8080/api/applications/1
	@GetMapping("{id}")
	public ResponseEntity<Application> getApplicationById(@PathVariable("id") long applicationId){
		return new ResponseEntity<Application>(applicationService.getApplicationById(applicationId), HttpStatus.OK);
	}
	
	// build update application REST API
	// http://localhost:8080/api/applications/1
	@PutMapping("{id}")
	public ResponseEntity<Application> updateApplication(@PathVariable("id") long id
												  ,@RequestBody Application application){
		return new ResponseEntity<Application>(applicationService.updateApplication(application, id), HttpStatus.OK);
	}
	
	// build delete application REST API
	// http://localhost:8080/api/applications/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteApplication(@PathVariable("id") long id){
		
		// delete application from DB
		applicationService.deleteApplication(id);
		
		return new ResponseEntity<String>("Application deleted successfully!.", HttpStatus.OK);
	}

}

