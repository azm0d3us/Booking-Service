import { CameraService } from '../../services/camera.service';
import { Camera } from './../../models/camera';
import { Component } from '@angular/core';

@Component({
  selector: 'app-camera-singola',
  templateUrl: './camera-singola.component.html',
  styleUrl: './camera-singola.component.css'
})
export class CameraSingolaComponent {

  camera?: Camera;

  constructor(private cameraService: CameraService) {
  }

  ngOnInit(): void {
    this.cameraService.getById(1).subscribe(data => {
      this.camera = data;
      console.log(data);
    })
  }

}
