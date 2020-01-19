import { Component, OnInit, Input } from '@angular/core';
import { Field } from '../field';
import { Item } from '../../../models/item';

@Component({
  selector: 'app-shared-auto-complete',
  templateUrl: './auto-complete.component.html',
  styleUrls: ['./../field.css', './auto-complete.component.css']
})
export class SharedAutoCompleteComponent extends Field implements OnInit {

  @Input() items: Item;

  constructor() { super(); }

  ngOnInit() {
  }

}
