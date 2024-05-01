import { Foto } from "./foto";

export class User {
  id?: number;
  nome?: string;
  cognome?: string;
  email?: string;
  ddn?: Date;
  cf?: string;
  codDoc?: string;
  username?: string;
  password?: string;
  admin?: boolean;
  foto?: Foto;
}
