export class DateCustom {
  checkIn?: Date;
  checkOut?: Date;

  constructor(checkIn: Date, checkOut: Date) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }
}
