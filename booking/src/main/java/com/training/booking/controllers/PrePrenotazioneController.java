package com.training.booking.controllers;

import com.training.booking.POJOs.PrenotazionePOJO;
import com.training.booking.controllers.business.PrePrenotazioneBusiness;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pre-prenotazione")
public class PrePrenotazioneController {

    @Autowired
    private PrePrenotazioneBusiness preBusiness;

    @PutMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newPrePrenotazione(@RequestBody PrenotazionePOJO prenotazione) {
        try {
            return new ResponseEntity<>(preBusiness.newPrePrenotazione(prenotazione), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException | NotValidException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/delete/{idPrenotazione}")
    public ResponseEntity<?> deletePrePrenotazione(@PathVariable("idPrePrenotazione") Long idPrePrenotazione) {
        try {
            return new ResponseEntity<>(preBusiness.deletePrePrenotazione(idPrePrenotazione), HttpStatus.NO_CONTENT);
        } catch (InternalServerErrorException | NotFoundException | NotValidException e) {
            throw new RuntimeException(e);
        }
    }
}
