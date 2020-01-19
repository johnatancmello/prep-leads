import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Pessoa } from '../../../models/pessoa';
import { Item } from '../../../../shared/models/item';

@Component({
  selector: 'app-pessoa-form-basic',
  templateUrl: './form-basic.component.html',
  styleUrls: ['./form-basic.component.css']
})
export class PessoaFormBasicComponent implements OnInit {

  @Output() clean = new EventEmitter();
  @Input() readonly: boolean;
  @Input() pessoa: Pessoa;
  @Input() titulo: string;
  @Input() clear: boolean;

  sexos = [];
  idades = [];

  constructor() {
    this.buildIdades();
    this.buildSexos();
  }

  ngOnInit() { }

  buildIdades() {
    for (let i = 1; i < 90; i++) {
      const item = new Item();
      item.label = i.toString();
      item.value = i.toString();
      this.idades.push(item);
    }
  }

  buildSexos() {
    let item = new Item();

    item.label = 'M';
    item.value = 'M';

    this.sexos.push(item);

    item = new Item();

    item.label = 'F';
    item.value = 'F';
    this.sexos.push(item);
  }

}
