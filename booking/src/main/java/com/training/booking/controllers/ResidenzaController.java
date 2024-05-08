package com.training.booking.controllers;

import com.training.booking.DTOs.GenericDTO;
import com.training.booking.POJOs.ResidenzaPOJO;
import com.training.booking.controllers.business.ResidenzaBusiness;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/residenze")
public class ResidenzaController {

    @Autowired
    ResidenzaBusiness residenzaBusiness;

    @GetMapping(value = "/residenze", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(residenzaBusiness.getAll(), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/residenze/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(residenzaBusiness.getById(id), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    /*TODO
    *  Superfluo?*/
    @PostMapping(value = "/perNome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByNome(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(residenzaBusiness.getByNomeContainingIgnoreCase(generic.getTesto()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/perIndirizzo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByIndirizzo(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(residenzaBusiness.getByIndirizzoContainingIgnoreCase(generic.getTesto()), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/perNomeUguale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByNomeEquals(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(residenzaBusiness.getByNomeEqualsIgnoreCase(generic.getTesto()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/newResidenza", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newResidenza(@RequestBody ResidenzaPOJO residenza) {
        try {
            return new ResponseEntity<>(residenzaBusiness.newResidenza(residenza), HttpStatus.OK);
        } catch (NotValidException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/updateResidenza", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateResidenza(@RequestBody ResidenzaPOJO residenza) {
        try {
            return new ResponseEntity<>(residenzaBusiness.updateResidenza(residenza), HttpStatus.OK);
        } catch (NotValidException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/deleteResidenza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteResidenza(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(residenzaBusiness.delete(generic.getId()), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
