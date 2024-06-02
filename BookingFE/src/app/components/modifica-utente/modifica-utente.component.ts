import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UserUpdate } from '../../models/user-update';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modifica-utente',
  templateUrl: './modifica-utente.component.html',
  styleUrl: './modifica-utente.component.css'
})
export class ModificaUtenteComponent {

  nome = "";
  cognome = "";
  ddn = "";
  cf = "";
  codDoc = "";
  id = 0;
  utente?: User;
  utenteUpdate?: UserUpdate;
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.userService.getUserIdByUsername(sessionStorage.getItem("Utente")!).subscribe({
      next: (data) => {
        this.id = data;
        this.userService.getById(this.id).subscribe({
          next: (data) => {
            this.utente = data;
            console.log(data);
            this.utenteUpdate = new UserUpdate(
              this.utente.idUser!,
              this.utente.nome!,
              this.utente.cognome!,
              new Date(),
              this.utente.codDoc!,
              this.utente.cf!
            )
            this.ddn = this.formatDate(this.utente.ddn!);
          },
          error: (e) => {
            console.error("Errore nella richiesta http: ", e.message);
          }
        });
      },
      error: (e) => {
        console.error("Errore nella richiesta http: ", e.message);
      }
    });
  }

  formatDate(date: Date): string {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }

  modificaUtente() {
    this.utenteUpdate!.ddn = new Date(this.ddn);
    console.log(this.utenteUpdate);
    this.userService.update(this.utenteUpdate).subscribe({
      next: (data) => {
        console.log(data);
          Swal.fire({
            icon: 'success',
            title: 'Successo',
            text: 'Le modifiche sono state apportate con successo',
            confirmButtonColor: '#28a745'
          });
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    })
  }

}
