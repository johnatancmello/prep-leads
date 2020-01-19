import { Component, OnInit, Input } from '@angular/core';
import { Field } from '../field';

@Component({
  selector: 'app-shared-list',
  templateUrl: './list.component.html',
  styleUrls: ['./../field.css', './list.component.css']
})
export class ListSharedComponent extends Field implements OnInit {

  @Input() items;

  constructor() { super(); }

  ngOnInit() {
  }

  trackByFn(index: any, item: any) {
    return index;
 }

}
