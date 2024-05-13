import { Camera } from "./camera";
import { User } from "./user";

export class Prenotazione {
  idPrenotazione?: number;
  idPrePrenotazione?: number;
  numAdulti?: number;
  numBambini?: number;
  numPersone?: number;
  totale?: number;
  checkIn?: Date;
  checkOut?: Date;
  utente?: User;
  camera?: Camera;
}
