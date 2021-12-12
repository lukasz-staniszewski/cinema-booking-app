package com.thaichicken.cinemabooking.model;

import com.thaichicken.cinemabooking.registration.token.ConfirmationToken;
import com.thaichicken.cinemabooking.registration.token.ConfirmationTokenService;
import com.thaichicken.cinemabooking.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientEntityService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return clientRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpClient (ClientEntity client){
        boolean clientExist = clientRepository.findByEmail(client.getEmail()).isPresent();
        if (clientExist) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(client.getPassword());

        client.setPassword(encodedPassword);

        clientRepository.save(client);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                client

        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableClient(String email) {
        return clientRepository.enableClientEntity(email);
    }
    }


