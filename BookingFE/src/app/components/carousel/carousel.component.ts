import { CameraService } from './../../services/camera.service';
import { Component } from '@angular/core';
import { CameraComponent } from '../camera/camera.component';
import { CrossTestService } from '../../services/cross-test.service';
import { Camera } from '../../models/camera';
import { ImmaginiService } from '../../services/immagini.service';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {

  urls: string[] = [];
  shuffledUrls: string[] = [];

  constructor(private crossService: CrossTestService, ) {
  }

  ngOnInit(): void {
    this.urls = this.crossService.urls;
    this.shuffledUrls = this.extractRandomUrls(3);
    console.log(this.shuffledUrls)
  }


  //TESTING PHASE
  //This works
  // testerUrls() {
  //   console.log(this.urls);
  // }

  // //Now this works as well ^^
  // testerShuffledUrls() {
  //   //Se volessi caricare randomicamente da 3 a urls.length elementi, sconsigliabile ^^
  //   // this.extractRandomUrls(Math.floor(Math.random() * (this.urls.length - 2)) + 3)
  //   console.log(this.extractRandomUrls(3));
  // }

  private extractRandomUrls(n: any) {
    const shuffledUrls = this.shuffleArray(this.urls);
    return shuffledUrls.slice(0, n);
  }

  // Algoritmo di Fisher-Yates
  private shuffleArray(array: any[]) {
    for(let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }
}
