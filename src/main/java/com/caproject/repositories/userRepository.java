package com.caproject.repositories;

import com.caproject.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByEmail(String email);
    UserEntity findUserEntityById(String id);




}
