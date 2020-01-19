import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedSnackSuccessComponent} from './components/snack/success/success.component';
import {SharedInputComponent} from './components/forms/input/input.component';
import {SharedConfirmDialogComponent} from './components/confirm-dialog/confirm-dialog.component';
import {SharedAutoCompleteComponent} from './components/forms/auto-complete/auto-complete.component';
import {SharedSnackErrorComponent} from './components/snack/error/error.component';
import {SheredSelectComponent} from './components/forms/select/select.component';
import {SharedFormTitleComponent} from './components/forms/form-title/form-title.component';
import {ListSharedComponent} from './components/forms/list/list.component';
import {SharedSpeedDialFabComponent} from './components/buttons/speed-dial-fab/speed-dial-fab.component';
import {SahredTextAreaComponent} from './components/forms/text-area/text-area.component';
import {SharedDateComponent} from './components/forms/date/date.component';
import {SharedTelComponent} from './components/forms/tel/tel.component';
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatDialogModule, MatIconModule,
  MatInputModule,
  MatSelectModule, MatSnackBarModule,
  MatTooltipModule
} from '@angular/material';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from 'ng-pick-datetime-ex';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    SharedSnackErrorComponent,
    SharedSnackSuccessComponent,
    SharedConfirmDialogComponent,
    SharedInputComponent,
    SharedAutoCompleteComponent,
    SheredSelectComponent,
    SharedFormTitleComponent,
    ListSharedComponent,
    SharedSpeedDialFabComponent,
    SahredTextAreaComponent,
    SharedDateComponent,
    SharedTelComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatSnackBarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatTooltipModule,
    MatIconModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
  ],
  entryComponents: [
    SharedConfirmDialogComponent,
    SharedSnackErrorComponent,
    SharedSnackSuccessComponent,
  ],
  exports: [
    SharedInputComponent,
    SharedAutoCompleteComponent,
    SheredSelectComponent,
    ListSharedComponent,
    SharedFormTitleComponent,
    SharedSpeedDialFabComponent,
    SahredTextAreaComponent,
    SharedDateComponent,
    SharedTelComponent,
  ]
})
export class SharedModule { }
