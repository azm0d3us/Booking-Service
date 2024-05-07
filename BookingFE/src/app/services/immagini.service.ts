import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImmaginiService {

  private imgCameraUrlBase = "http://localhost:8080/api/immagini/"

  constructor(private http: HttpClient) { }

  public getByCamera(idCamera: any): Observable<string[]> {
    let url = this.imgCameraUrlBase + `camera/${idCamera}`;
    return this.http.get<string[]>(url);
  }
}
