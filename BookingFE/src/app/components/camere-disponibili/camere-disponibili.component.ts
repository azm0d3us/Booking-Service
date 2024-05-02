import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-camere-disponibili',
  templateUrl: './camere-disponibili.component.html',
  styleUrl: './camere-disponibili.component.css'
})
export class CamereDisponibiliComponent {

  camere: any;

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params["camere"]) {
          this.camere = JSON.parse(params["camere"]);
        }
      },
      error: (e) => {
        console.error("Errore dutante la richiesta HTTP: ", e.message);
      }
    });
  }

}
