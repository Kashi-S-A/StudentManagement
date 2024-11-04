package com.ty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ty.entity.Staff;
import com.ty.entity.Student;
import com.ty.service.StaffService;
import com.ty.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("staff", new Staff());
		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView registerStaff(Staff staff) {
		boolean result = staffService.register(staff);
		ModelAndView mv = new ModelAndView();
		if (result) {
			mv.setViewName("login");
			mv.addObject("msg", "Registered Successfully");
		} else {
			mv.setViewName("register");
			mv.addObject("msg", "already registered");
		}

		return mv;
	}

	@PostMapping("/login")
	public ModelAndView loginPage(HttpServletRequest request) {

		String email = request.getParameter("email");

		String password = request.getParameter("password");

		boolean result = staffService.login(email, password);
		ModelAndView mv = new ModelAndView();
		if (result) {
			List<Student> all = studentService.getAll();
			mv.setViewName("home");
			mv.addObject("students", all);
			mv.addObject("msg", "Welcome to App");
		} else {
			mv.setViewName("login");
			mv.addObject("msg", "Invalid credentials");
		}

		return mv;
	}

}
