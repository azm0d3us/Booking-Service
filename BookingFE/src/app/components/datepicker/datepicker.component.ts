import { Component, inject } from '@angular/core';
import { NgbCalendar, NgbDate, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { PrenotazioneService } from '../../services/prenotazione.service';
import { DateCustom } from '../../models/date-custom';
import { Router } from '@angular/router';

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrl: './datepicker.component.css'
})
export class DatepickerComponent {

  checkIn?: Date;
  checkOut?: Date;
  alloggi?: any;

  constructor(public calendar: NgbCalendar, public formatter: NgbDateParserFormatter, public prenotazioneService: PrenotazioneService, private router: Router) {}


	hoveredDate: NgbDate | null = null;
	fromDate: NgbDate | null = this.calendar.getToday();
	toDate: NgbDate | null = this.calendar.getNext(this.calendar.getToday(), 'd', 10);

	onDateSelection(date: NgbDate) {
		if (!this.fromDate && !this.toDate) {
			this.fromDate = date;
		} else if (this.fromDate && !this.toDate && date && date.after(this.fromDate)) {
			this.toDate = date;
		} else {
			this.toDate = null;
			this.fromDate = date;
		}
	}

	isHovered(date: NgbDate) {
		return (
			this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate)
		);
	}

	isInside(date: NgbDate) {
		return this.toDate && date.after(this.fromDate) && date.before(this.toDate);
	}

	isRange(date: NgbDate) {
		return (
			date.equals(this.fromDate) ||
			(this.toDate && date.equals(this.toDate)) ||
			this.isInside(date) ||
			this.isHovered(date)
		);
	}

	validateInput(currentValue: NgbDate | null, input: string): NgbDate | null {
		const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
	}

  trovaAlloggi() {
    this.checkIn = new Date(this.fromDate?.year!, this.fromDate?.month!, this.fromDate?.day);
    this.checkOut = new Date(this.toDate?.year!, this.toDate?.month!, this.toDate?.day);
    this.prenotazioneService.getDisponibili(new DateCustom(this.checkIn, this.checkOut)).subscribe( data => {
      this.alloggi = data;
      console.log(this.alloggi);
      this.router.navigate(["/camereDisponibili"], { queryParams: { alloggi: JSON.stringify(this.alloggi) } });
    }, error => {
      console.error("Errore durante la richiesta HTTP: ", error.message);
    });

  }

}
