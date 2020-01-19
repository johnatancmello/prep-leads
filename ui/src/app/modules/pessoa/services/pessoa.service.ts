import { Injectable } from '@angular/core';
import { Pessoa } from '../models/pessoa';
import { HttpParams } from '@angular/common/http';
import {SecutiryHttpService} from '../../security/services/http.service';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private location = '/pessoas';

  constructor(private http: SecutiryHttpService) { }

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
