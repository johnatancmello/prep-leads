import { Component, OnInit, Input } from '@angular/core';
import { Field } from '../field';

@Component({
  selector: 'app-shared-date',
  templateUrl: './date.component.html',
  styleUrls: ['./../field.css', './date.component.css']
})
export class SharedDateComponent extends Field  implements OnInit {

  @Input() pickerType: string;
  @Input() pickerMode: string;

  constructor() {
    super();
   }

  ngOnInit() {
  }

}
