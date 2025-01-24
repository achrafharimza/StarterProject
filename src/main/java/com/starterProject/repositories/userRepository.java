package com.starterProject.repositories;

import com.starterProject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByEmail(String email);
    UserEntity findUserEntityById(String id);




}
