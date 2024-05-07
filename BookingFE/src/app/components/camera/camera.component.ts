import { Component } from '@angular/core';
import { Camera } from '../../models/camera';
import { CameraService } from '../../services/camera.service';
import { ImmaginiService } from '../../services/immagini.service';

@Component({
  selector: 'app-camera',
  templateUrl: './camera.component.html',
  styleUrl: './camera.component.css'
})
export class CameraComponent {

  camere?: Camera[];
  immagini?: string[];

  constructor(private cameraService: CameraService, private immaginiService: ImmaginiService) {
  }

  ngOnInit(): void {
    this.cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
        console.log(data);
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

  test(idCamera: any) {
    this.immaginiService.getByCamera(idCamera).subscribe({
      next: (data) => {
        this.immagini = data;
        console.log(data);
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }
}
