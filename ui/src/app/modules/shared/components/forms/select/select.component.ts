import { Component, OnInit, Input } from '@angular/core';

import { Field } from '../field';
import { Item } from '../../../models/item';

@Component({
  selector: 'app-shared-select',
  templateUrl: './select.component.html',
  styleUrls: ['./../field.css', './select.component.css']
})
export class SheredSelectComponent extends Field implements OnInit {

  @Input() items: Item;

  constructor() {
    super();
  }

  ngOnInit() {
  }

}
