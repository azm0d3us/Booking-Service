package com.training.booking.POJOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaPrezziPOJO {

    private Long id;
    private Double prezzo;
    private Date dataInizio;
    private Date dataFine;
    private Long idCamera;
}
