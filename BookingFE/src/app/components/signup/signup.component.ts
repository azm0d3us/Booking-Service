import { UserRegistrationModel } from './../../models/user-registration-model';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UserService } from './../../services/user.service';
import { Component } from '@angular/core';
import { UserComplete } from '../../models/user-complete';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  nuovoUtente?: User;

  idUser?: number;
  nome = "";
  cognome = "";
  ddn = new Date();
  cf = "";
  codDoc = "";
  email = "";
  username = "";
  password = "";
  confirmPassword = "";
  cfFound?: boolean;



  constructor(private userService: UserService, private router: Router) {}

  gestioneRegistrazione() {
    if(this.nome === "" || this.username === "" || this.email ==="" || this.password === "" || this.confirmPassword === "") {
      Swal.fire({
        icon: 'warning',
        title: 'Attenzione',
        text: 'Compilare tutti i campi',
        confirmButtonColor: '#3085d6'
      })
      return;
    }
    if(this.confirmPassword !== this.password) {
      Swal.fire({
        icon: 'error',
        title: 'Errore',
        text: 'Errore, password non corrispondenti',
        confirmButtonColor: '#d33'
      });
    } else if(!this.cfFound) {
      this.userService.newUser(new UserRegistrationModel(this.nome, this.email, this.username, this.password)).subscribe({
        next: (data) => {
          this.nuovoUtente = data;
          Swal.fire({
            icon: 'success',
            title: 'Successo',
            text: 'Registrazione avvenuta con successo',
            confirmButtonColor: '#28a745'
          }).then(() => {
            this.router.navigate(["/login"]);
          });
        },
        error: (e) => {
          console.error("Errore durante la richiesta HTTP: ", e.message);
          Swal.fire({
            icon: 'error',
            title: 'Errore',
            text: 'Errore durante la richiesta HTTP: ' + e.message,
            confirmButtonColor: '#d33'
          });
        }
      });
    } else {
      let userComplete = new UserComplete();
      userComplete.idUser = this.idUser;
      userComplete.nome = this.nome;
      userComplete.cognome = this.cognome;
      userComplete.ddn = this.ddn;
      userComplete.cf = this.cf;
      userComplete.codDoc = this.codDoc;
      userComplete.email = this.email;
      userComplete.username = this.username;
      userComplete.password = this.password;
      userComplete.admin = false;

      this.userService.userUpdateComplete(userComplete).subscribe({
        next: (data) => {
          console.log(data);
          Swal.fire({
            icon: 'success',
            title: 'Successo',
            text: 'Registrazione avvenuta con successo',
            confirmButtonColor: '#28a745'
          }).then(() => {
            this.router.navigate(["/login"]);
          });
        },
        error: (e) => {
          console.error("Errore nella richiesta HTTP: ", e.message);
        }
      });
    }
  }

  verificaCf() {
    if(this.cf === "") this.verificaClear();
    this.userService.getUserByDocumentCode(this.cf).subscribe({
      next: (data) => {
        if(data) {
          this.nome = data.nome;
          this.cfFound = true;
          console.log(data);
          this.idUser = data.idUser;
          Swal.fire({
            icon: 'success',
            title: 'Bentornato!',
            text: 'Codice Fiscale già presente in memoria, i campi di inserimento sono stati precompilati, procedi con la registrazione.',
            confirmButtonColor: '#28a745'
          });
        } else {
          this.nome = "";
          this.cfFound = false;
          // Swal.fire({
          //     icon: 'info',
          //     title: 'Non trovato',
          //     text: 'Codice Fiscale non trovato',
          //     confirmButtonColor: '#3085d6'
          //   });
        }
      },
      error: (e) => {
        console.error("Errore nella richiesta HTTP: ", e.message);
        // Swal.fire({
        //   icon: 'error',
        //   title: 'Errore',
        //   text: 'Errore durante la verifica del Codice Fiscale: ' + e.message,
        //   confirmButtonColor: '#d33'
        // });
      }
    });
  }

  verificaClear() {
    this.cfFound = false;
    this.nome = "";
  }

}
