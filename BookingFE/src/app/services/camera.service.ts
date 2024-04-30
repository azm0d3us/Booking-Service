import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Camera } from '../models/camera';

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
    this.camereUrl = this.urlBase + "camere";
    return this.http.get<Camera[]>(this.camereUrl);
   }

   public getById(id: any): Observable<Camera> {
    return this.http.get<Camera>(`http://localhost:8080/api/camere/camere/${id}`);
   }
}
