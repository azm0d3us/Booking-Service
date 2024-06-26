package com.training.booking.controllers.business;

import com.training.booking.DTOs.GenericDTO;
import com.training.booking.POJOs.UtenteCompletePOJO;
import com.training.booking.POJOs.UtenteRegistra;
import com.training.booking.POJOs.UtenteUpdate;
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

    public Long getUserIdByUsername(String username) throws InternalServerErrorException, NotFoundException {
        Optional<Utente> utenteOptional = userService.getUserIdByUsername(username);
        if(!utenteOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            return utenteOptional.get().getIdUser();
        }
    }

    public Utente getUserByCf(String cf) throws InternalServerErrorException, NotFoundException {
        Optional<Utente> utenteOptional = userService.getByCf(cf);
        if(!utenteOptional.isPresent()) {
            throw new NotFoundException();
        }
        return utenteOptional.get();
    }

    public Utente getByUsername(String username) throws NotValidException, InternalServerErrorException, NotFoundException {
        if(username.trim().isEmpty()) {
            throw new NotValidException();
        }
        Optional<Utente> utenteOptional = userService.getByUsernameEquals(username);
        if(!utenteOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            return utenteOptional.get();
        }

    }

    public Utente getUser(String username, String password) throws NotFoundException, NotValidException, InternalServerErrorException {
        if(username.trim().isEmpty() || password.trim().isEmpty()) {
            throw new NotValidException();
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
            throw new NotFoundException();
        }
    }

    public Utente saveUtente(UtenteRegistra utente) throws InternalServerErrorException, NotValidException {
        if(!utenteValidation(utente)) {
            throw new NotValidException();
        }
        try {
            return userService.save(new Utente(null, utente.getNome(), null, null, null, null, utente.getEmail(), utente.getUsername(), utente.getPassword(), false, null, null));
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    /**
     * Overload di saveUtente cui passare un UtenteUpdate per registrare i dati dell'utente immessi nel form della registrazione da parte dell'amministratore
     * lasciando i campi email, username e password a null.
     * @param utente
     * @return
     * @throws InternalServerErrorException
     * @throws NotValidException
     */
    public Utente saveUtente(UtenteUpdate utente) throws InternalServerErrorException, NotValidException {
        return userService.save(new Utente(
                null,
                utente.getNome(),
                utente.getCognome(),
                utente.getDdn(),
                utente.getCf(),
                utente.getDocCod(),
                null,
                null,
                null,
                false,
                null,
                null
        ));
    }

    public Utente saveUtente(UtenteCompletePOJO utente) throws InternalServerErrorException, NotValidException, NotFoundException {
        Utente utenteMod = this.getById(utente.getIdUser());
        utenteMod.setEmail(utente.getEmail());
        utenteMod.setUsername(utente.getUsername());
        utenteMod.setPassword(utente.getPassword());
        return userService.save(utenteMod);
    }

    public Utente update(UtenteUpdate utenteUpdate) throws InternalServerErrorException, NotValidException, NotFoundException {
        Optional<Utente> utenteOptional = userService.getById(utenteUpdate.getId());
        if(!utenteOptional.isPresent()) {
            throw new NotFoundException();
        }
        Utente utente = utenteOptional.get();
        utente.setNome(utenteUpdate.getNome());
        utente.setCognome(utenteUpdate.getCognome());
        utente.setDdn(utenteUpdate.getDdn());
        utente.setCodDoc(utenteUpdate.getDocCod());
        utente.setCf(utenteUpdate.getCf());
//        if(!utenteValidation(utente)) {
//            throw new NotValidException();
//        }
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

    private boolean utenteValidation(UtenteRegistra utente) {
        if(utente.getNome().trim().isEmpty() || utente.getUsername().trim().isEmpty()
                || !passwordValidation(utente.getPassword())){
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
