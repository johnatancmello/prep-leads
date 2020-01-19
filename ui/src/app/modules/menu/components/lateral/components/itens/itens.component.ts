import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Imagem } from '../../../../../pessoa/models/imagem';
import { SegurancaLoggedService } from 'src/app/modules/seguranca/services/logged.service';
import { SecurityOauthService } from 'src/app/modules/seguranca/services/auth.service';
import { Router } from '@angular/router';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';

@Component({
  selector: 'app-menu-lateral-itens',
  templateUrl: './itens.component.html',
  styleUrls: ['./itens.component.css']
})
export class MenuLateralItensComponent implements OnInit {

  @Output() closeLateral = new EventEmitter();

  public imagem;

  constructor(
    private logged: SegurancaLoggedService,
    private auth: SecurityOauthService,
    private router: Router,
    private handlerMensage: SharedHandlerMensageService,
  ) { }

  ngOnInit() {
    this.loadImagem();
  }

  public getImagem(): string {
    return Imagem.getImagemBase64(this.imagem, null);
  }

  public logout() {
    this.logged.logout();
    this.auth.logout();
    this.closeLateral.emit();
  }

  public openFullscreen() {
    document.documentElement.requestFullscreen();
  }

  private loadImagem() {
    const funcionario = this.logged.getFuncionario()
      .then(response => {
        if (response.pessoa.imagem) {
          this.imagem = response.pessoa.imagem;
        }
      })
      .catch(response => {
        this.imagem = null;
        this.closeLateral.emit();
        this.handlerMensage.handle(response);
      });
  }


}
