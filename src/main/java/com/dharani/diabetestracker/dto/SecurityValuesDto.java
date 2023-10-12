package com.dharani.diabetestracker.dto;

import com.dharani.diabetestracker.entities.SecurityValues;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityValuesDto {
    private long id;
    private SecurityValues.SecurityType securityType;
    private String question;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String answer;
}
