import { Injectable } from '@angular/core';
import { CameraService } from './camera.service';
import { Camera } from '../models/camera';
import { ImmaginiService } from './immagini.service';

@Injectable({
  providedIn: 'root'
})
export class CrossTestService {

  public camere: Camera[] = [];
  public urls: string[] = [];

  constructor(private cameraService: CameraService, private immaginiService: ImmaginiService) {
    this.cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
        this.camere.forEach(camera => {
          this.test(camera.idCamera);
          console.log(this.camere);
          console.log(this.urls);
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
}
