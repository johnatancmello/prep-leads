import { Component, OnInit, Input, OnDestroy, ChangeDetectorRef } from '@angular/core';

import { Pessoa } from '../../models/pessoa';
import { ContatoFilter } from '../../../contato/models/contato.filter';
import { ContatoService } from '../../../contato/services/contato.service';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Contato } from '../../../contato/models/contatos';
import { Funcionario } from '../../../funcionario/models/funcionarios';
import { NavBarService } from '../../../menu/services/nav-bar.service';
import { SharedHandlerMensageService } from '../../../shared/services/error/handler-mensage.service';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { MatBottomSheet } from '@angular/material';
import { ContatoDialogComponent } from '../../../contato/components/dialog/dialog.component';
import { Subscription } from 'rxjs';

import {FuncionarioLoggedService} from '../../../funcionario/services/logged.service';

@Component({
  selector: 'app-pessoa-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class PessoaContatoComponent implements OnInit, OnDestroy {

  @Input() idPessoa: number;
  @Input() pessoa: Pessoa;
  @Input() idContatoSelecionado: number;
  @Input() contatos: Array<Contato>;

  public filter = new ContatoFilter();
  private subscription: Subscription;

  constructor(
    private contatoService: ContatoService,
    private navBarService: NavBarService,
    private handlerMensage: SharedHandlerMensageService,
    private logged: FuncionarioLoggedService,
    private bottomSheet: MatBottomSheet,
  ) {
    this.subscription = EventEmitterService
      .get(Events.contatosChange)
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

  public openDialogContato(contato: Contato) {
    const idContato = contato.id;
    const bottomSheetRef = this.bottomSheet.open(
      ContatoDialogComponent,
      { data: { idContato } }
    );

    bottomSheetRef.afterDismissed().subscribe((result) => {
      // if (result) { this.addOrChange(result); }
    });
  }

  public pesquisar() {
    // this.navBarService.loading = true;  //gera erro
    this.filter.idPessoa = this.idPessoa;
    this.contatoService.pesquisarInSummary(this.filter.getParams())
      .then(response => {
        this.contatos = response.content;
        this.filter.totalRecords = response.totalElements;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
    // .finally(() => { this.navBarService.loading = false; });
  }

  private change(contato: Contato) {
    this.navBarService.loading = true;
    this.addPessoa(contato);
    this.contatoService.alterar(contato)
      .then(response => {
        this.pesquisar();
        this.handlerMensage.success(response);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private add(contato: Contato) {
    this.navBarService.loading = true;
    this.addFuncionarioLogado(contato);
    this.addPessoa(contato);
    this.contatoService.adicionar(contato)
      .then(response => {
        this.pesquisar();
        this.handlerMensage.success(response);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private addFuncionarioLogado(contato: Contato) {
    contato.funcionario = new Funcionario();
    contato.funcionario.id = this.logged.getIdFuncionario();
  }

  private addPessoa(contato: Contato) {
    contato.pessoa = this.pessoa;
  }

}
