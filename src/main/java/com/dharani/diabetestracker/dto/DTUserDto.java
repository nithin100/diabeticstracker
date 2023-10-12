package com.dharani.diabetestracker.dto;

import com.dharani.diabetestracker.entities.SecurityValues;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTUserDto {
    private String userName;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Date dateofbirth;
    private List<SecurityValuesDto> securityValues;
    private String password;
}
