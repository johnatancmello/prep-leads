import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

import { Service } from '../service';
import { SharedSnackErrorComponent } from '../../components/snack/error/error.component';
import { SharedSnackSuccessComponent } from '../../components/snack/success/success.component';

@Injectable({
  providedIn: 'root'
})
export class SharedHandlerMensageService extends Service {

  constructor(
    protected snackBar: MatSnackBar,
  ) { super(snackBar); }

  public handle(response: any) {
    console.log(response);
    if (this.is4xx(response)) { this.errorApi(response); }
    else if (typeof response === 'string') { this.showError(response); }
    else { this.showError(this.getMsgErroPadrao()); }
  }

  public success(response: any) {
    if (this.is2xx(response)) { this.showSuccess(response); } // TODO: Arrumar
    else { this.showSuccess(response); }
  }

  private errorApi(errorResponse: any) {
    this.showError(this.getMsgAPI(errorResponse));
  }

  private showSuccess(msg: string) {
    this.openSnackBar(SharedSnackSuccessComponent, msg, 2);
  }

  private showError(msg: string) {
    this.openSnackBar(SharedSnackErrorComponent, msg, 8);
  }

  private getMsgAPI(errorResponse: any) {
    let msg = '';
    try {
      if (errorResponse.error instanceof Array) {
        errorResponse.error.forEach(element => {
          msg += element.mensagemUsuario + ' ';
        });
      } else {
        msg += errorResponse.error.error_description + ' ';
      }
    } catch (error) {
      console.log('error response!');
      msg = this.getMsgErroPadrao();
    }
    return msg;
  }

  private getMsgErroPadrao() {
    return 'Erro ao processar serviço remoto. Tente novamente ou atualize pagina.';
  }

  private is4xx(errorResponse: any) {
    return (errorResponse instanceof HttpErrorResponse)
      && (errorResponse.status >= 400) && (errorResponse.status <= 499);
  }

  private is2xx(errorResponse: any) {
    return (errorResponse instanceof HttpErrorResponse)
      && (errorResponse.status >= 200) && (errorResponse.status <= 299);
  }


}
