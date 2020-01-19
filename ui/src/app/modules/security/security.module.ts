import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {JwtModule} from '@auth0/angular-jwt';
import {environment} from '../../../environments/environment';
import {MatButtonModule, MatInputModule} from '@angular/material';
import {FormsModule} from '@angular/forms';

import {SharedModule} from '../shared/shared.module';
import { SecurityLoginComponent } from './components/login/login.component';
import { SecurityLoginFormComponent } from './components/login/form/form.component';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [SecurityLoginComponent, SecurityLoginFormComponent],
  imports: [
    CommonModule,

    FormsModule,

    MatInputModule,
    MatButtonModule,

    SharedModule,

    JwtModule.forRoot({
      config: {
        tokenGetter,
        whitelistedDomains: environment.whitelistedDomains,
        blacklistedRoutes: environment.blacklistedRoutes,
        skipWhenExpired: true
      }
    }),
  ],
  exports: [
    SecurityLoginComponent,
  ]
})
export class SecurityModule { }
