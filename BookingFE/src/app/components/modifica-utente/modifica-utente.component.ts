import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UserUpdate } from '../../models/user-update';

@Component({
  selector: 'app-modifica-utente',
  templateUrl: './modifica-utente.component.html',
  styleUrl: './modifica-utente.component.css'
})
export class ModificaUtenteComponent {

  nome = "";
  cognome = "";
  ddn = new Date();
  cf = "";
  codDoc = "";
  utente?: User;

  constructor(private userService: UserService, private route: Router) {}

  modificaUtente() {
    this.userService.getUserIdByUsername(sessionStorage.getItem("Utente")!).subscribe({
      next: (data) => {
        this.userService.update(new UserUpdate(data, this.nome, this.cognome, this.ddn, this.codDoc, this.cf)).subscribe({
          next: (v) => {
            console.log("Aggiornamento dati " + v.username + " riuscito");
            this.route.navigate(["/success"]);
          },
          error: (e) => {
            console.error("Errore durante la richiesta HTTP: ",e.message);
          }
        })
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    })
  }

}
