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
      console.log("Compilare tutti i campi");
      return;
    }
    if(this.confirmPassword !== this.password) {
      console.log("Errore, password non corrispondenti");
    } else {
      this.userService.newUser(new UserRegistrationModel(this.nome, this.email, this.username, this.password)).subscribe( data => {
        this.nuovoUtente = data;
        console.log("Registrazione avvenuta con successo");
        this.router.navigate(["/login"]);
      })
    }

  }

}
