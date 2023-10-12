package com.dharani.diabetestracker;


import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DiabetestrackerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testSomething() {
        LocalDateTime currentLocalDate = LocalDateTime.of(2010, 2, 13, 12, 0);
        ZoneId zone = ZoneId.of("America/New_York");
        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(() -> LocalDateTime.now(zone)).thenReturn(currentLocalDate);
            assertTrue(LocalDateTime.now(zone).equals(currentLocalDate));
        }
    }

    @Test
    public void testSomething2() {
        try{
            some();
        } catch (Exception ex){
            System.out.println("Okay");
        }
    }

    public void some(){
        try{
            throw new Exception("some");
        } catch (Exception ex){
            System.out.println("throwed exception");
        }
    }


}
