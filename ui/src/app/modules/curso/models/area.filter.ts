import { HttpParams } from '@angular/common/http';

import { FilterInterface } from '../../shared/models/filter-interface';
import { Area } from './area';

export class AreaFilter extends Area implements FilterInterface {
  // api
  page = 0;
  itensForPage = 20;
  totalRecords: number;
  totalPages: number;
  pagina: number;

  // atributos
  public nomeAreaPrecedente;

  public getParams(): HttpParams {
    let params = new HttpParams();
    params = this.addParamPaginator(params);
    params = this.addParamId(params);
    params = this.addnomeAreaPrecedente(params);
    return params;
  }

  private addParamPaginator(params: HttpParams) {
    params = params.set('page', this.page.toString());
    params = params.set('size', this.itensForPage.toString());
    return params;
  }

  private addParamId(params: HttpParams) {
    if (this.id) {
      params = params.set('id', this.id.toString());
    }
    return params;
  }

  private addnomeAreaPrecedente(params: HttpParams) {
    if (this.nomeAreaPrecedente) {
      params = params.set('nomeAreaPrecedente', this.nomeAreaPrecedente);
    }
    return params;
  }
}
