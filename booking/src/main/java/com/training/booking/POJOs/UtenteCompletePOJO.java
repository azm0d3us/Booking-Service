package com.training.booking.POJOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteCompletePOJO {
    private Long idUser;
    private String nome;
    private String cognome;
    private Date ddn;
    private String cf;
    private String codDoc;
    private String email;
    private String username;
    private String password;
    private boolean admin;
}
