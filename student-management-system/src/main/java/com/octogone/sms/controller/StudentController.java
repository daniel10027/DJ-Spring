package com.octogone.sms.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.octogone.sms.entity.Student;
import com.octogone.sms.service.StudentService;

@Controller 
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students",studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		// creer un object etudiant pour recuperer les données du formulaire
		Student student = new Student();
		model.addAttribute("student",student);
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		// creer un object etudiant pour recuperer les données du formulaire
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(
		@PathVariable Long id,
		@ModelAttribute("student") Student student,
		Model model){
		//Obtenir l'etudiant de la base de données par son Id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		// sauvegarde
		studentService.updateStudent(existingStudent);
		// redirection
		return "redirect:/students";
	}
	
	// Supprimer un étudiant
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id){
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	
	
	
	
	
}