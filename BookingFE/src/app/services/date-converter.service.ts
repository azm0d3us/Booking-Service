import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateConverterService {

  constructor() { }

  public dateConverter(data: any) {
    let timestamp = data;
    let date = new Date(timestamp);

    let year = date.getFullYear();
    let month = (date.getMonth() + 1).toString().padStart(2, '0'); // getMonth() returns a zero-based value
    let day = date.getDate().toString().padStart(2, '0');

    let formattedDate = `${year}-${month}-${day}`;
    return formattedDate;
  }
}
