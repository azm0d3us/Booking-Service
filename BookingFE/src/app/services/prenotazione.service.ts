import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PrenotazioneCustom } from '../models/prenotazione-custom';
import { Prenotazione } from '../models/prenotazione';

@Injectable({
  providedIn: 'root'
})
export class PrenotazioneService {

  private prenotazioneUrl: string;

  constructor(private http: HttpClient) {
    this.prenotazioneUrl = "http://localhost:8080/api/prenotazioni/"
   }

   public getAll(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/api/prenotazioni/prenotazioni");
   }

   public getById(id: any): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/prenotazioni/prenotazioni/${id}`);
   }

   public prenota(prenotazione: PrenotazioneCustom): Observable<Prenotazione>{
    return this.http.post<Prenotazione>("http://localhost:8080/api/prenotazioni/nuovaPrenotazione", prenotazione);
   }

}
