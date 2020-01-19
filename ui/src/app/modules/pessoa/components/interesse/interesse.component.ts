import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { MatBottomSheet } from '@angular/material';

import { InteresseFilter } from '../../../interesse/models/interesse.filter';

import { InteresseService } from '../../../interesse/services/interesse.service';
import { NavBarService } from '../../../menu/services/nav-bar.service';
import { SharedHandlerMensageService } from '../../../shared/services/error/handler-mensage.service';
import { Pessoa } from '../../models/pessoa';
import { Interesse } from 'src/app/modules/interesse/models/interesse';
import { Funcionario } from 'src/app/modules/funcionario/models/funcionarios';
import { InteresseDialogAddComponent } from '../../../interesse/components/dialog/add.component';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';

@Component({
  selector: 'app-pessoa-interesse',
  templateUrl: './interesse.component.html',
  styleUrls: ['./interesse.component.css']
})
export class PessoaInteresseComponent implements OnInit, OnDestroy {

  @Input() idPessoa: number;
  @Input() pessoa: Pessoa;
  @Input() idInteresseSelecionado: number;
  @Input() interesses: Array<Interesse>;
  @Input() filter = new InteresseFilter();

  private subscription: Subscription;

  constructor(
    private bottomSheet: MatBottomSheet,
    public interesseService: InteresseService,
    public handlerMensage: SharedHandlerMensageService,
  ) {
    this.subscription = EventEmitterService
      .get(Events.interessesChange)
      .subscribe(() => { this.pesquisar(); });
  }

  ngOnInit() {
    if (this.idPessoa) { this.pesquisar(); }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public changePage(event) {
    this.filter.page = event.pageIndex;
    this.filter.itensForPage = event.pageSize;
    this.pesquisar();
  }

  public openDialogInteresse(interesse: Interesse) {
    const pessoa = { id: this.pessoa.id };

    const bottomSheetRef = this.bottomSheet.open(
      InteresseDialogAddComponent,
      { data: { interesse, pessoa  } }
    );

    bottomSheetRef.afterDismissed().subscribe((result) => {
      // if (result) { this.addOrChange(result); }
    });
  }

  public pesquisar() {
    // this.navBarService.loading = true;  //gera erro
    this.filter.idPessoa = this.idPessoa;
    this.interesseService.pesquisarInSummary(this.filter.getParams())
      .then(response => {
        this.interesses = response.content;
        this.filter.totalRecords = response.totalElements;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
    // .finally(() => { this.navBarService.loading = false; });
  }

}
