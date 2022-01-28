package gr.hua.dit.ds.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "studentid")
	private String studentId;
	
	@Column(name = "program")
	private String program;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "professors")
	private String professors;
	
	@Column(name = "master")
	private String master;
	
	
	public Application() {
		super();
	}
	
	public Application(String studentId, String program, String grade, String professors, String master) {
		super();
		this.studentId = studentId;
		this.program = program;
		this.grade = grade;
		this.professors = professors;
		this.master = master;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProfessors() {
		return professors;
	}

	public void setProfessors(String professors) {
		this.professors = professors;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	
	

}
