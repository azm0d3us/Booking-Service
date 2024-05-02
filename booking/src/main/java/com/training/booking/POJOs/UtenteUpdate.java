package com.training.booking.POJOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteUpdate {
    private Long id;
    private String nome;
    private String cognome;
    private Date ddn;
    private String docCod;
    private String cf;
}
