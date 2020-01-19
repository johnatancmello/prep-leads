import { HttpParams } from '@angular/common/http';

import { Imagem } from './imagem';
import { FilterInterface } from '../../shared/models/filter-interface';

export class ImagemFilter extends Imagem implements FilterInterface {

  // api
  page = 0;
  itensForPage = 3;
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
