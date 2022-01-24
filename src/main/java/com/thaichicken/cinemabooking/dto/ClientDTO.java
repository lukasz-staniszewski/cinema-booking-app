package com.thaichicken.cinemabooking.dto;

import com.thaichicken.cinemabooking.model.ClientRole;
import lombok.Data;

@Data
public class ClientDTO {
    private Integer clientId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private ClientRole clientRole;
}
