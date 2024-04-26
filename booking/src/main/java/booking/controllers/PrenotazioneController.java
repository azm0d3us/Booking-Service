package booking.controllers;

import com.training.booking.POJOs.DatesPOJO;
import com.training.booking.POJOs.PrenotazionePOJO;
import com.training.booking.POJOs.RequestPOJO;
import com.training.booking.POJOs.TwoIdPOJO;
import com.training.booking.controllers.business.PrenotazioneBusiness;
import com.training.booking.entities.*;
import com.training.booking.errors.InternalServerErrorException;
import com.training.booking.errors.NotFoundException;
import com.training.booking.errors.NotValidException;
import com.training.booking.services.interfaces.ICameraService;
import com.training.booking.services.interfaces.IListaPrezziService;
import com.training.booking.services.interfaces.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneBusiness prenotazioneBusiness;

    @GetMapping(value = "/prenotazioni", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getAll(), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/prenotazioni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getById(id), HttpStatus.OK);
        } catch (NotFoundException | InternalServerErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/prenotazioniFraDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByCheckBetween(@RequestBody DatesPOJO date) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getByCheckInCheckOutBetween(date.getCheckIn(), date.getCheckOut()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/prenotazioniPerNumPersone", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByNumPersone(@RequestParam("persone") Integer persone) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getByNumPersone(persone), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/disponibili", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> camereDisponibili(@RequestBody DatesPOJO date) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.getStruttureDisponibili(date.getCheckIn(), date.getCheckOut()), HttpStatus.OK);
        } catch (InternalServerErrorException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/nuovaPrenotazione", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newPrenotazione(@RequestBody PrenotazionePOJO prenotazione) {
        try {
            return new ResponseEntity<>(prenotazioneBusiness.newPrenotazione(prenotazione), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (InternalServerErrorException e) {
            throw new RuntimeException(e);
        } catch (NotValidException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    ICameraService cameraService;
    @Autowired
    IUtenteService utenteService;
    @Autowired
    IListaPrezziService listaPrezziService;

    /*Calcola il totale su un solo cambio di fascia di prezzo.*/
    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(@RequestBody RequestPOJO request) throws InternalServerErrorException, NotFoundException {
        TwoIdPOJO prenotazione = request.getPrenotazione();
        DatesPOJO date = request.getDate();
        Camera camera = cameraService.getById(prenotazione.getId1());
        Utente utente = utenteService.getById(prenotazione.getId2()).get();

        Double totale = 0.0;
        List<ListaPrezzi> listini = listaPrezziService.getAll();
        Set<ListaPrezzi> setListini = listaPrezziService.getByCamera(camera);
        Set<ListaPrezzi> set = new HashSet<>();
        Long period = ChronoUnit.DAYS.between(date.getCheckIn().toLocalDate(), date.getCheckOut().toLocalDate());

        /*Controllare che la prenotazione accavalli listini speciali e nel caso sommare sul totale usando prezzi base e prezzi differenziati al caso*/
        //Probabilmente non funziona su più listini.
        for(ListaPrezzi l : setListini) {
            /*Esclusione listini al di fuori del mio periodo di check*/
            if(!(l.getValiditaInizio().before(date.getCheckIn()) && l.getValiditaFine().before(date.getCheckIn()) ||
            l.getValiditaInizio().after(date.getCheckOut()) && l.getValiditaFine().after(date.getCheckOut()))){
                for(int i = 0; i < period; i++) {
                    LocalDate d = date.getCheckIn().toLocalDate().plusDays(i);
                    if(d.isBefore(l.getValiditaInizio().toLocalDate()) || d.isAfter(l.getValiditaFine().toLocalDate())) {
                        totale += camera.getPrezzoBase();
                    }  else {
                        totale += l.getPrezzo();
                    }
                }
                set.add(l);
            }
        }

        Prenotazione p = new Prenotazione();
        p.setTotale(totale);


        return new ResponseEntity<>(p, HttpStatus.OK);

    }

    /*Soluzione previsione totale funzionante*/
    @PostMapping(value = "/test1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test1(@RequestBody RequestPOJO request) throws InternalServerErrorException, NotFoundException {
        TwoIdPOJO prenotazione = request.getPrenotazione();
        DatesPOJO date = request.getDate();
        Camera camera = cameraService.getById(prenotazione.getId1());
        Utente utente = utenteService.getById(prenotazione.getId2()).get();

        Double totale = 0.0;
        List<ListaPrezzi> listini = listaPrezziService.getAll();
        Set<ListaPrezzi> setListini = listaPrezziService.getByCamera(camera);
        Set<ListaPrezzi> set = new HashSet<>();
        Long period = ChronoUnit.DAYS.between(date.getCheckIn().toLocalDate(), date.getCheckOut().toLocalDate());

        /*Controllare che la prenotazione accavalli listini speciali e nel caso sommare sul totale usando prezzi base e prezzi differenziati al caso*/
        //Probabilmente non funziona su più listini.
        for(ListaPrezzi l : setListini) {
            if(!(l.getValiditaInizio().before(date.getCheckIn()) && l.getValiditaFine().before(date.getCheckIn()) ||
                    l.getValiditaInizio().after(date.getCheckOut()) && l.getValiditaFine().after(date.getCheckOut()))){

                period -= ChronoUnit.DAYS.between(l.getValiditaInizio().toLocalDate().isBefore(date.getCheckIn().toLocalDate()) ? date.getCheckIn().toLocalDate() : l.getValiditaInizio().toLocalDate(),
                        l.getValiditaFine().toLocalDate().isAfter(date.getCheckOut().toLocalDate()) ? date.getCheckOut().toLocalDate() : l.getValiditaFine().toLocalDate());

                for(int i = 0; i < ChronoUnit.DAYS.between(l.getValiditaInizio().toLocalDate().isBefore(date.getCheckIn().toLocalDate()) ? date.getCheckIn().toLocalDate() : l.getValiditaInizio().toLocalDate(),
                        l.getValiditaFine().toLocalDate().isAfter(date.getCheckOut().toLocalDate()) ? date.getCheckOut().toLocalDate() : l.getValiditaFine().toLocalDate()); i++){
                    totale += l.getPrezzo();
                }
            }
        }
        totale += period * camera.getPrezzoBase();

        Prenotazione p = new Prenotazione();
        p.setTotale(totale);


        return new ResponseEntity<>(p, HttpStatus.OK);

    }


}
