package com.training.booking.services;

import com.training.booking.entities.Prenotazione;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.repository.PrenotazioneRepository;
import com.training.booking.services.interfaces.IPrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService implements IPrenotazioneService {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Override
    public List<Prenotazione> getAll() throws InternalServerErrorException {
        return prenotazioneRepository.findAll();
    }

    @Override
    public Optional<Prenotazione> getById(Long id) throws InternalServerErrorException {
        return prenotazioneRepository.findById(id);
    }

    @Override
    public List<Prenotazione> getByCheckInCheckOutBetween(Date checkIn, Date checkOut) throws InternalServerErrorException {
        return prenotazioneRepository.findByCheckInBetweenOrCheckOutBetween(checkIn, checkOut, checkIn, checkOut);
    }

    @Override
    public List<Prenotazione> getByNumPersone(Integer nPersone) throws InternalServerErrorException {
        return prenotazioneRepository.findByNumPersone(nPersone);
    }

    @Override
    public List<Prenotazione> getStruttureDisponibili(Date checkIn, Date checkOut) throws InternalServerErrorException {
        try {
            return prenotazioneRepository.findByCheckOutBeforeOrCheckInAfterOrCheckOutBeforeAndCheckInAfter(
                    Date.valueOf(checkIn.toLocalDate().plusDays(1L)), Date.valueOf(checkOut.toLocalDate().minusDays(1L)), checkIn, checkOut);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Prenotazione newPrenotazione(Prenotazione prenotazione) throws InternalServerErrorException {
        try {
            return prenotazioneRepository.save(prenotazione);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }
}
