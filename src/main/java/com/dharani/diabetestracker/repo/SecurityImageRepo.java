package com.dharani.diabetestracker.repo;

import com.dharani.diabetestracker.entities.SecurityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityImageRepo extends JpaRepository<SecurityImage, Long> {
}
