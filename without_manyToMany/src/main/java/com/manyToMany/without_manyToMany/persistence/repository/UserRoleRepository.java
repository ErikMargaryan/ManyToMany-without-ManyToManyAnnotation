package com.manyToMany.without_manyToMany.persistence.repository;

import com.manyToMany.without_manyToMany.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    @Query(value = "select role_id from user_role where user_id = ?", nativeQuery = true)
    Long findRoleId(Long id);

}
