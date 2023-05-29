package com.fuad.crud.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    String findByName(String roleUser);
}
