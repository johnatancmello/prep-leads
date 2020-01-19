import { Component, OnInit, Inject, Input, ChangeDetectorRef } from '@angular/core';
import { MAT_BOTTOM_SHEET_DATA, MatBottomSheetRef } from '@angular/material';

import { Item } from '../../../shared/models/item';

import { ContatoService } from '../../services/contato.service';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { SegurancaLoggedService } from 'src/app/modules/seguranca/services/logged.service';
import { Visita } from '../../../visita/models/visita';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { Contato } from '../../models/contatos';
import { ContatoStatus } from '../../models/contato.status';

@Component({
  selector: 'app-contato-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class ContatoDialogComponent implements OnInit {

  public contato = Contato.newContato();
  public status = [];
  public readonly: boolean;

  constructor(
    private contatoService: ContatoService,
    // private visitaService: VisitaService,
    private navBarService: NavBarService,
    private handlerMensage: SharedHandlerMensageService,
    private logged: SegurancaLoggedService,
    private bottomSheetRef: MatBottomSheetRef<ContatoDialogComponent>,
    private changeDetectorRef: ChangeDetectorRef,
    @Inject(MAT_BOTTOM_SHEET_DATA) public data: any,
  ) {
    if (data.idContato) {
      this.pesquisarContatoById(data.idContato);
      this.readonly = true;
    } else if (data.contato) {
      this.contato = data.contato;
      this.readonly = true;
    } else if (data.interesse) {
      this.contato = Contato.newContato();
      this.contato.interesse = data.interesse;
      this.readonly = false;
    }
  }

  ngOnInit() {
    this.buildTipos();
  }

  public visibleBtnSave(): boolean {
    return this.valid() && this.validDataAgendamento();
  }

  private validDataAgendamento(): boolean {
    if (this.contato.status === ContatoStatus.AGENDADO) {
      if (this.contato.visita.dataDoAgendamento) {
        return true;
      } else { return false; }
    } else { return true; }
  }

  private valid(): boolean {
    return this.contato.interesse
      && this.contato.observacao
      && !this.contato.id;
  }

  public switch() {
    this.add();
  }


  private add() {
    this.navBarService.loading = true;
    this.contatoService.adicionar(this.getContatoFromSend())
      .then(response => {
        // this.contato = response;
        EventEmitterService.get(Events.contatosChange).emit(); // Broadcast
        EventEmitterService.get(Events.interessesChange).emit(); // Broadcast
        EventEmitterService.get(Events.visitasChange).emit(); // Broadcast
        this.handlerMensage.success(null);
        this.bottomSheetRef.dismiss(true);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private pesquisarContatoById(id: number) {
    // this.navBarService.loading = true;  //gera erro
    this.contatoService.pesquisarById(id)
      .then(response => {
        this.contato = response;
        this.convertDate();
        this.changeDetectorRef.detectChanges();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
    // .finally(() => { this.navBarService.loading = false; });
  }


  private getContatoFromSend(): Contato {
    const contato = this.getContatoClone();

    this.addDadosContato(contato);

    if (this.contato.visita.dataDoAgendamento) {
      this.addDadosVisita(contato.visita);
    } else { contato.visita = null; }

    return contato;
  }

  private addDadosContato(contato: Contato) {
    this.addFuncionarioLogado(contato);
    this.addPessoa(contato);
    this.cleanAdditionalInformationInteresse(contato);
  }

  private addDadosVisita(visita: Visita) {
    visita.status = this.contato.status;
    visita.observacao = this.contato.observacao;
    this.addFuncionarioLogado(visita);
    this.addPessoa(visita);
  }

  private addFuncionarioLogado(modelo: any) {
    modelo.funcionario = { id: this.logged.getIdFuncionario() };
  }

  private addPessoa(modelo: any) {
    modelo.pessoa = { id: this.contato.interesse.pessoa.id };
  }

  private convertDate() {
    if (this.contato.visita && this.contato.visita.dataDoAgendamento) {
      this.contato.visita.dataDoAgendamento
        = new Date(this.contato.visita.dataDoAgendamento);
    }
  }

  private cleanAdditionalInformationInteresse(contato: any) {
    // const interesse = new Interesse();
    // interesse.id = contato.interesse.id;
    contato.interesse = { id: contato.interesse.id };
  }

  private getContatoClone(): Contato {
    return JSON.parse(JSON.stringify(this.contato));
  }

  private buildTipos() {
    for (const index in ContatoStatus) {
      if (typeof ContatoStatus[index] === 'string') {
        const item = new Item();
        item.label = ContatoStatus[index];
        item.value = ContatoStatus[index];
        this.status.push(item);
      }
    }
  }

}
