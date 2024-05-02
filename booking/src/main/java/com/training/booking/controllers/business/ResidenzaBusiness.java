package com.training.booking.controllers.business;

import com.training.booking.POJOs.ResidenzaPOJO;
import com.training.booking.entities.Residenza;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.IResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ResidenzaBusiness {

    @Autowired
    IResidenzaService residenzaService;

    public List<?> getAll() throws NotFoundException, InternalServerErrorException {
        List<Residenza> residenzaList = residenzaService.getAll();
        if(residenzaList.isEmpty()) {
            throw new NotFoundException();
        } else {
            //Prendi l'url se l'immagine c'è, se no mett la stringa a null
            record r(Long id, String nome, String indirizzo, String urlImg){};
            return residenzaList.stream().map(e -> new r(e.getIdResidenza(), e.getNome(), e.getIndirizzo(),
                    e.getImgResidenza() != null ? e.getImgResidenza().getUrl() : null)).collect(Collectors.toList());
        }
    }

    public Residenza getById(Long id) throws NotFoundException, InternalServerErrorException {

        Optional<Residenza> residenzaOptional = residenzaService.getById(id);
        if(!residenzaOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            return residenzaOptional.get();
        }
    }

    public List<Residenza> getByNomeContainingIgnoreCase(String nome) throws InternalServerErrorException, NotFoundException {
        List<Residenza> residenzaList = residenzaService.getByNomeContainingIgnoreCase(nome);
        if(residenzaList.isEmpty()) {
            throw new NotFoundException();
        } else {
            return residenzaList;
        }
    }

    public List<Residenza> getByIndirizzoContainingIgnoreCase(String indirizzo) throws NotFoundException, InternalServerErrorException {
        List<Residenza> residenzaList = residenzaService.getByIndirizzoContainingIgnoreCase(indirizzo);
        if(residenzaList.isEmpty()) {
            throw new NotFoundException();
        } else {
            return residenzaList;
        }
    }

    public Residenza getByNomeEqualsIgnoreCase(String nome) throws InternalServerErrorException, NotFoundException {
        Optional<Residenza> residenzaOptional = residenzaService.getByNomeEqualsIgnoreCase(nome);
        if(!residenzaOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            return residenzaOptional.get();
        }
    }

    public Residenza newResidenza(ResidenzaPOJO residenza) throws NotValidException, InternalServerErrorException {
        if(!residenzaValidation(residenza) || residenzaService.existsResidenzaByNomeOrIndirizzo(residenza.getNome(), residenza.getIndirizzo())) {
            throw new NotValidException();
        } else {
            Residenza r = new Residenza(null, residenza.getNome(), residenza.getIndirizzo(), null, null);
            return residenzaService.save(r);
        }
    }

    public Residenza updateResidenza(ResidenzaPOJO residenza) throws NotValidException, InternalServerErrorException {
        if(!residenzaValidation(residenza) || residenzaService.existsResidenzaByNomeOrIndirizzo(residenza.getNome(), residenza.getIndirizzo())) {
            throw new NotValidException();
        } else {
            Residenza r = new Residenza(null, residenza.getNome(), residenza.getIndirizzo(), null, null);
            return residenzaService.update(r);
        }
    }

    public ResidenzaPOJO delete(Long id) throws NotFoundException, InternalServerErrorException {
        Residenza residenza = this.getById(id);
        ResidenzaPOJO residenzaPOJO = new ResidenzaPOJO(residenza.getNome(), residenza.getIndirizzo());
        residenzaService.delete(residenza);
        return residenzaPOJO;
    }

    private boolean residenzaValidation(ResidenzaPOJO residenza) {
        if(residenza.getNome().trim().isEmpty() || residenza.getIndirizzo().trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
