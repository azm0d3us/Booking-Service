package com.training.booking.services;

import com.training.booking.entities.PrePrenotazione;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.repository.PrePrenotazioneRepository;
import com.training.booking.services.interfaces.IPrePrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class PrePrenotazioneService implements IPrePrenotazioneService {

    @Autowired
    private PrePrenotazioneRepository preRepo;

    @Override
    public PrePrenotazione newPrenotazione(PrePrenotazione p) throws InternalServerErrorException {
        try {
            return preRepo.save(p);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public void delete(PrePrenotazione prenotazione) throws InternalServerErrorException {
        try {
            preRepo.delete(prenotazione);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Optional<PrePrenotazione> getById(Long idPrenotazione) throws InternalServerErrorException {
        try {
            return preRepo.findById(idPrenotazione);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }
}
