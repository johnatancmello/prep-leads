import { Component, OnInit, ChangeDetectorRef, Inject } from '@angular/core';
import { VisitaService } from '../../services/visita.service';
import { NavBarService } from '../../../menu/services/nav-bar.service';
import { SharedHandlerMensageService } from '../../../shared/services/error/handler-mensage.service';
import { ContatoDialogComponent } from '../../../contato/components/dialog/dialog.component';
import { MatBottomSheetRef, MAT_BOTTOM_SHEET_DATA } from '@angular/material';
import { Visita } from '../../models/visita';
import { Item } from '../../../shared/models/item';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { VisitaStatus } from '../../models/visita.status';
import {FuncionarioLoggedService} from '../../../funcionario/services/logged.service';

@Component({
  selector: 'app-visita-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class VisitaDialogComponent implements OnInit {

  public visita = Visita.newVisita();
  public status = [];
  public readonly: boolean;

  constructor(
    private visitaService: VisitaService,
    private navBarService: NavBarService,
    private handlerMensage: SharedHandlerMensageService,
    private logged: FuncionarioLoggedService,
    private bottomSheetRef: MatBottomSheetRef<ContatoDialogComponent>,
    private changeDetectorRef: ChangeDetectorRef,
    @Inject(MAT_BOTTOM_SHEET_DATA) public data: any,
  ) {
    if (data.idVisita) {
      this.pesquisarVisitaById(data.idVisita);
      this.readonly = true;
    } else if (data.visita) {
      this.visita = data.visita;
      this.readonly = true;
    }
  }

  ngOnInit() {
    this.buildTipos();
  }

  public visibleDateField(): boolean {
    return this.visita.status === VisitaStatus.RETORNO;
  }

  public getTextAlterarSalvar(): string {
    return this.readonly ? 'edit' : 'done' ;
  }

  public aterarSavar() {
    if (this.readonly) {
      this.readonly = false;
    } else {
      this.switch();
    }
  }

  private switch() {
    if (this.visita.id) {
      this.change(this.visita);
    } else {
      //
    }
  }

  private pesquisarVisitaById(id: number) {
    // this.navBarService.loading = true;  //gera erro
    this.visitaService.pesquisarById(id)
      .then(response => {
        this.visita = response;
        this.convertDate();
        this.changeDetectorRef.detectChanges();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
    // .finally(() => { this.navBarService.loading = false; });
  }

  private change(visita: Visita) {
    this.navBarService.loading = true;
    this.visitaService.alterar(this.getVisitaForSend())
      .then(response => {
        EventEmitterService.get(Events.visitasChange).emit();
        EventEmitterService.get(Events.contatosChange).emit();
        EventEmitterService.get(Events.interessesChange).emit();
        this.handlerMensage.success(response);
        this.bottomSheetRef.dismiss();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  /*private add(interesse: Interesse) {
    this.navBarService.loading = true;
    this.addFuncionarioLogado(interesse);
    this.addPessoa(interesse);
    this.cleanAdditionalInformationOfModulos(interesse);
    this.interesseService.adicionar(interesse)
      .then(response => {
        EventEmitterService.get(Events.interessesChange).emit();
        this.handlerMensage.success(response);
        this.bottomSheetRef.dismiss();
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }*/

  private convertDate() {
    if (this.visita && this.visita.dataDoAgendamento) {
      this.visita.dataDoAgendamento
        = new Date(this.visita.dataDoAgendamento);
    }
  }

  private addFuncionarioLogado(modelo: any) {
    modelo.funcionario = { id: this.logged.getIdFuncionario() };
  }

  private getVisitaForSend(): Visita {
    const visita = this.getVisitaClone();
    this.addFuncionarioLogado(visita);
    return visita;
  }

  private getVisitaClone(): Visita {
    return JSON.parse(JSON.stringify(this.visita));
  }

  private buildTipos() {
    for (const index in VisitaStatus) {
      if (typeof VisitaStatus[index] === 'string') {
        const item = new Item();
        item.label = VisitaStatus[index];
        item.value = VisitaStatus[index];
        this.status.push(item);
      }
    }
  }

}
