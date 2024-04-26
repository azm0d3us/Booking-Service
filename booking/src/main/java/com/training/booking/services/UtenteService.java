package com.training.booking.services;

import com.training.booking.entities.Utente;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.repository.UtenteRepository;
import com.training.booking.services.interfaces.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService implements IUtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAll() throws InternalServerErrorException {
        try {
            return utenteRepository.findAll();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Optional<Utente> getByUsernameEquals(String username) throws InternalServerErrorException {
        try {
            return utenteRepository.findByUsernameEquals(username);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Optional<Utente> getById(Long id) throws InternalServerErrorException {
        try {
            return utenteRepository.findById(id);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Utente save(Utente obj) throws InternalServerErrorException {
        try {
            return utenteRepository.save(obj);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Utente update(Utente obj) throws InternalServerErrorException {
        try {
            return this.save(obj);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public void delete(Utente obj) throws InternalServerErrorException {
        try {
            utenteRepository.delete(obj);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }
}
