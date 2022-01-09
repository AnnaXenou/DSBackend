package gr.hua.dit.ds.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import gr.hua.dit.ds.exception.ResourceNotFoundException;
import gr.hua.dit.ds.entities.Application;
import gr.hua.dit.ds.repository.ApplicationRepository;
import gr.hua.dit.ds.services.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{

	private ApplicationRepository applicationRepository;
	private ApplicationService applicationService;
	
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}

	@Override
	public Application saveApplication(Application application) {
		return applicationRepository.save(application);
	}

	@Override
	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	@Override
	public Application getApplicationById(long id) {
		return applicationRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Application", "Id", id));
		
	}
	
	@Override
	public Application getApplicationByStudent(String studentId) {
		
		List<Application> list = applicationRepository.findAll();
		Application app = null;
		
		for(Application l : list) {
			if(l.getStudentId().equals(studentId)) {
				app = l;
			}
		}
		
		if (app != null) {
			return app;
		}else {
			throw new ResourceNotFoundException("Application", "studentId", studentId);
		}
		
//		try {
//			return applicationService.getApplicationByStudent(studentId);
//		}catch(Exception e) {
//			throw new ResourceNotFoundException("Application", "studentId", studentId);
//		}
		
//		return applicationRepository.findByStudent(id).orElseThrow(() -> 
//						new ResourceNotFoundException("Application", "Id", id));
		
	}

	@Override
	public Application updateApplication(Application application, long id) {
		
		// we need to check whether application with given id is exist in DB or not
		Application existingApplication= applicationRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Application", "Id", id)); 
		
		existingApplication.setStudentId(application.getStudentId());
		existingApplication.setProgram(application.getProgram());
		existingApplication.setGrade(application.getGrade());
		existingApplication.setProfessors(application.getProfessors());
		// save existing application to DB
		applicationRepository.save(existingApplication);
		return existingApplication;
	}

	@Override
	public void deleteApplication(long id) {
		
		// check whether a application exist in a DB or not
		applicationRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Application", "Id", id));
		applicationRepository.deleteById(id);
	}
	
}

