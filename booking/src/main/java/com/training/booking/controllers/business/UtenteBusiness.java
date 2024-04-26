package com.training.booking.controllers.business;

import com.training.booking.entities.Utente;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UtenteBusiness {

    @Autowired
    IUtenteService userService;

    public List<?> getAll() throws NotFoundException, InternalServerErrorException {
        List<Utente> listaUtente = userService.getAll();
        if(listaUtente == null) {
            throw new NotFoundException("Nessun utente trovato");
        }
        record r(String username, boolean admin){};
        return listaUtente.stream().map(u -> new r(u.getUsername(), u.isAdmin())).collect(Collectors.toList());
    }

    public Utente getById(Long id) throws NotFoundException, InternalServerErrorException {
        Optional<Utente> utenteOptional = userService.getById(id);
        if(utenteOptional.isPresent()) {
           return utenteOptional.get();
        } else {
            throw new NotFoundException("Utente non trovato");
        }
    }

    public Utente getUser(String username, String password) throws NotFoundException, NotValidException, InternalServerErrorException {
        if(username.trim().isEmpty() || password.trim().isEmpty()) {
            throw new NotValidException("Utente o password mancanti");
        }
        Optional<Utente> utenteOptional = userService.getByUsernameEquals(username);
        if(utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            if(utente.getPassword().equals(password)) {
                return utente;
            } else {
                throw new NotValidException("Password errata");
            }
        } else {
            throw new NotFoundException("Utente non trovato");
        }
    }

    public Utente saveUtente(Utente utente) throws InternalServerErrorException, NotValidException {
        if(!utenteValidation(utente)) {
            throw new NotValidException();
        }
        try {
            return userService.save(utente);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    public Utente update(Utente utente) throws InternalServerErrorException, NotValidException {
        if(!utenteValidation(utente)) {
            throw new NotValidException();
        }
        return userService.update(utente);
    }

    public void delete(Utente utente) throws InternalServerErrorException, NotFoundException {
        if(!userService.getById(utente.getIdUser()).isPresent()){
            throw new NotFoundException();
        }
        try {
            userService.delete(utente);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    private boolean utenteValidation(Utente utente) {
        if(utente.getNome().trim().isEmpty() || utente.getCognome().trim().isEmpty() || utente.getUsername().trim().isEmpty()
        || !passwordValidation(utente.getPassword()) || utente.getCodDoc().trim().isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    private boolean passwordValidation(String password) {
        if(password.trim().isEmpty() || password.length() < 8) {
            return false;
        } else {
            return true;
        }
    }
}
