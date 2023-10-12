package com.dharani.diabetestracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dt_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateofbirth;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="SECURITY_VALUE_ID")
    private List<SecurityValues> securityValues;

    private String password;

    public List<SecurityValues> getSecurityValues() {
        return securityValues;
    }
}
