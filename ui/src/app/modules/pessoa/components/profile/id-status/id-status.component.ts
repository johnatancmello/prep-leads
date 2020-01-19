import { Component, OnInit, Input } from '@angular/core';
import { Pessoa } from '../../../models/pessoa';

@Component({
  selector: 'app-pessoa-id-status',
  templateUrl: './id-status.component.html',
  styleUrls: ['./id-status.component.css']
})
export class PessoaProfileIdStatusComponent implements OnInit {

  @Input() pessoa; // Pode ser tanto funcionario quanto pessoa simples
  @Input() readonly: boolean;

  constructor() { }

  ngOnInit() { }

  togglerStatus() {
    if (!this.readonly) {
      switch (this.pessoa.status) {
        case 'ATIVO': this.pessoa.status = 'INATIVO'; break;
        case 'INATIVO': this.pessoa.status = 'ATIVO'; break;
      }
    }
  }

  public getStatus(): string {
    return (this.pessoa && this.pessoa.status) ? this.pessoa.status : '';
  }

  public getId(): string {
    return (this.pessoa && this.pessoa.id) ? this.pessoa.id : '';
  }

}
