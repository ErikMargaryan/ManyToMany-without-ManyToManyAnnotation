package com.manyToMany.without_manyToMany.persistence.repository;


import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
