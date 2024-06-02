import { Component } from '@angular/core';
import { CrossTestService } from '../../services/cross-test.service';
import { Camera } from '../../models/camera';
import { CameraService } from '../../services/camera.service';
import { ImmaginiService } from '../../services/immagini.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  camere: Camera[];
  urls: string[];
  imgRandom: string[];

  constructor(private cameraService: CameraService, private immaginiService: ImmaginiService) {
    this.urls = [];
    this.camere = [];
    this.imgRandom = [];
  }

  ngOnInit(): void {

    this.cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
        const requests = this.camere.map(camera => this.immaginiService.getByCamera(camera.idCamera));

        forkJoin(requests).subscribe({
          next: (results) => {
            results.forEach(urls => {
              if(urls) {
                urls.forEach((element: string | undefined) => {
                  if (element !== undefined) {
                    this.urls.push(element);
                  }
                });
              }
            });
            console.log("urls");
           console.log(this.urls);
           this.imgRandom = this.extractRandomUrls(3);
           console.log("test")
           console.log(this.imgRandom);
          },
          error: (error) => {
            console.error("errore ", error.message);
          }
        });
      },
      error: (error) => {
        console.error("Errore : ", error.message);
      }
    });
  }

  ngAfterContentInit(): void {
      this.imgRandom = this.extractRandomUrls(3);
      console.log(this.imgRandom);
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
