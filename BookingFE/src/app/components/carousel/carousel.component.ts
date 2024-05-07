import { Component } from '@angular/core';
import { CameraComponent } from '../camera/camera.component';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {

  // urls: string[] = [];

  // constructor(private camere: CameraComponent) {
  //   camere.camere?.forEach(camera => {
  //     if(camera.urlImmagini) {
  //       this.urls.push(...camera.urlImmagini);
  //     }
  //     // camera.urlImmagini!.forEach(element => {
  //     //   this.urls?.push(element);
  //     // });
  //   });

  //   this.extractRandomUrls(5);
  // }

  // private extractRandomUrls(n: any) {
  //   const shuffledUrls = this.shuffleArray(this.urls);
  //   return shuffledUrls.slice(0, n);
  // }

  // // Algoritmo di Fisher-Yates
  // private shuffleArray(array: any[]) {
  //   for(let i = array.length - 1; i > 0; i--) {
  //     const j = Math.floor(Math.random() * (i + 1));
  //     [array[i], array[j] = array[j], array[i]];
  //   }
  //   return array;
  // }
}
