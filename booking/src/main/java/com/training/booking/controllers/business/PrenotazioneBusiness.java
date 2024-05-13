package com.training.booking.controllers.business;

import com.training.booking.POJOs.PrenotazionePOJO;
import com.training.booking.entities.*;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.IPrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PrenotazioneBusiness {
    @Autowired
    IPrenotazioneService prenotazioneService;

    @Autowired
    CameraBusiness cameraBusiness;

    @Autowired
    UtenteBusiness utenteBusiness;

    public List<Prenotazione> getAll() throws InternalServerErrorException, NotFoundException {
        List<Prenotazione> prenotazioni = prenotazioneService.getAll();
        if(prenotazioni.isEmpty()) {
            throw new NotFoundException();
        } else {
            return prenotazioni;
        }
    }

    public Prenotazione getById(Long id) throws NotFoundException, InternalServerErrorException {
        Optional<Prenotazione> prenotazioneOptional = prenotazioneService.getById(id);
        if(!prenotazioneOptional.isPresent()) {
            throw new NotFoundException();
        } else {
            return prenotazioneOptional.get();
        }
    }

    public List<Prenotazione> getByCheckInCheckOutBetween(Date checkIn, Date checkOut) throws InternalServerErrorException, NotFoundException {
        List<Prenotazione> prenotazioni = prenotazioneService.getByCheckInCheckOutBetween(checkIn, checkOut);
        if(prenotazioni.isEmpty()) {
            throw new NotFoundException();
        } else {
            return prenotazioni;
        }
    }

    public List<Prenotazione> getByNumPersone(Integer nPersone) throws InternalServerErrorException, NotFoundException {
        List<Prenotazione> prenotazioni = prenotazioneService.getByNumPersone(nPersone);
        if(prenotazioni.isEmpty()) {
            throw new NotFoundException();
        } else {
            return prenotazioni;
        }
    }

//    public Set<?> getStruttureDisponibili(Date checkIn, Date checkOut) throws InternalServerErrorException, NotFoundException {
//        List<Prenotazione> lista = prenotazioneService.getStruttureDisponibili(checkIn, checkOut);
//        if(lista.isEmpty()) {
//            throw new NotFoundException();
//        } else {
//            record r(Long id, Integer postiLetto, Double prezzoBase, String tipo, String infoCheckOut, Residenza residenza){};
//            Set<r> lc = lista.stream().map(l -> new r(l.getCameraPrenotata().getIdCamera(), l.getCameraPrenotata().getPostiLetto(),
//                    l.getCameraPrenotata().getPrezzoBase(), l.getCameraPrenotata().getTipo(), l.getCameraPrenotata().getInfoCheckOut(), l.getCameraPrenotata().getResidenza())).collect(Collectors.toSet());
//            return lc;
//        }
//    }

    @Autowired
    ListaPrezziBusiness listaPrezziBusiness;

    public Prenotazione newPrenotazione(PrenotazionePOJO prenotazione) throws NotFoundException, InternalServerErrorException, NotValidException {
        Camera camera = cameraBusiness.getById(prenotazione.getIdCamera());
        Utente utente = utenteBusiness.getById(prenotazione.getIdUser());
        Set<ListaPrezzi> setListini = listaPrezziBusiness.getByCamera(camera.getIdCamera());
        Double totale = makeToalePrev(setListini, prenotazione.getCheckIn(), prenotazione.getCheckOut(), camera);
        totale *= prenotazione.getNumPersone();
        Prenotazione p = new Prenotazione(null, prenotazione.getNumPersone(), totale, prenotazione.getCheckIn(), prenotazione.getCheckOut(), utente, camera);
        return prenotazioneService.newPrenotazione(p);
    }

    private Double makeToalePrev(Set<ListaPrezzi> setListini, Date checkIn, Date checkOut , Camera camera) {
        Double totale = 0.0;
        Long period = ChronoUnit.DAYS.between(checkIn.toLocalDate(), checkOut.toLocalDate());

        /*Controllare che la prenotazione accavalli listini speciali e nel caso sommare sul totale usando prezzi base e prezzi differenziati al caso*/
        for(ListaPrezzi l : setListini) {
            //Escludo listini che non mi interessano
            if(!(l.getValiditaInizio().before(checkIn) && l.getValiditaFine().before(checkIn) ||
                    l.getValiditaInizio().after(checkOut) && l.getValiditaFine().after(checkOut))){
                //Calcolo permanenza sotto prezzo alterato
                Long periodoListino = ChronoUnit.DAYS.between(
                        l.getValiditaInizio().toLocalDate().isBefore(checkIn.toLocalDate()) ? checkIn.toLocalDate() : l.getValiditaInizio().toLocalDate(),
                        l.getValiditaFine().toLocalDate().isAfter(checkOut.toLocalDate()) ? checkOut.toLocalDate() : l.getValiditaFine().toLocalDate());
                //Ricalcolo periodo totale da preventivare sul prezzo base della camera.
                period -= periodoListino;
                totale += periodoListino * l.getPrezzo();
            }
        }
        totale += period * camera.getPrezzoBase();
        return totale;
    }
}
