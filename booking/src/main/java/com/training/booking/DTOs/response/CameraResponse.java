package com.training.booking.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraResponse {
    private Long idCamera;
    private Integer postiLetto;
    private boolean disponibile;
    private Double prezzoBase;
    private String tipo;
    private String infoCheckOut;
}
