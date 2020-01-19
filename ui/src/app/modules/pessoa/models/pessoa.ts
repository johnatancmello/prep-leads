import { Imagem } from './imagem';
import { Telefone } from './telefone';
import { Aluno } from '../../aluno/models/aluno';
import { Interesse } from '../../interesse/models/interesse';

export class Pessoa {

  public id: number;
  public nome: string;
  public status: string;
  public localDoCadastro: string;
  public sexo: string;
  public idade: number;
  public dataDoCadastro: Date;
  public responsavel: Pessoa;
  public imagem: Imagem ;
  public telefones: Array<Telefone>;
  public interesses: Array<Interesse>;
  public ocupacao: string;
  public aluno: Aluno;

  public static newPessoa() {
    const p = new Pessoa();
    p.dataDoCadastro = new Date();
    p.status = 'ATIVO';
    p.localDoCadastro = 'PREPARA';
    p.telefones = new Array<Telefone>();
    return p;
  }

  // Obter Primeiro e ultimo nome
  public static getFirstAndLastName(fullName: string): string {
    let realeseName = '';
    if (fullName) {
      const names = fullName.split(' ');
      realeseName = this.getFirstName(names) + ' ' + this.getLastName(names);
    }
    return realeseName;
  }

  private static getFirstName(names: Array<string>): string {
    return (names[0]) ? names[0] : '';
  }

  private static  getLastName(names: Array<string>): string {
    return (names.length > 1 && names[names.length - 1]) ?
      names[names.length - 1] : '';
  }

  public isValid(): boolean {
    return this.isNomeValid()
      && this.isStatusValid()
      && this.isDataDoCadastroValid()
      && this.isLocalDoCadastroValid();
  }

  private isNomeValid(): boolean {
    return this.isStringValid(this.nome);
  }

  private isStatusValid(): boolean {
    return this.isStringValid(this.status);
  }

  private isLocalDoCadastroValid(): boolean {
    return this.isStringValid(this.localDoCadastro);
  }

  private isDataDoCadastroValid(): boolean {
    return this.dataDoCadastro !== null;
  }

  private isStringValid(text: string): boolean {
    return (text && text.length !== 0);
  }

}
