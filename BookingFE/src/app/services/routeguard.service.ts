import { Injectable } from '@angular/core';
import { AuthorizationService } from './authorization.service';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteguardService {

  constructor(private basicAuth: AuthorizationService, private route: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(!this.basicAuth.isLogged()) {
      this.route.navigate(['login']);
      return false;
    } else {
      return true;
    }
  }
}
