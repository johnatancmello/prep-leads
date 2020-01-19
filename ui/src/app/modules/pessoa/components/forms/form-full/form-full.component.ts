import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Pessoa } from '../../../models/pessoa';

@Component({
  selector: 'app-pessoa-form-full',
  templateUrl: './form-full.component.html',
  styleUrls: ['./form-full.component.css']
})
export class PessoaFormFullComponent implements OnInit {

  @Input() pessoa: Pessoa;
  @Input() readonly: boolean;

  constructor() { }

  ngOnInit() {
  }


  addResponsavel() {
    this.pessoa.responsavel = Pessoa.newPessoa();
  }

  addPessoa() {
    this.pessoa = Pessoa.newPessoa();
  }


}
