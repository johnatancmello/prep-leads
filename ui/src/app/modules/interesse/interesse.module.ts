import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MatListModule, MatTableModule, MatPaginatorModule, MatButtonModule, MatSnackBarModule } from '@angular/material';

import { SharedModule } from '../shared/shared.module';

import { InteresseDialogAddComponent } from './components/dialog/add.component';
import { InteresseTableComponent } from './components/table/table.component';
import { InteresseFormSingleComponent } from './components/form/single/single.component';
import { ContatoModule } from '../contato/contato.module';

@NgModule({
  declarations: [
    InteresseFormSingleComponent,
    InteresseDialogAddComponent,
    InteresseTableComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,

    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,

    MatSnackBarModule,

    SharedModule,
    ContatoModule,
  ],
  exports: [
    InteresseTableComponent
  ],
  entryComponents: [
    InteresseDialogAddComponent,
  ]
})
export class InteresseModule { }
