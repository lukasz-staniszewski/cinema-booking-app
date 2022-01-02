package com.thaichicken.cinemabooking.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailValidatorTest {

    EmailValidator validator = new EmailValidator();

    @Test
    public void emailNotValid() throws Exception{

        String email = "notvalid$mail.com";

        assert validator.test(email)==Boolean.FALSE;
    }

    @Test
    public void emailValid() throws Exception{

        String email = "valid@mail.com";

        assert validator.test(email)==Boolean.TRUE;
    }
}
