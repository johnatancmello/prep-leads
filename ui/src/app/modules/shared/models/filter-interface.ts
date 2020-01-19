import { HttpParams } from '@angular/common/http';

export interface FilterInterface {

  // api
  page: number;
  itensForPage: number;
  totalRecords: number;
  totalPages: number;
  pagina: number;

  getParams(): HttpParams;
}
