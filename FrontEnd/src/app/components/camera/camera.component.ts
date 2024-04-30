import { Component } from '@angular/core';
import { Camera } from '../../models/camera';
import { CameraService } from '../../services/camera.service';

@Component({
  selector: 'app-camera',
  templateUrl: './camera.component.html',
  styleUrl: './camera.component.css'
})
export class CameraComponent {

  camere?: Camera[];

  constructor(private cameraService: CameraService) {
  }

  ngOnInit(): void {
    this.cameraService.getAll().subscribe(data => {
      this.camere = data;
      console.log(data);
    })
  }

}
