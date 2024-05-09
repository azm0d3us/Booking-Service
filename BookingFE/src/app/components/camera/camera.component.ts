import { Component } from '@angular/core';
import { Camera } from '../../models/camera';
import { CameraService } from '../../services/camera.service';
import { ImmaginiService } from '../../services/immagini.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-camera',
  templateUrl: './camera.component.html',
  styleUrl: './camera.component.css'
})
export class CameraComponent {

  camere?: Camera[];

  constructor(
    private cameraService: CameraService,
    private immaginiService: ImmaginiService,
    private route: Router
    ) {  }

  ngOnInit(): void {
    this.cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
        console.log(this.camere);
        this.camere.forEach(camera => {
          this.test(camera.idCamera);
        })
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

  test(idCamera: any) {
    this.immaginiService.getByCamera(idCamera).subscribe({
      next: (data) => {
        const camera = this.camere?.find(camera => camera.idCamera === idCamera);
        if(camera) {
          if(Array.isArray(data) && data.length === 0) {
            camera.urlImmagini = ["/assets/images/camere/nonPhoto.jpg"]
          } else {
            camera.urlImmagini = data;
            console.log(data);
          }
        }
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    });
  }

  onMouseEnter() {
    console.log("Something to do here");
  }

  onMouseLeave() {
    console.log("Something to do here as well.");
  }

  goToCamera(idCamera: any) {
    this.route.navigate(['camera-singola'], { queryParams: {
      idCamera: JSON.stringify(idCamera)
    }});
  }
}
