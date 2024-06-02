import { SimpleUser } from '../../models/simple-user';
import { UserService } from './../../services/user.service';
import { Component } from '@angular/core';
import { User } from '../../models/user';
import { AuthorizationService } from '../../services/authorization.service';
import { Route, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username = '';
  password = '';
  autenticato = false;
  utente?: User;
  errorMsg = 'Spiacente, la userId o la password non sono corretti.';

  constructor(
    private userService: UserService,
    private authService: AuthorizationService,
    private router: Router
  ) {}

  gestioneLogIn() {
    if (this.username.trim() === '' && this.password.trim() === '') {
      Swal.fire({
        icon: 'error',
        title: 'Errore',
        text: 'Inserire i dati per effettuare il login',
        confirmButtonColor: '#d33',
      });
    } else if (this.username.trim() === '') {
      Swal.fire({
        icon: 'error',
        title: 'Errore',
        text: 'Inserire uno username!',
        confirmButtonColor: '#d33',
      });
    } else if (this.password.trim() === '') {
      Swal.fire({
        icon: 'error',
        title: 'Errore',
        text: 'Inserire una password!',
        confirmButtonColor: '#d33',
      });
    } else {
      this.userService
        .login(new SimpleUser(this.username, this.password))
        .subscribe({
          next: (data) => {
            this.utente = data;
            if (
              this.authService.autentica(
                this.username,
                this.password,
                this.utente
              )
            ) {
              this.router.navigate(['home']);
            }
          },
          error: (e) => {
            console.error('Errore durante la richiesta HTTP: ', e.message);
            Swal.fire({
              icon: 'info',
              title: 'Non trovato',
              text: 'Utente non trovato',
              confirmButtonColor: '#3085d6',
            });
            this.username = '';
            this.password = '';
          }
        });
    }
  }
}
