package com.demo.MOW.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.MOW.Dto.MemberRegistrationDTO;
import com.demo.MOW.Dto.PartnerRegistrationDTO;
import com.demo.MOW.Dto.UserRegistrationDTO;
import com.demo.MOW.Entity.Role;
import com.demo.MOW.Entity.User;
import com.demo.MOW.Repository.RoleRepository;
import com.demo.MOW.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerMember(MemberRegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setQualifiedCase(registrationDTO.getQualifiedCase());
        user.setAddress(registrationDTO.getAddress());

        // Implement validation, password encoding, and other necessary logic
        // For simplicity, let's assume password encoding has been done already

     // Check if "member" role already exists
        Role memberRole = roleRepository.findByRole("member");

        if (memberRole == null) {
            // If "member" role doesn't exist, create a new one
            memberRole = new Role();
            memberRole.setRole("member");
        }

        user.getRoles().add(memberRole);
        return userRepository.save(user);
    }

    public User registerPartner(PartnerRegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setAddress(registrationDTO.getAddress());

        Role partnerRole = roleRepository.findByRole("partner");

        if (partnerRole == null) {
            partnerRole = new Role();
            partnerRole.setRole("partner");
        }

        user.getRoles().add(partnerRole);
        return userRepository.save(user);
    }
    
    public User registerCaregiver(UserRegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        Role caregiverRole = roleRepository.findByRole("caregiver");

        if (caregiverRole == null) {
            caregiverRole = new Role();
            caregiverRole.setRole("caregiver");
        }

        user.getRoles().add(caregiverRole);
        return userRepository.save(user);
    }
    
    public User registerVolunteer(UserRegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        Role volunteerRole = roleRepository.findByRole("volunteer");

        if (volunteerRole == null) {
            volunteerRole = new Role();
            volunteerRole.setRole("caregiver");
        }

        user.getRoles().add(volunteerRole);
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public boolean isEmailUnique(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
