package com.dharani.diabetestracker.rest;

import com.dharani.diabetestracker.dto.SecurityValuesDto;
import com.dharani.diabetestracker.entities.DTUserSecurityValidationModel;
import com.dharani.diabetestracker.entities.SecurityValues;
import com.dharani.diabetestracker.service.UserSecurityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.dharani.diabetestracker.entities.DTUserSecurityValidationModel.Phase.PHASE2;
import static com.dharani.diabetestracker.entities.DTUserSecurityValidationModel.Phase.PHASE3;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    private UserSecurityValidationService userSecurityValidationService;

    @PostMapping("/validate")
    public boolean validateSecurityQuestions(@RequestBody List<SecurityValues> dtUserSecurityQuestions, Principal user){
        boolean questionsValidation = this.userSecurityValidationService
                .validateUserSecurityValues(PHASE2, user.getName(),
                        dtUserSecurityQuestions
                                .stream()
                                .filter(q-> q.getSecurityType().equals(SecurityValues.SecurityType.QUESTION))
                                .collect(Collectors.toList()));

        boolean imageValidation = this.userSecurityValidationService
                .validateUserSecurityValues(PHASE3, user.getName(),
                        dtUserSecurityQuestions
                                .stream()
                                .filter(q-> q.getSecurityType().equals(SecurityValues.SecurityType.IMAGE))
                                .collect(Collectors.toList()));

        if(questionsValidation && imageValidation){
            System.out.println("Validation passed");
            return true;
        } else {
            System.out.println("validation failed");
            return false;
        }
    }

    @GetMapping("/{phase}")
    public List<SecurityValuesDto> getSecurityValuesOfCurrentUserByPhase(@PathVariable("phase") String phase, Principal principal){
        return this.transformsecurityvaluesentirytodto(this.userSecurityValidationService.getValuesByUserNameAndPhase(principal.getName(), phase.toUpperCase()));
    }

    private List<SecurityValuesDto> transformsecurityvaluesentirytodto(List<SecurityValues> valuesByUserNameAndPhase) {
        return valuesByUserNameAndPhase.stream().map(values-> SecurityValuesDto
                                                                .builder()
                                                                .question(values.getQuestion())
                                                                .securityType(values.getSecurityType())
                                                                .id(values.getId())
                                                                .build())
                                                                .collect(Collectors.toList());
    };
}
