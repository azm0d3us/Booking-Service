package com.training.booking.POJOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazionePOJO {
    private Long idCamera;
    private Long idUser;
    private Integer numAdulti;
    private Integer numBambini;
    private Integer numPersone;
    private Date checkIn;
    private Date checkOut;
}
