import { Component } from '@angular/core';
import { AuthorizationService } from '../../services/authorization.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {

  constructor(private basicAuth: AuthorizationService, private router: Router) {}

  ngOnInit(): void {
    this.basicAuth.clearAll();
    this.router.navigate(['login']);
  }

}
