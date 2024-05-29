import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'customCurrency'
})
export class CustomCurrencyPipe implements PipeTransform {

  transform(value: number, currencyCode: string = 'EUR', decimalLength: number = 2) {
    if(isNaN(value)) {
      return '';
    }
    const formattedValue = value.toFixed(decimalLength);
    return `${formattedValue} €`;
  }

}
