package com.cst438.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/student")
	public StudentDTO addStudent(@RequestBody StudentDTO s) {
		Student addStudent = studentRepository.findByEmail(s.email); // looking up in the database
		// if the student isn't in there, we create the student
		if(addStudent == null) {
			addStudent = new Student();
			addStudent.setName(s.name);
			addStudent.setEmail(s.email);
			addStudent = studentRepository.save(addStudent);
			s.student_id = addStudent.getStudent_id();
			return s;
		} else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student email already exists." );
		}
	}
	

	
	@PutMapping("/student") // put is updating
	public void updateStudent(@RequestBody StudentDTO s) {
		Student updateStudent = studentRepository.findByEmail(s.email);
		// Another way to find the student
		//Student student = studentRepository.findById(s.student_id).orElse(null);
		//Optional<Student> t = studentRepository.findById(s.student_id);
		if (updateStudent == null) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student does not exist. Cannot update" );
		} else {
			updateStudent.setStatus(s.status);
			updateStudent.setStatusCode(s.statusCode);
			studentRepository.save(updateStudent);
		}
	}	
}
