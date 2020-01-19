import { NgModule, LOCALE_ID } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule, registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';

import { MenuModule } from '../menu/menu.module';
import { FuncionarioModule } from '../funcionario/funcionario.module';
import { CursoModule } from '../curso/curso.module';
import { VisitaModule } from '../visita/visita.module';
import { AlunoModule } from '../aluno/aluno.module';
import {SecurityModule} from '../security/security.module';

registerLocaleData(localePt);

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    HttpClientModule,

    SecurityModule,
    MenuModule,
    FuncionarioModule,
    VisitaModule,
    CursoModule,
    AlunoModule,
  ],
  exports: [
    SecurityModule,
    HttpClientModule,
    MenuModule,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' }
  ]
})
export class CoreModule { }
