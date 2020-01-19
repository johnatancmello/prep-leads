import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SetorService } from '../../services/setor.service';
import { SharedHandlerMensageService } from 'src/app/modules/shared/services/error/handler-mensage.service';
import { NavBarService } from 'src/app/modules/menu/services/nav-bar.service';

@Component({
  selector: 'app-setor-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class SetorDialogComponent implements OnInit {

  @Output() changePage = new EventEmitter();
  readonly = true;
  textButtonSaveEdit = 'Alterar';

  constructor(
    public dialogRef: MatDialogRef<SetorDialogComponent>,
    private service: SetorService,
    private navBarService: NavBarService,
    private handlerMensage: SharedHandlerMensageService,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
  }

  toggleReadonly(event) {
    if (this.readonly) {
      this.setSalvar();
    } else {
      this.alterar();
    }
  }

  excluir(event) {
    this.navBarService.loading = true;
    this.service.excluir(this.data.setor.id)
      .then(response => {
        this.dialogRef.close(true);
      })
      .catch(response => {
        this.handlerMensage.handle(response);
      })
      .finally(() => {
        this.setAlterar();
        this.navBarService.loading = false;
      });
  }

  private alterar() {
    this.navBarService.loading = true;
    this.service.alterar(this.data.setor)
    .then(response => {
      this.dialogRef.close(false);
    })
    .catch(response => {
      this.handlerMensage.handle(response);
    })
    .finally(() => {
      this.setAlterar();
      this.navBarService.loading = false;
    });
  }

  private setAlterar() {
      this.readonly = true;
      this.textButtonSaveEdit = 'Alterar';
  }

  private setSalvar() {
    this.readonly = false;
    this.textButtonSaveEdit = 'Salvar';
  }

}
