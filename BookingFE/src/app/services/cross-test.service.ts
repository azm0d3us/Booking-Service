import { Injectable } from '@angular/core';
import { CameraService } from './camera.service';
import { Camera } from '../models/camera';
import { ImmaginiService } from './immagini.service';
import { BehaviorSubject, Observable, forkJoin } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CrossTestService {
  private camere: Camera[] = [];
  private urls: string[] = [];
  private test: BehaviorSubject<string[]> = new BehaviorSubject<string[]>([]);

  constructor(
    private cameraService: CameraService,
    private immaginiService: ImmaginiService
  ) {
    this.init();
  }

  private init() {
    this.cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
        const requests = this.camere.map((camera) =>
          this.immaginiService.getByCamera(camera.idCamera)
        );
        forkJoin(requests).subscribe({
          next: (results) => {
            results.forEach((urls) => {
              if (urls) {
                urls.forEach((element: string | undefined) => {
                  if (element !== undefined) {
                    this.urls.push(element);
                  }
                });
              }
            });
            this.test.next(this.extractRandomUrls(3));
          },
          error: (error) => {
            console.error('errore ', error.message);
          },
        });
      },
      error: (error) => {
        console.error('Errore : ', error.message);
      },
    });
  }

  public getTest(): Observable<string[]> {
    return this.test.asObservable();
  }


  private extractRandomUrls(n: any) {
    const shuffledUrls = this.shuffleArray(this.urls!);
    return shuffledUrls.slice(0, n);
  }

  // Algoritmo di Fisher-Yates
  private shuffleArray(array: string[]) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }
}
