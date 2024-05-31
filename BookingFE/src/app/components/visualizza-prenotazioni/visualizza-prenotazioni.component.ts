import { Component } from '@angular/core';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { DateConverterService } from '../../services/date-converter.service';

@Component({
  selector: 'app-visualizza-prenotazioni',
  templateUrl: './visualizza-prenotazioni.component.html',
  styleUrl: './visualizza-prenotazioni.component.css'
})
export class VisualizzaPrenotazioniComponent {

  prenotazioni: any[] = [];

  sortDirection: { [key: string]: boolean } = {
    'camera': true,
    'utente': true,
    'checkIn': true,
    'checkOut': true
  };

  constructor(private prenotazioneService: PrenotazioneService, public dateConverter: DateConverterService) {}

  ngOnInit() {
    this.prenotazioneService.getAll().subscribe({
      next: (data) => {
        console.log(data);
        this.prenotazioni = data;
      },
      error: (e) => {
        console.error("Errore durante richiesta HTTP: ", e.message);
      }
    })
  }

  sortData(column: string) {
    const direction = this.sortDirection[column] ? 1 : -1;
    this.prenotazioni.sort((a, b) => {
      if (column === 'camera') {
        return (a.camera.numero > b.camera.numero ? 1 : -1) * direction;
      } else if (column === 'utente') {
        const fullNameA = `${a.utente.nome} ${a.utente.cognome}`;
        const fullNameB = `${b.utente.nome} ${b.utente.cognome}`;
        return (fullNameA > fullNameB ? 1 : -1) * direction;
      } else if (column === 'checkIn' || column === 'checkOut') {
        return (new Date(a[column]).getTime() - new Date(b[column]).getTime()) * direction;
      }
      return 0;
    });
    this.sortDirection[column] = !this.sortDirection[column];
  }

  goToPrenotazione(id: any) {
    console.log(id);
  }

}
