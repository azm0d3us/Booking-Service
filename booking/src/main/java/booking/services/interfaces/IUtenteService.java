package booking.services.interfaces;

import com.training.booking.entities.Utente;
import com.training.booking.errors.InternalServerErrorException;

import java.util.List;
import java.util.Optional;

public interface IUtenteService {

    List<Utente> getAll() throws InternalServerErrorException;

    Optional<Utente> getByUsernameEquals(String username) throws InternalServerErrorException;

    Optional<Utente> getById(Long id) throws InternalServerErrorException;

    Utente save(Utente obj) throws InternalServerErrorException;

    Utente update(Utente obj) throws InternalServerErrorException;

    void delete(Utente obj) throws InternalServerErrorException;

}
