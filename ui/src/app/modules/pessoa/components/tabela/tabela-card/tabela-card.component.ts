import { Component, OnInit, OnDestroy } from '@angular/core';

import { PessoaService } from '../../../services/pessoa.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { PessoaFilter } from '../../../models/pessoa.filter';
import { Pessoa } from '../../../models/pessoa';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { PessoaDialogComponent } from '../../dialog/dialog.component';
import { NavBarService } from '../../../../menu/services/nav-bar.service';
import { Subscription } from 'rxjs';
import { EventEmitterService } from '../../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';

@Component({
  selector: 'app-tabela-card',
  templateUrl: './tabela-card.component.html',
  styleUrls: ['./tabela-card.component.css']
})
export class PessoaTabelaCardComponent implements OnInit, OnDestroy {

  private filter = new PessoaFilter();
  private pessoas = new Array<Pessoa>();
  private subscription: Subscription;

  constructor(
    public dialog: MatDialog,
    private pessoaService: PessoaService,
    private navBarService: NavBarService,
    private handlerMensage: SharedHandlerMensageService,
  ) {
    this.subscription = EventEmitterService
      .get(Events.pessoasChange)
      .subscribe(() => { this.pesquisar(); });
   }

  ngOnInit() {
    this.pesquisar();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  private pesquisar() {
    this.navBarService.loading = true;
    this.pessoaService.pesquisar(this.filter.getParams())
      .then(response => {
        this.filter.totalRecords = response.totalElements;
        this.pessoas = response.content;
        document.documentElement.scrollTop = 0;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  // Modificado 9-9-19-12-33
  public openDialogPessoa(pessoa: Pessoa) {

    let readonly = false;
    if (pessoa) {
      pessoa = pessoa;
      readonly = true;
    } else {
      readonly = false;
      pessoa = Pessoa.newPessoa();
    }

    const dialogRef = this.dialog.open(
      PessoaDialogComponent,
      PessoaDialogComponent.configDialog({ readonly, pessoa })
    );

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  public changePage(event) {
    this.filter.page = event.pageIndex;
    this.filter.itensForPage = event.pageSize;
    this.pesquisar();
  }


}
