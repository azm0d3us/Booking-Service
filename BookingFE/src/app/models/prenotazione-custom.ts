export class PrenotazioneCustom {
  idCamera?: number;
  idUser?: number;
  numPersone?: number;
  checkIn?: Date;
  checkOut?: Date;

  constructor(idCamera: number, idUser: number, numPersone: number, checkIn: Date, checkOut: Date){
    this.idCamera = idCamera;
    this.idUser = idUser;
    this.numPersone = numPersone;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }
}
