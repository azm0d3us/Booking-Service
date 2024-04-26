package com.training.booking.POJOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPOJO {
    private TwoIdPOJO prenotazione;
    private DatesPOJO date;
}
