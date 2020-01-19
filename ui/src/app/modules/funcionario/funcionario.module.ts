import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';

import { SharedModule } from '../shared/shared.module';
import { PessoaModule } from '../pessoa/pessoa.module';

import { SetorModule } from '../setor/setor.module';
import { FuncionarioDialogComponent } from './components/dialog/dialog.component';
import { FuncionarioFormUsuarioComponent } from './components/forms/form-usuario/form-usuario.component';
import { FuncionarioFormFullComponent } from './components/forms/form-full/form-full.component';
import { FuncionarioFlexgridCardComponent } from './components/flexgrid-card/flexgrid-card.component';

@NgModule({
  declarations: [
    FuncionarioFlexgridCardComponent,
    FuncionarioDialogComponent,
    FuncionarioFormUsuarioComponent,
    FuncionarioFormFullComponent
  ],
  imports: [
    CommonModule,

    FormsModule,

    MatTooltipModule,
    MatTabsModule,

    SharedModule,
    PessoaModule,
    SetorModule,
  ],
  exports: [
    FuncionarioFlexgridCardComponent,
  ],
  entryComponents: [
    FuncionarioDialogComponent
  ]
})
export class FuncionarioModule { }
