package com.dharani.diabetestracker.repo;

import com.dharani.diabetestracker.entities.SecurityValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DTUserSecurityValuesRepo extends JpaRepository<SecurityValues, Long> {
    List<SecurityValues> findSecurityValuesByUserNameAndSecurityType(String userName, SecurityValues.SecurityType securityType);
}
