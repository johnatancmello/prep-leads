import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

import { Contato } from '../../models/contatos';
import { FilterInterface } from '../../../shared/models/filter-interface';
import { ContatoFilter } from '../../models/contato.filter';
import { Pessoa } from '../../../pessoa/models/pessoa';

@Component({
  selector: 'app-contato-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class ContatoTableComponent implements OnInit {

  @Output() changePage = new EventEmitter();
  @Output() openDialogDetails = new EventEmitter();

  @Input() contatos: Array<Contato>;
  @Input() filter: FilterInterface = new ContatoFilter();
  @Input() idContatoSelecionado: number;

  public displayedColumns: string[] = ['id', 'status', 'dataDoContato', 'funcionario'];

  constructor() { }

  ngOnInit() {
  }

  public getFirstAndLastName(fullName: string): string {
    return Pessoa.getFirstAndLastName(fullName);
  }


  public getClass(idRow: number): string {
    if (this.idContatoSelecionado === idRow) {
      return 'mat-primary-dark primary-light--active';
    }
  }

}
