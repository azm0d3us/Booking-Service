package com.training.booking.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteResponse {

    private Long idUser;
    private String nome;
    private String cognome;
    private Date ddn;
    private String cf;
    private String codDoc;
    private String email;
    private String username;
    private boolean admin;
}
