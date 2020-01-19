import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

import { SharedModule } from '../shared/shared.module';

import { SetorFormSetorComponent } from './components/forms/form-setor/form-setor.component';
import { SetorFormFullComponent } from './components/forms/form-full/form-full.component';
import { SetorTableComponent } from './components/table/table.component';
import { SetorDialogComponent } from './components/dialog/dialog.component';
import { MatDialogModule, MatPaginatorIntl } from '@angular/material';

import { getBRPaginatorIntl } from '../shared/components/table/paginator';

@NgModule({
  declarations: [SetorFormSetorComponent, SetorFormFullComponent, SetorTableComponent, SetorDialogComponent],
  imports: [
    CommonModule,
    FormsModule,

    MatDialogModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,

    SharedModule,
  ],
  exports: [
    SetorFormSetorComponent
  ],
  entryComponents: [
    SetorDialogComponent,
  ],
  providers: [
    { provide: MatPaginatorIntl, useValue: getBRPaginatorIntl() }
  ]
})
export class SetorModule { }
