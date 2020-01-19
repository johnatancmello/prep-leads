import { Pessoa } from '../../pessoa/models/pessoa';
import { Funcionario } from '../../funcionario/models/funcionarios';
import { Interesse } from '../../interesse/models/interesse';
import { Visita } from '../../visita/models/visita';

export class Contato {
  public id: number;
  public status: string;
  public observacao: string;
  public dataDoContato: Date;
  public pessoa: Pessoa;
  public funcionario: Funcionario;
  public interesse: Interesse;
  public visita: Visita;

  public static newContato() {
    const c = new Contato();
    c.dataDoContato = new Date();
    c.visita = Visita.newVisita();
    return c;
  }
}
