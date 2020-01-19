import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-shared-form-title',
  templateUrl: './form-title.component.html',
  styleUrls: ['./form-title.component.css']
})
export class SharedFormTitleComponent implements OnInit {

  @Output() clean = new EventEmitter();
  @Input() title: string;
  @Input() date: Date;
  @Input() clear: boolean;

  constructor() { }

  ngOnInit() {
  }



}
