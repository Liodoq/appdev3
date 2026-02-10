package com.gabriel.emplms.controller;
import com.gabriel.emplms.model.Student;
import com.gabriel.emplms.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class StudentController {
	Logger logger = LoggerFactory.getLogger( StudentController.class);
	@Autowired
	private StudentService StudentService;
	@GetMapping("/api/Student")
	public ResponseEntity<?> listStudent()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Student[] Students = StudentService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(Students);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/Student")
	public ResponseEntity<?> add(@RequestBody Student Student){
		logger.info("Input >> " + Student.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Student newStudent = StudentService.create(Student);
			logger.info("created Student >> " + newStudent.toString() );
			response = ResponseEntity.ok(newStudent);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve Student with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/Student")
	public ResponseEntity<?> update(@RequestBody Student Student){
		logger.info("Update Input >> Student.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Student newStudent = StudentService.update(Student);
			response = ResponseEntity.ok(newStudent);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve Student with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/Student/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input Student id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Student Student = StudentService.get(id);
			response = ResponseEntity.ok(Student);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/Student/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			StudentService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
