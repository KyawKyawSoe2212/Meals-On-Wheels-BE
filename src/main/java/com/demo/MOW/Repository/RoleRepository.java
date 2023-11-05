package com.demo.MOW.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.MOW.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
