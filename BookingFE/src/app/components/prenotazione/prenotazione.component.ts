import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CameraService } from '../../services/camera.service';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Prenotazione } from '../../models/prenotazione';
import { PrePrenotazioneService } from '../../services/pre-prenotazione.service';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { DateCustom } from '../../models/date-custom';
import { CameraCustom } from '../../models/camera-custom';

@Component({
  selector: 'app-prenotazione',
  templateUrl: './prenotazione.component.html',
  styleUrl: './prenotazione.component.css'
})
export class PrenotazioneComponent {

  prenotazione: Prenotazione = new Prenotazione();
  camere: any;
  idPrePrenotazione = 0;
  idUser: number = 0;
  utente: User = new User();
  dataCheckIn!: Date;
  dataCheckOut!: Date;
  tassaGiornaliera: number = 5;
  totaleNotti: number = 1;
  totaleParziale: number = 0;
  totaleTassa: number = 0;
  totaleFinale: number = 0;
  currentDate = new Date();

  constructor(
    private prePrenotazioneService: PrePrenotazioneService,
    private prenotazioneService: PrenotazioneService,
    private cameraService: CameraService,
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params['prenotazione']) {
          this.prenotazione = JSON.parse(params['prenotazione']);
        }
        this.idPrePrenotazione = this.prenotazione.idPrePrenotazione!;
      },
      error: (e) => {
        console.error("Errore durante la ricezione dei parametri: ", e.message);
      }
    });
    this.getUser();
    this.totaleParziale = this.prenotazione.totale! / this.prenotazione.numPersone!;
    this.dataCheckIn = this.prenotazione.checkIn!;
    this.dataCheckOut = this.prenotazione.checkOut!;
    // this.totaleNotti = Math.floor((this.dataCheckOut.getTime() - this.dataCheckIn.getTime()) / (1000 * 60 * 60 * 24));
    // this.totaleTassa = this.tassaGiornaliera * this.totaleNotti;
    this.totaleFinale = this.totaleTassa + this.prenotazione.totale!;
  }

  getUser() {
    this.userService.getUserIdByUsername(sessionStorage.getItem('Utente')!).subscribe({
      next: (data) => {
        this.idUser = data;
        this.userService.getById(this.idUser).subscribe({
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

  confermaPrenotazione(id: number) {
    console.log(this.prenotazione);
    console.log(id);
    this.cameraService.getDisponibili(new DateCustom(this.dataCheckIn, this.dataCheckOut)).subscribe({
      next: (data) => {
        this.camere = data;
        console.log(this.camere);
        this.camere.forEach((camera: CameraCustom) => {
          console.log(camera.idCamera);
          console.log(camera.idCamera === id);
        });
      },
      error: (e) => {
        console.error("Errore nella richiesta HTTP: ", e.message);
      }
    })
    // this.prenotazioneService.prenota(this.prenotazione);
    // this.router.navigate(['ricevuta']);
  }

  annullaPrenotazione() {
    console.log(this.idPrePrenotazione);
    this.prePrenotazioneService.delete(this.idPrePrenotazione).subscribe({
      next: () => {
        this.router.navigate(['home']);
      },
      error: (e) => {
        console.error("Errore nella richiesta di delete: ", e.message);
      }
    });
  }
}
