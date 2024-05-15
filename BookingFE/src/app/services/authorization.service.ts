import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor() { }

  //Commented for debugging §
  admin: boolean = false;
  // admin = true;

  autentica = (userid: string, password: string, user: User): boolean => {
    var retVal = (userid === user.username && password === user.password) ? true : false;
    //Commented for debugging §
    user.admin ? this.admin = true : this.admin = false;
    if(retVal) {
      sessionStorage.setItem("Utente", user?.username!);
    }

    return retVal;
  }

  isAdmin = () : boolean => this.admin;
  loggedUser = () : string | null => (sessionStorage.getItem("Utente"));
  isLogged = () : boolean => (sessionStorage.getItem("Utente") != null) ? true : false;
  clearAll = () : void => sessionStorage.clear();

}
