import { Component, OnInit } from '@angular/core';

import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { CursoAreaService } from '../../services/area.service';
import { AreaFilter } from '../../models/area.filter';
import { Area } from '../../models/area';
import { Formacao } from '../../models/formacao';
import { MatDialogRef, MatDialogConfig } from '@angular/material';

import { SpeedDialFabPosition } from 'src/app/modules/shared/components/buttons/speed-dial-fab/speed-dial-fab.component';
import { FabButton } from '../../../shared/components/buttons/speed-dial-fab/speed-dial-fab.component';

export class MyFabButton implements FabButton {
  icon: string;
  tooltip: string;
}

@Component({
  selector: 'app-curso-painel-formacao',
  templateUrl: './painel-formacao.component.html',
  styleUrls: ['./painel-formacao.component.css']
})
export class CursoPainelFormacaoComponent implements OnInit {

  private filter = new AreaFilter();
  private areas = new Array<Area>();
  private formacoes = new Array<Formacao>();

  // DialFabButton
  private speedDialFabButtons = this.builFabButtons();

  SpeedDialFabPosition = SpeedDialFabPosition;
  speedDialFabColumnDirection = 'column';
  speedDialFabPosition = SpeedDialFabPosition.Top;
  speedDialFabPositionClassName = 'speed-dial-container-top';

  public static config(): MatDialogConfig {
    const config = new MatDialogConfig();
    config.minHeight = '100%';
    config.minWidth = '100%';
    config.width = '100%';
    config.height = '100%';
    config.maxWidth = '100%';
    config.maxHeight = '100%';
    config.hasBackdrop = false;
    return config;
  }

  constructor(
    public dialogRef: MatDialogRef<CursoPainelFormacaoComponent>,
    private areaService: CursoAreaService,
    private handlerMensage: SharedHandlerMensageService,
    ) { }

    ngOnInit() {
    this.goHome();
  }

  public clickFormacao(formacao: Formacao) {
    this.dialogRef.close(formacao);
  }

  public clickArea(area: Area) {
    this.areas.forEach(element => {
      if (element.id === area.id) {
        this.formacoes = element.formacoes;
        return;
      }
    });
    this.pesquisar(area.nome);
  }

  public goHome() {
    this.areas = new Array<Area>();
    this.formacoes = new Array<Formacao>();
    this.pesquisar('geral');
  }

  public onPositionChange(position: SpeedDialFabPosition) {
    switch (position) {
      case SpeedDialFabPosition.Bottom:
        this.speedDialFabPositionClassName = 'speed-dial-container-bottom';
        this.speedDialFabColumnDirection = 'column-reverse';
        break;
      default:
        this.speedDialFabPositionClassName = 'speed-dial-container-top';
        this.speedDialFabColumnDirection = 'column';
      }
  }

  public fabClick(btn) {
    if (btn.icon === 'close') {
      this.dialogRef.close();
    } else if (btn.icon === 'home') {
      this.goHome();
    }
  }


  private pesquisar(nomeArea: string) {
    this.filter.nomeAreaPrecedente = nomeArea;
    this.areaService.pesquisar(this.filter.getParams())
      .then(response => {
        this.loadItens(response);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
  }

  private loadItens(response: any) {
    this.filter.totalRecords = response.totalElements;
    this.areas = response.content;
    document.documentElement.scrollTop = 0;
  }

  private builFabButtons(): Array<MyFabButton> {
    const fabButtonClose = new MyFabButton();
    fabButtonClose.icon = 'close';
    fabButtonClose.tooltip = 'Sair';

    const fabButtonInicio = new MyFabButton();
    fabButtonInicio.icon = 'home';
    fabButtonInicio.tooltip = 'Início';

    const buttons = new Array<MyFabButton>();

    buttons.push(fabButtonInicio);
    buttons.push(fabButtonClose);
    return buttons;
  }
}
