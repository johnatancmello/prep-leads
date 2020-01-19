import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHandler} from '@angular/common/http';
import { Observable, from } from 'rxjs';

import { environment } from '../../../../environments/environment';
import {filter, switchMap, take} from 'rxjs/operators';
import {SecurityOauthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SegurancaHttpInterceptService extends HttpClient {

  private next: HttpHandler;

  constructor(
    public oauthService: SecurityOauthService,
    handler: HttpHandler
  ) {
    super(handler);
    this.next = handler;
  }

  // Intercepcao
  public post(location, object, headers): Observable<any> {
    return this.doRequest(() => super.post(location, object, headers));
  }

  public put(location, object, headers): Observable<any> {
    return this.doRequest(() => super.put(location, object, headers));
  }

  public delete(location, headers): Observable<any> {
    return this.doRequest(() => super.delete(location, headers));
  }

  public get(location, headers): Observable<any> {
    return this.doRequest(() => super.get(location, headers));
  }
  // Intercepcao

  protected getUrl(location: string): string {
    return environment.protocolApi
      + '://' + environment.urlApi
      + ':' + environment.portApi
      + location;
  }

  private doRequest(fn: () => Observable<any>): Observable<any> {
    if (this.oauthService.isTokenInvalid()) {
      console.log('ATENÇÃO: Access token invalido para' + fn);
      return this.careRequest(fn);
    } else {
      console.log('ATENÇÃO: Token valido para ' + fn);
      return this.doAndObserver(fn);
    }
  }

  private doAndObserver(fn: () => Observable<any>) {
    return from(fn().toPromise()
      .catch( err => {
        if (err instanceof HttpErrorResponse && (err.status === 401)) {
          console.log('ATENÇÃO: Deu erro mas vamos recuperar ..');
          return this.careRequest(fn);
        }
        console.log('ERROR: NAO CONSEGUIU RECUPERAR');
        return err;
      })
    );
  }

  private careRequest(fn: () => Observable<any>): Observable<any> {

    if (!this.oauthService.refreshTokenInProgress) {
      this.oauthService.refreshTokenInProgress = true;
      this.oauthService.refreshTokenSubject.next(null);
      this.oauthService.getNewToken();
      console.log('ATENÇÃO: Pedindo token para ' + fn);
    }

    // Armazena as requisições
    return this.oauthService.refreshTokenSubject
      .pipe(
        filter(token => token != null)
        , take(1)
        , switchMap(token => {
          return from(fn());
        })
      );
  }

}
