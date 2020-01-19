import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';

import { SegurancaHttpService } from '../../seguranca/services/http.service';
import { Funcionario } from 'src/app/modules/funcionario/models/funcionarios';


@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  private location = '/funcionarios';

  constructor(private http: SegurancaHttpService) { }

  adicionar(funcionario: Funcionario): Promise<any> {
    return this.http.adicionar(funcionario, this.location);
  }

  alterar(funcionario: Funcionario): Promise<any> {
    return this.http.alterar(funcionario, this.location);
  }

  excluir(id: number): Promise<void> {
    return this.http.excluir(id, this.location);
  }

  pesquisar(params: HttpParams): Promise<any> {
    return this.http.pesquisar(params, this.location);
  }

  pesquisarById(id: number): Promise<any> {
    return this.http.pesquisar(null, this.location + '/' + id);
  }

  pesquisarSummaryById(id: number): Promise<any> {
    return this.http.pesquisar(null, this.location + '/summary' + '/' + id);
  }

}
