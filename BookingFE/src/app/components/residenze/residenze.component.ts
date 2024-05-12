import { ResidenzaService } from './../../services/residenza.service';
import { Residenza } from './../../models/residenza';
import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { AuthorizationService } from '../../services/authorization.service';

@Component({
  selector: 'app-residenze',
  templateUrl: './residenze.component.html',
  styleUrl: './residenze.component.css'
})
export class ResidenzeComponent {

  residenze: Residenza[] = [];
  panel: boolean[] = [];
  admin: boolean = false;

  constructor(private authorizationService: AuthorizationService, private residenzeService: ResidenzaService) {
  }

  ngOnInit() : void {
    this.residenzeService.getAll().subscribe({
      next: (data) => {
        this.residenze = data;
        console.log(data);
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    })
    this.admin = this.authorizationService.admin;
  }

  resDetails(id: any) {
    console.log(id);
  }

  adminToggle(index: number){
    this.panel.fill(false);
    this.panel[index] = true;
  }

  adminHide(index: number) {
    this.panel[index] = false;
  }

}
