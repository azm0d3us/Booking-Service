package com.training.booking.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidenzaResponse {
    private Long idResidenza;
    private String nome;
    private String indirizzo;
}
