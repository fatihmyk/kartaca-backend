package com.fatihmayuk.kartaca.backend.repository;

import com.fatihmayuk.kartaca.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
