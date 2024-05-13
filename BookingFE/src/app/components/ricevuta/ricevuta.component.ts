import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prenotazione } from '../../models/prenotazione';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-ricevuta',
  templateUrl: './ricevuta.component.html',
  styleUrl: './ricevuta.component.css'
})
export class RicevutaComponent {

  prenotazione?: Prenotazione;
  utente?: User;
  id?: number;
  currentDate = new Date();
  totaleParziale?: number;
  tassa = 5;
  totaleTassa?: number;
  dataCheckIn?: Date;
  dataCheckOut?: Date;
  totaleNotti: any;
  totaleFinale?: number;

  constructor(private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params["prenotazione"]) {
          this.prenotazione = JSON.parse(params["prenotazione"]);
        }
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
    this.getUser();
    this.totaleParziale = this.prenotazione!.totale! / this.prenotazione!.numPersone!;
    this.dataCheckIn = new Date(this.prenotazione?.checkIn?.toString()!);
    this.dataCheckOut = new Date(this.prenotazione?.checkOut?.toString()!);
    this.totaleNotti = Math.floor((this.dataCheckOut.getTime() - this.dataCheckIn.getTime()) / (1000 * 60 * 60 * 24));
    this.totaleTassa = this.tassa * this.totaleNotti;
    this.totaleFinale = this.totaleTassa + this.prenotazione?.totale!;
  }

  getUser() {
    console.log(this.prenotazione);
    this.userService.getUserIdByUsername(sessionStorage.getItem("Utente")!).subscribe({
      next: (data) => {
        this.id = data;
        this.userService.getById(this.id).subscribe({
          next: (data) => {
            this.utente = data;
          },
          error: (e) => {
            console.error("Errore durante la richiesta HTTP: ", e.message);
          }
        })
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

}
