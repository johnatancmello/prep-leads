import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

import { MatDialog } from '@angular/material';
import { PessoaProfileImagemDialogComponent } from './dialog/dialog.component';
import { Imagem } from '../../../models/imagem';

@Component({
  selector: 'app-pessoa-profile-imagem',
  templateUrl: './imagem.component.html',
  styleUrls: ['./imagem.animation.component.css', './imagem.component.css']
})
export class PessoaProfileImagemComponent implements OnInit {

  private ICOM_EDIT = 'edit';
  private ICOM_SAVE = 'done';
  private ICOM_DELETE = 'delete';
  private ICOM_NONE = '';


  @Output() editEvent = new EventEmitter();
  @Output() closeEvent = new EventEmitter();
  @Output() deleteEvent = new EventEmitter();
  @Output() newImagem = new EventEmitter();

  @Input() imagem: Imagem;
  @Input() readonly: boolean;

  public iconEdit = this.ICOM_EDIT; // Usado no HTML
  public iconDelete = this.ICOM_DELETE; // Usado no HTML
  public img: string;

  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.analiseReadonly();
  }

  getIconEdit() {
    this.analiseReadonly();
    return this.iconEdit;
  }

  analiseReadonly() {
    if (this.readonly === null) {
      this.iconEdit = this.ICOM_NONE;
      this.iconDelete = this.ICOM_NONE;
    } else if (this.readonly) {
      this.iconEdit = this.ICOM_EDIT;
    } else {
      this.iconEdit = this.ICOM_SAVE;
    }
  }

  togglerEdit(event) {
    if (this.iconEdit === this.ICOM_EDIT) {
      this.iconEdit = this.ICOM_SAVE;
      this.editEvent.emit('EDIT');
    } else {
      this.iconEdit = this.ICOM_EDIT;
      this.editEvent.emit('SAVE');
    }
  }

  loadImagem(event) {
    const dialogRef = this.dialog.open(
      PessoaProfileImagemDialogComponent
    );

    dialogRef.afterClosed().subscribe(result => {
      this.newImagem.emit(result);
    });
  }

  /*onFileChanged(event) {
    if (event.target.files && event.target.files[0]) {
      const file: File = event.target.files[0];
      const reader = new FileReader();

      this.ng2ImgMaxService.resize([file], 540, 540)
        .subscribe((result) => {
          reader.readAsBinaryString(result); // read file as data url
        });

      reader.onload = (event2: any) => { // called once readAsDataURL is completed
        const img = new Imagem();
        img.nome = file.name;
        img.tamanho = file.size;
        img.tipo = file.type;
        img.dados = btoa(event2.target.result);
        this.newImagem.emit(img);
      }
    }
  }*/

  getBase64(): string {
    return Imagem.getImagemBase64(this.imagem, '');
  }

  buildImagem(): Imagem {
    if (!this.imagem) {
      this.imagem = new Imagem();
    }
    return this.imagem;
  }

}
