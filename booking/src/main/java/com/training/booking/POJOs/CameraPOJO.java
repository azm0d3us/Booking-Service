package com.training.booking.POJOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPOJO {

    private Long id;
    private Integer postiLetto;
    private Double prezzoBase;
    private String tipo;
    private String numero;
    private String infoCheckOut;
}
