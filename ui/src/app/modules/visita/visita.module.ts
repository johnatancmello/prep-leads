import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { VisitaTableComponent } from './components/table/table.component';
import { VisitaSingleComponent } from './components/form/single/single.component';
import { FormsModule } from '@angular/forms';
import { MatTableModule, MatPaginatorModule, MatButtonModule } from '@angular/material';
import { VisitaDialogComponent } from './components/dialog/dialog.component';

@NgModule({
  declarations: [
    VisitaTableComponent,
    VisitaSingleComponent,
    VisitaDialogComponent
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
    VisitaTableComponent,
  ],
  entryComponents: [
    VisitaDialogComponent,
  ]
})
export class VisitaModule { }
