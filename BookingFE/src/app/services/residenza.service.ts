import { ResidenzaRequest } from './../models/residenza-request';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Residenza } from '../models/residenza';

@Injectable({
  providedIn: 'root'
})
export class ResidenzaService {

  private residenzeUrl: string;
  private urlBase = "http://localhost:8080/api/residenze/";

  constructor(private http: HttpClient) {
    this.residenzeUrl = this.urlBase;
   }

   public getAll(): Observable<Residenza[]> {
    this.residenzeUrl = this.urlBase;
    this.residenzeUrl += "residenze";
    return this.http.get<Residenza[]>(this.residenzeUrl);
   }

   public add(residenza: ResidenzaRequest): Observable<any> {
    return this.http.post("http://localhost:8080/api/residenze/newResidenza", residenza);
   }

}
