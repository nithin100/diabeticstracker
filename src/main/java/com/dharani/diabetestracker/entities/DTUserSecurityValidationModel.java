package com.dharani.diabetestracker.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
public class DTUserSecurityValidationModel {

    public enum Phase{
        PHASE2, PHASE3
    }

    @JsonIgnore
    private Phase securityPhase;
    private List<SecurityValues> securityValuesList;
}
