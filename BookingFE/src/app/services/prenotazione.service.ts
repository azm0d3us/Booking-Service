import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CameraCustom } from '../models/camera-custom';
import { Observable } from 'rxjs';
import { DateCustom } from '../models/date-custom';

@Injectable({
  providedIn: 'root'
})
export class PrenotazioneService {

  private prenotazioneUrl: string;

  constructor(private http: HttpClient) {
    this.prenotazioneUrl = "http://localhost:8080/api/prenotazioni/"
   }
}
