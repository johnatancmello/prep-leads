import { Component, OnInit, Inject } from '@angular/core';
import { Aluno } from '../../models/aluno';
import { MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AlunoService } from '../../services/aluno.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';
import { Imagem } from '../../../pessoa/models/imagem';
import { EventEmitterService } from '../../../shared/services/broadcaster/event-emitter';
import { Events } from 'src/app/modules/shared/services/broadcaster/events';

@Component({
  selector: 'app-aluno-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class AlunoDialogComponent implements OnInit {

  public aluno = Aluno.newAluno();
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
    private alunoService: AlunoService,
    private handlerMensage: SharedHandlerMensageService,
    private navBarService: NavBarService,
    public dialogRef: MatDialogRef<AlunoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data.aluno) { this.aluno = JSON.parse(JSON.stringify(data.aluno)); }
    if (this.data.idAluno) { this.pesquisar(this.data.idAluno); }
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
      if (this.aluno.pessoa.imagem
        && this.aluno.pessoa.imagem.id) {
        img.id = this.aluno.pessoa.imagem.id;
      }
      this.aluno.pessoa.imagem = img;
    }
  }

  private descobreTarefa() {
    if (this.aluno.id) {
      this.edit();
    } else {
      this.add();
    }
  }

  public close() {
    this.dialogRef.close();
  }

  public getImagem(): Imagem {
    return (this.aluno) ? this.aluno.pessoa.imagem : null;
  }

  public getIdPessoa(): number {
    return (this.data.idPessoa) ? this.data.idPessoa : this.aluno.pessoa.id;
  }

  private add() {
    this.navBarService.loading = true;
    this.alunoService.adicionar(this.aluno)
      .then(response => {
        EventEmitterService.get(Events.alunosChange).emit(); // Broadcast
        this.aluno = response;
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  public delete() {
    this.navBarService.loading = true;
    this.alunoService.excluir(this.aluno.id)
      .then(() => {
        EventEmitterService.get(Events.alunosChange).emit(); // Broadcast
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
    this.alunoService.pesquisarById(id)
      .then(response => {
        this.aluno = response;
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

  private edit() {
    this.navBarService.loading = true;
    this.alunoService.alterar(this.aluno)
      .then(response => {
        EventEmitterService.get(Events.alunosChange).emit(); // Broadcast
        this.aluno = response;
        this.handlerMensage.success(null);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => { this.navBarService.loading = false; });
  }

}
