import { Component, OnInit, EventEmitter, Input } from '@angular/core';

import { Funcionario } from '../../models/funcionarios';
import { FuncionarioService } from '../../services/funcionario.service';
import { Pessoa } from '../../../pessoa/models/pessoa';
import { FuncionarioFilter } from '../../models/funcionario.filter';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { FuncionarioDialogComponent } from '../dialog/dialog.component';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';

@Component({
  selector: 'app-funcionario-flexgrid-card',
  templateUrl: './flexgrid-card.component.html',
  styleUrls: ['./flexgrid-card.component.css']
})
export class FuncionarioFlexgridCardComponent implements OnInit {

  private filter = new FuncionarioFilter();
  private funcionarios = new Array<Funcionario>();

  constructor(
    public dialog: MatDialog,
    private funcionarioService: FuncionarioService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
  ) { }

  ngOnInit() {
    if (this.funcionarios.length === 0) {
      this.pesquisar();
    }
  }

  public changePage(event) {
    this.filter.page = event.pageIndex;
    this.filter.itensForPage = event.pageSize;
    this.pesquisar();
  }

  private pesquisar() {
    this.navBarService.loading = true;
    this.funcionarioService.pesquisar(this.filter.getParams())
      .then(response => {
        this.filter.totalRecords = response.totalElements;
        this.funcionarios = response.content;
        document.documentElement.scrollTop = 0;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private getPessoas() {
    return this.funcionarios.map(f => {
      return f.pessoa;
    });
  }

  private getFuncionarioByPessoa(pessoa: Pessoa): Funcionario {
    let funcionarioEncontrado: Funcionario;
    this.funcionarios.forEach(f => {
      if (f.pessoa.id === pessoa.id) {
        funcionarioEncontrado = f;
      }
    });
    return funcionarioEncontrado;
  }

  public openDialogFuncionario(pessoa: Pessoa) {

    let readonly = false;
    let funcionario: Funcionario;

    if (pessoa) {
      funcionario = this.getFuncionarioByPessoa(pessoa);
      readonly = true;
    } else {
      readonly = false;
      funcionario = Funcionario.newFuncionario();
    }

    const dialogRef = this.dialog.open(
      FuncionarioDialogComponent,
      FuncionarioDialogComponent.configDialog({ readonly, funcionario })
    );

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.pesquisar();
      }
    });
  }

}
