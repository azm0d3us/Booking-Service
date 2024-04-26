package booking.services;

import com.training.booking.entities.Camera;
import com.training.booking.entities.ListaPrezzi;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.repository.ListaPrezziRepository;
import com.training.booking.services.interfaces.IListaPrezziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ListaPrezziService implements IListaPrezziService {

    @Autowired
    ListaPrezziRepository listaPrezziRepository;

    @Override
    public List<ListaPrezzi> getAll() throws InternalServerErrorException {
        try {
            return listaPrezziRepository.findAll();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Optional<ListaPrezzi> getById(Long id) throws InternalServerErrorException {
        try {
            return listaPrezziRepository.findById(id);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Set<ListaPrezzi> getByCamera(Camera camera) {
        return listaPrezziRepository.findByCamera(camera);
    }

    @Override
    public List<ListaPrezzi> getByPrezzoBetween(Double prezzoMin, Double prezzoMax) throws InternalServerErrorException {
        try {
            return listaPrezziRepository.findByPrezzoBetween(prezzoMin, prezzoMax);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public List<ListaPrezzi> getByValiditaInizioBetweenOrValiditaFineBetween(Date inizioMin, Date inizioMax, Date fineMin, Date fineMax) throws InternalServerErrorException {
        try {
           return listaPrezziRepository.findByValiditaInizioBetweenOrValiditaFineBetween(inizioMin, inizioMax, fineMin, fineMax);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerErrorException();
        }
    }

    /*TODO TODO TODO*/
    @Override
    public ListaPrezzi save(ListaPrezzi listaPrezzi) throws InternalServerErrorException {
        return listaPrezziRepository.save(listaPrezzi);
    }

    @Override
    public ListaPrezzi update(ListaPrezzi listaPrezzi) throws InternalServerErrorException {
        return listaPrezziRepository.save(listaPrezzi);
    }

    @Override
    public void delete(ListaPrezzi listaPrezzi) throws InternalServerErrorException {
        listaPrezziRepository.delete(listaPrezzi);
    }
}
