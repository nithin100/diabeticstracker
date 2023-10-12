package com.dharani.diabetestracker.repo;

import com.dharani.diabetestracker.entities.DTUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DTUserRepo extends JpaRepository<DTUser, Long> {
    DTUser findByUserName(String userName);
    DTUser findByEmail(String email);
}
