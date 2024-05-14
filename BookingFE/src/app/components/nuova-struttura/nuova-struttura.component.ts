import { Component } from '@angular/core';
import { ResidenzaService } from '../../services/residenza.service';
import { ResidenzaRequest } from '../../models/residenza-request';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nuova-struttura',
  templateUrl: './nuova-struttura.component.html',
  styleUrl: './nuova-struttura.component.css'
})
export class NuovaStrutturaComponent {

  nomeStruttura: string = "";
  indirizzo: string = "";
  idResidenza!: number;

  constructor(
    private residenzaService: ResidenzaService,
    private router: Router
  ) {}

  nuovaStruttura() {
    this.residenzaService.add(new ResidenzaRequest(
      this.nomeStruttura,
      this.indirizzo
    )).subscribe({
      next: (data) => {
        this.idResidenza = data.idResidenza;
        console.log(this.idResidenza)
        this.router.navigate(['/residenza-singola'], { queryParams: {
          idStruttura: JSON.stringify(this.idResidenza),
          showModify: JSON.stringify(true)
        }});
      },
      error: (e) => {
        console.error("Errore nell'invio della richiesta HTTP. Non sono riuscito ad aggiungere la residenza!", e.message);
      }
    })
  }


}
