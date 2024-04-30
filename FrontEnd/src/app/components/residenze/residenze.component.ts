import { ResidenzaService } from './../../services/residenza.service';
import { Residenza } from './../../models/residenza';
import { Component } from '@angular/core';

@Component({
  selector: 'app-residenze',
  templateUrl: './residenze.component.html',
  styleUrl: './residenze.component.css'
})
export class ResidenzeComponent {

  residenze?: Residenza[];

  constructor(private residenzeService: ResidenzaService) {
  }

  ngOnInit() : void {
    this.residenzeService.getAll().subscribe(data => {
      this.residenze = data;
      console.log(data);
    })
  }

}
