package com.test.authorizationserver.controllers;

import com.test.authorizationserver.entities.Utente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UtenteController {

    public List<Utente> utenti = new ArrayList<>();
}
