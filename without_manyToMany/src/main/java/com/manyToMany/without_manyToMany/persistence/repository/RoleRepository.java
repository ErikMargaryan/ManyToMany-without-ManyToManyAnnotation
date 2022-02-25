package com.manyToMany.without_manyToMany.persistence.repository;

import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "select id from role where name = ?", nativeQuery = true)
    Long findRoleId(String name);
}
