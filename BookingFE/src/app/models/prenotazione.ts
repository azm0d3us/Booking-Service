import { Camera } from "./camera";
import { User } from "./user";

export class Prenotazione {
  idPrenotazione?: number;
  numPersone?: number;
  totale?: number;
  checkIn?: Date;
  checkOut?: Date;
  utente?: User;
  camera?: Camera;
}
