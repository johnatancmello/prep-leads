import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { ImageCropperComponent, CropperSettings } from 'ng2-img-cropper';

import { Imagem } from '../../../../models/imagem';
import { NavBarService } from '../../../../../menu/services/nav-bar.service';

@Component({
  selector: 'app-imagem-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css'],
})
export class PessoaProfileImagemDialogComponent implements OnInit {

  public imagem = new Imagem();

  cropperData: any;
  cropperSettings: CropperSettings;
  @ViewChild('cropper', undefined) cropper: ImageCropperComponent;

  constructor(
    public dialogRef: MatDialogRef<PessoaProfileImagemDialogComponent>,
    private navBarService: NavBarService,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
    this.settings();
  }

  ngOnInit() { }

  getImagem() {
    this.imagem.dados = this.cropperData.image.split(',')[1];
    return this.imagem;
  }

  fileChangeListener(event) {
    if (event.target.files && event.target.files[0]) {
      const file: File = event.target.files[0];
      this.setMetaData(file);
      this.changeImagem(file);
      this.saveImagem(file);
    }
  }

  private setMetaData(file: File) {
    this.imagem.nome = file.name;
    this.imagem.tamanho = file.size;
    this.imagem.tipo = file.type;
  }

  private saveImagem(file: File) {
    const reader = new FileReader();
    reader.readAsBinaryString(file); // read file as cropperData url
    reader.onload = (event: any) => { // called once readAsDataURL is completed
      this.imagem.dados = btoa(event.target.result);
    };
  }

  private changeImagem(file: File) {
    const reader = new FileReader();
    const that = this;

    this.navBarService.loading = true; // Inicio do carregamento
    reader.readAsDataURL(file); // read file as cropperData url
    reader.onload = (event: any) => { // called once readAsDataURL is completed
      const image = new Image();
      image.src = event.target.result;
      that.cropper.setImage(image);
      this.navBarService.loading = false; // Fim do carregamento
    }
  }

  private settings() {
    this.cropperSettings = new CropperSettings();
    this.cropperSettings.noFileInput = true;
    //this.cropperSettings.width = 100;
    //this.cropperSettings.height = 100;
    this.cropperSettings.croppedWidth = 500;
    this.cropperSettings.croppedHeight = 500;
    //this.cropperSettings.canvasWidth = 200;
    //this.cropperSettings.canvasHeight = 200;
    this.cropperSettings.preserveSize = false;
    this.cropperSettings.compressRatio = 10.0;
    this.cropperSettings.dynamicSizing =  false;
    this.cropperSettings.cropperClass = 'edit';
    //this.cropperSettings.croppingClass = 'canvas';
    this.cropperSettings.keepAspect = true;
    this.cropperSettings.cropperDrawSettings.strokeColor = 'rgba(255,255,255,1)';
    this.cropperSettings.cropperDrawSettings.strokeWidth = 4;
    this.cropperData = {};
  }

}
