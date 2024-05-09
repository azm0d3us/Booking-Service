import { Component } from '@angular/core';
import { CrossTestService } from '../../services/cross-test.service';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {

  urls: string[] = [];
  shuffledUrls: string[] = [];

  constructor(private crossService: CrossTestService, ) {
    this.urls = this.crossService.getUrls();
    this.shuffledUrls = this.extractRandomUrls(3);
  }

  ngOnInit(): void {
    console.log(this.shuffledUrls)
  }

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
