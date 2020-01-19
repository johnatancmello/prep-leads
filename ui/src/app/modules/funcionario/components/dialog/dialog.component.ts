import { Component, OnInit, Inject } from '@angular/core';

import { FuncionarioService } from 'src/app/modules/funcionario/services/funcionario.service';
import { Imagem } from '../../../pessoa/models/imagem';
import { Funcionario } from '../../models/funcionarios';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig } from '@angular/material';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';


@Component({
  selector: 'app-funcionario-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class FuncionarioDialogComponent implements OnInit {

  public funcionario = Funcionario.newFuncionario();
  public readonly: boolean;

  // flags
  private modified = false;

  public static configDialog(data: any): MatDialogConfig {
    const config = new MatDialogConfig();
    config.minHeight = '100%';
    config.minWidth = '100%';
    config.width = '100%';
    config.height = '100%';
    config.maxWidth = '100%';
    config.maxHeight = '100%';
    config.hasBackdrop = false;
    config.data = data;
    return config;
  }

  constructor(
    private funcionarioService: FuncionarioService,
    private handlerMensage: SharedHandlerMensageService,
    public dialogRef: MatDialogRef<FuncionarioDialogComponent>,
    private navBarService: NavBarService,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
    if (data.funcionario) {
      this.funcionario = data.funcionario;
    } else if (this.data.idFuncionario) {
      this.pesquisar(this.data.idFuncionario);
    }
    if (data.readonly) {
      this.readonly = data.readonly;
    }
  }

  ngOnInit() {
    window.scrollTo(0, 0);
  }

  public togglerEdit(event) {
    if (event === 'SAVE') {
      this.descobreTarefa();
    } else if (event === 'EDIT') {
      this.readonly = false;
    }
  }

  public refreshImagem(img: Imagem) {
    if (img) {
      if (this.funcionario.pessoa.imagem
          && this.funcionario.pessoa.imagem.id ) {
        img.id = this.funcionario.pessoa.imagem.id;
      }
      this.funcionario.pessoa.imagem = img;
    }
  }

  private descobreTarefa() {
    if (this.funcionario.id) {
      this.edit();
    } else {
      this.add();
    }
  }

  public delete() {
    this.navBarService.loading = true;
    this.funcionarioService.excluir(this.funcionario.id)
      .then(() => {
        this.modified = true;
        this.close();
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  public close() {
    this.dialogRef.close(this.modified);
  }

  private pesquisar(id: number) {
    this.navBarService.loading = true;
    this.funcionarioService.pesquisarById(id)
      .then(response => {
        this.funcionario = response;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private edit() {
    this.navBarService.loading = true;
    this.funcionarioService.alterar(this.funcionario)
      .then(response => {
        this.funcionario = response;
        this.modified = true;
        this.readonly = true;
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private add() {
    this.navBarService.loading = true;
    this.funcionarioService.adicionar(this.funcionario)
      .then(response => {
        this.funcionario = response;
        this.modified = true;
        this.readonly = true;
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

}
