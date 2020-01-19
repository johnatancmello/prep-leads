import { Permissao } from '../../seguranca/models/permissao';

export class Usuario {
  public idUsuario: string;
  public senha: string;
  public permissoes: Array<Permissao>;

  public isValid(): boolean {
    return this.isIdUsuarioValid()
      && this.isSenhaValid();
  }

  private isIdUsuarioValid(): boolean {
    return this.isStringValid(this.idUsuario);
  }

  private isSenhaValid(): boolean {
    return this.isStringValid(this.senha);
  }

  private isStringValid(text: string): boolean {
    return (text && text.length !== 0);
  }
}
