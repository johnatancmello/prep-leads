import { Funcionario } from '../../funcionario/models/funcionarios';
import { Modulo } from '../../curso/models/modulo';
import { Pessoa } from '../../pessoa/models/pessoa';
import { InteresseStatus } from './interesse.status';

export class Interesse {

  public id: number;
  public status: string;
  public tipo: string;
  public dataDoCadastro: Date;
  public pessoa: Pessoa;
  public funcionario: Funcionario;
  public modulos: Array<Modulo>;
  // TODO: Arrumar
  // public modulos: Array<Modulo>;

  public static newInteresse(): Interesse {
    const i = new Interesse();
    i.status = InteresseStatus.PENDENTE;
    i.dataDoCadastro = new Date();
    i.modulos = new Array<Modulo>();
    return i;
  }
}
