import { Injectable } from '@angular/core';
import { CameraService } from './camera.service';
import { Camera } from '../models/camera';
import { ImmaginiService } from './immagini.service';

@Injectable({
  providedIn: 'root'
})
export class CrossTestService {

  private camere: Camera[] = [];
  private urls: string[] = [];

  constructor(private cameraService: CameraService, private immaginiService: ImmaginiService) {
    this.cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
        this.camere.forEach(camera => {
          this.test(camera.idCamera);
          // console.log(this.camere);
          // console.log(this.urls);
        })

      }
    });
  }

  test(idCamera: any) {
    this.immaginiService.getByCamera(idCamera).subscribe({
      next: (data) => {
        const camera = this.camere?.find(camera => camera.idCamera === idCamera);
        if(camera) {
          if(Array.isArray(data) && data.length != 0) {
            data.forEach(element => {
              this.urls.push(element);
            });
          }
        }
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

  public getUrls() {
    return this.urls;
  }

  public getCamere() {
    return this.camere;
  }

  public getShuffled(n: any) {
    return this.extractRandomUrls(n);
  }

  private extractRandomUrls(n: any) {
    const shuffledUrls = this.shuffleArray(this.urls!);
    return shuffledUrls.slice(0, n);
  }

  // Algoritmo di Fisher-Yates
  private shuffleArray(array: string[]) {
    for(let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }
}
