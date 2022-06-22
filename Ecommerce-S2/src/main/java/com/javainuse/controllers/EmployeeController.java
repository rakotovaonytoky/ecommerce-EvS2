package com.javainuse.controllers;

import com.javainuse.model.Employee;
import com.javainuse.model.UserRegistration;
import com.javainuse.service.EmployeeService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/welcome")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }
    
    @GetMapping("/")
    public RedirectView LoadIndex(Principal p){
        RedirectView r=new RedirectView("/customer/shop");
//        return "index";
         return r;
    }
    
    @RequestMapping(value = "/addNewEmployee", method = RequestMethod.GET)
    public ModelAndView show() {
        return new ModelAndView("addEmployee", "emp", new Employee());
    }

    @RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST)
    public ModelAndView processRequest(@ModelAttribute("emp") Employee emp) {

        employeeService.insertEmployee(emp);
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView model = new ModelAndView("getEmployees");
        model.addObject("employees", employees);
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("registration", "user", new UserRegistration());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute("user") UserRegistration userRegistrationObject) {

        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        String encodedPassword = bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());
        User user = new User(userRegistrationObject.getUsername(), encodedPassword, authorities);
        jdbcUserDetailsManager.createUser(user);
        return new ModelAndView("redirect:/welcome");
    }

    @RequestMapping("/getEmployees")
    public ModelAndView getEmployees(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getUsername());
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView model = new ModelAndView("getEmployees");
        model.addObject("employees", employees);
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully.");
        }

        return "index";
    }
    
    
}
