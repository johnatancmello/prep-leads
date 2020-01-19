import { Component, OnInit, Input } from '@angular/core';
import { Field } from '../field';
import { Mask, telMask } from '../mask/mask';

@Component({
  selector: 'app-shared-tel',
  templateUrl: './tel.component.html',
  styleUrls: ['./../field.css', './tel.component.css']
})
export class SharedTelComponent extends Field implements OnInit {

  @Input() type = 'text';

  constructor() { super(); }

  ngOnInit() {
  }

  public applyMask(input) {
    Mask.apply(input, telMask);
    this.ngModelChange.emit(input.value);
  }


}
