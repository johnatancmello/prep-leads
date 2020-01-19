import { Pessoa } from '../../pessoa/models/pessoa';
import { Funcionario } from '../../funcionario/models/funcionarios';
import { Contato } from '../../contato/models/contatos';

export class Visita {

  public id: number;
  public status: string;
  public observacao: string;
  public dataDoAgendamento: Date;

  public pessoa: Pessoa;
  public funionario: Funcionario;
  public contato: Contato;

  public static newVisita(): Visita {
    const v = new Visita();
    return v;
  }
}
