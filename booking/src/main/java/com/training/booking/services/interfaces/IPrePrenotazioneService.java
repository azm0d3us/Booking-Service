package com.training.booking.services.interfaces;

import com.training.booking.entities.PrePrenotazione;
import com.training.booking.errors.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IPrePrenotazioneService {

    Optional<PrePrenotazione> getById(Long idPrenotazione) throws InternalServerErrorException;

    PrePrenotazione newPrenotazione(PrePrenotazione p) throws InternalServerErrorException;

    void delete(PrePrenotazione prenotazione) throws InternalServerErrorException;

}
