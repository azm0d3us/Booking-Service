import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prenotazione } from '../models/prenotazione';
import { PrenotazioneCustom } from '../models/prenotazione-custom';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PrePrenotazioneService {

  constructor(private http: HttpClient) { }

  public prenota(prenotazione: PrenotazioneCustom): Observable<Prenotazione>{
    return this.http.put<Prenotazione>("http://localhost:8080/api/pre-prenotazione/new", prenotazione);
   }

   public delete(idPrePrenotazione: number): Observable<any>{
    return this.http.delete<Observable<any>>(`http://localhost:8080/api/pre-prenotazione/delete/${idPrePrenotazione}`);

   }
}
