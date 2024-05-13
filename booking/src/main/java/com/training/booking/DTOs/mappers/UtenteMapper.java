package com.training.booking.DTOs.mappers;

import com.training.booking.DTOs.response.UtenteResponse;
import com.training.booking.entities.Utente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UtenteMapper {

    public UtenteResponse utenteMapper(Utente utente) {
        return new UtenteResponse(
                utente.getIdUser(),
                utente.getNome(),
                utente.getCognome(),
                utente.getDdn(),
                utente.getCf(),
                utente.getCodDoc(),
                utente.getEmail(),
                utente.getUsername(),
                utente.isAdmin()
        );
    }

    public List<UtenteResponse> utenteListMapper(List<Utente> lista) {
        List<UtenteResponse> listaUtentiResponse = new ArrayList<>();

        for(Utente u: lista) {
            listaUtentiResponse.add(new UtenteResponse(
                    u.getIdUser(),
                    u.getNome(),
                    u.getCognome(),
                    u.getDdn(),
                    u.getCf(),
                    u.getCodDoc(),
                    u.getEmail(),
                    u.getUsername(),
                    u.isAdmin()
            ));
        }

        return listaUtentiResponse;
    }
}
