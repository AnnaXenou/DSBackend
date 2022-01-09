package gr.hua.dit.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.dit.ds.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{
	//Application getApplicationByStudent(String studentId);
}
