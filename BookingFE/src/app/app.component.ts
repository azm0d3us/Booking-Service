import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'BookingFE';

  /**
   * Visualizzo il componente DatePicker sempre, a meno che non mi trovi nella route della ricevuta
   */
  displayDatePicker: boolean = true;

  constructor(private router: Router) {
    this.router.events.subscribe( event => {
      if(event instanceof NavigationEnd) {
        const currentUrl = this.router.url;
        if(currentUrl.includes("/ricevuta")
        || currentUrl.includes("/nuova-struttura")
        || currentUrl.includes("prenotazione")
        || currentUrl.includes("visualizza-prenotazioni")) {
          this.displayDatePicker = false;
        } else {
          this.displayDatePicker = true;
        }
      }
    })
  }
}
