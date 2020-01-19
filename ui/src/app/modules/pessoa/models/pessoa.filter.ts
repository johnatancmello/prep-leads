import { HttpParams } from '@angular/common/http';
import { Pessoa } from './pessoa';
import { FilterInterface } from '../../shared/models/filter-interface';

export class PessoaFilter extends Pessoa implements FilterInterface {

  page = 0;
  itensForPage = 7;
  totalRecords: number;
  totalPages: number;
  pagina: number;

  public getParams(): HttpParams {
    let params = new HttpParams();
    params = this.addParamPaginator(params);
    params = this.addParamId(params);
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
}
