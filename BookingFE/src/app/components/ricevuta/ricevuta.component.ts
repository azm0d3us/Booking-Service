import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Prenotazione } from '../../models/prenotazione';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { PrenotazioneCustom } from '../../models/prenotazione-custom';

@Component({
  selector: 'app-ricevuta',
  templateUrl: './ricevuta.component.html',
  styleUrl: './ricevuta.component.css'
})
export class RicevutaComponent {

  prenotazione?: PrenotazioneCustom;
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

  constructor(private route: ActivatedRoute, private userService: UserService, private prenotazioneService: PrenotazioneService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params["prenotazione"]) {
          this.prenotazione = JSON.parse(params["prenotazione"]);
        }
        console.log(this.prenotazione);
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
    this.getUser();
    this.dataCheckIn = new Date(this.prenotazione?.checkIn?.toString()!);
    this.dataCheckOut = new Date(this.prenotazione?.checkOut?.toString()!);
    this.totNotti();
    this.totaleParziale = this.prenotazione!.totale! / this.totaleNotti!;
    this.totaleTassa = this.tassa * this.totaleNotti;
    this.totaleFinale = this.totaleTassa + this.prenotazione?.totale!;
  }

  totNotti() {
    // Converti le stringhe in oggetti Date
    const checkInDate = new Date(this.prenotazione?.checkIn!);
    const checkOutDate = new Date(this.prenotazione?.checkOut!);
    // Calcola la differenza in millisecondi
    const differenceInMilliseconds = checkOutDate.getTime() - checkInDate.getTime();
    // Converti la differenza da millisecondi a giorni
    const millisecondsPerDay = 24 * 60 * 60 * 1000;
    this.totaleNotti = differenceInMilliseconds / millisecondsPerDay;
  }

  getUser() {
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
