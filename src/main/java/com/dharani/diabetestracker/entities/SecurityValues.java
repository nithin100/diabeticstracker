package com.dharani.diabetestracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "security_type", nullable = false)
    private SecurityType securityType;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private DTUser user;

    private String question;
    private String answer;

    @Column(name = "user_name")
    private String userName;

    public void setUserName(String userName){
        this.userName = userName;
    }

    public enum SecurityType {
        QUESTION,
        IMAGE
    };
}
