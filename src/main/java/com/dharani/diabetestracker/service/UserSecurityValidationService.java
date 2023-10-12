package com.dharani.diabetestracker.service;

import com.dharani.diabetestracker.entities.DTUser;
import com.dharani.diabetestracker.entities.DTUserSecurityValidationModel;
import com.dharani.diabetestracker.entities.SecurityValues;
import com.dharani.diabetestracker.repo.DTUserRepo;
import com.dharani.diabetestracker.repo.DTUserSecurityValuesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

import static com.dharani.diabetestracker.entities.DTUserSecurityValidationModel.Phase.PHASE2;
import static com.dharani.diabetestracker.entities.DTUserSecurityValidationModel.Phase.PHASE3;
import static com.dharani.diabetestracker.entities.SecurityValues.SecurityType.IMAGE;
import static com.dharani.diabetestracker.entities.SecurityValues.SecurityType.QUESTION;

@Service
public class UserSecurityValidationService {
    @Autowired
    private DTUserRepo userRepo;

    @Autowired
    private DTUserSecurityValuesRepo dtUserSecurityValuesRepo;

    public boolean validateUserSecurityValues(DTUserSecurityValidationModel.Phase securityPhase, String userName, List<SecurityValues> securityValuesList) {
        if(securityPhase.equals(PHASE2)){
            Map<Long, String> securityValueIdAnswersMap = new HashMap();
            List<SecurityValues> securityValuesByUsername = dtUserSecurityValuesRepo.findSecurityValuesByUserNameAndSecurityType(userName, QUESTION);
            securityValuesByUsername.stream().forEach(value-> securityValueIdAnswersMap.put(value.getId(), value.getAnswer()));
            boolean areValidAnswers = securityValueIdAnswersMap.get(securityValuesList.get(0).getId()).equals(securityValuesList.get(0).getAnswer())
                    && securityValueIdAnswersMap.get(securityValuesList.get(1).getId()).equals(securityValuesList.get(1).getAnswer());
            return areValidAnswers;
        } else if(securityPhase.equals(PHASE3)){
            List<SecurityValues> securityValuesByUsername = dtUserSecurityValuesRepo.findSecurityValuesByUserNameAndSecurityType(userName, IMAGE);
            return securityValuesList.get(0).getAnswer().equals(securityValuesByUsername.get(0).getAnswer());
        }
        return false;
    }

    public List<SecurityValues> getValuesByUserNameAndPhase(String userName, String phase){
        if(PHASE2.name().equals(phase)){
            return this.dtUserSecurityValuesRepo.findSecurityValuesByUserNameAndSecurityType(userName, QUESTION);
        } else if(PHASE3.name().equals(phase)){
            return this.dtUserSecurityValuesRepo.findSecurityValuesByUserNameAndSecurityType(userName, IMAGE);
        }
        return Collections.emptyList();
    }
}
