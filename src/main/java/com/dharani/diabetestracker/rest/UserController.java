package com.dharani.diabetestracker.rest;

import com.dharani.diabetestracker.dto.DTUserDto;
import com.dharani.diabetestracker.entities.DTUser;
import com.dharani.diabetestracker.entities.SecurityValues;
import com.dharani.diabetestracker.repo.DTUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private DTUserRepo dtUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/forgetpassword")
    public boolean updateWithTemperoryPassword(@RequestBody String userEmail) {
        try {
            DTUser user = this.dtUserRepo.findByEmail(userEmail);
            String temporaryPassword = UUID.randomUUID().toString().substring(0, 7);
            System.out.println(temporaryPassword);
            user.setPassword(this.passwordEncoder.encode(temporaryPassword));
            this.dtUserRepo.save(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PostMapping
    public DTUser user(@RequestBody DTUserDto dtUserDto) {
        //dtUserDto.setPassword(this.passwordEncoder.encode(dtUserDto.getPassword()));
        DTUser userEntity = transformUserDtoToUserEntity(dtUserDto);
        return this.dtUserRepo.save(userEntity);
    }

    private DTUser transformUserDtoToUserEntity(DTUserDto dtUserDto) {
        DTUser userEntity = DTUser.builder()
                .userName(dtUserDto.getUserName())
                .fullName(dtUserDto.getFullName())
                .phoneNumber(dtUserDto.getPhoneNumber())
                .email(dtUserDto.getEmail())
                .dateofbirth(dtUserDto.getDateofbirth())
                .password(this.passwordEncoder.encode(dtUserDto.getPassword()))
                .securityValues(dtUserDto.getSecurityValues().stream().map(securityDto -> {
                    return SecurityValues.builder()
                            .securityType(securityDto.getSecurityType())
                            .answer(securityDto.getAnswer())
                            .question(securityDto.getQuestion())
                            .userName(dtUserDto.getUserName())
                            .build();
                }).collect(Collectors.toList()))
                .build();
        //userEntity.getSecurityValues().forEach(securityValue -> securityValue.setUser(userEntity));
        return userEntity;
    }

    ;
}
