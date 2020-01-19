import { Component, OnInit, Input, ViewChild, ElementRef, ViewChildren } from '@angular/core';
import { Field } from '../field';
import { PatternValidator, Validators } from '@angular/forms';


@Component({
  selector: 'app-shared-input',
  templateUrl: './input.component.html',
  styleUrls: ['./../field.css', './input.component.css']
})
export class SharedInputComponent extends Field implements OnInit {

  @Input() type = 'text';

  constructor() { super(); }

  ngOnInit() {
  }


}
