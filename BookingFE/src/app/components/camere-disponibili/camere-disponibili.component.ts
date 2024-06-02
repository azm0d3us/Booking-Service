import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { AuthorizationService } from '../../services/authorization.service';
import { PrenotazioneCustom } from '../../models/prenotazione-custom';
import { CameraService } from '../../services/camera.service';
import { DateCustom } from '../../models/date-custom';
import { ImmaginiService } from '../../services/immagini.service';
import { PrePrenotazioneService } from '../../services/pre-prenotazione.service';

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
  numAdulti: any;
  numBambini: any;
  numOspiti: any;
  prenotazione: any;
  prenotazioneCustom: PrenotazioneCustom = new PrenotazioneCustom();

  p = 1;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cameraService: CameraService,
    private immaginiService: ImmaginiService,
    private prePrenotazioneService: PrePrenotazioneService,
    private userService: UserService,
    public authService: AuthorizationService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if (params['checkIn'] && params['checkOut']&& params['numAdulti'] && params['numBambini'] && params['numOspiti']) {
          this.prenotazioneCustom.checkIn = JSON.parse(params['checkIn']);
          this.prenotazioneCustom.checkOut = JSON.parse(params['checkOut']);
          this.prenotazioneCustom.numAdulti = JSON.parse(params['numAdulti']);
          this.prenotazioneCustom.numBambini = JSON.parse(params['numBambini']);
          this.prenotazioneCustom.numPersone = JSON.parse(params['numOspiti']);
        }
        this.cameraService
          .getDisponibili(new DateCustom(this.prenotazioneCustom.checkIn!, this.prenotazioneCustom.checkOut!))
          .subscribe({
            next: (data) => {
              this.camere = data;
              this.camere.forEach((camera: { idCamera: any; }) => {
                this.getImmagini(camera.idCamera);
              })
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

  getImmagini(idCamera: any) {
    this.immaginiService.getByCamera(idCamera).subscribe({
      next: (data) => {
        const camera = this.camere?.find((camera: { idCamera: any; }) => camera.idCamera === idCamera);
        if(camera) {
          if(Array.isArray(data) && data.length === 0) {
            camera.urlImmagini = ["/assets/images/camere/nonPhoto.jpg"]
          } else {
            camera.urlImmagini = data;
          }
        }
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

  preparaPrenotazione(idCamera: number) {
    this.userService
      .getUserIdByUsername(sessionStorage.getItem('Utente')!)
      .subscribe({
        next: (data) => {
          this.userId = data;
          this.prenotazioneCustom.idCamera = idCamera;
          this.prenotazioneCustom.idUser = this.userId;
          this.prePrenotazioneService
            .prenota(this.prenotazioneCustom)
            .subscribe({
              next: (data) => {
                this.prenotazioneCustom.idPrePrenotazione = data.idPrePrenotazione;
                this.router.navigate(['prenotazione'], {
                  queryParams: {
                    idPrePrenotazione: JSON.stringify(this.prenotazioneCustom.idPrePrenotazione),
                    prenotazione: JSON.stringify(this.prenotazioneCustom),
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
