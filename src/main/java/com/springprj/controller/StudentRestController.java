package com.springprj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprj.dao.StudentDAO;
import com.springprj.entity.Student;
import com.springprj.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	@Autowired
	private StudentService studentService; 
	
	@GetMapping("/list")
	public List<Student> listStudents(Model theModel) {
		
		// get students from the dao
		List<Student> thestudents = studentService.getStudents();		
		// add the students to the model
		theModel.addAttribute("students", thestudents);
		return thestudents;
		//return "list-students";
	}
	
	@GetMapping("/list/{studentId}")
	public Student getStudent(@PathVariable int studentId)
	{		
		return studentService.getStudentDetails(studentId);
	}
	
	@PostMapping("/students")
	public Student saveStudents(@RequestBody Student theStudent)	
	{
		theStudent.setId(0);
		studentService.saveStudent(theStudent);
		//studentService.saveStudent(theStudent);
  	    //List<Student> thestudents = studentService.getStudents();		
		return theStudent;		
	}
	
	@PutMapping("/students")
	public Student updateCustomer(@RequestBody Student theStudent) {
		
		studentService.saveStudent(theStudent);
		return theStudent;
		
	}

}

