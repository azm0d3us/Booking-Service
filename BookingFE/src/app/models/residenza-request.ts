export class ResidenzaRequest {
  nome?: string;
  indirizzo?: string;

  constructor(nome: string, indirizzo: string) {
    this.nome = nome;
    this.indirizzo = indirizzo;
  }
}
