package org.a2lpo.taskblock.repository;

import org.a2lpo.taskblock.model.Role;
import org.a2lpo.taskblock.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
