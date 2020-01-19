import { Usuario } from '../../funcionario/models/usuario';

export class Permissao {
  public id: number;
  public descricao: string;
  public usuarios: Array<Usuario>;
}
