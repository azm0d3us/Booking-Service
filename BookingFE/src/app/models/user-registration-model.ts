export class UserRegistrationModel {
  nome?: string;
  email?: string;
  username?: string;
  password?: string;

  constructor(nome: string, email: string, username: string, password: string) {
    this.nome = nome;
    this.email = email;
    this.username = username;
    this.password = password;
  }
}
