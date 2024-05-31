import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timestampToDate'
})
export class TimestampToDatePipe implements PipeTransform {

  transform(value: any): string {
    if(!value) {
      return '';
    }
    let timestamp = Number(value);
    let date = new Date(timestamp);

    let day = date.getDate().toString().padStart(2, '0');
    let month = (date.getMonth() + 1 ).toString().padStart(2, '0');
    let year = date.getFullYear();

    return `${day}/${month}/${year}`;
  }

}
