import { Injectable } from '@angular/core';

import { HttpParams } from '@angular/common/http';
import { Interesse } from '../models/interesse';
import {SecutiryHttpService} from '../../security/services/http.service';


@Injectable({
  providedIn: 'root'
})
export class InteresseService {

  private location = '/interesses';

  constructor(private http: SecutiryHttpService) { }

  adicionar(interesse: Interesse): Promise<any> {
    return this.http.adicionar(interesse, this.location);
  }

  alterar(interesse: Interesse): Promise<any> {
    return this.http.alterar(interesse, this.location);
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
