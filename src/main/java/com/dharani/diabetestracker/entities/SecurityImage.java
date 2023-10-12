package com.dharani.diabetestracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "security_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public String src;
    public String alt;
}
