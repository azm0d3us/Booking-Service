import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { UserService } from '../../services/user.service';
import { AuthorizationService } from '../../services/authorization.service';
import { PrenotazioneCustom } from '../../models/prenotazione-custom';
import { CameraService } from '../../services/camera.service';
import { DateCustom } from '../../models/date-custom';

@Component({
  selector: 'app-camere-disponibili',
  templateUrl: './camere-disponibili.component.html',
  styleUrl: './camere-disponibili.component.css',
})
export class CamereDisponibiliComponent {
  camere: any;
  checkIn: any;
  checkOut: any;
  userId: any;
  numOspiti: any;
  prenotazione: any;
  p = 1;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cameraService: CameraService,
    private prenotazioneService: PrenotazioneService,
    private userService: UserService,
    public authService: AuthorizationService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if (params['checkIn'] && params['checkOut'] && params['numOspiti']) {
          this.checkIn = JSON.parse(params['checkIn']);
          this.checkOut = JSON.parse(params['checkOut']);
          this.numOspiti = JSON.parse(params['numOspiti']);
        }
        this.cameraService
          .getDisponibili(new DateCustom(this.checkIn, this.checkOut))
          .subscribe({
            next: (data) => {
              this.camere = data;
              console.log(this.camere);
            },
            error: (e) => {
              console.error('Errore durante la richiesta HTTP: ', e.message);
            },
          });
      },
      error: (e) => {
        console.error('Errore dutante la richiesta HTTP: ', e.message);
      },
    });
  }

  prenota(idCamera: number) {
    this.userService
      .getUserIdByUsername(sessionStorage.getItem('Utente')!)
      .subscribe({
        next: (data) => {
          this.userId = data;
          this.prenotazioneService
            .prenota(
              new PrenotazioneCustom(
                idCamera,
                this.userId,
                this.numOspiti,
                this.checkIn,
                this.checkOut
              )
            )
            .subscribe({
              next: (data) => {
                this.prenotazione = data;
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
        },
        error: (e) => {
          console.error('Errore durante la richiesta HTTP: ', e.message);
        },
      });
  }
}
