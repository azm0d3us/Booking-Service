package com.training.booking.controllers;

import com.training.booking.POJOs.DatePOJO;
import com.training.booking.POJOs.ListaPrezziPOJO;
import com.training.booking.POJOs.PrezziPOJO;
import com.training.booking.POJOs.TwoIdPOJO;
import com.training.booking.controllers.business.ListaPrezziBusiness;
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
@RequestMapping("/api/listaprezzi")
public class ListaPrezziController {

    @Autowired
    ListaPrezziBusiness listaPrezziBusiness;

    @GetMapping(value = "/liste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.getAll(), HttpStatus.OK);
    } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/liste/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.getById(id), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/betweenPrezzi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByPrezzoBetween(@RequestBody PrezziPOJO prezzi) {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.getByPrezzoBetween(prezzi.getPrezzoMin(), prezzi.getPrezzoMax()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/betweenDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByDateBetween(@RequestBody DatePOJO date) {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.getByDateBetween(date.getDataInizio(), date.getDataFine(), date.getDataInizio(), date.getDataFine()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/getByCamera", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByCamera(@RequestBody TwoIdPOJO idCamera) {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.getByCamera(idCamera.getId1()), HttpStatus.OK);
        } catch (NotValidException | NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    /*TODO TODO TODO */
    @PostMapping(value = "/newListaPrezzi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveListaPrezzi(@RequestBody ListaPrezziPOJO listaPrezzi) {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.newListaPrezzi(listaPrezzi), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/updateListaPrezzi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateListaPrezzi(@RequestBody ListaPrezziPOJO listaPrezzi) {
        try {
            return new ResponseEntity<>(listaPrezziBusiness.updateListaPrezzi(listaPrezzi), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/deleteListaPrezzi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteListaPrezzi(@RequestBody ListaPrezziPOJO listaPrezzi) {
        try {
            listaPrezziBusiness.deleteListaPrezzi(listaPrezzi);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
