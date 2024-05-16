package com.test.authorizationserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    private Long idUtente;
    private String username;
    private String password;
}
