import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { AlunoFilter } from '../../../models/aluno.filter';
import { Aluno } from '../../../models/aluno';
import { MatDialog } from '@angular/material';
import { AlunoService } from '../../../services/aluno.service';
import { SharedHandlerMensageService } from '../../../../shared/services/error/handler-mensage.service';
import { NavBarService } from '../../../../menu/services/nav-bar.service';
import { Pessoa } from 'src/app/modules/pessoa/models/pessoa';
import { AlunoDialogComponent } from '../../dialog/dialog.component';
import { SegurancaLoggedService } from 'src/app/modules/seguranca/services/logged.service';
import { EventEmitterService } from '../../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';

@Component({
  selector: 'app-aluno-table-card',
  templateUrl: './table-card.component.html',
  styleUrls: ['./table-card.component.css']
})
export class AlunoTableCardComponent implements OnInit, OnDestroy {

  private filter = new AlunoFilter();
  private alunos = new Array<Aluno>();

  private subscription: Subscription;

  constructor(
    public dialog: MatDialog,
    private alunoService: AlunoService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
    private logged: SegurancaLoggedService,
  ) {
    this.subscription = EventEmitterService
      .get(Events.alunosChange)
      .subscribe(() => { this.pesquisar(); });
  }

  ngOnInit() {
    if (this.alunos.length === 0) {
      this.pesquisar();
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public changePage(event) {
    this.filter.page = event.pageIndex;
    this.filter.itensForPage = event.pageSize;
    this.pesquisar();
  }

  private pesquisar() {
    this.navBarService.loading = true;
    this.alunoService.pesquisar(this.filter.getParams())
      .then(response => {
        this.filter.totalRecords = response.totalElements;
        this.alunos = response.content;
        document.documentElement.scrollTop = 0;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private getPessoas() {
    /*return this.alunos.map(f => {
      return f.pessoa;
    });*/
  }

  private getAlunoByPessoa(pessoa: Pessoa): Aluno {
    let alunoEncontrado: Aluno;

    this.alunos.forEach(a => {
      if (a.pessoa.id === pessoa.id) {
        alunoEncontrado = a;
      }
    });
    return alunoEncontrado;
  }

  public openDialogDetails(pessoa: Pessoa) {

    let readonly = false;
    let aluno;

    if (pessoa) {
      aluno = this.getAlunoByPessoa(pessoa);
      readonly = true;
    } else {
      readonly = false;
      aluno = Aluno.newAluno();
    }

    aluno.funcionario = { id: this.logged.getIdFuncionario() };

    const dialogRef = this.dialog.open(
      AlunoDialogComponent,
      AlunoDialogComponent.configDialog({ readonly, aluno })
    );

    dialogRef.afterClosed().subscribe(result => {
/*      if (result) {
        this.pesquisar();
      }*/
    });
  }

}
