package io.mdubovac.LoginSystem.controller;

import io.mdubovac.LoginSystem.model.User;
import io.mdubovac.LoginSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
    	model.addAttribute("user", new User());
    	return "auth/register";
    }
    
    @GetMapping("/register_success")
    public String registerSuccess() {
    	return "auth/register_success";
    }
    
    @PostMapping("/register_user")
    public String proccessRegister(User user) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
    	
    	userRepository.save(user);
		
    	return "redirect:/register_success";
    }

}
