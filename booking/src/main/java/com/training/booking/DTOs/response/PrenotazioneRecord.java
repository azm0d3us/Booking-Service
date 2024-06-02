package com.training.booking.DTOs.response;

import com.training.booking.entities.Camera;
import com.training.booking.entities.Utente;

import java.sql.Date;

public record PrenotazioneRecord(
        Long idPrenotazione,
        Integer numAdulti,
        Integer numBambini,
        Integer numPersone,
        Double totale,
        Date checkIn,
        Date checkOut,
        Utente utente,
        Camera camera
) {};
