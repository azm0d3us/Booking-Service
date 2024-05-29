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
          this.checkIn = JSON.parse(params['checkIn']);
          this.checkOut = JSON.parse(params['checkOut']);
          this.numAdulti = JSON.parse(params['numAdulti']);
          this.numBambini = JSON.parse(params['numBambini']);
          this.numOspiti = JSON.parse(params['numOspiti']);
        }
        this.cameraService
          .getDisponibili(new DateCustom(this.checkIn, this.checkOut))
          .subscribe({
            next: (data) => {
              this.camere = data;
              console.log(this.camere);
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
            console.log(data);
          }
        }
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

  preventivo(idcamera: number) {
    this.router.navigate(['prenotazione'], {
      queryParams: {
        idCamera: JSON.stringify(idcamera),
      }
    });
  }

  preparaPrenotazione(idCamera: number) {
    this.userService
      .getUserIdByUsername(sessionStorage.getItem('Utente')!)
      .subscribe({
        next: (data) => {
          this.userId = data;
          this.prePrenotazioneService
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
                // console.log(this.prenotazione);
                this.router.navigate(['prenotazione'], {
                  queryParams: {
                    prenotazione: JSON.stringify(this.prenotazione),
                    idCamera: JSON.stringify(idCamera)
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
