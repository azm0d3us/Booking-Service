import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username?: string;
  autenticato = false;
  errorMsg = "Spiacente, la userId o la password non sono corretti.";

  constructor() {
  }

}
