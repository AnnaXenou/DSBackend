package gr.hua.dit.ds.services;

import java.util.List;

import gr.hua.dit.ds.entities.Application;


public interface ApplicationService {
	Application saveApplication(Application application);
	List<Application> getAllApplications();
	Application getApplicationById(long id);
	Application updateApplication(Application application, long id);
	Application getApplicationByStudent(String studentId);
	void deleteApplication(long id);
}
