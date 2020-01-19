import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Setor } from '../../../models/setor';
import { SetorService } from '../../../services/setor.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { SetorFilter } from '../../../models/setor.filter';
import { NavBarService } from '../../../../menu/services/nav-bar.service';

@Component({
  selector: 'app-setor-form-full',
  templateUrl: './form-full.component.html',
  styleUrls: ['./form-full.component.css']
})
export class SetorFormFullComponent implements OnInit {

  @Input() readonly: boolean;

  filter = new SetorFilter();
  setor = new Setor();
  setores = new Array<Setor>();

  constructor(
    private service: SetorService,
    private navBarService: NavBarService,
    private handlerMensage: SharedHandlerMensageService,
  ) { }

  ngOnInit() {
    this.pesquisar();
  }

  add(event) {
    this.navBarService.loading = true;
    this.service.adicionar(this.setor)
      .then(response => {
        this.pesquisar();
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

  public pesquisar() {
    this.navBarService.loading = true;
    this.service.pesquisar(this.filter.getParams())
      .then(response => {
        this.setores = response.content;
        this.filter.totalRecords = response.totalElements;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

}
