package com.training.booking.controllers;

import com.training.booking.DTOs.GenericDTO;
import com.training.booking.POJOs.CameraPOJO;
import com.training.booking.controllers.business.CameraBusiness;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/camere")
public class CameraController {

    @Autowired
    CameraBusiness cameraBusiness;

    @GetMapping(value = "/camere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(cameraBusiness.getAll(), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/camere/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(cameraBusiness.getById(id), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/descrizioneCamera", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByNumero(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(cameraBusiness.getByNumero(generic.getTesto()), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/camerePerPosti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByPostiLetto(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(cameraBusiness.getByPostiLetto(generic.getNum()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/camerePerPrezzo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByPrezzoBetween(@RequestBody GenericDTO generic) {
        try {
            return new ResponseEntity<>(cameraBusiness.getByPrezzoBaseBetween(generic.getPrezzoMin(), generic.getPrezzoMax()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "newCamera", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCamera(@RequestBody CameraPOJO camera) {
        try {
            return new ResponseEntity<>(cameraBusiness.newCamera(camera), HttpStatus.OK);
        } catch (NotValidException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "editCamera", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editCamera(@RequestBody CameraPOJO camera) {
        try {
            return new ResponseEntity<>(cameraBusiness.editCamera(camera), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "deleteCamera", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCamera(@RequestBody GenericDTO camera) {
        try {
            return new ResponseEntity<>(cameraBusiness.deleteCamera(camera), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
