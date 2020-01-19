import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SetorFormFullComponent } from './modules/setor/components/forms/form-full/form-full.component';
import { FuncionarioFlexgridCardComponent } from './modules/funcionario/components/flexgrid-card/flexgrid-card.component';
import { PessoaTabelaCardComponent } from './modules/pessoa/components/tabela/tabela-card/tabela-card.component';
import { InteresseFormSingleComponent } from './modules/interesse/components/form/single/single.component';
import { VisitaSingleComponent } from './modules/visita/components/form/single/single.component';
import { AlunoTableCardComponent } from './modules/aluno/components/table/table-card/table-card.component';
import {SecurityLoginComponent} from './modules/security/components/login/login.component';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: SecurityLoginComponent },
  { path: 'funcionarios', component: FuncionarioFlexgridCardComponent },
  { path: 'pessoas', component: PessoaTabelaCardComponent },
  { path: 'setores', component: SetorFormFullComponent },
  { path: 'interesses', component: InteresseFormSingleComponent },
  { path: 'agendamentos', component: VisitaSingleComponent },
  { path: 'alunos', component: AlunoTableCardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
