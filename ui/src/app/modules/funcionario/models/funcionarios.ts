import { Usuario } from './usuario';
import { Pessoa } from '../../pessoa/models/pessoa';
import { Setor } from '../../setor/models/setor';
import { Meta } from '@angular/platform-browser';

export class Funcionario {
  public id: number;
  public status: string;
  public cargaHoraria: number;
  public usuario: Usuario;
  public pessoa: Pessoa;
  public setor: Setor;
  public metas: Array<Meta>;

  public static newFuncionario(): Funcionario {
    const f = new Funcionario();
    f.pessoa = Pessoa.newPessoa();
    f.status = 'ATIVO';
    f.usuario = new Usuario();
    f.setor = new Setor();
    return f;
  }

  public isValid(): boolean {
    return this.isStatusValid();
  }

  private isStatusValid(): boolean {
    return this.isStringValid(this.status);
  }

  private isStringValid(text: string): boolean {
    return (text && text.length !== 0);
  }

}
