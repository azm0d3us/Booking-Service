package com.training.booking.controllers;

import com.training.booking.POJOs.UserPOJO;
import com.training.booking.POJOs.UtenteRegistra;
import com.training.booking.POJOs.UtenteUpdate;
import com.training.booking.controllers.business.UtenteBusiness;
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
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteBusiness utenteBusiness;

    @GetMapping(value = "/utentiMapped", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUtentiMapped() {
        return new ResponseEntity<>(utenteBusiness.getMapped(), HttpStatus.OK);
    }

    @GetMapping(value = "/utenteMapped/{idUtente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUtenteMapped(@PathVariable("idUtente") Long idUtente){
        try {
            return new ResponseEntity<>(utenteBusiness.getMappedById(idUtente), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/utenti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(utenteBusiness.getAll(), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }



    @GetMapping(value = "/utente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(utenteBusiness.getById(id), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/getIdByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserIdByUsername(@RequestBody String username) {
        try {
            return new ResponseEntity<>(utenteBusiness.getUserIdByUsername(username), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/byUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByUsername(@RequestBody String username) {
        try {
            return new ResponseEntity<>(utenteBusiness.getByUsername(username), HttpStatus.OK);
        } catch (NotValidException | NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logIn(@RequestBody UserPOJO userPOJO) {
        try {
            return new ResponseEntity<>(utenteBusiness.getUser(userPOJO.getUsername(), userPOJO.getPassword()), HttpStatus.OK);
        } catch (NotFoundException | NotValidException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sigIn(@RequestBody UtenteRegistra utente) {
        try {
            return new ResponseEntity<>(utenteBusiness.saveUtente(utente), HttpStatus.OK);
        } catch (NotValidException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/updateUtente", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userUpdate(@RequestBody UtenteUpdate utente) {
        try {
            return new ResponseEntity<>(utenteBusiness.update(utente), HttpStatus.OK);
        } catch (InternalServerErrorException | NotValidException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
