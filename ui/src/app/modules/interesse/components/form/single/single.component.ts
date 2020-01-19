import { Component,
  OnInit,
  OnDestroy
} from '@angular/core';
import { Subscription } from 'rxjs';

import { InteresseFilter } from 'src/app/modules/interesse/models/interesse.filter';
import { InteresseService } from 'src/app/modules/interesse/services/interesse.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { MatDialog } from '@angular/material';
import { PessoaDialogComponent } from '../../../../pessoa/components/dialog/dialog.component';
import { NavBarService } from '../../../../menu/services/nav-bar.service';
import { Interesse } from '../../../models/interesse';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { EventEmitterService } from '../../../../shared/services/broadcaster/event-emitter';
import { AlunoDialogComponent } from 'src/app/modules/aluno/components/dialog/dialog.component';

@Component({
  selector: 'app-pessoa-interesse-form-single',
  templateUrl: './single.component.html',
  styleUrls: ['./single.component.css']
})
export class InteresseFormSingleComponent implements OnInit, OnDestroy  {

  private filter = new InteresseFilter();
  private interesses: Array<Interesse>;
  private subscription: Subscription;

  constructor(
    public dialog: MatDialog,
    // private bottomSheet: MatBottomSheet,
    private interesseService: InteresseService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
    // private logged: SegurancaLoggedService,
  ) {
    this.subscription = EventEmitterService
      .get(Events.interessesChange)
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
    this.interesseService.pesquisarInSummary(this.filter.getParams())
      .then(response => {
        this.interesses = response.content;
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

  public switchMotor(interesse: Interesse) {
    if (interesse.pessoa.aluno) {
      this.openDialogAluno(interesse);
    } else {
      this.openDialogPessoa(interesse);
    }
  }

  private openDialogPessoa(interesse: Interesse) {
    const readonly = true;
    const idPessoa = interesse.pessoa.id;
    const idInteresseSelecionado = interesse.id;

    const dialogRef = this.dialog.open(
      PessoaDialogComponent,
      PessoaDialogComponent
        .configDialog({ readonly, idPessoa, idInteresseSelecionado }));

    dialogRef.afterClosed().subscribe(modified => {
    });
  }

  private openDialogAluno(interesse: Interesse) {

    const readonly = true;
    const idPessoa = interesse.pessoa.id;
    const idAluno = interesse.pessoa.aluno.id;
    const idInteresseSelecionado = interesse.id;

    const dialogRef = this.dialog.open(
      AlunoDialogComponent,
      AlunoDialogComponent
        .configDialog({ readonly, idPessoa, idAluno, idInteresseSelecionado })
    );

    dialogRef.afterClosed().subscribe(result => {
    });
  }

}
