package com.paymo.login.repository;

import com.paymo.login.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Asus on 11/15/2018.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRole(String role);
}
