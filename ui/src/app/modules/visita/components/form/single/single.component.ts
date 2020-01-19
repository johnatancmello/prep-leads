import { Component, OnInit, OnDestroy, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';

import { VisitaFilter } from '../../../models/vistita.filter';
import { Visita } from '../../../models/visita';
import { VisitaService } from '../../../services/visita.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';
import { MatDialog } from '@angular/material';
import { PessoaDialogComponent } from 'src/app/modules/pessoa/components/dialog/dialog.component';
import { EventEmitterService } from '../../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { AlunoDialogComponent } from 'src/app/modules/aluno/components/dialog/dialog.component';

@Component({
  selector: 'app-visita-single',
  templateUrl: './single.component.html',
  styleUrls: ['./single.component.css']
})
export class VisitaSingleComponent implements OnInit, OnDestroy  {

  private filter = new VisitaFilter();
  private visitas: Array<Visita>;
  private subscription: Subscription;

  constructor(
    //public dialog: MatDialog,
    // private bottomSheet: MatBottomSheet,
    private visitaService: VisitaService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
    public dialog: MatDialog,
    // private logged: SegurancaLoggedService,
  ) {
    this.subscription = EventEmitterService
      .get(Events.visitasChange)
      .subscribe(() => { this.pesquisar(); });
  }

  ngOnInit() {
    this.pesquisar();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public pesquisar() {
    this.navBarService.loading = true;
    this.visitaService.pesquisarInSummary(this.filter.getParams())
      .then(response => {
        this.visitas = response.content;
        this.filter.totalRecords = response.totalElements;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  public changePage(event) {
    this.filter.page = event.pageIndex;
    this.filter.itensForPage = event.pageSize;
    this.pesquisar();
  }

  public switchMotor(visita: Visita) {
    if (visita.pessoa.aluno) {
      this.openDialogAluno(visita);
    } else {
      this.openDialogPessoa(visita);
    }
  }

  public openDialogPessoa(visita: Visita) {
    const readonly = true;
    const idPessoa = visita.pessoa.id;
    const idVisitaSelecionado = visita.id;
    const dialogRef = this.dialog.open(
      PessoaDialogComponent,
      PessoaDialogComponent
        .configDialog({ readonly, idPessoa, idVisitaSelecionado }));

    dialogRef.afterClosed().subscribe(modified => {
      if (modified) {
        this.pesquisar();
      }
    });
  }

  private openDialogAluno(visita: Visita) {

    const readonly = true;
    const idPessoa = visita.pessoa.id;
    const idAluno = visita.pessoa.aluno.id;
    const idVisitaSelecionado = visita.id;

    const dialogRef = this.dialog.open(
      AlunoDialogComponent,
      AlunoDialogComponent
        .configDialog({ readonly, idAluno, idPessoa, idVisitaSelecionado })
    );

    dialogRef.afterClosed().subscribe(result => {
    });
  }

}
