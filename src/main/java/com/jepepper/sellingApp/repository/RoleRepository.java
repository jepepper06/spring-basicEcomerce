package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}