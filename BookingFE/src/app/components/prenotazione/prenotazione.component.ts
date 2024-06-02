import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CameraService } from '../../services/camera.service';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { PrePrenotazioneService } from '../../services/pre-prenotazione.service';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { DateCustom } from '../../models/date-custom';
import { CameraCustom } from '../../models/camera-custom';
import { PrenotazioneCustom } from '../../models/prenotazione-custom';

@Component({
  selector: 'app-prenotazione',
  templateUrl: './prenotazione.component.html',
  styleUrl: './prenotazione.component.css',
})
export class PrenotazioneComponent {
  prenotazione: PrenotazioneCustom = new PrenotazioneCustom();
  prenotazioneCustom: PrenotazioneCustom = new PrenotazioneCustom();
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
        if (params['prenotazione'] && params['idPrePrenotazione']) {
          this.prenotazione = JSON.parse(params['prenotazione']);
          let idPrePrenotazione = JSON.parse(params['idPrePrenotazione']);
          this.prePrenotazioneService.getById(idPrePrenotazione).subscribe({
            next: (data) => {
              this.prenotazione.totale = data.totale;
              this.preparaConto();
            },
            error: (e) => {
              console.error("Errore nella richiesta HTTP: ", e.message);
            }
          });

        }
        console.log(this.prenotazione);
      },
      error: (e) => {
        console.error('Errore durante la ricezione dei parametri: ', e.message);
      },
    });
    this.getUser();

  }

  getUser() {
    this.userService
      .getUserIdByUsername(sessionStorage.getItem('Utente')!)
      .subscribe({
        next: (data) => {
          this.idUser = data;
          this.userService.getById(this.idUser).subscribe({
            next: (data) => {
              this.utente = data;
            },
            error: (e) => {
              console.error('Errore durante la richiesta HTTP: ', e.message);
            },
          });
        },
        error: (e) => {
          console.error('Errore durante la richiesta HTTP: ', e.message);
        },
      });
  }

  preparaConto() {
    this.dataCheckIn = this.prenotazione.checkIn!;
    this.dataCheckOut = this.prenotazione.checkOut!;
    this.totNotti();
    this.totaleParziale = this.prenotazione.totale! / this.totaleNotti!;
    this.totaleTassa = this.totaleNotti * this.tassaGiornaliera;
    console.log(this.totaleNotti, "<notti tassa> " , this.totaleTassa)
    this.totaleFinale = this.totaleTassa + this.prenotazione.totale!;

    console.log(typeof(this.totaleNotti), " ", typeof(this.totaleTassa))
    console.log(this.totaleNotti+this.totaleTassa)
    console.log((this.prenotazione.totale));
  }

  totNotti() {
    // Converti le stringhe in oggetti Date
    const checkInDate = new Date(this.dataCheckIn);
    const checkOutDate = new Date(this.dataCheckOut);
    // Calcola la differenza in millisecondi
    const differenceInMilliseconds = checkOutDate.getTime() - checkInDate.getTime();
    // Converti la differenza da millisecondi a giorni
    const millisecondsPerDay = 24 * 60 * 60 * 1000;
    this.totaleNotti = differenceInMilliseconds / millisecondsPerDay;
  }

  confermaPrenotazione() {
    this.cameraService
      .getDisponibili(new DateCustom(this.dataCheckIn, this.dataCheckOut))
      .subscribe({
        next: (data) => {
          this.camere = data;
          this.camere.forEach((camera: CameraCustom) => {
            if (camera.idCamera === this.prenotazione.idCamera) {
              this.prenota();
              this.router.navigate(['ricevuta'], {
                queryParams: {
                  prenotazione: JSON.stringify(this.prenotazione),
                },
              });
            }
          });
        },
        error: (e) => {
          console.error('Errore nella richiesta HTTP: ', e.message);
        },
      });
  }

  annullaPrenotazione() {
    this.prePrenotazioneService.delete(this.prenotazione.idPrePrenotazione!).subscribe({
      next: () => {
        this.router.navigate(['home']);
      },
      error: (e) => {
        console.error('Errore nella richiesta di delete: ', e.message);
      },
    });
  }

  prenota() {
    this.prenotazioneService.prenota(this.prenotazione).subscribe({
      next: (data) => {
        const prenotazione = data;
        this.router.navigate(['ricevuta'], {
          queryParams: {
            prenotazione: JSON.stringify(prenotazione),
          },
        });
      },
      error: (e) => {
        console.error('Errore durante la richiesta HTTP: ', e.messgae);
      },
    });
  }
}
