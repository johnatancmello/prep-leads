import { Interesse } from './interesse';
import { HttpParams } from '@angular/common/http';
import { FilterInterface } from '../../shared/models/filter-interface';

export class InteresseFilter extends Interesse implements FilterInterface {

  // api
  page = 0;
  itensForPage = 7;
  totalRecords: number;
  totalPages: number;
  pagina: number;

  // Atributos
  idPessoa: number;

  public getParams(): HttpParams {
    let params = new HttpParams();
    params = this.addParamPaginator(params);
    params = this.addParamId(params);
    params = this.addParamIdPessoa(params);
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

  private addParamIdPessoa(params: HttpParams) {
    if (this.idPessoa) {
      params = params.set('idPessoa', this.idPessoa.toString());
    }
    return params;
  }

}
