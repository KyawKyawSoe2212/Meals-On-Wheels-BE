package com.demo.MOW.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.MOW.Dto.LoginRequestDTO;
import com.demo.MOW.Dto.MemberRegistrationDTO;
import com.demo.MOW.Dto.MessageResponse;
import com.demo.MOW.Dto.PartnerRegistrationDTO;
import com.demo.MOW.Dto.UserRegistrationDTO;
import com.demo.MOW.Entity.User;
import com.demo.MOW.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/member")
    public ResponseEntity<?> registerMember(@Valid @RequestBody MemberRegistrationDTO registrationDTO) {
        User registeredUser = userService.registerMember(registrationDTO);
        return ResponseEntity.ok(new MessageResponse("Member registered successfully!"));
    }
    
    @PostMapping("/register/partner")
    public ResponseEntity<?> registerPartner(@Valid @RequestBody PartnerRegistrationDTO registrationDTO) {
        User registeredUser = userService.registerPartner(registrationDTO);
        return ResponseEntity.ok(new MessageResponse("Partner registered successfully!"));
    }
    
    @PostMapping("/register/caregiver")
    public ResponseEntity<?> registerCaregiver(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        User registeredUser = userService.registerCaregiver(registrationDTO);
        return ResponseEntity.ok(new MessageResponse("Caregiver registered successfully!"));
    }

    @PostMapping("/register/volunteer")
    public ResponseEntity<?> registerVolunteer(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        User registeredUser = userService.registerVolunteer(registrationDTO);
        return ResponseEntity.ok(new MessageResponse("Volunteer registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
    	System.out.print(loginRequest.getEmail());
        Optional<User> user = userService.findUserByEmail(loginRequest.getEmail());

        if (user.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Invalid email or password"));
        }

        return ResponseEntity.ok(new MessageResponse("Login successful"));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Logout successful");
    }
}


