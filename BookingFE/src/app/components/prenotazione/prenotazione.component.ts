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
import { PrenotazioneCustom } from '../../models/prenotazione-custom';

@Component({
  selector: 'app-prenotazione',
  templateUrl: './prenotazione.component.html',
  styleUrl: './prenotazione.component.css'
})
export class PrenotazioneComponent {

  prenotazione: Prenotazione = new Prenotazione();
  camere: any;
  idCameraMonitored: number = 0;
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
        if(params['prenotazione'] && params['idCamera']) {
          this.prenotazione = JSON.parse(params['prenotazione']);
          this.idCameraMonitored = JSON.parse(params['idCamera']);
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

  confermaPrenotazione() {
    console.log(this.prenotazione);
    console.log(this.idCameraMonitored);
    this.cameraService.getDisponibili(new DateCustom(this.dataCheckIn, this.dataCheckOut)).subscribe({
      next: (data) => {
        this.camere = data;
        console.log(this.camere);
        this.camere.forEach((camera: CameraCustom) => {
          // console.log(camera.idCamera);
          if(camera.idCamera === this.idCameraMonitored) {
            this.prenota();
            this.router.navigate(['ricevuta'], {queryParams: {
              prenotazione: JSON.stringify(this.prenotazione)
          }});
          };
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

  prenota() {
    this.prenotazioneService.prenota(
      new PrenotazioneCustom(
        this.idCameraMonitored,
        this.idUser,
        this.prenotazione.numPersone!,
        this.prenotazione.checkIn!,
        this.prenotazione.checkOut!
      )
      )
      .subscribe({
          next: (data) => {
            this.prenotazione = data;
            // console.log(this.prenotazione);
            this.router.navigate(['ricevuta'], {
              queryParams: {
                prenotazione: JSON.stringify(this.prenotazione),
              },
            });
          },
          error: (e) => {
            console.error('Errore durante la richiesta HTTP: ', e.messgae);
          },
        });
  }

  // // Funzione di prenotazione
  // prenota(idCamera: number) {
  //   this.userService
  //     .getUserIdByUsername(sessionStorage.getItem('Utente')!)
  //     .subscribe({
  //       next: (data) => {
  //         this.userId = data;
  //         this.prenotazioneService
  //           .prenota(
  //             new PrenotazioneCustom(
  //               idCamera,
  //               this.userId,
  //               this.numOspiti,
  //               this.checkIn,
  //               this.checkOut
  //             )
  //           )
  //           .subscribe({
  //             next: (data) => {
  //               this.prenotazione = data;
  //               // console.log(this.prenotazione);
  //               this.router.navigate(['ricevuta'], {
  //                 queryParams: {
  //                   prenotazione: JSON.stringify(this.prenotazione),
  //                 },
  //               });
  //             },
  //             error: (e) => {
  //               console.error('Errore durante la richiesta HTTP: ', e.messgae);
  //             },
  //           });
  //       },
  //       error: (e) => {
  //         console.error('Errore durante la richiesta HTTP: ', e.message);
  //       },
  //     });
  // }
}
