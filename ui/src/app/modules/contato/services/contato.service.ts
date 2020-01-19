import { Injectable } from '@angular/core';
import { SegurancaHttpService } from '../../seguranca/services/http.service';
import { HttpParams } from '@angular/common/http';
import { Contato } from '../models/contatos';

@Injectable({
  providedIn: 'root'
})
export class ContatoService {

  private location = '/contatos';

  constructor(private http: SegurancaHttpService) { }

  adicionar(contato: Contato): Promise<any> {
    return this.http.adicionar(contato, this.location);
  }

  alterar(contato: Contato): Promise<any> {
    return this.http.alterar(contato, this.location);
  }

  excluir(id: number): Promise<void> {
    return this.http.excluir(id, this.location);
  }

  pesquisar(params: HttpParams): Promise<any> {
    return this.http.pesquisar(params, this.location);
  }

  pesquisarInSummary(params: HttpParams): Promise<any> {
    return this.http.pesquisar(params, this.location + '/summary');
  }

  pesquisarById(id: number): Promise<any> {
    return this.http.pesquisar(null, this.location + '/' + id);
  }
}

