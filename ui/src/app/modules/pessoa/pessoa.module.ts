import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTabsModule } from '@angular/material/tabs';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';

import { ImageCropperComponent } from 'ng2-img-cropper';
import { Ng2ImgMaxModule } from 'ng2-img-max';

import { SharedModule } from '../shared/shared.module';
import { VisitaModule } from '../visita/visita.module';
import { ContatoModule } from '../contato/contato.module';
import { CursoModule } from '../curso/curso.module';
import { InteresseModule } from '../interesse/interesse.module';

import { PessoaCardComponent } from './components/card/card.component';
import { PessoaFlexgridCardComponent } from './components/flexgrid-card/flexgrid-card.component';
import { PessoaFormBasicComponent } from './components/forms/form-basic/form-basic.component';
import { PessoaFormFoneComponent } from './components/forms/form-fone/form-fone.component';
import { PessoaProfileImagemComponent } from './components/profile/imagem/imagem.component';
import { PessoaFormFullComponent } from './components/forms/form-full/form-full.component';
import { PessoaProfileImagemDialogComponent } from './components/profile/imagem/dialog/dialog.component';
import { PessoaDialogComponent } from './components/dialog/dialog.component';
import { PessoaTabelaCardComponent } from './components/tabela/tabela-card/tabela-card.component';
import { PessoaInteresseComponent } from './components/interesse/interesse.component';
import { PessoaContatoComponent } from './components/contato/contato.component';
import { PessoaVisitaComponent } from './components/visita/visita.component';
import { PessoaProfileNomeComponent } from './components/profile/nome/nome.component';
import { PessoaProfileIdStatusComponent } from './components/profile/id-status/id-status.component';

@NgModule({
  declarations: [
    ImageCropperComponent,

    PessoaCardComponent,
    PessoaFlexgridCardComponent,
    PessoaFormBasicComponent,
    PessoaFormFoneComponent,
    PessoaProfileImagemComponent,
    PessoaProfileIdStatusComponent,
    PessoaFormFullComponent,
    PessoaProfileImagemDialogComponent,
    PessoaDialogComponent,
    PessoaTabelaCardComponent,
    PessoaInteresseComponent,
    PessoaContatoComponent,
    PessoaVisitaComponent,
    PessoaProfileNomeComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,

    MatDialogModule,
    MatButtonModule,
    MatPaginatorModule,
    MatTabsModule,
    MatTableModule,
    MatBottomSheetModule,

    Ng2ImgMaxModule,

    SharedModule,
    CursoModule,
    ContatoModule,
    InteresseModule,
    VisitaModule,
  ],
  exports: [
    PessoaFlexgridCardComponent,
    PessoaFormFullComponent,
    PessoaProfileImagemComponent,
    PessoaProfileIdStatusComponent,
    PessoaProfileNomeComponent,
    PessoaInteresseComponent,
    PessoaContatoComponent,
    PessoaVisitaComponent,
  ],
  entryComponents: [
    PessoaProfileImagemDialogComponent,
    PessoaDialogComponent,
  ]
})
export class PessoaModule { }
