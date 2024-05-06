import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Camera } from '../models/camera';
import { DateCustom } from '../models/date-custom';
import { CameraCustom } from '../models/camera-custom';

@Injectable({
  providedIn: 'root'
})
export class CameraService {

  private camereUrl: string;
  private urlBase = "http://localhost:8080/api/camere/";

  constructor(private http: HttpClient) {
    this.camereUrl = this.urlBase;
   }

   public getAll(): Observable<Camera[]> {
    let url = this.urlBase + "camere";
    return this.http.get<Camera[]>(url);
   }

   public getById(id: any): Observable<Camera> {
    return this.http.get<Camera>(`http://localhost:8080/api/camere/camere/${id}`);
   }

   public getDisponibili(date: DateCustom): Observable<CameraCustom> {
    return this.http.post<CameraCustom>("http://localhost:8080/api/camere/getDisponibili", date);
   }
}
