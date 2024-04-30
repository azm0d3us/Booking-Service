import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl: string;

  constructor(private http: HttpClient) {
    this.userUrl = "http://localhost:8080/api/utenti/";
  }

  public getUser(): Observable<User> {
    this.userUrl += "login";
    return this.http.get<User>(this.userUrl);
  }
}
