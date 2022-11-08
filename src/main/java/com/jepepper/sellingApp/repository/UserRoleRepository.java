package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.UserRole;
import com.jepepper.sellingApp.domain.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
}