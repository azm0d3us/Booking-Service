package booking.services.interfaces;

import com.training.booking.entities.Prenotazione;
import com.training.booking.errors.InternalServerErrorException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IPrenotazioneService {

    List<Prenotazione> getAll() throws InternalServerErrorException;

    Optional<Prenotazione> getById(Long id) throws InternalServerErrorException;

    List<Prenotazione> getByCheckInCheckOutBetween(Date checkIn, Date checkOut) throws InternalServerErrorException;

    List<Prenotazione> getByNumPersone(Integer nPersone) throws InternalServerErrorException;

    List<Prenotazione> getStruttureDisponibili(Date checkIn, Date checkOut) throws InternalServerErrorException;

    Prenotazione newPrenotazione(Prenotazione prenotazione) throws InternalServerErrorException;
}
