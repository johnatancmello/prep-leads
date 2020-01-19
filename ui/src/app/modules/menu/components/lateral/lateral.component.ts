import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-menu-lateral',
  templateUrl: './lateral.component.html',
  styleUrls: ['./lateral.component.css']
})
export class MenuLateralComponent implements OnInit {

  @Input() classLateralBar: string;
  @Input() classContraBar: string;
  @Output() closeLateral = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

}
