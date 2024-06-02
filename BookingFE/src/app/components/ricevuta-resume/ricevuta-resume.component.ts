import { Component } from '@angular/core';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { Prenotazione } from '../../models/prenotazione';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ricevuta-resume',
  templateUrl: './ricevuta-resume.component.html',
  styleUrl: './ricevuta-resume.component.css'
})
export class RicevutaResumeComponent {

  prenotazione?: Prenotazione;
  currentDate: Date = new Date();
  totaleNotti?: number;

  constructor(private prenotazioneService: PrenotazioneService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params["id"]) {
          let id = JSON.parse(params["id"]);
          this.prenotazioneService.getById(id).subscribe({
            next: (data) => {
              console.log(data);
              this.prenotazione = data;
              this.totNotti();
            },
            error: (e) => {
              console.error("Errore nella richiesta HTTP ", e.message);
            }
          });
        }
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
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

}
