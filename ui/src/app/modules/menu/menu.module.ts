import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatProgressBarModule } from '@angular/material/progress-bar';

import { MenuNavbarComponent } from './components/navbar/navbar.component';
import { MenuLateralComponent } from './components/lateral/lateral.component';
import { MenuMainComponent } from './components/main/main.component';
import { RouterModule } from '@angular/router';
import { MenuLateralItensComponent } from './components/lateral/components/itens/itens.component';


@NgModule({
  declarations: [
    MenuNavbarComponent,
    MenuLateralComponent,
    MenuMainComponent,
    MenuLateralItensComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,

    MatProgressBarModule,
  ],
  exports: [
    MenuMainComponent,
  ]
})
export class MenuModule { }
