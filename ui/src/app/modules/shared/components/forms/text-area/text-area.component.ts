import { Component, OnInit } from '@angular/core';
import { Field } from '../field';

@Component({
  selector: 'app-shared-text-area',
  templateUrl: './text-area.component.html',
  styleUrls: ['./../field.css', './text-area.component.css']
})
export class SahredTextAreaComponent extends Field implements OnInit {

  constructor() {
    super();
   }

  ngOnInit() {
  }

}
