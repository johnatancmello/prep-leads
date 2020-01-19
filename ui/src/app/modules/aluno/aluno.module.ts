import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AlunoTableCardComponent} from './components/table/table-card/table-card.component';
import {MatTabsModule} from '@angular/material';
import {AlunoDialogComponent} from './components/dialog/dialog.component';
import {FormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {PessoaModule} from '../pessoa/pessoa.module';
import {InteresseModule} from '../interesse/interesse.module';
import {ContatoModule} from '../contato/contato.module';
import {VisitaModule} from '../visita/visita.module';



@NgModule({
  declarations: [
    AlunoTableCardComponent,
    AlunoDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,

    MatTabsModule,

    SharedModule,
    PessoaModule,
    InteresseModule,
    ContatoModule,
    VisitaModule,
  ],
  exports: [
    AlunoTableCardComponent,
  ],
  entryComponents: [
    AlunoDialogComponent,
  ]
})
export class AlunoModule { }
