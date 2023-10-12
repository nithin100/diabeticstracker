package com.dharani.diabetestracker.config;

import com.dharani.diabetestracker.entities.DTUser;
import com.dharani.diabetestracker.entities.SecurityImage;
import com.dharani.diabetestracker.entities.SecurityValues;
import com.dharani.diabetestracker.repo.DTUserRepo;
import com.dharani.diabetestracker.repo.SecurityImageRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DTUserDetailsService implements UserDetailsService{

    @Autowired
    private DTUserRepo dtUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityImageRepo imageRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = convertToUserDetails(dtUserRepo.findByUserName(username));
        return userDetails;
    }

    private UserDetails convertToUserDetails(DTUser dtUser) {
        UserDetails userDetails = User.builder()
                .username(dtUser.getUserName())
                .password(dtUser.getPassword())
                .authorities(new SimpleGrantedAuthority("PHASE1"))
                .accountExpired(false)
                .accountLocked(false)
                .build();
        return userDetails;
    }

    @PostConstruct
    public void insertDummyUser(){

        SecurityValues securityValues1 = SecurityValues
                                            .builder()
                                            .securityType(SecurityValues.SecurityType.QUESTION)
                                            .question("test q1")
                                            .answer("a1")
                                            .userName("nithin")
                                            .build();

        SecurityValues securityValues2 = SecurityValues
                .builder()
                .securityType(SecurityValues.SecurityType.QUESTION)
                .question("test q2")
                .answer("a2")
                .userName("nithin")
                .build();

        List<SecurityValues> securityValuesList = Arrays.asList(securityValues1, securityValues2);
        DTUser dtUser = DTUser.builder()
                .userName("nithing")
                .fullName("Nithin Ganji")
                .phoneNumber("1111111111")
                .email("test@test.com")
                .dateofbirth(new Date())
                .password(this.passwordEncoder.encode("password"))
                .securityValues(securityValuesList)
                .build();

        List<SecurityImage> images = new ArrayList<>();
        for(int i=0; i<9; i++){
            images.add(SecurityImage
                    .builder()
                    .src("https://via.placeholder.com/100x100")
                    .alt("image1")
                    .build());
        }

        this.imageRepo.saveAll(images);

        this.dtUserRepo
                .save(dtUser);
    }

}
