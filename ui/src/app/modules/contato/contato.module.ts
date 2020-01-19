import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatTableModule, MatPaginatorModule, MatButtonModule } from '@angular/material';

import { SharedModule } from '../shared/shared.module';
import { ContatoTableComponent } from './components/table/table.component';
import { ContatoDialogComponent } from './components/dialog/dialog.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ContatoTableComponent,
    ContatoDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,

    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,


    SharedModule,
  ],
  exports: [
    ContatoTableComponent,
    ContatoDialogComponent,
  ],
  entryComponents: [
    ContatoDialogComponent,
  ]
})
export class ContatoModule { }
