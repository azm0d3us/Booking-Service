import { Component, } from '@angular/core';
import { NgbCalendar, NgbDate, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { CameraCustom } from '../../models/camera-custom';

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrl: './datepicker.component.css'
})
export class DatepickerComponent {

  checkIn?: Date;
  checkOut?: Date;
  camere?: CameraCustom;
  numAdulti = 1;
  numBambini = 0;
  numOspiti = this.numAdulti + this.numBambini;
  hoveredDate: NgbDate | null = null;
	fromDate: NgbDate | null = this.calendar.getToday();
	toDate: NgbDate | null = this.calendar.getNext(this.calendar.getToday(), 'd', 10);

  constructor(public calendar: NgbCalendar, public formatter: NgbDateParserFormatter, private router: Router) {}

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
    this.checkIn = new Date(this.fromDate?.year!, this.fromDate?.month! - 1, this.fromDate?.day);
    this.checkOut = new Date(this.toDate?.year!, this.toDate?.month! - 1, this.toDate?.day);
    this.numOspiti = this.numAdulti + this.numBambini;

    this.router.navigate(["/camereDisponibili"], { queryParams:{
      checkIn: JSON.stringify(this.checkIn),
      checkOut: JSON.stringify(this.checkOut),
      numAdulti: JSON.stringify(this.numAdulti),
      numBambini: JSON.stringify(this.numBambini),
      numOspiti: JSON.stringify(this.numOspiti)
    }});
  }
}
