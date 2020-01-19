import { Component, OnInit, Input, Output, EventEmitter, Inject, ViewChild } from '@angular/core';

import { Pessoa } from '../../models/pessoa';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { Imagem } from '../../models/imagem';
import { PessoaService } from '../../services/pessoa.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig, MatTabGroup } from '@angular/material';
import { NavBarService } from '../../../menu/services/nav-bar.service';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';

@Component({
  selector: 'app-pessoa-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class PessoaDialogComponent implements OnInit {

  public pessoa = new Pessoa();
  public readonly: boolean;

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
    private pessoaService: PessoaService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
    public dialogRef: MatDialogRef<PessoaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data.pessoa) { this.pessoa = JSON.parse(JSON.stringify(data.pessoa)); }
    if (this.data.idPessoa) { this.pesquisar(this.data.idPessoa); }
    if (data.readonly) { this.readonly = data.readonly; }
  }

  ngOnInit() { }

  public togglerEdit(event) {
    if (event === 'SAVE') {
      this.readonly = true;
      this.descobreTarefa();
    } else if (event === 'EDIT') {
      this.readonly = false;
    }
  }

  public getIndexPrimaryTab(): number {
    if (this.data.idInteresseSelecionado
      || this.data.idContatoSelecionado
      || this.data.idVisitaSelecionado) {
      return 1;
    } else {
      return 0;
    }
  }

  public getIndexSecundaryTab(): number {
    if (this.data.idInteresseSelecionado) {
      return 0;
    } else if (this.data.idContatoSelecionado) {
      return 1;
    } else if (this.data.idVisitaSelecionado) {
      return 2;
    } else {
      return 0;
    }
  }

  public refreshImagem(img: Imagem) {
    if (img) {
      if (this.pessoa.imagem
        && this.pessoa.imagem.id) {
        img.id = this.pessoa.imagem.id;
      }
      this.pessoa.imagem = img;
    }
  }

  private descobreTarefa() {
    if (this.pessoa.id) {
      this.edit();
    } else {
      this.add();
    }
  }

  public close() {
    this.dialogRef.close();
  }

  public getImagem(): Imagem {
    return (this.pessoa) ? this.pessoa.imagem : null;
  }

  public getIdPessoa(): number {
    return (this.data.idPessoa) ? this.data.idPessoa : this.pessoa.id;
  }

  private add() {
    this.navBarService.loading = true;
    this.pessoaService.adicionar(this.pessoa)
      .then(response => {
        EventEmitterService.get(Events.pessoasChange).emit(); // Broadcast
        this.pessoa = response;
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  public delete() {
    this.navBarService.loading = true;
    this.pessoaService.excluir(this.pessoa.id)
      .then(() => {
        EventEmitterService.get(Events.pessoasChange).emit(); // Broadcast
        this.close();
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private pesquisar(id: number) {
    this.navBarService.loading = true;
    this.pessoaService.pesquisarById(id)
      .then(response => {
        this.pessoa = response;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private edit() {
    this.navBarService.loading = true;
    this.pessoaService.alterar(this.pessoa)
      .then(response => {
        EventEmitterService.get(Events.pessoasChange).emit(); // Broadcast
        this.pessoa = response;
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }


}
