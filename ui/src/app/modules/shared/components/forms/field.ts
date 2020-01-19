import { Input, Output, EventEmitter } from '@angular/core';

export class Field {
  @Input() readonly;
  @Input() ngModel = '';
  @Input() placeholder: string;
  @Input() selectMode: string;
  @Output() ngModelChange = new EventEmitter();
  @Output() keydownEnter = new EventEmitter();

}
