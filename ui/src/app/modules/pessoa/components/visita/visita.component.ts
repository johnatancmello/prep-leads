import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Pessoa } from '../../models/pessoa';
import { Visita } from '../../../visita/models/visita';
import { InteresseFilter } from '../../../interesse/models/interesse.filter';
import { Subscription } from 'rxjs';
import { MatBottomSheet } from '@angular/material';
import { VisitaService } from '../../../visita/services/visita.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';
import { VisitaDialogComponent } from '../../../visita/components/dialog/dialog.component';

@Component({
  selector: 'app-pessoa-visita',
  templateUrl: './visita.component.html',
  styleUrls: ['./visita.component.css']
})
export class PessoaVisitaComponent implements OnInit, OnDestroy {

  @Input() idPessoa: number;
  @Input() pessoa: Pessoa;
  @Input() idVisitaSelecionado: number;
  @Input() visitas: Array<Visita>;
  @Input() filter = new InteresseFilter();

  private subscription: Subscription;

  constructor(
    private bottomSheet: MatBottomSheet,
    public visitaService: VisitaService,
    public handlerMensage: SharedHandlerMensageService,
  ) {
    this.subscription = EventEmitterService
      .get(Events.visitasChange)
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

  public openDialogDetails(visita: Visita) {
    const bottomSheetRef = this.bottomSheet.open(
      VisitaDialogComponent,
      { data: { idVisita: visita.id } }
    );

    bottomSheetRef.afterDismissed().subscribe((result) => {
      // if (result) { this.addOrChange(result); }
    });
  }

  public pesquisar() {
    // this.navBarService.loading = true;  //gera erro
    this.filter.idPessoa = this.idPessoa;
    this.visitaService.pesquisarInSummary(this.filter.getParams())
      .then(response => {
        this.visitas = response.content;
        this.filter.totalRecords = response.totalElements;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      });
    // .finally(() => { this.navBarService.loading = false; });
  }

}
