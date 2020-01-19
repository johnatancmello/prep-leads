import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Usuario} from '../../../models/usuario';
import {MatInput} from '@angular/material';
import {Router} from '@angular/router';
import {SecurityOauthService} from '../../../services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {SharedHandlerMensageService} from '../../../../shared/services/error/handler-mensage.service';
import {SegurancaStrings} from '../../../strings/string';

@Component({
  selector: 'app-security-login-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class SecurityLoginFormComponent implements OnInit {

  @ViewChild('usuarioInput', { static: true }) usuarioInputGeneric: ElementRef;
  @ViewChild('senhaInput', { static: true }) senhaInputGeneric: ElementRef;

  public classCSS = 'bounceInLeft';
  public readonly = false;
  public disable = false;

  private usuario = new Usuario();
  private usuarioInput: MatInput;
  private senhaInput: MatInput;

  constructor(
    private router: Router,
    private oauthService: SecurityOauthService,
    private handlerMensage: SharedHandlerMensageService,
  ) { }

  ngOnInit() {
    this.usuarioInput = this.usuarioInputGeneric.nativeElement;
    this.senhaInput = this.senhaInputGeneric.nativeElement;
    this.init();
  }

  logar() {
    this.readonly = true;
    this.disable = true;
    this.oauthService.login(this.usuario)
      .then(() => {
        this.animarEEntrar();
      })
      .catch((response: HttpErrorResponse) => {
        this.tratarError(response);
        this.disable = false;
      }).finally(() => this.readonly = false);
  }

  public getSaudacaoLogin(): string {
    let msg: string;
    if (this.oauthService.isDirty) {
      msg = 'Volte logo!';
    } else {
      msg = 'Vamos lá?';
    }
    return msg;
  }

  private tratarError(response) {
    if (response.status === 400) {
      this.senhaInvalid();
    } else if (response.status === 401) {
      this.usuarioInvalid();
    } else {
      this.handlerMensage.handle(response);
    }
  }

  private senhaInvalid() {
    this.handlerMensage.handle(
      SegurancaStrings.MESSAGE_DATA_INVALID + ' ' +
      SegurancaStrings.MESSAGE_SENHA_INVALID
    );
    this.senhaInput.value = '';
  }

  private usuarioInvalid() {
    this.handlerMensage.handle(
      SegurancaStrings.MESSAGE_DATA_INVALID + ' ' + SegurancaStrings.MESSAGE_USUARIO_INVALID
    );
    this.usuarioInput.focus();
    this.senhaInput.value = '';
  }

  private init() {
    if (this.oauthService.hasToken()) {
      if (this.oauthService.isTokenInvalid()) {
        this.getNewAccessToken();
      } else {
        this.redirect();
      }
    } else {
      this.usuarioInput.focus();
    }
  }

  private getNewAccessToken() {
    this.oauthService.getNewToken()
      .then(response => {
        if (!this.oauthService.isTokenInvalid()) {
          this.redirect();
        }
      })
      .catch(response => {
        this.usuarioInput.focus();
        // this.handlerMensage.handle(response);
      });
  }

  private cleanInputs() {
    this.usuario.idUsuario = '';
    this.usuario.senha = '';
  }

  private animarEEntrar() {
    this.classCSS = 'bounceOut';
    setTimeout(() => {
      this.cleanInputs();
      this.redirect();
    }, 1000);
  }

  private redirect() {
    this.router.navigate(['/alunos']);
  }
}
