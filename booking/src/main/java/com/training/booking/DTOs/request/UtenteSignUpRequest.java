package com.training.booking.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteSignUpRequest {

    private String nome;
    private String username;
    private String email;
    private String password;

}
