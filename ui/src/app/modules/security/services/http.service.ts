import { Injectable } from '@angular/core';
import { HttpParams, HttpHeaders, HttpHandler } from '@angular/common/http';

import { SegurancaHttpInterceptService } from './http-intercept.service';
import {SecurityOauthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SecutiryHttpService extends SegurancaHttpInterceptService {

  public headers = new HttpHeaders().append('Content-Type', 'application/json');

  constructor(
    public authService: SecurityOauthService,
    handler: HttpHandler
  ) {
    super(
      authService,
      handler
    );
  }

  public adicionar(object: any, location: string): Promise<any> {
    return super.post(this.getUrl(location), JSON.stringify(object), this.getHttpOptions(null))
      .toPromise()
      .then(response => response);
  }

  public alterar(object: any, location: string): Promise<any> {
    //const recurso = `/${id}`;
    return super.put(this.getUrl(location), JSON.stringify(object), this.getHttpOptions(null))
      .toPromise()
      .then(response => response);
  }

  public excluir(id: number, location: string): Promise<void> {
    const recurso = `/${id}`;
    return super.delete(this.getUrl(location) + recurso, this.getHttpOptions(null))
      .toPromise()
      .then(() => null);
  }

  public pesquisar(params: HttpParams, location: string): Promise<any> {
    return super.get(this.getUrl(location), this.getHttpOptions(params))
      .toPromise()
      .then(response => response);
  }

  private getHttpOptions(params) {
    if (params) {
      return {
        'headers': this.headers,
        params,
      };
    } else {
      return { 'headers': this.headers };
    }
  }
}
