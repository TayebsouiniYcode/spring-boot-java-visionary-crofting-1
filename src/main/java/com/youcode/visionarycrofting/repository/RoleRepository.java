package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository< Role, Long> {
    Role findByName(String name);
}
