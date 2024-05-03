import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { UserService } from '../../services/user.service';
import { AuthorizationService } from '../../services/authorization.service';
import { PrenotazioneCustom } from '../../models/prenotazione-custom';

@Component({
  selector: 'app-camere-disponibili',
  templateUrl: './camere-disponibili.component.html',
  styleUrl: './camere-disponibili.component.css'
})
export class CamereDisponibiliComponent {

  camere: any;
  checkIn: any;
  checkOut: any;
  userId: any;
  numPersone: any;
  prenotazione: any;

  constructor(private route: ActivatedRoute, private router: Router, private prenotazioneService: PrenotazioneService, private userService: UserService, public authService: AuthorizationService) {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params["camere"] && params["checkIn"] && params["checkOut"] && params["numPersone"]) {
          this.camere = JSON.parse(params["camere"]);
          this.checkIn = JSON.parse(params["checkIn"]);
          this.checkOut = JSON.parse(params["checkOut"]);
          this.numPersone = JSON.parse(params["numPersone"]);
        }
      },
      error: (e) => {
        console.error("Errore dutante la richiesta HTTP: ", e.message);
      }
    });
  }

  prenota(idCamera: number) {
    this.userService.getUserIdByUsername(sessionStorage.getItem("Utente")!).subscribe({
      next: (data) => {
        this.userId = data;
        this.prenotazioneService.prenota(new PrenotazioneCustom(idCamera, this.userId, this.numPersone, this.checkIn, this.checkOut)).subscribe({
          next: (data) => {
            this.prenotazione = data;
            this.router.navigate(["ricevuta"], { queryParams: {
              prenotazione: JSON.stringify(this.prenotazione)
            }});
          },
          error: (e) => {
            console.error("Errore durante la richiesta HTTP: ", e.messgae);
          }
        })
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

}
