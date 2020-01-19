import { Component, OnInit } from '@angular/core';

import { SecurityOauthService } from 'src/app/modules/seguranca/services/auth.service';

@Component({
  selector: 'app-menu-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MenuMainComponent implements OnInit {

  // Animacoes
  ANIMACAO_SAIDA_LATERAL = 'fadeOutLeftBig fast';
  ANIMACAO_ENTRADA_LATERAL = 'fadeInLeftBig faster';
  ANIMACAO_SAIDA_TOP = 'fadeOutUpBig';
  ANIMACAO_ENTRADA_TOP = 'fadeInDownBig faster';
  ANIMACAO_SAIDA_CONTRA = 'fadeOut';
  ANIMACAO_ENTRADA_CONTRA = 'fadeIn';

  private animacaoLateralBar: string;
  private animacaoContraBar: string;
  private visibleLateralBar: boolean;

  constructor(private authService: SecurityOauthService) { }

  ngOnInit() {
    this.animacaoLateralBar = 'invisivel';
  }

  private toggleBar() {
    if (this.visibleLateralBar) {
      this.desativarLateralBar();
    } else {
      this.ativarLateralBar();
    }
  }

  private ativarLateralBar() {
    this.animacaoLateralBar = this.ANIMACAO_ENTRADA_LATERAL;
    this.animacaoContraBar = this.ANIMACAO_ENTRADA_CONTRA;
    this.drawLateralBar();
  }

  private desativarLateralBar() {
    this.animacaoLateralBar = this.ANIMACAO_SAIDA_LATERAL;
    this.animacaoContraBar = this.ANIMACAO_SAIDA_CONTRA;
    this.cleanLateralBar();
  }

  private cleanLateralBar() {
    setTimeout(() => {
      this.visibleLateralBar = false;
    }, 300);
  }

  private drawLateralBar() {
    this.visibleLateralBar = true;
  }

}
