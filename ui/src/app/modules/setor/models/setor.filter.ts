import { Setor } from './setor';
import { FilterInterface } from '../../shared/models/filter-interface';
import { HttpParams } from '@angular/common/http';

export class SetorFilter extends Setor implements FilterInterface {

  // api
  page = 0;
  itensForPage = 4;
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
