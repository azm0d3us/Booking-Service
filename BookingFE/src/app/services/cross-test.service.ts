import { Injectable } from '@angular/core';
import { CameraService } from './camera.service';
import { Camera } from '../models/camera';

@Injectable({
  providedIn: 'root'
})
export class CrossTestService {

  public camere: Camera[] = [];
  constructor(private cameraService: CameraService) {
    cameraService.getAll().subscribe({
      next: (data) => {
        this.camere = data;
      }
    })
  }
}
