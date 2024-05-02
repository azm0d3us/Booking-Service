import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-camere-disponibili',
  templateUrl: './camere-disponibili.component.html',
  styleUrl: './camere-disponibili.component.css'
})
export class CamereDisponibiliComponent {

  alloggi: any;

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if(params["alloggi"]) {
        this.alloggi = JSON.parse(params["alloggi"]);
      }
    })
  }

}
