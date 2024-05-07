package com.training.booking.controllers;

import com.training.booking.controllers.business.ImmaginiBusiness;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/immagini")
public class ImmaginiController {

    @Autowired
    private ImmaginiBusiness immaginiBusiness;

    @GetMapping(value = "/camera/{idCamera}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdCamera(@PathVariable("idCamera") Long idCamera) {
        try {
            return new ResponseEntity<>(immaginiBusiness.getByIdCamera(idCamera), HttpStatus.OK);
        } catch (NotValidException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }


}
