package com.training.booking.controllers;

import com.training.booking.POJOs.DatesPOJO;
import com.training.booking.POJOs.PrenotazionePOJO;
import com.training.booking.POJOs.RequestPOJO;
import com.training.booking.POJOs.TwoIdPOJO;
import com.training.booking.controllers.business.PrenotazioneBusiness;
import com.training.booking.entities.*;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.ICameraService;
import com.training.booking.services.interfaces.IListaPrezziService;
import com.training.booking.services.interfaces.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneBusiness prenotazioneBusiness;

    @GetMapping(value = "/prenotazioni", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getAll(), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/prenotazioni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getById(id), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/prenotazioniFraDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByCheckBetween(@RequestBody DatesPOJO date) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getByCheckInCheckOutBetween(date.getCheckIn(), date.getCheckOut()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/prenotazioniPerNumPersone", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByNumPersone(@RequestParam("persone") Integer persone) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getByNumPersone(persone), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/disponibili", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> camereDisponibili(@RequestBody DatesPOJO date) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getStruttureDisponibili(date.getCheckIn(), date.getCheckOut()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/nuovaPrenotazione", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newPrenotazione(@RequestBody PrenotazionePOJO prenotazione) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.newPrenotazione(prenotazione), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (InternalServerErrorException e) {
            throw new RuntimeException(e);
        } catch (NotValidException e) {
            throw new RuntimeException(e);
        }
    }
}
