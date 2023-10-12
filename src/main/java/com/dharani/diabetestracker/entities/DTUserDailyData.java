package com.dharani.diabetestracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "dt_user_daily_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTUserDailyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String userName;
    private LocalDate date;
    private int bloodSugarAM;
    private String breakfast;
    private String breakfastTime;
    private String lunch;
    private String lunchTime;
    private String dinner;
    private String dinnerTime;
    private int bloodSugarPM;
}
