import { UserRegistrationModel } from './../../models/user-registration-model';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UserService } from './../../services/user.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  nuovoUtente?: User;

  nome = "";
  username = "";
  email = "";
  password = "";
  confirmPassword = "";


  constructor(private userService: UserService, private router: Router) {}

  gestioneRegistrazione() {
    if(this.nome === "" || this.username === "" || this.email ==="" || this.password === "" || this.confirmPassword === "") {
      console.log("Compilare tutti i campi"); //Ci va un alert qui! o qualche controllo sul form, meglio!
      return;
    }
    if(this.confirmPassword !== this.password) {
      console.log("Errore, password non corrispondenti"); //Qui un alert meglio...
    } else {
      this.userService.newUser(new UserRegistrationModel(this.nome, this.email, this.username, this.password)).subscribe({
        next: (data) => {
          this.nuovoUtente = data;
          console.log("Registrazione avvenuta con successo"); //Meglio alert o success redirection, con modifica sul link però
          this.router.navigate(["/login"]);
        },
        error: (e) => {
          console.error("Errore durante la richiesta HTTP: ", e.message);
        }
      })
    }

  }

}
