package com.springprj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprj.dao.StudentDAO;
import com.springprj.entity.Student;
import com.springprj.service.StudentService;

@RestController
@RequestMapping("/student")
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
	public String saveStudents(@RequestBody Student theStudent)	
	{
		theStudent.setId(0);
		studentService.saveStudent(theStudent);
		//studentService.saveStudent(theStudent);
		return "redirect:/student/list";		
	}
	
	@GetMapping("/showFormForAdd")
	public String showForm(Model theModel)
	{		
		//This is for binding the data
		Student theStudent=new Student();
	    theModel.addAttribute("student",theStudent);
		return "student-form";
	}
	
	@PostMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student theStudent)	
	{
		studentService.saveStudent(theStudent);
		return "redirect:/student/list";		
	}
	
	//Because for the below the form is in the format loca..../showFormForUpdate?studentId=1
	@GetMapping("/showFormForUpdate")
	public String updateData(@RequestParam("studentId") int theId,Model theModel) 
	{
		//theModel
		Student theStudent=studentService.getStudentDetails(theId);
		theModel.addAttribute("student", theStudent); 
		return "student-update-form";
	}
	
	
	@GetMapping("/deleteAction")
	public String deleteAction(@RequestParam("studentId") int theId)
	{
		studentService.deleteStudent(theId);
		/*
		List<Student> thestudents = studentService.getStudents();
		theModel.addAttribute("students",thestudents);
		return "list-students";*/
		return "redirect:/student/list";
	}
	
}

