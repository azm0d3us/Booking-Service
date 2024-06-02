package com.training.booking.controllers.business;

import com.training.booking.POJOs.PrenotazionePOJO;
import com.training.booking.entities.*;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.IPrePrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

@Component
public class PrePrenotazioneBusiness {

    @Autowired
    private IPrePrenotazioneService preService;
    @Autowired
    private ListaPrezziBusiness listaPrezziBusiness;
    @Autowired
    private UtenteBusiness utenteBusiness;
    @Autowired
    private CameraBusiness cameraBusiness;


    public PrePrenotazione getById(Long idPrePrenotazione) throws InternalServerErrorException, NotFoundException {
        Optional<PrePrenotazione> prenotazioneOptional = preService.getById(idPrePrenotazione);
        if(!prenotazioneOptional.isPresent()) {
            throw new NotFoundException();
        }
        return prenotazioneOptional.get();
    }

    public PrePrenotazione newPrePrenotazione(PrenotazionePOJO prenotazione) throws NotFoundException, InternalServerErrorException, NotValidException {
        Camera camera = cameraBusiness.getById(prenotazione.getIdCamera());
        Utente utente = utenteBusiness.getById(prenotazione.getIdUser());
        Set<ListaPrezzi> setListini = listaPrezziBusiness.getByCamera(camera.getIdCamera());
        Double totale = makeToalePrev(setListini, prenotazione.getCheckIn(), prenotazione.getCheckOut(), camera);
//        totale *= prenotazione.getNumPersone();
        PrePrenotazione p = new PrePrenotazione(null, prenotazione.getNumAdulti(), prenotazione.getNumBambini(), prenotazione.getNumPersone(), totale, prenotazione.getCheckIn(), prenotazione.getCheckOut(), utente, camera);
        return preService.newPrenotazione(p);
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

    public PrePrenotazione deletePrePrenotazione(Long idPrenotazione) throws InternalServerErrorException, NotValidException, NotFoundException {
        if(idPrenotazione == null) {
            throw new NotValidException();
        }
        Optional<PrePrenotazione> prenotazioneOptional = preService.getById(idPrenotazione);
        if(!prenotazioneOptional.isPresent()) {
            throw new NotFoundException();
        }
        PrePrenotazione prenotazione = prenotazioneOptional.get();
        PrePrenotazione prenotazioneCancellata = new PrePrenotazione(
                prenotazione.getIdPrePrenotazione(),
                prenotazione.getNumAdulti(),
                prenotazione.getNumBambini(),
                prenotazione.getNumPersone(),
                prenotazione.getTotale(),
                prenotazione.getCheckIn(),
                prenotazione.getCheckOut(),
                prenotazione.getUtentePrenotante(),
                prenotazione.getCameraPrenotata()
        );
        preService.delete(prenotazione);
        return prenotazioneCancellata;
    }
}
