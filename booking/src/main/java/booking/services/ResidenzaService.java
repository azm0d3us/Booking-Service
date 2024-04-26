package booking.services;

import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.repository.ResidenzaRepository;
import com.training.booking.services.interfaces.IResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class ResidenzaService implements IResidenzaService {

    @Autowired
    ResidenzaRepository residenzaRepository;

    @Override
    public List<Residenza> getAll() throws InternalServerErrorException {
        try {
            return residenzaRepository.findAll();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Optional<Residenza> getById(Long id) throws InternalServerErrorException {
        try {
            return residenzaRepository.findById(id);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public List<Residenza> getByNomeContainingIgnoreCase(String nome) throws InternalServerErrorException {
        try {
            return residenzaRepository.findByNomeContainingIgnoreCase(nome);
            } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public List<Residenza> getByIndirizzoContainingIgnoreCase(String indirizzo) throws InternalServerErrorException {
        try {
            return residenzaRepository.findByIndirizzoContainingIgnoreCase(indirizzo);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Optional<Residenza> getByNomeEqualsIgnoreCase(String nome) throws InternalServerErrorException {
        try {
            return residenzaRepository.findByNomeEqualsIgnoreCase(nome);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Residenza save(Residenza residenza) throws InternalServerErrorException {
        try {
            return residenzaRepository.save(residenza);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Residenza update(Residenza residenza) throws InternalServerErrorException {
        try {
            return this.save(residenza);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public void delete(Residenza residenza) throws InternalServerErrorException {
        try {
            delete(residenza);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public boolean existsResidenzaByNomeOrIndirizzo(String nome, String indirizzo) {
        return residenzaRepository.existsResidenzaByNomeOrIndirizzo(nome, indirizzo);
    }


}
