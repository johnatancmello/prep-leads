import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { JwtHelperService } from '@auth0/angular-jwt';

import { environment } from '../../../../environments/environment';
import {BehaviorSubject} from 'rxjs';
import {Usuario} from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class SecurityOauthService {

  private jwtPayload;

  public refreshTokenInProgress = false;
  public refreshTokenSubject = new BehaviorSubject<any>(null);
  public isDirty: boolean;


  constructor(
    public jwtHelper: JwtHelperService,
    private router: Router,
    private http: HttpClient,
  ) {
    this.carregarToken();
  }

  public login(usuario: Usuario): Promise<void> {
    const body = `username=${usuario.idUsuario}&password=${usuario.senha}&grant_type=password`;
    return this.autenticar(body);
  }

  public logout() {
    if (this.isTokenInvalid()) {
      this.getNewToken()
        .then(() => this.delete())
        .finally(() => this.close());
    } else { this.delete(); }
  }

  public getNewToken(): Promise<any> {
    this.refreshTokenInProgress = true;
    const body = 'grant_type=refresh_token';
    return ( this.autenticar(body)
        .then(response => {
          console.log('INFO: Novo access token obtido!');
          return response;
        })
        .catch(response => {
          this.close();
          console.log(response);
          console.log('ATENÇÃO: Sessão expirada!');
          return Promise.reject('Sessão expirada!');
        })
        .finally(() => {
          this.refreshTokenInProgress = false;
          this.refreshTokenSubject.next('OK');
        } )
    );
  }

  public isTokenInvalid(): boolean {
    const token = this.getTokenLocal();
    return !token || this.jwtHelper.isTokenExpired(token);
  }

  public hasToken(): boolean {
    return this.getTokenLocal();
  }

  public isLogged(): boolean {
    return this.jwtPayload ? true : false;
  }

  public getIdFuncionario(): number {
    return this.jwtPayload.id;
  }

  public getNomeFuncionario(): string {
    return this.jwtPayload.nome;
  }

  private autenticar(object: string): Promise<any> {
    const location = '/oauth/token';
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', environment.passUi);
    return this.http.post(this.getUrl(location), object, { headers, withCredentials: true })
      .toPromise()
      .then(response => {
        this.armazenarToken(this.findToken(response));
        return (this.isTokenInvalid()) ?
          Promise.reject('Token Invalido!')
          : Promise.resolve(response);
      });
  }

  private delete() {
    const location = '/oauth/revoke';
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', environment.passUi);
    return this.http.delete(this.getUrl(location), { headers, withCredentials: true })
      .toPromise()
      .finally(() => {
        this.close();
      });
  }

  public close() {
    this.limpar();
    this.goLogin();
  }

  private limpar() {
    this.isDirty = true;
    this.limparAccessToken();
  }

  private findToken(response): string {
    return response.access_token;
  }

  private getUrl(location: string): string {
    return environment.protocolApi
      + '://' + environment.urlApi
      + ':' + environment.portApi
      + location;
  }

  private limparAccessToken() {
    localStorage.removeItem('access_token');
    this.jwtPayload = null;
  }

  private armazenarToken(accessToken: string) {
    localStorage.setItem('access_token', accessToken);
    this.jwtPayload = this.jwtHelper.decodeToken(accessToken);
  }

  private carregarToken() {
    const token = this.getTokenLocal();
    if (token) { this.armazenarToken(token); }
  }

  private getTokenLocal(): any {
    return localStorage.getItem('access_token');
  }

  private goLogin() {
    this.router.navigate(['/login']);
  }

}
