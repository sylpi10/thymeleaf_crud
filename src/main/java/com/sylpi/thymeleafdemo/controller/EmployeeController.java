package com.sylpi.thymeleafdemo.controller;

import com.sylpi.thymeleafdemo.entity.Employee;
import com.sylpi.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    //load person data
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model model){

        //get employees from db
        List<Employee> employees = employeeService.findAllByOrderByLastNameAsc();
        // add to the spring model
        model.addAttribute("employees", employees);

        return "employees/list-persons";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        Employee theEmployee = new Employee();

        model.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);

        //use redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
}
