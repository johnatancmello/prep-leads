import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { FilterInterface } from '../../../shared/models/filter-interface';
import { Pessoa } from '../../models/pessoa';
import { PessoaFilter } from '../../models/pessoa.filter';

@Component({
  selector: 'app-pessoa-flexgrid-card',
  templateUrl: './flexgrid-card.component.html',
  styleUrls: ['./flexgrid-card.component.css']
})
export class PessoaFlexgridCardComponent implements OnInit {

  @Input() pessoas: Pessoa;
  @Input() filter: FilterInterface = new PessoaFilter();

  @Output() openCard = new EventEmitter();
  @Output() changePage = new EventEmitter();

  constructor() { }

  ngOnInit() { }

  getNewPessoa(): Pessoa {
    const pessoa = new Pessoa();
    pessoa.nome = 'Adicionar';
    pessoa.ocupacao = '';
    return pessoa;
  }

}
