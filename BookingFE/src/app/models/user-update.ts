export class UserUpdate {
  id?: number;
  nome?: string;
  cognome?: string;
  ddn?: Date;
  docCod?: string;
  cf?: string;

  constructor(id: number, nome: string, cognome: string, ddn: Date, docCod: string, cf: string) {
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.ddn = ddn;
    this.docCod = docCod;
    this.cf = cf;
  }
}
