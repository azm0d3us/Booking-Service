import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor() { }

  autentica = (userid: string, password: string, user: User): boolean => {
    var retVal = (userid === user.username && password === user.password) ? true : false;

    if(retVal) {
      sessionStorage.setItem("Utente", user?.username!);
    }

    return retVal;
  }

  loggedUser = () : string | null => (sessionStorage.getItem("Utente"));
  isLogged = () : boolean => (sessionStorage.getItem("Utente") != null) ? true : false;
  clearAll = () : void => sessionStorage.clear();

}
