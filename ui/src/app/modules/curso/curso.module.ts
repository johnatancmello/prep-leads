import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';


import { CursoPainelFormacaoComponent } from './components/painel-formacao/painel-formacao.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    CursoPainelFormacaoComponent
  ],
  imports: [
    CommonModule,

    MatButtonModule,
    MatDialogModule,

    SharedModule,
  ],
  entryComponents: [
    CursoPainelFormacaoComponent,
  ]
})
export class CursoModule { }
