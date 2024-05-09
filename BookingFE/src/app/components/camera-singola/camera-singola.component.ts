import { ActivatedRoute } from '@angular/router';
import { CameraService } from '../../services/camera.service';
import { Camera } from './../../models/camera';
import { Component } from '@angular/core';
import { ImmaginiService } from '../../services/immagini.service';

@Component({
  selector: 'app-camera-singola',
  templateUrl: './camera-singola.component.html',
  styleUrl: './camera-singola.component.css'
})
export class CameraSingolaComponent {

  camera: Camera = new Camera();
  urls: string[] = [];
  idCamera: any;

  constructor(private cameraService: CameraService, private immaginiService: ImmaginiService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        if(params['idCamera']){
          this.idCamera = JSON.parse(params['idCamera']);
        }
        this.cameraService.getById(this.idCamera).subscribe(data => {
        this.camera = data;
        //test
        this.immaginiService.getByCamera(this.camera.idCamera).subscribe({
          next: (data) => {
            // console.log(data);
            this.urls = data;
            console.log(this.camera);
            console.log(this.urls);
          }
        })
    })
      }
    })

  }

}
