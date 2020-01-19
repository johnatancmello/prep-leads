import { Pessoa } from '../../pessoa/models/pessoa';
import { Funcionario } from '../../funcionario/models/funcionarios';

export class Aluno {

  public id: number;
  public status: string;
  public pessoa: Pessoa;
  public funcionario: Funcionario;

  public static newAluno(): Aluno {
    const aluno = new Aluno();
    aluno.status = 'ATIVO';
    aluno.pessoa = Pessoa.newPessoa();
    return aluno;
  }

}
