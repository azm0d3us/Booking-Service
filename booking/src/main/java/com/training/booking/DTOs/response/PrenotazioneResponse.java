package com.training.booking.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneResponse {
    private Long idPrenotazione;
    private Integer numAdulti;
    private Integer numBambini;
    private Integer numPersone;
    private Double totale;
    private Date checkIn;
    private Date checkOut;
}
