import { Injectable } from '@angular/core';
import { Visita } from '../models/visita';
import { HttpParams } from '@angular/common/http';
import {SecutiryHttpService} from '../../security/services/http.service';

@Injectable({
  providedIn: 'root'
})
export class VisitaService {

  private location = '/visitas';

  constructor(private http: SecutiryHttpService) { }

  adicionar(visita: Visita): Promise<any> {
    return this.http.adicionar(visita, this.location);
  }

  alterar(visita: Visita): Promise<any> {
    return this.http.alterar(visita, this.location);
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
