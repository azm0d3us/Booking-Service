package com.training.booking.controllers;

import com.training.booking.controllers.business.ImmaginiBusiness;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/immagini")
public class ImmaginiController {

    @Autowired
    private ImmaginiBusiness immaginiBusiness;

    @GetMapping(value = "/camera/{idCamera}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdCamera(@PathVariable("idCamera") Long idCamera) {
        try {
            return new ResponseEntity<>(immaginiBusiness.getByIdCamera(idCamera), HttpStatus.OK);
        } catch (NotValidException | NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping(value = "/residenza/{idResidenza}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getByIdResidenza(@PathVariable("idResidenza") Long idResidenza) {
//        return new ResponseEntity<>(immaginiBusiness.getByIdResidenza(idResidenza), HttpStatus.OK);
//    }


}
