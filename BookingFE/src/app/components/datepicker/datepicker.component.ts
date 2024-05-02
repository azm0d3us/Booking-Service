import { Component, } from '@angular/core';
import { NgbCalendar, NgbDate, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { DateCustom } from '../../models/date-custom';
import { Router } from '@angular/router';
import { CameraService } from '../../services/camera.service';
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

  constructor(public calendar: NgbCalendar, public formatter: NgbDateParserFormatter, public cameraService: CameraService, private router: Router) {}


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
    this.checkIn = new Date(this.fromDate?.year!, this.fromDate?.month! - 1, this.fromDate?.day);
    this.checkOut = new Date(this.toDate?.year!, this.toDate?.month! - 1, this.toDate?.day);  //le date maledettissime
    // console.log(this.checkIn.toJSON())
    // console.log(this.checkOut.toJSON())
    this.cameraService.getDisponibili(new DateCustom(this.checkIn, this.checkOut)).subscribe({
      next: (data) => {
        this.camere = data;
        console.log(this.camere);
        this.router.navigate(["/camereDisponibili"], { queryParams: { camere: JSON.stringify(this.camere)}});
      },
      error: (e) => {
        console.error("Errore durante la richiesta HTTP: ", e.message);
      }
    })
  }
}
