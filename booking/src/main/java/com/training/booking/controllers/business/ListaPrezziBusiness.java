package com.training.booking.controllers.business;

import com.training.booking.POJOs.ListaPrezziPOJO;
import com.training.booking.entities.Camera;
import com.training.booking.entities.ListaPrezzi;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.CameraService;
import com.training.booking.services.ListaPrezziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ListaPrezziBusiness {

    @Autowired
    ListaPrezziService listaPrezziService;

    public List<ListaPrezzi> getAll() throws NotFoundException, InternalServerErrorException {
        List<ListaPrezzi> listaPrezzi = listaPrezziService.getAll();
        if(listaPrezzi.isEmpty()) {
            throw new NotFoundException();
        } else {
            return listaPrezzi;
        }
    }

    public ListaPrezzi getById(Long id) throws InternalServerErrorException, NotFoundException {
        Optional<ListaPrezzi> listinoOptional = listaPrezziService.getById(id);
        if(!listinoOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            return listinoOptional.get();
        }
    }

    public List<ListaPrezzi> getByPrezzoBetween(Double prezzoMin, Double prezzoMax) throws InternalServerErrorException, NotFoundException {
        List<ListaPrezzi> listaPrezzi = listaPrezziService.getByPrezzoBetween(prezzoMin, prezzoMax);
        if(listaPrezzi.isEmpty()) {
            throw new NotFoundException();
        } else {
            return listaPrezzi;
        }
    }

    public List<ListaPrezzi> getByDateBetween(Date inizioMin, Date inizioMax, Date fineMin, Date fineMax) throws InternalServerErrorException, NotFoundException {
        List<ListaPrezzi> listaPrezzi = listaPrezziService.getByValiditaInizioBetweenOrValiditaFineBetween(inizioMin, inizioMax, fineMin, fineMax);
        if(listaPrezzi.isEmpty()) {
            throw new NotFoundException();
        } else {
            return listaPrezzi;
        }
    }

    public Set<ListaPrezzi> getByCamera(Long idCamera) throws NotValidException, NotFoundException, InternalServerErrorException {
        if(idCamera == null){
            throw new NotValidException();
        }
        Camera cameraFound = cameraService.getById(idCamera);
        return listaPrezziService.getByCamera(cameraFound);
    }

    /*TODO TODO TODO*/
    @Autowired
    CameraService cameraService;
    public ListaPrezzi newListaPrezzi(ListaPrezziPOJO listaPrezziPOJO) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(listaPrezziPOJO.getIdCamera());
        ListaPrezzi listaPrezzi = new ListaPrezzi(listaPrezziPOJO.getId(), listaPrezziPOJO.getPrezzo(), listaPrezziPOJO.getDataInizio(), listaPrezziPOJO.getDataFine(), camera);
        return listaPrezziService.save(listaPrezzi);
    }

    public ListaPrezzi updateListaPrezzi(ListaPrezziPOJO listaPrezziPOJO) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(listaPrezziPOJO.getIdCamera());
        ListaPrezzi listaPrezzi = new ListaPrezzi(listaPrezziPOJO.getId(), listaPrezziPOJO.getPrezzo(), listaPrezziPOJO.getDataInizio(), listaPrezziPOJO.getDataFine(), camera);
        return listaPrezziService.update(listaPrezzi);
    }

    public void deleteListaPrezzi(ListaPrezziPOJO listaPrezziPOJO) throws InternalServerErrorException, NotFoundException {
        Camera camera = cameraService.getById(listaPrezziPOJO.getIdCamera());
        ListaPrezzi listaPrezzi = new ListaPrezzi(listaPrezziPOJO.getId(), listaPrezziPOJO.getPrezzo(), listaPrezziPOJO.getDataInizio(), listaPrezziPOJO.getDataFine(), camera);
        listaPrezziService.delete(listaPrezzi);
    }
}
