package com.dharani.diabetestracker.repo;

import com.dharani.diabetestracker.entities.DTUserDailyData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDailyDataRepo extends JpaRepository<DTUserDailyData, Long> {
    List<DTUserDailyData> findDTUserDailyDataByUserName(String userName);
}
