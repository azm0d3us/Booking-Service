import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImmaginiService {

  private imgUrlBase = "http://localhost:8080/api/immagini/";

  constructor(private http: HttpClient) { }

  public getByCamera(idCamera: any): Observable<any> {
    let url = this.imgUrlBase + `camera/${idCamera}`;
    return this.http.get<string[]>(url);
  }

  public getByResidenza(idResidenza: any): Observable<any>{
    let url = this.imgUrlBase + `residenza/${idResidenza}`;
    return this.http.get<string[]>(url);
  }
}
