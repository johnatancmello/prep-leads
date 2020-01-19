import { Injectable } from '@angular/core';
import { SegurancaHttpService } from '../../seguranca/services/http.service';
import { Pessoa } from '../models/pessoa';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private location = '/pessoas';

  constructor(private http: SegurancaHttpService) { }

  adicionar(pessoa: Pessoa): Promise<any> {
    return this.http.adicionar(pessoa, this.location);
  }

  alterar(pessoa: Pessoa): Promise<any> {
    return this.http.alterar(pessoa, this.location);
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
}
